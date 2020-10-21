package me.darrionat.schedulemaster;

import java.util.HashMap;
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

	public ShiftEmployeeTable(HashMap<Shift, Employee[]> table) {
		this.table = table;
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

	public ShiftEmployeeTable clone() {
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

}