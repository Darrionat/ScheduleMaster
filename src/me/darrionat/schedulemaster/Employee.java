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

	public void addAvailableShift(Shift shift) {
		availableShifts.add(shift);
	}

	public void setAvailableShifts(List<Shift> availableShifts) {
		this.availableShifts = availableShifts;
	}

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

	private boolean positionsContainJob(Shift shift) {
		Position jobPosition = shift.getPosition();
		for (Position position : positions)
			if (position.getJobName().equals(jobPosition.getJobName()))
				return true;
		return false;
	}
}