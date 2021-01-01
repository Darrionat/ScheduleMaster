package me.darrionat.schedulemaster.ui;

import javax.swing.JFrame;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.components.UiColors;
import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.components.UiEllipse;
import me.darrionat.schedulemaster.ui.components.UiImage;
import me.darrionat.schedulemaster.ui.constraints.AspectConstraint;
import me.darrionat.schedulemaster.ui.constraints.CenterConstraint;
import me.darrionat.schedulemaster.ui.constraints.PixelConstraint;
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

		UiComponent uiElement = new UiEllipse(UiColors.DARK_GREY);

		UiConstraints constraints = new UiConstraints();

		constraints.setX(new CenterConstraint());
		constraints.setY(new RelativeConstraint(0.1f));
		constraints.setWidth(new RelativeConstraint(0.5f));
		constraints.setHeight(new RelativeConstraint(0.3f));

		display.add(uiElement, constraints);

		UiComponent image = new UiImage("res/icon.png");
		UiConstraints constraints2 = new UiConstraints();
		constraints2.setX(new CenterConstraint());
		constraints2.setY(new RelativeConstraint(0.1f));
		constraints2.setWidth(new PixelConstraint(250));
		constraints2.setHeight(new AspectConstraint(1));
		display.add(image, constraints2);
	}
}