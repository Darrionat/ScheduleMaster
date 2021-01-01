package me.darrionat.schedulemaster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The Schedule class is to create objects that represent a schedule. These
 * schedules are utilized in exporting to .csv files and displaying information
 * in the Schedule Master GUI.
 * 
 * @author Darrion Thornburgh
 */
public class Schedule {

	private HashMap<Shift, Employee> table;

	/**
	 * Creates a Schedule object from an inputed HashMap or table.
	 * 
	 * @param schedule A HashMap that has a key for every shift that needs a single
	 *                 employee to fill
	 */
	public Schedule(HashMap<Shift, Employee> schedule) {
		this.table = schedule;
	}

	public HashMap<Shift, Employee> getTable() {
		return table;
	}

	/**
	 * Inserts a shift employee pair into the schedule
	 * 
	 * @param shift    the shift key being inserted
	 * @param employee the employee value being inserted
	 * @return returns the Employee object, or null if the Shift did not exist
	 *         within the Schedule
	 */
	public Employee addShift(Shift shift, Employee employee) {
		return table.put(shift, employee);
	}

	/**
	 * Returns if the schedule has an employee listed more than once
	 * 
	 * @return returns if the schedule has an employee listed twice on the schedule
	 */
	public boolean hasDuplicateEmployee() {
		Set<Employee> set = new HashSet<>();
		for (Employee e : table.values())
			set.add(e);
		return table.size() != set.size();
	}

	/**
	 * Creates a deep copy of the schedule of which is not tied to the values of the
	 * original object.
	 * 
	 * @return a deep copy of the schedule.
	 */
	public Schedule cloneSchedule() {
		HashMap<Shift, Employee> table = new HashMap<>();
		for (Entry<Shift, Employee> entry : this.table.entrySet()) {
			table.put(entry.getKey(), entry.getValue());
		}
		return new Schedule(table);
	}

	/**
	 * Gets the total wage cost of schedule by employee wages and shift duration.
	 * 
	 * @return total cost of the schedule.
	 */
	public double getCost() {
		double cost = 0;
		for (Entry<Shift, Employee> entry : table.entrySet()) {

			// Wage and shift length
			double employeeWage = entry.getValue().getWage(entry.getKey().getPosition());
			double shiftLength = entry.getKey().getHours();

			// If the employee's wage is defined, add it after multiplying it by time.
			if (employeeWage > 0)
				cost += (employeeWage * shiftLength);
		}
		return cost;
	}

	/**
	 * Prints the schedule in the console. Purely for debugging purposes.
	 */
	public void print() {
		System.out.println("Permutation/Schedule");
		for (Entry<Shift, Employee> entry : table.entrySet())
			System.out.println(" " + entry.getKey().getPosition().getJobName() + " = " + entry.getValue().getName());
		System.out.println();
	}
}