package me.darrionat.schedulemaster.ui;

import javax.swing.JFrame;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.components.UiBlock;
import me.darrionat.schedulemaster.ui.components.UiColors;
import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.components.UiText;
import me.darrionat.schedulemaster.ui.constraints.AspectConstraint;
import me.darrionat.schedulemaster.ui.constraints.PixelConstraint;
import me.darrionat.schedulemaster.ui.constraints.RelativeConstraint;
import me.darrionat.schedulemaster.ui.constraints.UiConstraints;

public class MainMenuUi extends UiContainer {

	private static final long serialVersionUID = 1L;

	private final String[] buttonTexts = { "View Schedules", "Edit Schedule", "Employees", "Shifts", "Information" };

	public MainMenuUi(JFrame frame) {
		super(frame);
	}

	// TODO: Finish MainMenuUi
	// Currently all testing lines
	@Override
	public void setComponents() {
		UiContainer display = UiService.getContainer();

		for (int i = 0; i < buttonTexts.length; i++) {
			UiComponent rect = new UiBlock(UiColors.DARK_GREY);
			UiConstraints buttonConstraints = new UiConstraints();
			buttonConstraints.setX(new RelativeConstraint(0.05f));
			buttonConstraints.setY(new RelativeConstraint((float) (0.4 + i * 0.08)));
			buttonConstraints.setWidth(new RelativeConstraint(0.18f));
			buttonConstraints.setHeight(new AspectConstraint(4.667));
			// String text = buttonTexts[i];
			display.add(rect, buttonConstraints);
		}
		UiComponent textComponent = new UiText("test");
		UiConstraints textConstraints = new UiConstraints();
		textConstraints.setX(new PixelConstraint(30));
		textConstraints.setY(new PixelConstraint(50));
		textConstraints.setWidth(new PixelConstraint(30));
		textConstraints.setHeight(new PixelConstraint(50));
		display.add(textComponent, textConstraints);
	}
}