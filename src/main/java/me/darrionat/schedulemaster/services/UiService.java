package me.darrionat.schedulemaster.services;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import me.darrionat.darrionGL.UI;
import me.darrionat.darrionGL.UiContainer;
import me.darrionat.schedulemaster.ScheduleMaster;

public class UiService extends UI {

	private static final long serialVersionUID = 1L;

	public static int width;
	public static int height;

	public UiService() {
		setMinimumSize(new Dimension(1600, 900));
		ImageIcon icon = new ImageIcon("res/icon.png");
		setIconImage(icon.getImage());
		setTitle(ScheduleMaster.NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
}