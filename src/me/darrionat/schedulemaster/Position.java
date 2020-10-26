package me.darrionat.schedulemaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * The Position class is to create objects corresponding to a job. These objects
 * can hold values such as a job name and an abbreviation utilized on the
 * schedule.
 * 
 * @author Darrion Thornburgh
 */
public class Position {

	private String jobName;
	private char abbreviation;

	public Position() {

	}

	public Position(String jobName) {
		this.jobName = jobName;
		this.abbreviation = jobName.charAt(0);
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public char getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(char c) {
		this.abbreviation = c;
	}

	public String toString() {
		return jobName;
	}

	private final static String SEP = "-";

	public String toString(double wage) {
		return toString() + SEP + wage;
	}

	public static Position fromString(String position) {
		String[] arr = position.split(SEP);
		return new Position(arr[0]);
	}

	public static List<Position> getListFromString(String positionsStr) {
		List<Position> positions = new ArrayList<>();
		for (String s : positionsStr.split(",")) {
			positions.add(Position.fromString(s));
		}
		if (positions.isEmpty())
			return null;
		return positions;
	}

	/**
	 * Returns a formatted list of positions able to be saved within an employee's
	 * file
	 * 
	 * @param hashMap The list of positions being formatted
	 * @return The concatenated string of all positions
	 */
	public static String getStringFromList(HashMap<Position, Double> hashMap) {
		String[] positionArray = new String[hashMap.size()];
		int index = 0;
		for (Entry<Position, Double> entry : hashMap.entrySet()) {
			// Converts the Position to a string and adds it to the array
			positionArray[index] = entry.getKey().toString(entry.getValue());
			index++;
		}
		return String.join(",", positionArray);
	}

}