package me.darrionat.schedulemaster.ui.components.menu;

import java.awt.Graphics;

import me.darrionat.schedulemaster.ui.UiColor;
import me.darrionat.schedulemaster.ui.components.UiBlock;
import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.components.UiText;
import me.darrionat.schedulemaster.ui.components.enums.HorizontalAlignment;
import me.darrionat.schedulemaster.ui.components.enums.VerticalAlignment;

public class MenuButton extends UiComponent {

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
	public MenuButton(String displayedText) {
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

	public HorizontalAlignment getHorizontalAlignment() {
		return horzAlignment;
	}

	public void setAlignment(HorizontalAlignment alignment) {
		this.horzAlignment = alignment;
	}

	public VerticalAlignment getVerticalAlignment() {
		return vertAlignment;
	}

	public void setAlignment(VerticalAlignment alignment) {
		this.vertAlignment = alignment;
	}

	public UiColor getTextColor() {
		return text.getUiColor();
	}

	public void setTextColor(UiColor color) {
		text.setUiColor(color);
	}

	/**
	 * Sets the constraints of both the UiBlock and the UiText contained within the
	 * MenuButton. Afterwards, they are added as subcomponents to MenuButton and
	 * will be displayed through their own methods.
	 */
	@Override
	protected void draw(Graphics g) {
		rect.setConstraints(constraints);

		// text.setConstraints(constraints);
		// add(text, textConstraints);
	}

	private void setTextX() {
		switch (horzAlignment) {
		case LEFT:

			break;

		case CENTER:
			break;

		case RIGHT:

			break;
		}
	}

	private void setTextY() {

	}
}