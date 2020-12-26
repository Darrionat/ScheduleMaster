package me.darrionat.schedulemaster.ui.animations;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import me.darrionat.schedulemaster.ui.MenuButton;
import me.darrionat.schedulemaster.utils.Utils;

/**
 * 
 * Changes the color of a MenuButton and expands it. The beginning color and
 * final color are defaulted to transparent light gray and light blue,
 * respectively
 * 
 * @author Darrion Thornburgh
 *
 */
public class MenuButtonHoverAnimation extends Animation {

	private MenuButton button;
	/**
	 * The rate of the button extending, 1 for moving outwards and -1 for moving
	 * inwards
	 */
	private int dX = 2;

	public MenuButtonHoverAnimation(MenuButton button, boolean expand) {
		this.button = button;
		if (!expand)
			dX = -2;
	}

	@Override
	public void run() {

		if (button.wasClicked()) {
			button.setAnimation(null);
			return;
		}

		if (button.getAnimation() != null) {
			button.getAnimation().cancel();
		}

		timer = new Timer();
		button.setAnimation(this);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				boolean stopped;
				if (dX == 2) {
					stopped = button.currentStep == button.maxStep;
				} else {
					stopped = button.currentStep == 0;
				}
				if (stopped) {
					button.setAnimation(null);
					cancel();
					return;
				}
				button.currentStep += dX;
				button.w += dX;
				button.setColor(calculateHoverColor());
				button.getPanel().repaint();
			}
		}, 0, 1000 / fps);
	}

	@Override
	public void cancel() {
		timer.cancel();
	}

	/**
	 * Gets the current color of the button based on the hover animation
	 * 
	 * @return a color based upon the percentage of the hover animation
	 */
	private Color calculateHoverColor() {
		return Utils.getColorByPercent(button.getBoxColor(), button.getSelectedColor(), button.currentStep,
				button.maxStep);
	}

}