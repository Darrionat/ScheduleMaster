package me.darrionat.schedulemaster;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A Shift object represents a start to end time of which a defined position is
 * required or is able to be done. Every Shift object is unique due to the
 * unique ID which is generated every time one is instantiated.
 * 
 * @author Darrion Thornburgh
 */
public class Shift {

	private long start;
	private long end;
	private Position position;

	/**
	 * ID's are generated to avoid the issue of a schedule needing two positions at
	 * the same time. This is because the Schedule object is based on a HashMap
	 * which does not allow duplicated keys (shifts). The probability of two shifts
	 * having the same ID is equal to 36^8.
	 */
	private String id;

	public Shift(Date start, Date end, Position position) {
		this.start = start.getTime();
		this.end = end.getTime();
		this.position = position;
		this.id = generateID();
	}

	public Shift(long start, long end, Position position) {
		this.start = start;
		this.end = end;
		this.position = position;
		this.id = generateID();
	}

	private Shift(long start, long end, Position position, String id) {
		this.start = start;
		this.end = end;
		this.position = position;
		this.id = id;
	}

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Generates a unique ID that is 8 characters long randomly selected from the
	 * alphabet as well as all integers from 0 to 9.
	 * 
	 * @return a random ID
	 */
	private String generateID() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890";
		Random r = new Random();

		String id = "";
		for (int i = 0; i < 8; i++) {
			char randomCharacter = alphabet.charAt(r.nextInt(alphabet.length()));
			id = id + randomCharacter;
		}
		return id;
	}

	/**
	 * The separator character for values when converted to or from a String.
	 */
	private final static String SEP = "-";

	/**
	 * Converts the Shift object into a String. The string will change depending on
	 * if the Position is defined.
	 * 
	 * @param includePosition {@code true} if to include the position within the
	 *                        String, {@code false} otherwise
	 * @return a Shift object converted to a String
	 */
	public String toString(boolean includePosition) {
		String s = String.valueOf(start) + SEP + String.valueOf(end) + SEP + id;
		if (position != null && includePosition)
			return s + SEP + position.toString();
		return s;
	}

	/**
	 * Returns a Shift object from an inputed string if formatted properly
	 * 
	 * @param shift the string to convert into a shift
	 * @return Shift object with start and end but no position defined
	 */
	public static Shift fromString(String shift) {
		String[] arr = shift.split(SEP);
		long start = Long.parseLong(arr[0]);
		long end = Long.parseLong(arr[1]);
		String id = arr[2];

		Position position;
		try {
			position = Position.fromString(arr[3]);
		} catch (ArrayIndexOutOfBoundsException e) {
			position = null;
		}
		return new Shift(start, end, position, id);
	}

	/**
	 * Utilized in reading data from an employee's file and getting a list of
	 * available shifts from a string
	 * 
	 * @param shiftsStr A string value saved within the .properties file of an
	 *                  employee.
	 * @return the list of shifts converted from shiftStr
	 */
	public static List<Shift> getListFromString(String shiftsStr) {
		List<Shift> shifts = new ArrayList<Shift>();
		for (String s : shiftsStr.split(",")) {
			shifts.add(Shift.fromString(s));
		}
		if (shifts.isEmpty())
			return null;
		return shifts;
	}

	/**
	 * Converts a list of Shift objects into a String object. Utilized to save the
	 * available shifts to an employee's file.
	 * 
	 * @param shifts          the list of shifts to convert into a String
	 * @param includePosition {@code true} if to include the position within the
	 *                        String, {@code false} otherwise
	 * @return the list converted into a string
	 */
	public static String getStringFromList(List<Shift> shifts, boolean includePosition) {
		String[] arr = new String[shifts.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = shifts.get(i).toString(includePosition);
		}
		return String.join(",", arr);
	}

	/**
	 * Calculates the length of the shift in hours.
	 * 
	 * @return the length of the shift.
	 */
	public double getHours() {
		long millis = end - start;
		double seconds = millis / 1000;
		double hours = seconds / 3600;
		return hours;
	}
}