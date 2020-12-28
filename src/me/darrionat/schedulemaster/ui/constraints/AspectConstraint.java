package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.ui.componenets.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.interfaces.HeightConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.WidthConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.XConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.YConstraint;

public class AspectConstraint extends UiConstraints
		implements XConstraint, YConstraint, WidthConstraint, HeightConstraint {

	private final double ratio;

	public AspectConstraint(double aspectRatio) {
		this.ratio = aspectRatio;
	}

	@Override
	public int getX(UiComponent component) {
		return (int) (component.getY() * ratio);
	}

	@Override
	public int getY(UiComponent component) {
		return (int) (component.getX() / ratio);
	}

	@Override
	public int getWidth(UiComponent component) {
		return (int) (component.getHeight() * ratio);
	}

	@Override
	public int getHeight(UiComponent component) {
		return (int) (component.getWidth() / ratio);
	}
}