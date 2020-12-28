package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.componenets.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.interfaces.HeightConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.WidthConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.XConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.YConstraint;

public class RelativeConstraint extends UiConstraints
		implements XConstraint, YConstraint, WidthConstraint, HeightConstraint {

	private final float ratio;

	/**
	 * Creates a Constraint object that sets the x, y, width, or height to a ratio
	 * of the screen
	 * 
	 * @param ratio percentage of the screen to occupy; should be between 0f and 1f
	 */
	public RelativeConstraint(float ratio) {
		this.ratio = ratio;
	}

	@Override
	public int getX(UiComponent uiComponent) {
		return (int) (UiService.width * ratio);
	}

	@Override
	public int getY(UiComponent uiComponent) {
		return (int) (UiService.height * ratio);
	}

	@Override
	public int getWidth(UiComponent uiComponent) {
		return (int) (UiService.width * ratio);
	}

	@Override
	public int getHeight(UiComponent uiComponent) {
		return (int) (UiService.height * ratio);
	}
}