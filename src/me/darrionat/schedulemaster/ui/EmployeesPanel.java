package me.darrionat.schedulemaster.ui;

import java.util.List;

import javax.swing.JPanel;

import me.darrionat.schedulemaster.interfaces.Menu;
import me.darrionat.schedulemaster.ui.events.MenuButtonListener;

public class EmployeesPanel extends JPanel implements Menu {

	private static final long serialVersionUID = 1L;
	private MenuButtonListener listener;

	@Override
	public JPanel getPanel() {
		return this;
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

	@Override
	public List<MenuButton> getButtons() {
		return null;
	}

	@Override
	public void setButtons(List<MenuButton> newButtons) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawButtons() {
		// TODO Auto-generated method stub

	}
}