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

	/**
	 * A separator character which is utilized in saving data about the Position
	 * object.
	 */
	private final static String SEP = "-";

	public static Position fromString(String position) {
		String[] arr = position.split(SEP);
		return new Position(arr[0]);
	}

	/**
	 * @return a String including the wage associated with the position
	 */
	public String toString(double wage) {
		return toString() + SEP + wage;
	}

	/**
	 * Gets the wage of a position from the position as a string. This is used to
	 * add to an employee's map of positions with the corresponding wage.
	 * 
	 * @param position the position to retrieve data from as a string.
	 * @return an employee's position's wage.
	 */
	public static double getWageFromString(String position) {
		String[] arr = position.split(SEP);
		double wage = -1;

		// Throws both NumberFormatException and ArrayIndexOutOfBoundsException
		try {
			wage = Double.valueOf(arr[1]);
		} catch (Exception e) {
			wage = -1;
		}
		return wage;
	}

	/**
	 * Formats an inputed String which is then read and returned as a List of
	 * Position.
	 * 
	 * @param positionsStr a string of positions which can be
	 * @return a list of positions from an inputed string.
	 */
	@Deprecated
	public static List<Position> getListFromString(String positionsStr) {
		List<Position> positions = new ArrayList<>();
		for (String s : positionsStr.split(",")) {
			positions.add(fromString(s));
		}
		if (positions.isEmpty())
			return null;
		return positions;
	}

	/**
	 * Formats an inputed String which is then read and returned as a HashMap of
	 * Position as the key and Double, which represents a wage, as the value.
	 * 
	 * @param positionsStr a string of positions which can be
	 * @return a HashMap of positions and wages from an inputed string.
	 */
	public static HashMap<Position, Double> getMapFromString(String positionsStr) {
		HashMap<Position, Double> map = new HashMap<>();
		for (String s : positionsStr.split(",")) {
			map.put(fromString(s), getWageFromString(s));
		}
		if (map.isEmpty())
			return null;
		return map;
	}

	/**
	 * Returns a formatted list of positions able to be saved within an employee's
	 * file
	 * 
	 * @param hashMap The list of positions being formatted
	 * @return The concatenated string of all positions
	 */
	public static String getStringFromMap(HashMap<Position, Double> hashMap) {
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