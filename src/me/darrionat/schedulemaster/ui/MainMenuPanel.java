package me.darrionat.schedulemaster.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import me.darrionat.schedulemaster.interfaces.Menu;
import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.events.MenuButtonListener;

/**
 * The MainMenu is a JPanel that is added to the GuiService JFrame. The MainMenu
 * contains a series of buttons and ScheduleMaster's icon. These buttons are
 * from a subclass MenuButton, which is used for animating and displaying
 * buttons that can be interacted with.
 * 
 * @author Darrion Thornburgh
 */
public class MainMenuPanel extends JPanel implements Menu {

	private static final long serialVersionUID = 1L;

	private Graphics g;
	private Image icon;
	private MenuButtonListener listener;

	private final String[] buttonTexts = { "View Schedules", "Edit Schedule", "Employees", "Shifts", "Information" };

	/**
	 * Contains all buttons contained within the Main Menu
	 */
	private List<MenuButton> buttons = new ArrayList<>();

	public MainMenuPanel() {
		loadIcon();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		setBackground(new Color(0));
		drawIcon(0.65 * UiService.width, 0.15 * UiService.height, 300, 300);
		initButtons();
		drawButtons();
	}

	private void initButtons() {
		double x = 0.05 * UiService.width;
		if (buttons.isEmpty())
			for (int i = 0; i < buttonTexts.length; i++) {
				String text = buttonTexts[i];
				buttons.add(new MenuButton(this, text, x, UiService.height * (0.4 + i * 0.08), 350, 75));
			}
	}

	private void loadIcon() {
		ImageIcon ii = new ImageIcon("res/icon.png");
		icon = ii.getImage();
	}

	private void drawIcon(double x, double y, int w, int h) {
		g.drawImage(icon, (int) x, (int) y, w, h, null, null);
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public List<MenuButton> getButtons() {
		return buttons;
	}

	@Override
	public void setButtons(List<MenuButton> newButtons) {
		buttons.clear();
		buttons.addAll(newButtons);
	}

	@Override
	public void drawButtons() {
		for (MenuButton button : buttons)
			button.draw(g);
	}

	@Override
	public boolean hasListener() {
		return listener != null;
	}

	@Override
	public MenuButtonListener getListener() {
		return listener;
	}

	@Override
	public void setListener(MenuButtonListener listener) {
		this.listener = listener;
	}
}