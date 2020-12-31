package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.interfaces.HeightConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.WidthConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.XConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.YConstraint;

public class PixelConstraint extends UiConstraints
		implements XConstraint, YConstraint, WidthConstraint, HeightConstraint {

	private final int px;

	/**
	 * Initializes a UiConstraints object that sets x, y, width, or height to the
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