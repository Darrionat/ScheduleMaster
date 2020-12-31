package me.darrionat.schedulemaster.ui.constraints.interfaces;

import me.darrionat.schedulemaster.ui.components.UiComponent;

public interface HeightConstraint {
	/**
	 * Calculates the height value of a UiComponent
	 * 
	 * @param uiComponent the UiComponent to calculate the height of
	 * @return returns the height of the UiComponent utilizing the constraint's
	 *         method
	 */
	int getHeight(UiComponent component);
}