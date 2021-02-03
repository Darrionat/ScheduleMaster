package me.darrionat.schedulemaster.ui;

import javax.swing.JFrame;

import me.darrionat.darrionGL.UiContainer;
import me.darrionat.darrionGL.components.UiBlock;
import me.darrionat.darrionGL.constraints.ConstraintFactory;
import me.darrionat.darrionGL.constraints.UiConstraints;

public class EmployeesUi extends UiContainer {

	private static final long serialVersionUID = 1L;

	public EmployeesUi(JFrame frame) {
		super(frame);
	}

	@Override
	public void setComponents() {
		UiBlock block = new UiBlock();
		UiConstraints constraints = ConstraintFactory.getRelative(0.5f, 0.5f, 0.1f, 0.1f);
		add(block, constraints);
	}
}