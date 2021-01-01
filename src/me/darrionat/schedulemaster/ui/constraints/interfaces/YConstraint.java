package me.darrionat.schedulemaster.ui.constraints.interfaces;

import me.darrionat.schedulemaster.ui.components.UiComponent;

public interface YConstraint {
	/**
	 * Calculates the Y coordinate of a UiComponent
	 * 
	 * @param component the UiComponent to calculate the Y value of
	 * @return returns the Y coordinate of the UiComponent utilizing the
	 *         constraint's method
	 */
	int getY(UiComponent component);
}