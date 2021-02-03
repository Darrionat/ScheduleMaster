package me.darrionat.schedulemaster.ui;

import java.awt.Font;

import me.darrionat.darrionGL.UI;
import me.darrionat.darrionGL.UiContainer;
import me.darrionat.darrionGL.components.UiColor;
import me.darrionat.darrionGL.components.UiColors;
import me.darrionat.darrionGL.components.UiImage;
import me.darrionat.darrionGL.components.UiText;
import me.darrionat.darrionGL.constraints.AspectConstraint;
import me.darrionat.darrionGL.constraints.ConstraintFactory;
import me.darrionat.darrionGL.constraints.PixelConstraint;
import me.darrionat.darrionGL.constraints.RelativeConstraint;
import me.darrionat.darrionGL.constraints.UiConstraints;
import me.darrionat.darrionGL.events.ComponentClickedEvent;
import me.darrionat.darrionGL.events.EventManager;
import me.darrionat.darrionGL.events.annotations.EventHandler;
import me.darrionat.darrionGL.events.interfaces.Listener;
import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.ui.animations.UiButtonHoverAnimation;
import me.darrionat.schedulemaster.ui.components.UiButton;

public class MainMenuUi extends UiContainer implements Listener {
	private static final long serialVersionUID = 1L;

	private static final float RECT_RADIUS = 8;
	private static final float BUTTON_X = 0.05f;
	private static final float Y_START = 0.4f;
	private static final float Y_GAP = 0.08f;
	private static final float BUTTON_WIDTH = 0.2f;
	private static final float BUTTON_HEIGHT = 0.07f;
	public static final Font BUTTON_FONT = new Font("Anson", Font.PLAIN, 32);

	public static final UiColor BUTTON_COLOR = UiColors.DARK_GREY;
	public static final UiColor BUTTON_TEXT_COLOR = UiColors.DARK_GREY;
	public static final UiColor BUTTON_COLOR_EXTENDED = UiColors.LIGHT_BLUE_TRANSPARENT;

	private final String[] BUTTON_TEXTS = { "View Schedules", "Edit Schedule", "Employees", "Shifts", "Information" };
	private UiButton[] buttons = new UiButton[BUTTON_TEXTS.length];

	public UiButton viewSchedules;

	private UI ui;

	public MainMenuUi(UI ui) {
		super(ui);
		this.ui = ui;
		EventManager.registerListener(this);
	}

	// TODO: Finish MainMenuUi
	// Currently all testing lines
	@Override
	public void setComponents() {

		/*
		 * The ScheduleMaster logo
		 */
		UiImage icon = new UiImage(ScheduleMaster.RESOURCES_PATH + "icon.png");
		UiConstraints imageConstraints = new UiConstraints(new RelativeConstraint(0.05f), new RelativeConstraint(0.05f),
				new PixelConstraint(270), new AspectConstraint(1));
		add(icon, imageConstraints);

		/*
		 * The main menu buttons
		 */
		for (int i = 0; i < BUTTON_TEXTS.length; i++) {
			UiButton menuButton = setupUiButton(BUTTON_TEXTS[i], Y_START + i * Y_GAP);
			buttons[i] = menuButton;
			add(menuButton);
		}

		/*
		 * The version text
		 */
		UiText uiText = new UiText("Version:" + ScheduleMaster.VERSION);
		uiText.setFont(new Font("Anson", Font.PLAIN, 16));
		UiConstraints textConstraints = ConstraintFactory.getRelative(0.93f, 0.95f, 0.05f, 0.05f);
		add(uiText, textConstraints);
	}

	private UiButton setupUiButton(String text, float yPos) {
		UiButton menuButton = new UiButton(text);
		UiConstraints buttonConstraints = ConstraintFactory.getRelative(BUTTON_X, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);

		menuButton.setConstraints(buttonConstraints);
		menuButton.setUiColor(BUTTON_COLOR);
		menuButton.setTextColor(UiColors.WHITE);
		menuButton.setTextFont(BUTTON_FONT);
		menuButton.setAnimation(new UiButtonHoverAnimation(menuButton));
		menuButton.getUiBlock().setRoundedCorners(RECT_RADIUS);
		return menuButton;
	}

	@EventHandler
	public void onClick(ComponentClickedEvent e) {
		if (UI.getContainer() != this)
			return;

		for (int i = 0; i < buttons.length; i++) {
			if (e.getComponent() == buttons[i]) {
				setupMenu(i);
				return;
			}
		}
	}

	public void setupMenu(int buttonNo) {
		System.out.println("button no " + buttonNo);

		switch (buttonNo) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			ui.setCurrentContainer(new EmployeesUi(ui));
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}
}