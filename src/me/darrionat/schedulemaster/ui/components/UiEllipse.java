package me.darrionat.schedulemaster.ui.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class UiEllipse extends UiComponent {

	public UiEllipse() {
	}

	public UiEllipse(UiColor color) {
		super(color);
	}

	@Override
	protected void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D ellipse = new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
		g2d.setColor(uiColor.getColor());
		g2d.fill(ellipse);
		g2d.draw(ellipse);
	}
}