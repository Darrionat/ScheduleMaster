package me.darrionat.schedulemaster.statics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import me.darrionat.schedulemaster.ScheduleMaster;
import me.darrionat.schedulemaster.listeners.UiButtonHover;
import me.darrionat.schedulemaster.repositories.EmployeeRepository;
import me.darrionat.schedulemaster.repositories.FileRepository;
import me.darrionat.schedulemaster.repositories.PositionRepository;
import me.darrionat.schedulemaster.repositories.ShiftsRepository;
import me.darrionat.schedulemaster.services.EmployeeService;
import me.darrionat.schedulemaster.services.ScheduleService;
import me.darrionat.schedulemaster.services.UiService;
import me.darrionat.schedulemaster.ui.MainMenuUi;

/**
 * The Bootstrapper class is used to initialize all repositories then services
 * which are dependent on these repositories. This sets up the rest of the
 * program to be functional.
 * 
 * @author Darrion Thornburgh
 */
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
	private UiService guiService;

	private Bootstrapper() {
	}

	public void initialize(ScheduleMaster scheduleMaster) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
					new File(ScheduleMaster.RESOURCES_PATH + "anson-regular-webfont.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		fileRepository = new FileRepository();
		employeeRepository = new EmployeeRepository(fileRepository);
		positionRepository = new PositionRepository(fileRepository);
		shiftsRepository = new ShiftsRepository(fileRepository);

		// Services
		employeeService = new EmployeeService(fileRepository, employeeRepository);
		scheduleService = new ScheduleService(shiftsRepository, employeeRepository);

		new UiButtonHover();
		guiService = new UiService();
		guiService.setCurrentContainer(new MainMenuUi(guiService));
	}

	public static Bootstrapper getBootstrapper() {
		if (instance == null)
			instance = new Bootstrapper();
		return instance;
	}
}