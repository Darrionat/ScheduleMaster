package me.darrionat.schedulemaster.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.darrionat.schedulemaster.Employee;

public class SchedulePermutationUtil {

	public Set<Employee[]> permutations;

	public SchedulePermutationUtil() {
		permutations = new HashSet<>();
	}

	/**
	 * Generates a set of all permutations of a given input table Each entry into
	 * the table is considered a row. The key of the map is a slot, and the value
	 * are the potential values for that slot.
	 * 
	 * @param table the table to generate permutations for
	 * @return all permutations of the table's columns, indexed in sequential order
	 *         by row
	 */
	public Set<Employee[]> getPermutations(HashMap<Integer, Employee[]> table) {
		return getPermutations(table, 1, null);

	}

	/**
	 * A recursive method to return all possible permutations for a given table Both
	 * parameters are used within the method for recursion and should be given the
	 * initial as defined below.
	 * 
	 * @param table the table to define all permutations for
	 * @param depth the row to work with. Initial value should be 1
	 * @param array the array called in recursion that will be added to the final
	 *              Set that will be returned. Initial value when should be null
	 * @return the set containing all possible permutations of the table's columns
	 */
	private Set<Employee[]> getPermutations(HashMap<Integer, Employee[]> table, int tableRow, Employee[] array) {
		if (array == null) {
			permutations.clear();
			array = new Employee[table.keySet().size()];
		}
		// Replace 'x' with null
		// System.out.println(array);
		for (Employee choice : getPossibleChoices(table, tableRow)) {
			Employee[] arr = array.clone();
			arr[tableRow - 1] = choice;
			if (tableRow + 1 > table.keySet().size()) {
				permutations.add(arr);
				continue;
			}
			getPermutations(table, tableRow + 1, arr);
		}
		if (tableRow == 1) {
			removeRepeatedValuePermutations();
		}
		return permutations;
	}

	/**
	 * Fetches all possible values of the row below the current row
	 * 
	 * @param table    the table that contains the rows
	 * @param tableRow the row of which is being
	 * @return the potential children of the tableRow above
	 */
	private Employee[] getPossibleChoices(HashMap<Integer, Employee[]> table, int tableRow) {
		if (tableRow > table.keySet().size()) {
			return null;
		}
		int firstSlot = (int) table.keySet().toArray()[tableRow - 1];
		Employee[] allPossible = table.get(firstSlot);
		return allPossible;
	}

	/**
	 * Removes the permutations that contain a repeated value
	 */
	private void removeRepeatedValuePermutations() {
		Set<Employee[]> removeSet = new HashSet<>();
		for (Employee[] arr : permutations) {
			if (arrayContainsDuplicates(arr)) {
				removeSet.add(arr);
			}
		}
		for (Employee[] arr : removeSet) {
			permutations.remove(arr);
		}
	}

	/**
	 * Returns true if an array has duplicate values
	 * 
	 * @param arr the array that is being checked for duplicates
	 */
	private boolean arrayContainsDuplicates(Employee[] arr) {
		Set<Employee> set = new HashSet<>();
		for (Employee e : arr)
			set.add(e);
		return arr.length != set.size();
	}

}