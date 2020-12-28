package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.componenets.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.interfaces.XConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.YConstraint;

public class CenterConstraint extends UiConstraints implements XConstraint, YConstraint {

	@Override
	public int getX(UiComponent component) {
		int midX = UiService.width / 2;
		return midX - (component.getWidth()/2);
	}

	@Override
	public int getY(UiComponent component) {
		int midY = UiService.height / 2;
		return midY - (component.getHeight() / 2);
	}
}