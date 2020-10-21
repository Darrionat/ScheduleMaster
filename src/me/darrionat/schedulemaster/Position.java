package me.darrionat.schedulemaster;

import java.util.ArrayList;
import java.util.List;

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

	public static Position fromString(String position) {
		return new Position(position);
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
	 * @param positions The list of positions being formatted
	 * @return The concatenated string of all positions
	 */
	public static String getStringFromList(List<Position> positions) {
		String[] positionArray = new String[positions.size()];
		for (int i = 0; i < positionArray.length; i++) {
			positionArray[i] = positions.get(i).toString();
		}
		return String.join(",", positionArray);
	}
}