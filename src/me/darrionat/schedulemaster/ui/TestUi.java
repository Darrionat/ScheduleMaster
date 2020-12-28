package me.darrionat.schedulemaster.ui;

import javax.swing.JFrame;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.componenets.UiBlock;
import me.darrionat.schedulemaster.ui.componenets.UiColors;
import me.darrionat.schedulemaster.ui.componenets.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.CenterConstraint;
import me.darrionat.schedulemaster.ui.constraints.RelativeConstraint;
import me.darrionat.schedulemaster.ui.constraints.UiConstraints;

public class TestUi extends UiContainer {

	private static final long serialVersionUID = 1L;

	public TestUi(JFrame frame) {
		super(frame);
	}

	@Override
	public void setComponents() {
		UiContainer display = UiService.getContainer();

		UiComponent uiElement = new UiBlock(UiColors.DARK_GREY);

		UiConstraints constraints = new UiConstraints();

		constraints.setX(new CenterConstraint());
		constraints.setY(new RelativeConstraint(0.85f));
		constraints.setWidth(new RelativeConstraint(0.1f));
		constraints.setHeight(new RelativeConstraint(0.1f));
		
		display.add(uiElement, constraints);

	}
}