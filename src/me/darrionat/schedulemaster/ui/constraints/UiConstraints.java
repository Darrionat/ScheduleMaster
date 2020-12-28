package me.darrionat.schedulemaster.ui.constraints;

import me.darrionat.schedulemaster.ui.constraints.interfaces.HeightConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.WidthConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.XConstraint;
import me.darrionat.schedulemaster.ui.constraints.interfaces.YConstraint;

public class UiConstraints {

	private XConstraint xConstraint;
	private YConstraint yConstraint;
	private WidthConstraint widthConstraint;
	private HeightConstraint heightConstraint;

	public void setX(XConstraint constraint) {
		this.xConstraint = constraint;
	}

	public void setY(YConstraint constraint) {
		this.yConstraint = constraint;
	}

	public void setWidth(WidthConstraint constraint) {
		this.widthConstraint = constraint;
	}

	public void setHeight(HeightConstraint constraint) {
		this.heightConstraint = constraint;
	}

	public XConstraint getXConstraint() {
		return xConstraint;
	}

	public YConstraint getYConstraint() {
		return yConstraint;
	}

	public WidthConstraint getWidthConstraint() {
		return widthConstraint;
	}

	public HeightConstraint getHeightConstraint() {
		return heightConstraint;
	}
}