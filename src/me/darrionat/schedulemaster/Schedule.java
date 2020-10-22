package me.darrionat.schedulemaster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Schedule {

	private HashMap<Shift, Employee> table;

	/**
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
	 * @return
	 */
	public Employee addShift(Shift shift, Employee employee) {
		return table.put(shift, employee);
	}

	/**
	 * Returns if the schedule has an employee listed more than once.
	 */
	public boolean hasDuplicateEmployee() {
		Set<Employee> set = new HashSet<>();
		for (Employee e : table.values())
			set.add(e);
		return table.size() != set.size();
	}

	public Schedule cloneSchedule() {
		HashMap<Shift, Employee> table = new HashMap<>();
		for (Entry<Shift, Employee> entry : this.table.entrySet()) {
			table.put(entry.getKey(), entry.getValue());
		}
		return new Schedule(table);
	}

	public void print() {
		System.out.println("Permutation/Schedule");
		for (Entry<Shift, Employee> entry : table.entrySet())
			System.out.println(" " + entry.getKey().getPosition().getJobName() + " = " + entry.getValue().getName());
		System.out.println();
	}
}