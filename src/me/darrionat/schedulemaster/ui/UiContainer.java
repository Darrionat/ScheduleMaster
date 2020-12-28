package me.darrionat.schedulemaster.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.componenets.UiComponent;
import me.darrionat.schedulemaster.ui.constraints.UiConstraints;

public abstract class UiContainer extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JFrame frame;
	private List<UiComponent> components = new ArrayList<UiComponent>();

	public List<UiComponent> getComponenets() {
		return components;
	}

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

	public void add(UiComponent component, UiConstraints constraints) {
		component.setConstraints(constraints);
		components.add(component);
	}

	public abstract void setComponents();
}