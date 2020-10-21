package me.darrionat.schedulemaster;

import java.util.HashMap;

// Represents a HashMap<Shift, Employeep[]> Object
public class ShiftEmployeeTable {

	private HashMap<Shift, Employee[]> table;

	public ShiftEmployeeTable(HashMap<Shift, Employee[]> table) {
		this.table = table;
	}

	public HashMap<Shift, Employee[]> getTable() {
		return table;
	}

	public void setPotentialEmployees(Shift shift) {

	}

	public void getPotentialEmployees(Shift shift) {

	}

}