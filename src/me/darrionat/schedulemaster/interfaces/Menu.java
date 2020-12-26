package me.darrionat.schedulemaster.interfaces;

import java.util.List;

import javax.swing.JPanel;

import me.darrionat.schedulemaster.ui.MenuButton;
import me.darrionat.schedulemaster.ui.events.MenuButtonListener;

public interface Menu {

	public JPanel getPanel();

	public boolean hasListener();

	public MenuButtonListener getListener();

	public void setListener(MenuButtonListener listener);

	public List<MenuButton> getButtons();

	public void setButtons(List<MenuButton> button);

	public void drawButtons();
}