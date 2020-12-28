package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.ui.componenets.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.interfaces.HeightConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.WidthConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.XConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.YConstraint;

public class PixelConstraint extends UiConstraints
		implements XConstraint, YConstraint, WidthConstraint, HeightConstraint {

	private final int px;

	/**
	 * Creates a Constraint object that constrains x, y, width, and height to the
	 * defined value
	 * 
	 * @param pixels the amount of pixels to constrain by
	 */
	public PixelConstraint(int pixels) {
		px = pixels;
	}

	@Override
	public int getX(UiComponent uiComponent) {
		return px;
	}

	@Override
	public int getY(UiComponent uiComponent) {
		return px;
	}

	@Override
	public int getWidth(UiComponent uiComponent) {
		return px;
	}

	@Override
	public int getHeight(UiComponent uiComponent) {
		return px;
	}

}