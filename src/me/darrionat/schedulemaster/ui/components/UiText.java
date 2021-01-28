package me.darrionat.schedulemaster.ui.components;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import me.darrionat.schedulemaster.ui.components.enums.HorizontalAlignment;
import me.darrionat.schedulemaster.ui.components.enums.VerticalAlignment;
import me.darrionat.schedulemaster.ui.constraints.PixelConstraint;

public class UiText extends UiComponent {

	/**
	 * The displayed text
	 */
	private String text;
	/**
	 * The text's font; defaulted to Arial size 11
	 */
	private Font font;

	/**
	 * Creates a UiText object that contains the given text
	 * 
	 * @param text the text for the UiText object to display
	 */
	public UiText(String text) {
		super();
		this.text = text;
		font = new Font("Arial", Font.PLAIN, 11);
	}

	/**
	 * Sets the text of the UiText to the given parameter
	 * 
	 * @param s a String to set the text to
	 */
	public void setText(String s) {
		text = s;
	}

	/**
	 * Gets the current text within the UiText object
	 * 
	 * @return returns what text is displayed
	 */
	public String getText() {
		return text;
	}

	/**
	 * Changes the font of the UiText to the given font
	 * 
	 * @param font the font to change the text to
	 */
	public void setFont(Font f) {
		font = f;
	}

	/**
	 * Gets the current Font of the text that is displayed
	 * 
	 * @return returns the current Font of text that is displayed
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * This will throw a NullPointerException. The width must be calculated with the
	 * Graphics being taken into account. Utilize the bounding box instead
	 * {@link #getBoundingBox(Graphics g)}
	 * 
	 * @see #getBoundingBox(Graphics g)
	 */
	@Override
	public int getWidth() {
		return super.getWidth();
	}

	/**
	 * This will throw a NullPointerException. The width must be calculated with the
	 * Graphics being taken into account. Utilize the bounding box instead
	 * {@link #getBoundingBox(Graphics g)}
	 * 
	 * @see #getBoundingBox(Graphics)
	 */
	@Override
	public int getHeight() {
		return super.getHeight();
	}

	/**
	 * Gets the FontMetrics for the font being used
	 * 
	 * @param g the specified graphical context
	 * @return returns the FontMetrics for the font being used
	 */
	public FontMetrics getFontMetrics(Graphics g) {
		return g.getFontMetrics(font);
	}

	/**
	 * Gets the bounding box of the UiText, based on the text and font
	 * 
	 * @param g the specified graphical context
	 * @return returns a Rectangle2D object which is the bounds of bounds of the
	 *         text
	 */
	public Rectangle2D getBoundingBox(Graphics g) {
		FontMetrics metrics = g.getFontMetrics(font);
		return metrics.getStringBounds(getText(), 0, getText().length(), g);
	}

	public void boundXToComponent(UiComponent component, Graphics g, HorizontalAlignment horzAlignment) {
		Rectangle2D bounds = getBoundingBox(g);

		int x = 0;
		switch (horzAlignment) {
		case LEFT:
			x = (int) (component.getX() + component.getWidth() / 20);
			break;
		case CENTER:
			x = (int) (component.getX() + component.getWidth() / 2 - bounds.getWidth() / 2);
			break;
		case RIGHT:
			x = (int) (component.getX() + component.getWidth() - component.getWidth() / 20 - bounds.getWidth());
			break;
		}
		constraints.setX(new PixelConstraint(x));
	}

	public void boundYToComponent(UiComponent component, Graphics g, VerticalAlignment vertAlignment) {
		Rectangle2D bounds = getBoundingBox(g);

		int y = 0;
		switch (vertAlignment) {
		case TOP:
			y = (int) (component.getY() + bounds.getHeight() + getFontMetrics(g).getAscent());
			break;
		case CENTER:
			y = (int) (component.getY() + ((component.getHeight() - bounds.getHeight()) / 2)
					+ getFontMetrics(g).getAscent());
			break;
		case BOTTOM:
			y = (int) (component.getY() + component.getHeight() - component.getHeight() / 20 - bounds.getHeight()
					+ getFontMetrics(g).getAscent());
			break;
		}
		constraints.setY(new PixelConstraint(y));
	}

	@Override
	protected void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(uiColor.getColor());
		g.setFont(font);
		g.drawString(text, getX(), getY());
	}
}