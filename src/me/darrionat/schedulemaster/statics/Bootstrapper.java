package me.darrionat.schedulemaster.statics;

import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.repositories.EmployeeRepository;
import me.darrionat.schedulemaster.repositories.FileRepository;
import me.darrionat.schedulemaster.repositories.PositionRepository;
import me.darrionat.schedulemaster.repositories.ShiftsRepository;
import me.darrionat.schedulemaster.services.EmployeeService;
import me.darrionat.schedulemaster.services.ScheduleService;

public class Bootstrapper {

	private static Bootstrapper instance;

	// Repositories

	private FileRepository fileRepository;
	private EmployeeRepository employeeRepository;
	private PositionRepository positionRepository;
	private ShiftsRepository shiftsRepository;

	// Services
	private EmployeeService employeeService;
	private ScheduleService scheduleService;

	private Bootstrapper() {
	}

	public void initialize(ScheduleMaster scheduleMaster) {
		fileRepository = new FileRepository();
		employeeRepository = new EmployeeRepository(fileRepository);
		positionRepository = new PositionRepository(fileRepository);
		shiftsRepository = new ShiftsRepository(fileRepository);

		// Services
		employeeService = new EmployeeService(fileRepository, employeeRepository);
		scheduleService = new ScheduleService(shiftsRepository, employeeRepository);

		/*
		 * Position ceo = new Position("CEO"); Position cfo = new Position("CFO"); Shift
		 * shift = new Shift(new Date(), new Date(System.currentTimeMillis() + 1000),
		 * ceo); Shift shift2 = new Shift(new Date(), new
		 * Date(System.currentTimeMillis() + 5000), cfo); List<Position> positions = new
		 * ArrayList<>(); List<Shift> availableShifts = new ArrayList<>();
		 * positions.add(ceo); positions.add(cfo); availableShifts.add(shift);
		 * availableShifts.add(shift2); Employee employee = new Employee("Steve Jobs",
		 * positions, availableShifts); employeeRepository.createEmployeeFile(employee);
		 * shiftsRepository.addRequiredShift(shift);
		 * shiftsRepository.addRequiredShift(shift2);
		 */

		scheduleService.generateSchedule("w");
	}

	public static Bootstrapper getBootstrapper() {
		if (instance == null)
			instance = new Bootstrapper();
		return instance;
	}
}