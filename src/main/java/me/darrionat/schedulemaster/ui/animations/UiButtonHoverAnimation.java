package me.darrionat.schedulemaster.ui.animations;

import me.darrionat.darrionGL.animations.UiAnimation;
import me.darrionat.darrionGL.components.UiComponent;
import me.darrionat.darrionGL.constraints.PixelConstraint;
import me.darrionat.darrionGL.constraints.UiConstraints;
import me.darrionat.darrionGL.utils.Utils;
import me.darrionat.schedulemaster.ui.MainMenuUi;
import me.darrionat.schedulemaster.ui.components.UiButton;

public class UiButtonHoverAnimation extends UiAnimation {

	/**
	 * The amount to change the width by
	 */
	private int dX = 0;
	/**
	 * Current amount of pixels that have been extended
	 */
	private int currentStep = 0;
	/**
	 * The max amount of extension
	 */
	private int maxStep = 50;
	/**
	 * Used to save current state of animation
	 */
	private boolean expanding = false;

	/**
	 * Defines if it can extend
	 */
	private boolean extend = true;
	/**
	 * Defines if it can change color
	 */
	private boolean changeColor = true;

	/**
	 * Create a new UiButtonHoverAnimation object. The UiButtonHoverAnimation is one
	 * that is able to expand the width of a UiButton and gradually change its color
	 * as the animation progresses.
	 * 
	 * @param button the button which should be tracked for the animation
	 */
	public UiButtonHoverAnimation(UiButton button) {
		super(button);
		maxStep = button.getWidth() / 3;
	}

	/**
	 * Gets the current state of the animation's extending transition
	 * 
	 * @return returns {@code true} if the animation is currently expanding the
	 *         UiButton
	 */
	public boolean isExpanding() {
		return expanding;
	}

	/**
	 * Define what direction the animation will expand or compress
	 * 
	 * @param canExpand the new state of if the UiButton animation
	 */
	public void setExpanding(boolean canExpand) {
		this.expanding = canExpand;
	}

	/**
	 * Gets if the UiButton is able to expand
	 * 
	 * @return {@code true} if the button is able to expand
	 */
	public boolean canExtend() {
		return extend;
	}

	/**
	 * Sets if the UiButton is able to expand
	 * 
	 * @param canExtend the state of if the button can expand
	 */
	public void setCanExtend(boolean canExtend) {
		this.extend = canExtend;
	}

	/**
	 * Gets the status of the UiButton being able to change color
	 * 
	 * @return returns {@code true} if the UiButton will change color as it animates
	 */
	public boolean canChangeColor() {
		return changeColor;
	}

	/**
	 * Sets the status of the UiButton being able to change color
	 * 
	 * @param changeColor {@code true} if the UiButton will change color as it
	 *                    animates; {@code false} otherwise
	 */
	public void setChangeColor(boolean changeColor) {
		this.changeColor = changeColor;
	}

	@Override
	protected void animate(UiComponent component) {
		if (extend)
			if (component.isHovered()) {
				dX = 4;
				expanding = true;
			} else {
				dX = -4;
				expanding = false;
			}

		if (currentStep >= maxStep && expanding) {
			this.cancel();
			return;
		}
		if (currentStep <= 0 && !expanding) {
			this.cancel();
			return;
		}

		int width = component.getWidth();
		UiConstraints constraints = component.getConstraints();
		constraints.setWidth(new PixelConstraint(width + dX));
		currentStep += dX;

		if (changeColor)
			component.setUiColor(Utils.getColorByPercent(MainMenuUi.BUTTON_COLOR,
					MainMenuUi.BUTTON_COLOR_EXTENDED, currentStep, maxStep));
	}
}