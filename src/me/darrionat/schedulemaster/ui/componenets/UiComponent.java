package me.darrionat.schedulemaster.ui.componenets;

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
	public UiColor uiColor = UiColors.BLACK;

	protected UiConstraints constraints;

	protected List<UiComponent> components = new ArrayList<UiComponent>();

	public UiComponent() {

	}

	public UiComponent(UiColor color) {
		this.uiColor = color;
	}

	public int getX() {
		x = constraints.getXConstraint().getX(this);
		return x;
	}

	public int getY() {
		y = constraints.getYConstraint().getY(this);
		return y;
	}

	public int getWidth() {
		width = constraints.getWidthConstraint().getWidth(this);
		return width;
	}

	public int getHeight() {
		height = constraints.getHeightConstraint().getHeight(this);
		return height;
	}

	public void add(UiComponent component, UiConstraints constraints) {
		this.constraints = constraints;
		components.add(component);
	}

	public UiConstraints getConstraints() {
		return constraints;
	}

	public void setConstraints(UiConstraints constraints) {
		this.constraints = constraints;
	}

	public void displayComponent(Graphics2D g2D) {
		addRenderingHints(g2D);
		draw(g2D);
		for (UiComponent component : components)
			component.displayComponent(g2D);
	}

	/**
	 * Improves graphics of the button, especially the text
	 */
	private void addRenderingHints(Graphics2D g2d) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}

	protected abstract void draw(Graphics2D g2D);
}