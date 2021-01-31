package me.darrionat.schedulemaster.ui;

import java.awt.Font;

import javax.swing.JFrame;

import me.darrionat.darrionGL.UI;
import me.darrionat.darrionGL.UiContainer;
import me.darrionat.darrionGL.components.UiColor;
import me.darrionat.darrionGL.components.UiColors;
import me.darrionat.darrionGL.constraints.PixelConstraint;
import me.darrionat.darrionGL.constraints.RelativeConstraint;
import me.darrionat.darrionGL.constraints.UiConstraints;
import me.darrionat.schedulemaster.ui.animations.UiButtonHoverAnimation;
import me.darrionat.schedulemaster.ui.components.UiButton;

public class MainMenuUi extends UiContainer {

	public static final UiColor MENU_BUTTON_COLOR = UiColors.DARK_GREY;
	public static final UiColor MENU_BUTTON_COLOR_EXTENDED = UiColors.LIGHT_BLUE_TRANSPARENT;

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
			UiButton menuButton = new UiButton(buttonTexts[i]);

			UiConstraints buttonConstraints = new UiConstraints();
			buttonConstraints.setX(new RelativeConstraint(0.05f));
			buttonConstraints.setY(new RelativeConstraint((float) (0.4 + i * 0.08)));
			buttonConstraints.setWidth(new PixelConstraint(270));
			buttonConstraints.setHeight(new PixelConstraint(60));

			menuButton.setUiColor(UiColors.DARK_GREY);
			menuButton.setTextColor(UiColors.WHITE);
			menuButton.setTextFont(new Font("Anson", Font.PLAIN, 32));
			// String text = buttonTexts[i];
			display.add(menuButton, buttonConstraints);
			menuButton.setAnimation(new UiButtonHoverAnimation(menuButton));
		}
	}
}