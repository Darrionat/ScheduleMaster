package me.darrionat.schedulemaster;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import me.darrionat.schedulemaster.statics.Bootstrapper;

/**
 * Schedule Master is a program to assist businesses in creating the most
 * efficient schedule for their employees. Schedule Master is to help businesses
 * spend less time on the complex process of creating schedules and to start
 * spending more time on the things they enjoy. Start date of this project is
 * October 18 2020.
 * 
 * @author Darrion Thornburgh
 */
public class ScheduleMaster {

	public static transient String teString = "test";
	public static final String NAME = "Schedule Master";
	public static final String VERSION = getVersion();

	public static final String RESOURCES_PATH = "src/main/resources/";
	public static final String POM_XML_PATH = "pom.xml";

	public static void main(String[] args) {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(new ScheduleMaster());
	}

	private static String getVersion() {
		File file = new File(POM_XML_PATH);
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