package me.darrionat.schedulemaster.ui.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.darrionat.schedulemaster.interfaces.Menu;
import me.darrionat.schedulemaster.ui.MenuButton;

public class MenuButtonListener implements MouseListener, MouseMotionListener {

	private Menu menu;

	/**
	 * Creates the Listener class for the MainMenu
	 * 
	 * @param menu the instance of main menu
	 */
	public MenuButtonListener(Menu menu) {
		this.menu = menu;
	}
	// Start MouseListener

	@Override
	public void mouseClicked(MouseEvent e) {
		MenuButton button = getButton(e);
		if (button != null) {
			button.click();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	// End MouseListener

	// Start MouseMotionListener

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MenuButton button = getButton(e);
		for (MenuButton b : menu.getButtons()) {
			if (button == b) {
				b.select(true);
			} else if (b.isSelected()) {
				b.deselect(true);
			}
		}
	}

	// End MouseMotionListener

	/**
	 * Gets the button that is in the location of the defined coordinates
	 * 
	 * @param e the MouseEvent that is being checked for location
	 * @return returns the MenuButton that is located in the (x, y) position of the
	 *         MouseEvent; null if none exist
	 */
	private MenuButton getButton(MouseEvent e) {
		for (MenuButton b : menu.getButtons())
			if (b.containsPoint(e.getX(), e.getY()))
				return b;
		return null;
	}
}