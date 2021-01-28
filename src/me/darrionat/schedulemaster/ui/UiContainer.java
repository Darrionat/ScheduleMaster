package me.darrionat.schedulemaster.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.UiConstraints;

public abstract class UiContainer extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Parent frame
	 */
	protected JFrame frame;

	/**
	 * All components within the UiContainer
	 */
	private List<UiComponent> components = new ArrayList<UiComponent>();

	/**
	 * Parent frame that the UiContainer is within
	 * 
	 * @param frame the JFrame object that the UiContainer is within
	 */
	public UiContainer(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void paintComponent(Graphics g) {
		UiService.width = frame.getWidth();
		UiService.height = frame.getHeight();
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		for (UiComponent component : components) {
			component.displayComponent(g2d);
		}
	}

	/**
	 * Sets the components of the UiContainer
	 */
	public abstract void setComponents();

	/**
	 * Adds a component to the UiContainer, putting it on the display when
	 * {@link #repaint()} is ran, only if there are constraints that are defined
	 * before it is attempted to be displayed. A component cannot be displayed
	 * without constraints.
	 * 
	 * @param component the component to add
	 * @see #add(UiComponent, UiConstraints)
	 */
	public void add(UiComponent component) {
		components.add(component);
	}

	/**
	 * Adds a component to the UiContainer, putting it on the display when
	 * {@link #repaint()} is ran
	 * 
	 * @param component   the component to add
	 * @param constraints the constraints to set on the component to determine
	 *                    dimensions
	 */
	public void add(UiComponent component, UiConstraints constraints) {
		component.setConstraints(constraints);
		components.add(component);
	}

	/**
	 * Gets all components of this UiContainer
	 * 
	 * @return gets all UiComponent objects; first indexed are drawn first, making
	 *         the last indexed objects the ones on the top layer
	 */
	public List<UiComponent> getComponenets() {
		return components;
	}
}