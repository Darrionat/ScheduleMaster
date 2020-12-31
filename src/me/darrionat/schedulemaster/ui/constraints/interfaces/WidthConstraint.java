package me.darrionat.schedulemaster.ui.constraints.interfaces;

import me.darrionat.schedulemaster.ui.components.UiComponent;

public interface WidthConstraint {
	/**
	 * Calculates the width value of a UiComponent
	 * 
	 * @param uiComponent the UiComponent to calculate the width of
	 * @return returns the width of the UiComponent utilizing the constraint's
	 *         method
	 */
	int getWidth(UiComponent component);
}