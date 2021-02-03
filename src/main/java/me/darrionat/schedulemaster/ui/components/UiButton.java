package me.darrionat.schedulemaster.ui.components;

import java.awt.Font;
import java.awt.Graphics;

import me.darrionat.darrionGL.components.UiBlock;
import me.darrionat.darrionGL.components.UiColor;
import me.darrionat.darrionGL.components.UiComponent;
import me.darrionat.darrionGL.components.UiText;
import me.darrionat.darrionGL.components.enums.HorizontalAlignment;
import me.darrionat.darrionGL.components.enums.VerticalAlignment;
import me.darrionat.darrionGL.constraints.UiConstraints;
import me.darrionat.darrionGL.events.interfaces.Hoverable;

public class UiButton extends UiComponent implements Hoverable {

	private UiBlock rect;
	private UiText text;
	private HorizontalAlignment horzAlignment = HorizontalAlignment.LEFT;
	private VerticalAlignment vertAlignment = VerticalAlignment.CENTER;

	/**
	 * Creates a new MenuButton. A MenuButton contains both a UiBlock and a UiText
	 * object and they are both created based upon the UiConstraints that the
	 * MenuButton has.
	 * 
	 * @param text the String that will be displayed based on the MenuButton's
	 *             constraints
	 */
	public UiButton(String displayedText) {
		super();

		rect = new UiBlock();
		rect.setUiColor(this.uiColor);
		text = new UiText(displayedText);

		/*
		 * Adds the rectangle and text as subcomponents. Order does matter for the
		 * rectangle to be displayed as the top layer.
		 */
		add(rect, null);
		add(text, null);
	}

	public HorizontalAlignment getTextHorizontalAlignment() {
		return horzAlignment;
	}

	public VerticalAlignment getTextVerticalAlignment() {
		return vertAlignment;
	}

	public void setTextAlignment(HorizontalAlignment alignment) {
		this.horzAlignment = alignment;
	}

	public void setTextAlignment(VerticalAlignment alignment) {
		this.vertAlignment = alignment;
	}

	public UiColor getTextColor() {
		return text.getUiColor();
	}

	public void setTextColor(UiColor color) {
		text.setUiColor(color);
	}

	public Font getTextFont() {
		return text.getFont();
	}

	public void setTextFont(Font f) {
		text.setFont(f);
	}

	public UiBlock getUiBlock() {
		return rect;
	}

	/**
	 * Sets the constraints of both the UiBlock and the UiText contained within the
	 * MenuButton. Afterwards, they are added as subcomponents to MenuButton and
	 * will be displayed through their own methods.
	 */
	@Override
	protected void draw(Graphics g) {
		rect.setUiColor(uiColor);
		rect.setConstraints(constraints);
		text.setConstraints(new UiConstraints());
		text.boundToComponent(rect, horzAlignment);
		text.boundToComponent(rect, vertAlignment);
	}
}