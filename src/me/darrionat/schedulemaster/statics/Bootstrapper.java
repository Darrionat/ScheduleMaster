package me.darrionat.schedulemaster.statics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.darrionat.schedulemaster.Employee;
import me.darrionat.schedulemaster.Position;
import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.Shift;
import me.darrionat.schedulemaster.repositories.EmployeeRepository;
import me.darrionat.schedulemaster.repositories.FileRepository;
import me.darrionat.schedulemaster.services.EmployeeService;

public class Bootstrapper {

	private static Bootstrapper instance;

	// Repositories

	private FileRepository fileRepository;
	private EmployeeRepository employeeRepository;
	// Services
	@SuppressWarnings("unused")
	private EmployeeService employeeService;

	private Bootstrapper() {
	}

	public void initialize(ScheduleMaster scheduleMaster) {
		fileRepository = new FileRepository();
		employeeRepository = new EmployeeRepository(fileRepository);

		List<Position> positions = new ArrayList<>();
		positions.add(new Position("CEO"));
		positions.add(new Position("CFO"));
		List<Shift> availableShifts = new ArrayList<>();
		availableShifts.add(new Shift(new Date(), new Date(System.currentTimeMillis() + 1000), null));
		availableShifts.add(new Shift(new Date(), new Date(System.currentTimeMillis() + 5000), null));
		Employee employee = new Employee("Steve Jobs", positions, availableShifts);
		employeeRepository.createEmployeeFile(employee);

		for (Employee e : employeeRepository.getAllEmployees()) {
			System.out.println(e.getName());
			System.out.println(e.getAvailableShifts().get(0).getStart());
			System.out.println(e.getAvailableShifts().get(0).getEnd());
			System.out.println(e.getAvailableShifts().get(1).getStart());
			System.out.println(e.getAvailableShifts().get(1).getEnd());
			System.out.println(e.getPositions());
		}
		// Services
		employeeService = new EmployeeService(fileRepository, employeeRepository);
	}

	public static Bootstrapper getBootstrapper() {
		if (instance == null)
			instance = new Bootstrapper();
		return instance;
	}
}