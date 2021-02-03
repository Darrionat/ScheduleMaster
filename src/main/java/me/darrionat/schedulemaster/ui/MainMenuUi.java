package me.darrionat.schedulemaster.ui;

import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import me.darrionat.darrionGL.UI;
import me.darrionat.darrionGL.UiContainer;
import me.darrionat.darrionGL.components.UiColor;
import me.darrionat.darrionGL.components.UiColors;
import me.darrionat.darrionGL.components.UiGroup;
import me.darrionat.darrionGL.components.UiImage;
import me.darrionat.darrionGL.components.UiText;
import me.darrionat.darrionGL.constraints.AspectConstraint;
import me.darrionat.darrionGL.constraints.ConstraintFactory;
import me.darrionat.darrionGL.constraints.PixelConstraint;
import me.darrionat.darrionGL.constraints.RelativeConstraint;
import me.darrionat.darrionGL.constraints.UiConstraints;
import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.ui.animations.UiButtonHoverAnimation;
import me.darrionat.schedulemaster.ui.components.UiButton;

public class MainMenuUi extends UiContainer {
	private static final long serialVersionUID = 1L;

	private static final float RECT_RADIUS = 8;
	private static final float BUTTON_X = 0.05f;
	private static final float Y_START = 0.4f;
	private static final float Y_GAP = 0.08f;
	private static final float BUTTON_WIDTH = 0.2f;
	private static final float BUTTON_HEIGHT = 0.07f;

	public static final UiColor BUTTON_COLOR = UiColors.DARK_GREY;
	public static final UiColor BUTTON_TEXT_COLOR = UiColors.DARK_GREY;
	public static final UiColor BUTTON_COLOR_EXTENDED = UiColors.LIGHT_BLUE_TRANSPARENT;

	private final String[] BUTTON_TEXTS = { "View Schedules", "Edit Schedule", "Employees", "Shifts", "Information" };

	public UiButton viewSchedules;
	private String version;

	public MainMenuUi(JFrame frame) {
		super(frame);
		version = "Version: " + getVersion();
	}

	// TODO: Finish MainMenuUi
	// Currently all testing lines
	@Override
	public void setComponents() {
		UiContainer display = UI.getContainer();

		UiImage icon = new UiImage(ScheduleMaster.RESOURCES_PATH + "icon.png");
		UiConstraints imageConstraints = new UiConstraints(new RelativeConstraint(0.05f), new RelativeConstraint(0.05f),
				new PixelConstraint(270), new AspectConstraint(1));
		display.add(icon, imageConstraints);

		UiGroup buttonGroup = new UiGroup();
		for (int i = 0; i < BUTTON_TEXTS.length; i++) {
			UiButton menuButton = setupUiButton(BUTTON_TEXTS[i], Y_START + i * Y_GAP);
			buttonGroup.add(menuButton);
		}
		display.add(buttonGroup);

		UiText uiText = new UiText(version);
		uiText.setFont(new Font("Anson", Font.PLAIN, 16));
		UiConstraints textConstraints = ConstraintFactory.getRelative(0.93f, 0.95f, 0.05f, 0.05f);
		display.add(uiText, textConstraints);
	}

	private UiButton setupUiButton(String text, float yPos) {
		UiButton menuButton = new UiButton(text);
		UiConstraints buttonConstraints = ConstraintFactory.getRelative(BUTTON_X, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);

		menuButton.setConstraints(buttonConstraints);
		menuButton.setUiColor(BUTTON_COLOR);
		menuButton.setTextColor(UiColors.WHITE);
		menuButton.setTextFont(new Font("Anson", Font.PLAIN, 32));
		menuButton.setAnimation(new UiButtonHoverAnimation(menuButton));
		menuButton.getUiBlock().setRoundedCorners(RECT_RADIUS);
		return menuButton;
	}

	private String getVersion() {
		File file = new File(ScheduleMaster.POM_XML_PATH);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("version");
			Element element = (Element) nodeList.item(0);
			return element.getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}