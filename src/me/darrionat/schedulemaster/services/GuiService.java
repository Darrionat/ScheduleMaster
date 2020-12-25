package me.darrionat.schedulemaster.services;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.ui.MainMenu;

@SuppressWarnings("serial")
public class GuiService extends JFrame {

	public static int width;
	public static int height;

	public GuiService() {
		initUI();
		setVisible(true);
	}

	private MainMenu mainMenu;
	/**
	 * The font used for menu buttons
	 */
	public static final Font buttonFont = new Font("Anson", Font.PLAIN, 24);

	private void initUI() {
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600, 500));
		ImageIcon icon = new ImageIcon("res/icon.png");
		setIconImage(icon.getImage());
		setTitle(ScheduleMaster.NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		mainMenu = new MainMenu();
		add(mainMenu);
		pack();
		setVisible(true);

		width = getWidth();
		height = getHeight();
	}

	/**
	 * Draw a String centered in the middle of a Rectangle vertically and aligned to
	 * the left side.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
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
	 */
	public static void drawCenteredString(Graphics g, String text, Rectangle2D rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2);
		int y = (int) (rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

		g.setFont(font);
		g.drawString(text, x, y);
	}
}