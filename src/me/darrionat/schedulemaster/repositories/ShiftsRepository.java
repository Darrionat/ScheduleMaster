package me.darrionat.schedulemaster.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import me.darrionat.schedulemaster.Shift;

public class ShiftsRepository {

	private FileRepository fileRepository;

	public ShiftsRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	// Property Keys
	private final String monday = "m";
	private final String tuesday = "t";
	private final String wednesday = "w";
	private final String thursday = "r";
	private final String friday = "f";
	private final String saturday = "sa";
	private final String sunday = "su";
	// End of Property Keys

	/**
	 * 
	 * @return The path to required shifts file
	 */
	public String getShiftsPath() {
		return fileRepository.getPath() + "\\shifts.properties";
	}

	/**
	 * 
	 * @return The file of the required shifts
	 */
	public File getShiftsFile() {
		return new File(getShiftsPath());
	}

	/**
	 * Creates a new required shift. Adds to the weekday the shift is defined in
	 * 
	 * @param shift the time period and position that is required
	 * @return the success of addition of the shift
	 * @throws Exception when a shift is not contained in one weekday
	 */
	public boolean addRequiredShift(Shift shift) {
		if (!isInOneDay(shift)) {
			return false;
		}
		String dayKey = getWeekdayKey(shift);

		// Create a list to save
		List<Shift> shiftList = new ArrayList<>();

		// Check to see if the day already has current shifts that are required
		List<Shift> dayShifts = getShiftsForDay(dayKey);
		if (dayShifts != null) {
			// If there are, add them to the list to save
			shiftList = dayShifts;
		}
		// Add the shift to save
		shiftList.add(shift);

		File path = getShiftsFile();
		try (OutputStream output = new FileOutputStream(path)) {

			Properties prop = new Properties();

			prop.setProperty(dayKey, Shift.getStringFromList(shiftList, true));

			// Save properties to file
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Returns if the shift is contained in a single day
	 * 
	 * @param shift The shift of the time frame that is being checked
	 * @return
	 */
	private boolean isInOneDay(Shift shift) {
		SimpleDateFormat formatter = new SimpleDateFormat("u");
		return formatter.format(shift.getStart()).equals(formatter.format(shift.getEnd()));
	}

	/**
	 * Determines the weekday of the shift given. Utilized in order to get the key
	 * for the .properties file of shifts
	 * 
	 * @param shift the shift that is getting its weekday obtained
	 * @return a String which is the key within the .properties file for that dayt6
	 */
	private String getWeekdayKey(Shift shift) {
		SimpleDateFormat formatter = new SimpleDateFormat("u");
		int dayInt = Integer.parseInt(formatter.format(shift.getStart()));

		return getWeekdayKey(dayInt);

	}

	private String getWeekdayKey(int dayInt) {
		String weekday;
		switch (dayInt) {
		case 1:
			weekday = monday;
			break;
		case 2:
			weekday = tuesday;
			break;
		case 3:
			weekday = wednesday;
			break;
		case 4:
			weekday = thursday;
			break;
		case 5:
			weekday = friday;
			break;
		case 6:
			weekday = saturday;
			break;
		case 7:
			weekday = sunday;
			break;
		default:
			weekday = "";
			break;
		}
		return weekday;
	}

	/**
	 * Returns the required shifts for the day presented in a list format. If there
	 * is an error reading the file or the key doesn't exist, the method will return
	 * null.
	 * 
	 * @param dayKey the key of the property
	 * @return a list of shifts that are required for a day
	 */
	public List<Shift> getShiftsForDay(String dayKey) {
		Properties prop = new Properties();

		try (InputStream inputStream = new FileInputStream(getShiftsFile());) {
			prop.load(inputStream);
		} catch (IOException e) {
			return null;
		}

		// Get property value
		String shiftsString = prop.getProperty(dayKey);
		if (shiftsString == null) {
			return null;
		}
		return Shift.getListFromString(shiftsString);
	}
}