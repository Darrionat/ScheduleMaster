package me.darrionat.schedulemaster.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import me.darrionat.schedulemaster.services.GuiService;
import me.darrionat.schedulemaster.ui.events.MainMenuMouse;

/**
 * The MainMenu is a JPanel that is added to the GuiService JFrame. The MainMenu
 * contains a series of buttons and ScheduleMaster's icon. These buttons are
 * from a subclass MenuButton, which is used for animating and displaying
 * buttons that can be interacted with.
 * 
 * @author Darrion Thornburgh
 */
public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private Graphics g;
	private Image icon;

	public MainMenu() {
		registerListeners();
		loadIcon();
	}

	private final String[] buttonTexts = { "View Schedules", "Edit Schedule", "Employees", "Shifts", "Information" };

	/**
	 * Contains all buttons contained within the Main Menu
	 */
	public List<MenuButton> buttons = new ArrayList<>();

	public List<MenuButton> getButtons() {
		return buttons;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		setBackground(new Color(0));
		drawIcon(0.65 * GuiService.width, 0.15 * GuiService.height, 300, 300);
		createButtons();
	}

	private MainMenuMouse listener;

	public void registerListeners() {
		listener = new MainMenuMouse(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	public void stopListeners() {
		removeMouseListener(listener);
		removeMouseMotionListener(listener);
		listener = null;
	}

	private void createButtons() {

		double x = 0.05 * GuiService.width;
		if (buttons.isEmpty())
			for (int i = 0; i < buttonTexts.length; i++) {
				String text = buttonTexts[i];
				buttons.add(new MenuButton(this, text, x, GuiService.height * (0.4 + i * 0.08), 350, 75));
			}

		for (MenuButton button : buttons)
			button.draw(g);
	}

	/**
	 * Moves a button to the end of the list to make it displayed last, making it
	 * the top layer
	 * 
	 * @param button the button being moved to the top layer
	 */
	public void moveButtonToTopLayer(MenuButton button) {
		List<MenuButton> newButtons = new ArrayList<>();

		for (MenuButton mB : buttons) {
			if (mB == button)
				continue;
			newButtons.add(mB);
		}
		newButtons.add(button);
		buttons.clear();
		buttons.addAll(newButtons);
	}

	private void loadIcon() {
		ImageIcon ii = new ImageIcon("res/icon.png");
		icon = ii.getImage();
	}

	private void drawIcon(double x, double y, int w, int h) {
		g.drawImage(icon, (int) x, (int) y, w, h, null, null);
	}
}