package me.darrionat.schedulemaster;

import java.util.HashMap;
import java.util.List;

/**
 * The Employee class is to create employee objects. These objects hold values
 * that belong to an employee: a name, positions, wages, and shifts they are
 * able to work.
 * 
 * @author Darrion Thornburgh
 */
public class Employee {

	private String name;

	/**
	 * Positions TODO: Indexed by preference The HashMap containing keys of
	 * positions the employee is capable of working, and the values being the wage
	 * associated with that position
	 */
	private HashMap<Position, Double> positions;
	private List<Shift> availableShifts;

	/**
	 * Create an Employee object with no defined properties
	 */
	public Employee() {

	}

	/**
	 * Create an employee object with a name, map of defined positions with wages,
	 * and shifts they are able to work.
	 * 
	 * @param name            the name of the employee
	 * @param positions       the jobs that an employee is capable of performing
	 * @param availableShifts the time slots an employee is able to work. Positions
	 *                        are not taken into account for this.
	 */
	public Employee(String name, HashMap<Position, Double> positions, List<Shift> availableShifts) {
		this.name = name;
		this.positions = positions;
		this.availableShifts = availableShifts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return a HashMap of the employee's positions associated with its wage.
	 */
	public HashMap<Position, Double> getPositions() {
		return positions;
	}

	/**
	 * Gets the wage for a defined position of the employee. If the employee cannot
	 * work that position, the value returned is -1.
	 * 
	 * @param position the position used to look up the wage for the employee for
	 *                 that specified position
	 * @return the wage of the employee for the defined position
	 */
	public double getWage(Position position) {
		if (positions.get(position) == null)
			return -1;
		return positions.get(position);
	}

	public void setPositions(HashMap<Position, Double> positions) {
		this.positions = positions;
	}

	public List<Shift> getAvailableShifts() {
		return availableShifts;
	}

	public void addAvailableShift(Shift shift) {
		availableShifts.add(shift);
	}

	public void setAvailableShifts(List<Shift> availableShifts) {
		this.availableShifts = availableShifts;
	}

	/**
	 * Returns if an employee's positions contain the shift's position, as well as
	 * checking to see if their available times contain the shift's duration.
	 * 
	 * @param shift the shift which is being checked for compatibility
	 * @return if the employee is able to work that shift
	 */
	public boolean compatibleWithShift(Shift shift) {
		if (!positionsContainJob(shift)) {
			return false;
		}
		long start = shift.getStart();
		long end = shift.getEnd();
		for (Shift availableShift : availableShifts) {
			if (start >= availableShift.getStart() && end <= availableShift.getEnd())
				return true;
		}
		return false;
	}

	/**
	 * @return if the employee's available positions contain a particular shift.
	 */
	private boolean positionsContainJob(Shift shift) {
		Position jobPosition = shift.getPosition();
		for (Position position : positions.keySet())
			if (position.getJobName().equals(jobPosition.getJobName()))
				return true;
		return false;
	}
}