package me.darrionat.schedulemaster.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import me.darrionat.schedulemaster.Employee;
import me.darrionat.schedulemaster.Shift;
import me.darrionat.schedulemaster.ShiftEmployeeTable;
import me.darrionat.schedulemaster.repositories.EmployeeRepository;
import me.darrionat.schedulemaster.repositories.ShiftsRepository;
import me.darrionat.schedulemaster.utils.Utils;

public class ScheduleService {
	private ShiftsRepository shiftsRepository;
	private EmployeeRepository employeeRepository;

	public ScheduleService(ShiftsRepository shiftsRepository, EmployeeRepository employeeRepository) {
		this.shiftsRepository = shiftsRepository;
		this.employeeRepository = employeeRepository;
	}

	private HashMap<Shift, Employee> generateSchedule(String dayKey) {
		Employee[] employees = employeeRepository.getAllEmployees();
		List<Shift> dailyShifts = shiftsRepository.getShiftsForDay(dayKey);

		// The map that will be returned in the end as the final schedule
		HashMap<Shift, Employee> toReturn = new HashMap<>();

		// To-be sorted into the final schedule. Map of compatible shifts and employees
		ShiftEmployeeTable shiftEmployeeTable = getShiftEmployeesTable(dailyShifts, employees);

		sortGivens(toReturn, shiftEmployeeTable);

		if (shiftEmployeeTable.isEmpty()) {
			return toReturn;
		}
		// TODO: Continue with logical deduction
		employees = sortByAvailability(employees);

		// getPermutations(table);

		return null;
	}

	/**
	 * Adds the shifts that can be determined by logical deduction and the process
	 * of elimination to the schedule.
	 * 
	 * @param schedule the final schedule object that is being changed
	 * @param table    the map of shifts and the compatible employees
	 */
	private void sortGivens(HashMap<Shift, Employee> schedule, ShiftEmployeeTable table) {
		ShiftEmployeeTable clone = table.clone();
		clone.putAll(table);

		boolean changed = false;
		for (Entry<Shift, Employee[]> entry : clone.entrySet()) {
			if (entry.getValue().length != 1)
				continue;
			// If the list only contains one compatible employee, add them to the schedule
			Employee employee = entry.getValue()[0];
			schedule.put(entry.getKey(), employee);
			// Remove from the to-sort-into-schedule list
			table.removeShift(entry.getKey());
			changed = true;
			removeEmployeeFromShifts(table, employee);
			break;

		}
		if (changed) {
			sortGivens(schedule, table);
		}
	}

	/**
	 * Removes an employee from all shifts that are still being sorted because they
	 * have already been sorted into the schedule
	 * 
	 * @param table    the map that is being sorted into the schedule
	 * @param employee the employee to remove from all shifts because they have been
	 *                 sorted into the schedule already
	 */
	private void removeEmployeeFromShifts(ShiftEmployeeTable table, Employee employee) {
		ShiftEmployeeTable clone = table.clone();
		clone.putAll(table);
		for (Entry<Shift, Employee[]> entry : clone.entrySet()) {
			Employee[] employees = entry.getValue();
			if (Utils.arrayContainsValue(employees, employee)) {
				employees = (Employee[]) Utils.removeValueFromArray(employees, employee);
				table.setPotentialEmployees(entry.getKey(), employees);
			}
		}
	}

	/**
	 * Generates a HashMap that has a key value of Shift and an entry value of a
	 * list of employees. This determines the compatibility of shifts and what
	 * employees can work them. Determined by employee availability and job position
	 * 
	 * @param shifts    collection of shifts that needs to be filled
	 * @param employees all available employees to check
	 * @return a HashMap of shift, employee compatibility
	 */
	private ShiftEmployeeTable getShiftEmployeesTable(List<Shift> shifts, Employee[] employees) {
		HashMap<Shift, Employee[]> shiftEmployeesMap = new HashMap<>();

		for (Shift shift : shifts) {
			shiftEmployeesMap.put(shift, new Employee[employees.length]);
			for (Employee employee : employees) {
				if (!employee.getAvailableShifts().contains(shift))
					continue;
				if (!employee.getPositions().contains(shift.getPosition())) {
					continue;
				}
				// If employee is available during this shift and are able to perform the job,
				// mark them for compatibility
				Employee[] value = shiftEmployeesMap.get(shift);
				Employee[] temp = new Employee[value.length + 1];
				// Add all employees from value to temp
				for (int i = 0; i < value.length; i++) {
					temp[i] = value[i];
				}
				// Add employee to temp
				temp[value.length] = employee;
				// Insert temp as the new array
				shiftEmployeesMap.put(shift, temp);
			}
		}
		return new ShiftEmployeeTable(shiftEmployeesMap);
	}

	/**
	 * Sorts array of employees, prioritizing those with the least availabilities
	 * 
	 * @param employees the array of employees to sort
	 * @return an array of employees that is sorted by the ones with the least
	 *         availability first
	 */
	private Employee[] sortByAvailability(Employee[] employees) {
		// Put into HashMap to be sorted
		HashMap<Employee, Integer> hm = new HashMap<>();
		for (Employee employee : employees) {
			hm.put(employee, employee.getAvailableShifts().size());
		}
		// Sort HashMap
		HashMap<Employee, Integer> sortedMap = Utils.sortByValue(hm);

		// Implement into new array in sorted order
		Employee[] arr = new Employee[employees.length];
		int index = 0;
		for (Employee e : sortedMap.keySet()) {
			arr[index] = e;
			index++;
		}
		return arr;
	}
}