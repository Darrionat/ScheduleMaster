package me.darrionat.schedulemaster.ui.components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class UiText extends UiComponent {

	private String text;
	private Font font;

	/**
	 * Creates a UiText object that contains the given text and with a default color
	 * of black
	 * 
	 * @param text the text for the UiText object to display
	 */
	public UiText(String text) {
		this.text = text;
		font = new Font("Arial", Font.PLAIN, 11);
	}

	/**
	 * Creates a UiText object with both given text and a color
	 * 
	 * @param text  the text for the UiText object to display
	 * @param color the color to set the UiText object to
	 */
	public UiText(String text, UiColor color) {
		super(color);
		this.text = text;
		font = new Font("Arial", Font.PLAIN, 11);
	}

	/**
	 * Sets the text of the UiText to the given parameter
	 * 
	 * @param s a String to set the text to
	 */
	public void setText(String s) {
		this.text = s;
	}

	/**
	 * Changes the font of the UiText to the given font
	 * 
	 * @param font the font to change the text to
	 */
	public void setFont(Font f) {
		this.font = f;
	}

	@Override
	protected void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(uiColor.getColor());
		g.setFont(font);
		g.drawString(text, getX(), getY());
	}
}