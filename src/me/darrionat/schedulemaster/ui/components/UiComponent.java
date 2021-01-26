package me.darrionat.schedulemaster.ui.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import me.darrionat.schedulemaster.ui.constraints.UiConstraints;

/**
 * Represents a component of the display such as a pop up GUI. A UiComponent can
 * consist of multiple components with their own set of constraints.
 * 
 * @author Darrion Thornburgh
 */
public abstract class UiComponent {

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;

	/**
	 * Color of this object; defaulted to Black
	 */
	public UiColor uiColor;

	/**
	 * Constraints defined for this object
	 */
	protected UiConstraints constraints;

	/**
	 * A List of all subcomponents within this component
	 */
	protected List<UiComponent> components = new ArrayList<UiComponent>();

	/**
	 * Creates a UiComponent object without dimensions or constraints, and with a
	 * default color of black. Add constraints by making this a subcomponent of
	 * another component {@link #add(UiComponent, UiConstraints)} or by adding this
	 * component to a UiContainer object
	 */
	public UiComponent() {
		this.uiColor = UiColors.BLACK;
	}

	/**
	 * Creates a UiComponent object without dimensions or constraints, but does
	 * define color. Add constraints by making this a subcomponent of another
	 * component {@link #add(UiComponent, UiConstraints)} or by adding this
	 * component to a UiContainer object
	 * 
	 * @param color the set UiColor of the newly created UiComponent object
	 */
	public UiComponent(UiColor color) {
		this.uiColor = color;
	}

	/**
	 * Calculate the X coordinate of the component with the given constraints
	 * 
	 * @return returns the X coordinate of the UiComponent
	 */
	public int getX() {
		x = constraints.getXConstraint().getX(this);
		return x;
	}

	/**
	 * Calculate the Y coordinate of the component with the given constraints
	 * 
	 * @return returns the Y coordinate of the UiComponent
	 */
	public int getY() {
		y = constraints.getYConstraint().getY(this);
		return y;
	}

	/**
	 * Calculate the width of the component with the given constraints
	 * 
	 * @return returns the width of the UiComponent
	 */
	public int getWidth() {
		width = constraints.getWidthConstraint().getWidth(this);
		return width;
	}

	/**
	 * Calculate the height of the component with the given constraints
	 * 
	 * @return returns the height of the UiComponent
	 */
	public int getHeight() {
		height = constraints.getHeightConstraint().getHeight(this);
		return height;
	}

	/**
	 * Adds a subcomponent to this object
	 * 
	 * @param component   the subcomponent being added
	 * @param constraints the constraints to add to the subcomponent
	 */
	public void add(UiComponent component, UiConstraints constraints) {
		this.constraints = constraints;
		components.add(component);
	}

	/**
	 * Get the constraints of the UiComponent
	 * 
	 * @return returns a UiConstraints objects; which holds constraints for x, y,
	 *         width, and height
	 */
	public UiConstraints getConstraints() {
		return constraints;
	}

	/**
	 * Sets the components of the object
	 * 
	 * @param constraints the given constraints to set
	 */
	public void setConstraints(UiConstraints constraints) {
		this.constraints = constraints;
	}

	/**
	 * Displays the component and all of its subcomponents
	 * 
	 * @param g2D the graphics to display the components on
	 */
	public void displayComponent(Graphics2D g2D) {
		addRenderingHints(g2D);
		draw(g2D);
		for (UiComponent component : components)
			component.displayComponent(g2D);
	}

	/**
	 * Improves graphics of the UiComponent with antialiasing
	 * 
	 * @param g2d the graphics to add the rendering hints to
	 */
	private void addRenderingHints(Graphics2D g2d) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}

	/**
	 * Draws the component on the graphics passed through the method
	 * 
	 * @param g2D graphics to utilize for drawing on
	 */
	protected abstract void draw(Graphics g);
}