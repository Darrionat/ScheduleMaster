package me.darrionat.schedulemaster.ui.animations;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import me.darrionat.schedulemaster.ui.MenuButton;
import me.darrionat.schedulemaster.utils.Utils;

public class MenuButtonClickAnimation extends Animation {

	private MenuButton button;
	private Timer timer;

	/**
	 * Saves the progress in the click animation
	 */
	private int currentClickFrame = 0;
	/**
	 * Determines how many iterations (or frames) are used in the click animation.
	 * This can be divided by the frames per second to determine how long, in
	 * seconds, the animation will take.
	 */
	private int maxClickFrame = 40;

	public MenuButtonClickAnimation(MenuButton button) {
		this.button = button;
	}

	@Override
	public void run() {
		this.timer = new Timer();
		timer.schedule(new TimerTask() {
			int startX = (int) button.x;
			int startY = (int) button.y;
			// Upper-Left
			int diffX1 = 0 - startX;
			int diffY1 = 0 - startY;
			// Lower-Right
			int diffX2 = button.getPanel().getWidth() - startX;
			int diffY2 = button.getPanel().getHeight() - startY;
			// Upper-left corner
			double frameChangeX1 = (double) diffX1 / (double) maxClickFrame;
			double frameChangeY1 = (double) diffY1 / (double) maxClickFrame;
			// Lower-right corner
			double frameChangeX2 = (double) diffX2 / (double) maxClickFrame;
			double frameChangeY2 = (double) diffY2 / (double) maxClickFrame;

			@Override
			public void run() {
				button.x += frameChangeX1;
				button.y += frameChangeY1;
				button.w += frameChangeX2 + Math.abs(frameChangeX1);
				button.h += frameChangeY2 + Math.abs(frameChangeY1);

				button.setColor(calculateTransitionColor());
				button.getPanel().repaint();
				if (currentClickFrame == maxClickFrame) {
					button.setAnimation(null);
					this.cancel();
				}
				currentClickFrame++;
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
	private Color calculateTransitionColor() {
		return Utils.getColorByPercent(button.getClickedColor(), Color.WHITE, currentClickFrame, maxClickFrame);
	}
}