package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.interfaces.HeightConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.WidthConstraint;

public class AspectConstraint extends UiConstraints implements WidthConstraint, HeightConstraint {

	private final double ratio;

	/**
	 * Initializes a UiConstraints object that defines the aspect ratio of a
	 * UiComponent
	 * 
	 * @param aspectRatio the aspect ratio to define using the ratio width:height
	 */
	public AspectConstraint(double aspectRatio) {
		this.ratio = aspectRatio;
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