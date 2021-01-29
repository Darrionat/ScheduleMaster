package me.darrionat.schedulemaster.ui;

import javax.swing.JFrame;

import me.darrionat.darrionGL.UI;
import me.darrionat.darrionGL.UiContainer;
import me.darrionat.darrionGL.components.UiColors;
import me.darrionat.darrionGL.components.menu.MenuButton;
import me.darrionat.darrionGL.constraints.AspectConstraint;
import me.darrionat.darrionGL.constraints.RelativeConstraint;
import me.darrionat.darrionGL.constraints.UiConstraints;

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
		UiContainer display = UI.getContainer();
//		MenuButton menuButton = new MenuButton("Displayed Text");
//		menuButton.setTextColor(UiColors.WHITE);
//		menuButton.setAlignment(HorizontalAlignment.CENTER);
//		menuButton.setAlignment(VerticalAlignment.CENTER);
//
//		UiConstraints buttonConstraints = new UiConstraints();
//		buttonConstraints.setX(new RelativeConstraint(0.05f));
//		buttonConstraints.setY(new RelativeConstraint((float) (0.4 + 0.08)));
//		buttonConstraints.setWidth(new RelativeConstraint(0.25f));
//		buttonConstraints.setHeight(new AspectConstraint(1));
//
//		display.add(menuButton, buttonConstraints);

		for (int i = 0; i < buttonTexts.length; i++) {
			MenuButton menuButton = new MenuButton(buttonTexts[i]);
			menuButton.setUiColor(UiColors.DARK_GREY);
			menuButton.setTextColor(UiColors.WHITE);

			UiConstraints buttonConstraints = new UiConstraints();
			buttonConstraints.setX(new RelativeConstraint(0.05f));
			buttonConstraints.setY(new RelativeConstraint((float) (0.4 + i * 0.08)));
			buttonConstraints.setWidth(new RelativeConstraint(0.18f));
			buttonConstraints.setHeight(new AspectConstraint(4.667));
			// String text = buttonTexts[i];
			display.add(menuButton, buttonConstraints);
		}
	}
}