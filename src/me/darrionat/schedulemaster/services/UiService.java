package me.darrionat.schedulemaster.services;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.interfaces.Menu;
import me.darrionat.schedulemaster.ui.MenuButton;
import me.darrionat.schedulemaster.ui.TestUi;
import me.darrionat.schedulemaster.ui.UiContainer;
import me.darrionat.schedulemaster.ui.events.MenuButtonListener;

public class UiService extends JFrame {

	private static final long serialVersionUID = 1L;

	public static int width;
	public static int height;

	public UiService() {
		initUI();
		setVisible(true);
	}

	private static UiContainer currentMenu;

	public static UiContainer getContainer() {
		return currentMenu;
	}

	/**
	 * The font used for menu buttons
	 */
	public static final Font buttonFont = new Font("Anson", Font.PLAIN, 24);

	private void initUI() {
		// setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600, 600));

		ImageIcon icon = new ImageIcon("res/icon.png");
		setIconImage(icon.getImage());
		setTitle(ScheduleMaster.NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// mainMenu = new MainMenuPanel();
		// addUI(mainMenu, true);
		currentMenu = new TestUi(this);
		add(currentMenu);
		pack();
		setVisible(true);
		currentMenu.setComponents();

		width = getWidth();
		height = getHeight();
	}

	public void addUI(Component component, boolean buttonListeners) {
		add(component);
		pack();
		setVisible(true);
		if (buttonListeners) {
			Menu menu = (Menu) component;
			MenuButtonListener listener = new MenuButtonListener(menu);
			menu.setListener(listener);
			component.addMouseListener(listener);
			component.addMouseMotionListener(listener);
		}
	}

	public void removeUI(Component component) {
		Menu menu = (Menu) component;
		if (menu.hasListener()) {
			component.removeMouseListener(menu.getListener());
			component.removeMouseMotionListener(menu.getListener());
			menu.setListener(null);
		}
		remove(component);
	}

	/**
	 * Draw a String centered in the middle of a Rectangle vertically and aligned to
	 * the left side.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 * @param font The Font to draw the text as
	 */
	public static void drawLeftAlignedString(Graphics g, String text, Rectangle2D rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = (int) (rect.getX() + rect.getWidth() / 20);
		int y = (int) (rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

		g.setFont(font);
		g.drawString(text, x, y);
	}

	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 * @param font The Font to draw the text as
	 */
	public static void drawCenteredString(Graphics g, String text, Rectangle2D rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2);
		int y = (int) (rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

		g.setFont(font);
		g.drawString(text, x, y);
	}

	/**
	 * 
	 * Moves a button to the end of the list to make it displayed last, making it
	 * the top layer
	 * 
	 * @param menu   the menu of the button that is being changed
	 * @param button the button being moved to the top layer
	 */
	public static void moveButtonToTopLayer(Menu menu, MenuButton button) {
		List<MenuButton> newButtons = new ArrayList<>();

		for (MenuButton mB : menu.getButtons()) {
			if (mB == button)
				continue;
			newButtons.add(mB);
		}
		newButtons.add(button);
		menu.setButtons(newButtons);
	}
}