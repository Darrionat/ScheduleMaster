package me.darrionat.schedulemaster.ui.animations;

import me.darrionat.darrionGL.animations.UiAnimation;
import me.darrionat.darrionGL.components.UiComponent;
import me.darrionat.darrionGL.constraints.PixelConstraint;
import me.darrionat.darrionGL.constraints.UiConstraints;
import me.darrionat.darrionGL.utils.Utils;
import me.darrionat.schedulemaster.ui.MainMenuUi;
import me.darrionat.schedulemaster.ui.components.UiButton;

public class UiButtonHoverAnimation extends UiAnimation {

	private int dX;
	private int currentStep = 0;
	private int maxStep = 50;
	private boolean extending = false;

	public UiButtonHoverAnimation(UiButton button) {
		super(button);
		maxStep = button.getWidth() / 3;
	}

	public boolean isExtending() {
		return extending;
	}

	public void setExtending(boolean extending) {
		this.extending = extending;
	}

	@Override
	protected void animate(UiComponent component) {
		if (component.isHovered()) {
			dX = 4;
			extending = true;
		} else {
			dX = -4;
			extending = false;
		}

		if (currentStep >= maxStep && extending) {
			this.cancel();
			return;
		}
		if (currentStep <= 0 && !extending) {
			this.cancel();
			return;
		}

		int width = component.getWidth();
		UiConstraints constraints = component.getConstraints();
		constraints.setWidth(new PixelConstraint(width + dX));
		currentStep += dX;

		component.setUiColor(Utils.getColorByPercent(MainMenuUi.MENU_BUTTON_COLOR,
				MainMenuUi.MENU_BUTTON_COLOR_EXTENDED, currentStep, maxStep));
	}
}