package me.darrionat.schedulemaster.services;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import me.darrionat.darrionGL.UI;
import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.ui.MainMenuUi;

public class UiService extends UI {

	private static final long serialVersionUID = 1L;

	public UiService() {
		// 1600x900
		setMinimumSize(new Dimension(1599, 899));
		setIconImage(new ImageIcon(ScheduleMaster.RESOURCES_PATH + "icon.png").getImage());
		setTitle(ScheduleMaster.NAME);
		initUI();
		setCurrentContainer(new MainMenuUi(this));
		setMinimumSize(new Dimension(1600, 900));
	}
}