package me.darrionat.schedulemaster.ui.componenets;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class UiBlock extends UiComponent {

	public UiBlock() {
	}

	public UiBlock(UiColor color) {
		super(color);
	}

	@Override
	public void draw(Graphics2D g2d) {
		Rectangle2D rect = new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());

		System.out.println(getY());
		System.out.println(getHeight());
		g2d.setColor(uiColor.getColor());
		g2d.fill(rect);
		g2d.draw(rect);
	}
}