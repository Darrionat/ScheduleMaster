package me.darrionat.schedulemaster.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import me.darrionat.schedulemaster.interfaces.Menu;
import me.darrionat.schedulemaster.services.GuiService;
import me.darrionat.schedulemaster.ui.animations.Animation;
import me.darrionat.schedulemaster.ui.animations.MenuButtonClickAnimation;
import me.darrionat.schedulemaster.ui.animations.MenuButtonHoverAnimation;

/**
 * The MenuButton class is intended to make it easier to create buttons all
 * across a program. These buttons can be pushed to perform a function or open a
 * menu. Buttons are composed of a rectangle with text located inside.
 * 
 * @author Darrion Thornburgh
 */
public class MenuButton {

	/**
	 * Menu object which button is within
	 */
	private Menu menu;
	/**
	 * JPanel of the menu
	 */
	private JPanel panel;
	/**
	 * Graphics to draw onto
	 */
	private Graphics2D g2d;

	/*
	 * Button's Graphical Components
	 */
	public Rectangle2D rect;
	private String text;
	public double x, y;
	public int w, h;

	/*
	 * Colors
	 */
	/**
	 * The default color of the rectangle
	 */
	private Color boxColor = new Color(70, 70, 70, 128);
	/**
	 * The default color of the rectangle when being hovered
	 */
	private Color selectedColor = new Color(0, 184, 230, 200);
	/**
	 * The default color of the text within the rectangle
	 */
	private Color textColor = new Color(255, 255, 255);
	/**
	 * The current color of the rectangle
	 */
	private Color color = boxColor;

	/*
	 * Animation Fields and Objects
	 */

	/**
	 * Current animation
	 */
	private Animation animation;
	/**
	 * Hover Animation Expanding
	 */
	private Animation hoverAnimationExpand = new MenuButtonHoverAnimation(this, true);
	/**
	 * Hover Animation Compressing
	 */
	private Animation hoverAnimationCompress = new MenuButtonHoverAnimation(this, false);
	/**
	 * Click Animation
	 */
	private Animation clickAnimation = new MenuButtonClickAnimation(this);

	/*
	 * Hover Animation
	 */
	/**
	 * Current expansion of the button
	 */
	public int currentStep = 0;
	/**
	 * Max expansion of the button
	 */
	public int maxStep = 100;
	/**
	 * If the button is being hovered over
	 */
	private boolean selected = false;

	/*
	 * Click Animation
	 */

	/**
	 * Whether the box has been clicked or not
	 */
	private boolean clicked = false;
	/**
	 * The color at the time of the box being clicked
	 */
	private Color clickedColor;

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
	public MenuButton(Menu menu, String s, double x, double y, int w, int h) {
		this.menu = menu;
		this.panel = menu.getPanel();
		this.text = s;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	/**
	 * Draw the menu button, composed of a rectangle and text
	 * 
	 * @param g the graphics object of the Menu
	 */
	public void draw(Graphics g) {
		g2d = (Graphics2D) g;
		addRenderingHints();
		drawRectangle();
		if (text != null)
			drawText();
	}

	/**
	 * Improves graphics of the button, especially the text
	 */
	private void addRenderingHints() {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}

	/**
	 * Draws the base rectangle of the button
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
	 * Draws the text of the button; requires that the rectangle has already been
	 * drawn
	 */
	private void drawText() {
		g2d.setColor(textColor);
		GuiService.drawLeftAlignedString(g2d, text, rect, GuiService.buttonFont);
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
		if (animation != null && animation instanceof MenuButtonHoverAnimation) {
			return;
		}
		hoverAnimationExpand.run();
	}

	/**
	 * Motions the button to move outwards and becomes more like MainMenu.boxColor
	 * 
	 * @param compress determines whether to change the size and color of the
	 *                 rectangle
	 */
	public void deselect(boolean compress) {
		selected = false;
		hoverAnimationCompress.run();
	}

	/**
	 * Performs a clicking animation and opens the menu that the button is defined
	 * to open
	 */
	public void click() {
		if (animation != null)
			animation.cancel();
		clicked = true;
		clickedColor = color;

		GuiService.moveButtonToTopLayer(menu, this);
		clickAnimation.run();
	}

	/**
	 * Fetches the current animation that is occurring. Useful for canceling an
	 * animation while in progress
	 * 
	 * @return a Timer object which can be cancelled
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Sets the current animation of button. This does not mean that the animation
	 * will run automatically, it must be ran by Animation.run()
	 * 
	 * @param animation the animation being set to be the current animation
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * Gets the current color of the rectangle of the button
	 * 
	 * @return the color of the rectangle
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the default color of the button's rectangle
	 * 
	 * @param color the new color being defined
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Fetches the panel that the button is located in
	 * 
	 * @return the JPanel object that the MenuButton is within
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Fetches the state of the object's clicked field
	 * 
	 * @return returns the state if the object has been clicked
	 */
	public boolean wasClicked() {
		return clicked;
	}

	/**
	 * Gets the default color of the MenuButton's rectangle
	 * 
	 * @return returns the color of the rectangle
	 */
	public Color getBoxColor() {
		return boxColor;
	}

	/**
	 * Changes the default color of the MenuButton
	 * 
	 * @param color the new color
	 */
	public void setBoxColor(Color color) {
		this.boxColor = color;
	}

	/**
	 * Gets the default color of the MenuButton's rectangle when hovered over
	 * 
	 * @return the color of the rectangle during a MenuButtonHoverAnimation
	 */
	public Color getSelectedColor() {
		return selectedColor;
	}

	/**
	 * Changes the default color of the MenuButton during a MenuButtonHoverAnimation
	 * 
	 * @param color the new color
	 */
	public void setSelectedColor(Color color) {
		this.selectedColor = color;
	}

	/**
	 * Gets the color the button was when it was clicked; used in
	 * MenuButtonClickAnimation
	 * 
	 * @return the color the button was when {@link #click()} was ran
	 */
	public Color getClickedColor() {
		return clickedColor;
	}
}