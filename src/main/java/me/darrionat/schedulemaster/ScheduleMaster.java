package me.darrionat.schedulemaster;

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

	public final static String NAME = "Schedule Master";
	public static String VERSION;

	public final static String RESOURCES_PATH = "src/main/resources/";
	public final static String POM_XML_PATH = "pom.xml";

	public static void main(String[] args) {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(new ScheduleMaster());
	}
}