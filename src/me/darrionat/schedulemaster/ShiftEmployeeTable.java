package me.darrionat.schedulemaster;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A table representing each shift associated with a value of employees that
 * could potentially work that shift
 * 
 * @author Darrion Thornburgh
 */
public class ShiftEmployeeTable {

	private HashMap<Shift, Employee[]> table;

	public Set<Schedule> permutations;

	public ShiftEmployeeTable(HashMap<Shift, Employee[]> table) {
		this.table = table;
		permutations = new HashSet<>();
	}

	public HashMap<Shift, Employee[]> getTable() {
		return table;
	}

	public void setPotentialEmployees(Shift shift, Employee[] employees) {
		table.put(shift, employees);
	}

	public Employee[] getPotentialEmployees(Shift shift) {
		return table.get(shift);
	}

	public ShiftEmployeeTable cloneTable() {
		return new ShiftEmployeeTable(table);
	}

	public void putAll(ShiftEmployeeTable shiftEmployeeTable) {
		table.putAll(shiftEmployeeTable.getTable());
	}

	public Set<Entry<Shift, Employee[]>> entrySet() {
		return table.entrySet();
	}

	public Employee[] removeShift(Shift shift) {
		return table.remove(shift);
	}

	public boolean isEmpty() {
		return table.isEmpty();
	}

	public Shift getShift(int tableRow) {
		return (Shift) table.keySet().toArray()[tableRow];
	}

	public Collection<Employee[]> values() {
		return table.values();
	}

	/**
	 * Generates a set of all permutations of a given input table Each entry into
	 * the table is considered a row. The key of the map is a slot, and the value
	 * are the potential values for that slot.
	 * 
	 * @return all permutations of the table's columns, indexed in sequential order
	 *         by row
	 */
	public Set<Schedule> getPermutations() {
		return getPermutations(1, null);

	}

	/**
	 * A recursive method to return all possible permutations for a given table Both
	 * parameters are used within the method for recursion and should be given the
	 * initial as defined below.
	 * 
	 *
	 */

	private Set<Schedule> getPermutations(int tableRow, Schedule schedule) {
		if (schedule == null) {
			permutations.clear();
			schedule = new Schedule(new HashMap<>());
			// schedule = new Employee[table.keySet().size()];
		}
		Shift shift = (Shift) table.keySet().toArray()[tableRow - 1];
		for (Employee choice : getPossibleChoices(shift)) {

			Schedule sch = schedule.cloneSchedule();
			sch.addShift(shift, choice);

			if (tableRow + 1 > table.keySet().size()) {
				permutations.add(sch);
				continue;
			}
			getPermutations(tableRow + 1, sch);
		}
		if (tableRow == 1) {
			removeRepeatedValuePermutations();
		}
		return permutations;
	}

	/**
	 * Fetches all possible values of the row below the current row
	 * 
	 * @param shift the shift that contains some possible choices
	 * @return the potential children of the tableRow above
	 */
	private Employee[] getPossibleChoices(Shift shift) {
		return table.get(shift);
	}

	/**
	 * Removes the permutations that contain a repeated value
	 */
	private void removeRepeatedValuePermutations() {
		Set<Schedule> removeSet = new HashSet<>();
		for (Schedule schedule : permutations) {

			if (schedule.hasDuplicateEmployee()) {
				removeSet.add(schedule);
			}
		}
		for (Schedule schedule : removeSet) {
			permutations.remove(schedule);
		}
	}
}