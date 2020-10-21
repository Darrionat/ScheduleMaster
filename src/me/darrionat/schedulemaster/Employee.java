package me.darrionat.schedulemaster;

import java.util.List;

public class Employee {

	private String name;
	// Indexed by preference
	private List<Position> positions;
	private List<Shift> availableShifts;

	public Employee() {

	}

	public Employee(String name, List<Position> positions, List<Shift> availableShifts) {
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

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<Shift> getAvailableShifts() {
		return availableShifts;
	}

	public void setAvailableShifts(List<Shift> availableShifts) {
		this.availableShifts = availableShifts;
	}
}