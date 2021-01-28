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
	 * Defines what and where the UiText is bounded to for X values
	 */
	private UiComponent horzboundedComponent;
	private HorizontalAlignment horzAlignment;

	/**
	 * Defines what and where the UiText is bounded to for Y values
	 */
	private UiComponent vertBoundedComponent;
	private VerticalAlignment vertAlignment;

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

	/**
	 * Aligns the UiText to the defined component and horizontally aligns it
	 * 
	 * @param component the component to be aligned with
	 * @param alignment how to align the text
	 */
	public void boundToComponent(UiComponent component, HorizontalAlignment alignment) {
		horzboundedComponent = component;
		horzAlignment = alignment;
	}

	/**
	 * Aligns the UiText to the defined component and vertically aligns it
	 * 
	 * @param component the component to be aligned with
	 * @param alignment how to align the text
	 */
	public void boundToComponent(UiComponent component, VerticalAlignment alignment) {
		vertBoundedComponent = component;
		vertAlignment = alignment;
	}

	/**
	 * Removes any horizontal alignment that the UiText has. This will not affect
	 * the XConstraint, it will just stop any further changing of the XConstraint
	 * itself.
	 */
	public void unboundHorizontal() {
		horzboundedComponent = null;
		horzAlignment = null;
	}

	/**
	 * Removes any horizontal alignment that the UiText has. This will not affect
	 * the YConstraint, it will just stop any further changing of the YConstraint
	 * itself.
	 */
	public void unboundVertical() {
		vertBoundedComponent = null;
		vertAlignment = null;
	}

	/**
	 * Checks to see if the text is currently horizontally aligned to any
	 * UiComponent
	 * 
	 * If at any time the UiComponent that the UiText is bounded to is removed, it
	 * will no longer change its XConstraint, and should be correctly unbounded with
	 * {@link #unboundHorizontal()}.
	 * 
	 * @return returns {@code true} if the text is horizontally aligned
	 */
	public boolean isHorizontallyAligned() {
		return horzboundedComponent != null && horzAlignment != null;
	}

	/**
	 * Checks to see if the text is currently vertically aligned to any UiComponent
	 * 
	 * If at any time the UiComponent that the UiText is bounded to is removed, it
	 * will no longer change its YConstraint, and should be unbounded with
	 * {@link #unboundVertical()}.
	 * 
	 * @return returns {@code true} if the text is vertically aligned
	 */
	public boolean isVerticallyAligned() {
		return vertBoundedComponent != null && vertAlignment != null;
	}

	/**
	 * Horizontally aligns the text based on the defined alignment
	 * 
	 * @param g the specified graphic context
	 */
	private void boundXToComponent(Graphics g) {
		Rectangle2D bounds = getBoundingBox(g);

		int x = 0;
		switch (horzAlignment) {
		case LEFT:
			x = (int) (horzboundedComponent.getX() + horzboundedComponent.getWidth() / 20);
			break;
		case CENTER:
			x = (int) (horzboundedComponent.getX() + horzboundedComponent.getWidth() / 2 - bounds.getWidth() / 2);
			break;
		case RIGHT:
			x = (int) (horzboundedComponent.getX() + horzboundedComponent.getWidth()
					- horzboundedComponent.getWidth() / 20 - bounds.getWidth());
			break;
		}
		constraints.setX(new PixelConstraint(x));
	}

	/**
	 * Vertically aligns the text based on the defined alignment
	 * 
	 * @param g the specified graphic context
	 */
	private void boundYToComponent(Graphics g) {
		Rectangle2D bounds = getBoundingBox(g);

		int y = 0;
		switch (vertAlignment) {
		case TOP:
			y = (int) (vertBoundedComponent.getY() + bounds.getHeight() + getFontMetrics(g).getAscent());
			break;
		case CENTER:
			y = (int) (vertBoundedComponent.getY() + ((vertBoundedComponent.getHeight() - bounds.getHeight()) / 2)
					+ getFontMetrics(g).getAscent());
			break;
		case BOTTOM:
			y = (int) (vertBoundedComponent.getY() + vertBoundedComponent.getHeight()
					- vertBoundedComponent.getHeight() / 20 - bounds.getHeight() + getFontMetrics(g).getAscent());
			break;
		}
		constraints.setY(new PixelConstraint(y));
	}

	@Override
	protected void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(uiColor.getColor());
		g.setFont(font);

		if (isHorizontallyAligned()) {
			boundXToComponent(g);
		}
		if (isVerticallyAligned()) {
			boundYToComponent(g);
		}
		g.drawString(text, getX(), getY());
	}
}