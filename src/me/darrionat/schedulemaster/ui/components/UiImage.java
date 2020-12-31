package me.darrionat.schedulemaster.ui.components;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class UiImage extends UiComponent {

	private ImageIcon icon;

	public UiImage(String imageUrl) {
		this.icon = new ImageIcon(imageUrl);
	}

	@Override
	protected void draw(Graphics g) {
		g.drawImage(icon.getImage(), getX(), getY(), getWidth(), getHeight(), null, null);
	}
}