package me.darrionat.schedulemaster.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import me.darrionat.schedulemaster.services.GuiService;
import me.darrionat.schedulemaster.utils.Utils;

/**
 * Buttons that are located within the Main Menu
 * 
 * @author Darrion Thornburgh
 */
public class MenuButton {

	private JPanel panel;
	private Graphics2D g2d;
	public Rectangle2D rect;
	private String text;

	private double x, y;
	private int w, h;

	private final Color textColor = new Color(255, 255, 255);
	private final Color boxColor = new Color(70, 70, 70, 128);
	private final Color selectedColor = new Color(0, 184, 230, 200);
	private Color color = boxColor;

	/**
	 * Creates a MenuButton object. The button contains a text field and a rectangle
	 * 
	 * @param panel the panel in which the button is contained in
	 * @param g     the graphics object which is utilized to customize the
	 *              appearance of the shape and text
	 * @param s     the text which will appear within the rectangle. Make this null
	 *              if no text is desired
	 * @param x     X coordinate on the screen
	 * @param y     Y coordinate on the screen
	 * @param w     Width of the rectangle
	 * @param h     Height of the rectangle
	 */
	public MenuButton(JPanel panel, String s, double x, double y, int w, int h) {
		this.panel = panel;
		this.g2d = (Graphics2D) panel.getGraphics();
		this.text = s;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	/**
	 * Draw the menu button, composed of a rectangle and text
	 * 
	 * @param x the X location of the button
	 * @param y the Y location of the button
	 */
	public void draw(Graphics g) {
		g2d = (Graphics2D) g;
		addRenderingHints();
		drawRectangle();
		if (text != null)
			drawText();
	}

	/**
	 * Checks to see if the point is within the button
	 * 
	 * @param x the x location of a point
	 * @param y the y location of a point
	 * @return returns true if the point is within the rectangle; false otherwise
	 */
	public boolean containsPoint(double x, double y) {
		// Adjusted y value for
		return rect.contains(x, y);
	}

	/**
	 * Improves graphics of the button
	 */
	private void addRenderingHints() {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}

	/**
	 * Draws the base rectangle of the button
	 * 
	 * @param x X Coordinate of the button
	 * @param y Y Coordinate of the button
	 */
	private void drawRectangle() {
		rect = new Rectangle2D.Double(x, y, w, h);

		// Stroke width = 0
		g2d.setStroke(new BasicStroke(0));
		g2d.setColor(color);
		g2d.fill(rect);
		g2d.draw(rect);
	}

	/**
	 * Gets the current color of the button based on the hover animation
	 * 
	 * @return a color based upon the percentage of the hover animation
	 */
	private Color calculateHoverColor() {
		return Utils.getColorByPercent(boxColor, selectedColor, currentStep, maxStep);
	}

	/**
	 * Gets the current color of the button based on the hover animation
	 * 
	 * @return a color based upon the percentage of the hover animation
	 */
	private Color calculateClickColor() {
		return Utils.getColorByPercent(clickedColor, Color.WHITE, currentClickFrame, maxClickFrame);
	}

	/**
	 * Draws the text of the button; requires that the rectangle has already been
	 * drawn
	 */
	private void drawText() {
		g2d.setColor(textColor);
		GuiService.drawLeftAlignedString(g2d, text, rect, GuiService.buttonFont);
	}

	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	/**
	 * Motions the button to move outwards and becomes more like
	 * MainMenu.selectedColor
	 * 
	 * @param expand determines whether to change the size and color of the
	 *               rectangle
	 */
	public void select(boolean expand) {
		selected = true;
		if (expand)
			dX = 2;
		doHoverAnimation();
	}

	/**
	 * Motions the button to move outwards and becomes more like MainMenu.boxColor
	 * 
	 * @param compress determines whether to change the size and color of the
	 *                 rectangle
	 */
	public void deselect(boolean compress) {
		selected = false;
		if (compress)
			dX = -2;
		doHoverAnimation();
	}

	// The rate of the button extending, 1 for moving outwards and -1 for moving
	// inwards
	private int dX = 0;
	// Current expansion of the button
	private int currentStep = 0;
	// Max expansion of the box
	private int maxStep = 100;
	// Timer of the hover animation
	private Timer timer;

	/**
	 * Changes the button from a half transparent shade of gray to a opaque hue of
	 * light blue and vice versa
	 */
	private void doHoverAnimation() {
		if (clicked)
			return;
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				boolean stopped;
				if (dX == 2) {
					stopped = currentStep == maxStep;
				} else {
					stopped = currentStep == 0;
				}
				if (stopped) {
					cancel();
					return;
				}
				currentStep += dX;
				w += dX;
				color = calculateHoverColor();
				panel.repaint();
			}
		}, 0, 7);
	}

	// Ellipse used for animation
	// Saves the progress in the click animation
	private int currentClickFrame = 0;
	// Determines how many iterations are used in the click animation
	private int maxClickFrame = 100;

	/**
	 * Creates an animation that opens another menu
	 */
	public boolean clicked = false;
	private Color clickedColor;

	public void click() {
		if (timer != null)
			timer.cancel();
		clicked = true;
		clickedColor = color;

		((MainMenu) panel).moveButtonToTopLayer(this);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			int startX = (int) x;
			int startY = (int) y;
			// Upper-Left
			int diffX1 = 0 - startX;
			int diffY1 = 0 - startY;
			// Lower-Right
			int diffX2 = panel.getWidth() - startX;
			int diffY2 = panel.getHeight() - startY;
			// Upper-left corner
			double frameChangeX1 = (double) diffX1 / (double) maxClickFrame;
			double frameChangeY1 = (double) diffY1 / (double) maxClickFrame;
			// Lower-right corner
			double frameChangeX2 = (double) diffX2 / (double) maxClickFrame;
			double frameChangeY2 = (double) diffY2 / (double) maxClickFrame;

			@Override
			public void run() {
				x += frameChangeX1;
				y += frameChangeY1;
				w += frameChangeX2 + Math.abs(frameChangeX1);
				h += frameChangeY2 + Math.abs(frameChangeY1);
				color = calculateClickColor();
				panel.repaint();
				panel.getComponents();
				if (currentClickFrame == maxClickFrame)
					this.cancel();
				currentClickFrame++;
			}
		}, 0, 3);
	}
}