package me.darrionat.schedulemaster.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import me.darrionat.schedulemaster.Employee;
import me.darrionat.schedulemaster.Position;
import me.darrionat.schedulemaster.Shift;

public class EmployeeRepository {

	private FileRepository fileRepository;

	public EmployeeRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	// Property Keys
	private final String name = "name";
	private final String positions = "positions";
	private final String availableShifts = "availableShifts";
	// End of Property Keys

	/**
	 * Creates a .properties file within the `Schedule Master\employees` directory
	 * of the defined employee
	 * 
	 * @param employee The employee being saved
	 * @return If the file creation was done successfully
	 */
	public boolean createEmployeeFile(Employee employee) {
		File path = getEmployeeFile(employee);
		try (OutputStream output = new FileOutputStream(path)) {

			Properties prop = new Properties();

			prop.setProperty(name, employee.getName());
			prop.setProperty(positions, Position.getStringFromList(employee.getPositions()));
			prop.setProperty(availableShifts, Shift.getStringFromList(employee.getAvailableShifts(), false));

			// Save properties to file
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Deletes the .properties file within `Schedule Master\employees` directory of
	 * the defined employee
	 * 
	 * @param employee The employee being removed
	 * @return If the file deletion was done successfully
	 */
	public boolean deleteEmployeeFile(Employee employee) {
		return getEmployeeFile(employee).delete();
	}

	/**
	 * 
	 * @return The path to the employee directory
	 */
	public String getEmployeeDirPath() {
		return fileRepository.getPath() + "\\employees";
	}

	/**
	 * 
	 * @return The file of the employee directory
	 */
	public File getEmployeeDirFile() {
		return new File(getEmployeeDirPath());
	}

	/**
	 * 
	 * @return If the employee directory exists
	 */
	public boolean employeeDirectoryExists() {
		return getEmployeeDirFile().exists();
	}

	/**
	 * Grabs what would be the file name for the employee
	 * 
	 * @param employee the employee to generate the file name for
	 * @return the file name of the employee, including file type
	 */
	public String getEmployeeFileName(Employee employee) {
		return employee.getName().replace(" ", "_") + ".properties";
	}

	/**
	 * Gets the file of the employee. Does not determine if the file exists or not.
	 * 
	 * @param employee the employee object to get the file for
	 * @return the employee's file
	 */
	public File getEmployeeFile(Employee employee) {
		return new File(getEmployeeDirFile() + "\\" + getEmployeeFileName(employee));
	}

	/**
	 * Checks to see if the file for the given employee exists
	 * 
	 * @param employee the employee to grab the file for
	 * @return If the file of the employee exists
	 */
	public boolean employeeFileExists(Employee employee) {
		return getEmployeeFile(employee).exists();
	}

	/**
	 * 
	 * @param filePath The filename is formatted to be have an employee's name, all
	 *                 spaces are made underscores, in a properties file Example:
	 *                 John_Smith.properties Can be obtained from
	 *                 #EmployeeRepository.getEmployeeFileName(Employee)
	 * @return Returns an Employee object containing all information from the file
	 * @throws IOException Thrown when the InputStream fails to close
	 */
	public Employee getEmployeeFromFile(String filePath) throws IOException {
		Properties prop = new Properties();

		InputStream inputStream = new FileInputStream(filePath);

		try {
			prop.load(inputStream);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return null;
		} finally {
			inputStream.close();
		}
		// Get properties
		String name = prop.getProperty(this.name);
		String positions = prop.getProperty(this.positions);
		String availableShifts = prop.getProperty(this.availableShifts);

		Employee employee = new Employee();
		employee.setName(name);
		employee.setPositions(Position.getListFromString(positions));
		employee.setAvailableShifts(Shift.getListFromString(availableShifts));

		return employee;
	}

	/**
	 * Gets an array of all current employees. An Employee object in the array will
	 * be null if there was an error reading its file
	 * 
	 * @return an array of all current employees, the array is empty if there are no
	 *         employees
	 */
	public Employee[] getAllEmployees() {
		File employeeDir = getEmployeeDirFile();

		// Array of all file names and directories
		String[] pathList = employeeDir.list();

		// Array to return, same size as the count of files, 1 for each employee
		Employee[] employees = new Employee[pathList.length];

		// For each pathname in the pathnames array, create an positions object for it
		for (int i = 0; i < pathList.length; i++) {
			String fileName = pathList[i];
			try {
				employees[i] = getEmployeeFromFile(getEmployeeDirPath() + "\\" + fileName);
			} catch (IOException e) {
				System.out.println("IOException: Couldn't read Employee File " + fileName);
				employees[i] = null;
				e.printStackTrace();
			}
		}
		return employees;
	}
}