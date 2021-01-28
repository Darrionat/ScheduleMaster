package me.darrionat.schedulemaster.ui.components.menu;

import java.awt.Graphics;

import me.darrionat.schedulemaster.ui.components.UiBlock;
import me.darrionat.schedulemaster.ui.components.UiComponent;
import me.darrionat.schedulemaster.ui.components.UiText;
import me.darrionat.schedulemaster.ui.components.enums.HorizontalAlignment;
import me.darrionat.schedulemaster.ui.components.enums.VerticalAlignment;

public class MenuButton extends UiComponent {

	private UiBlock rect;
	private UiText text;
	private HorizontalAlignment horzAlignment;
	private VerticalAlignment vertAlignment;

	/**
	 * Creates a new MenuButton. A MenuButton contains both a UiBlock and a UiText
	 * object. Neither object should already have predefined constraints because
	 * they will be cleared to fit the constraints of the MenuButton.
	 * 
	 * @param rect          the UiBlock that will be displayed based on the
	 *                      MenuButton's constraints
	 * @param text          the UiText that will be displayed based on the
	 *                      MenuButton's constraints
	 * @param horzAlignment determines where the text is located relative to the
	 *                      UiBlock on the x axis, defaults to left side alignment
	 * @param vertAlignment determines where the text is located relative to the
	 *                      UiBlock on the y axis, defaults to center alignment
	 * 
	 */
	public MenuButton(UiBlock rect, UiText text, HorizontalAlignment horzAlignment, VerticalAlignment vertAlignment) {
		this.rect = rect;
		this.text = text;
		this.horzAlignment = horzAlignment;
		this.vertAlignment = vertAlignment;

		if (horzAlignment == null)
			this.horzAlignment = HorizontalAlignment.LEFT;

		if (vertAlignment == null)
			this.vertAlignment = VerticalAlignment.CENTER;

		/*
		 * Adds the rectangle and text as subcomponents. Order does matter for the
		 * rectangle to be displayed as the top layer.
		 */
		add(rect, null);
		add(text, null);
	}

	public HorizontalAlignment getAlignment() {
		return horzAlignment;
	}

	public void setAlignment(HorizontalAlignment alignment) {
		this.horzAlignment = alignment;
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