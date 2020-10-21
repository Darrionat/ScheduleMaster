package me.darrionat.schedulemaster;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shift {

	private Date start;
	private Date end;
	private Position position;

	public Shift(Date start, Date end, Position position) {
		this.start = start;
		this.end = end;
		this.position = position;
	}

	public Shift(long start, long end, Position position) {
		this.start = new Date(start);
		this.end = new Date(end);
		this.position = position;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * The String that seperates the start and end of a shift
	 */
	private final static String SEP = "-";

	public String toString() {
		if (position != null)
			return String.valueOf(start.getTime()) + SEP + String.valueOf(end.getTime()) + SEP + position.toString();
		return String.valueOf(start.getTime()) + SEP + String.valueOf(end.getTime());
	}

	/**
	 * Returns a Shift object from an inputed string if formatted properly
	 * 
	 * @param shift the string to conver into a shift
	 * @return Shift object with start and end but no position defined
	 */
	public static Shift fromString(String shift) {
		String[] arr = shift.split(SEP);
		Date start = new Date(Long.parseLong(arr[0]));
		Date end = new Date(Long.parseLong(arr[1]));

		Position position;
		try {
			position = Position.fromString(arr[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			position = null;
		}
		return new Shift(start, end, position);
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
		List<Shift> shifts = new ArrayList<>();
		for (String s : shiftsStr.split(",")) {
			shifts.add(Shift.fromString(s));
		}
		if (shifts.isEmpty())
			return null;
		return shifts;
	}

	public static String getStringFromList(List<Shift> shifts) {
		String[] arr = new String[shifts.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = shifts.get(i).toString();
		}
		return String.join(",", arr);
	}
}