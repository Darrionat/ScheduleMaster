package me.darrionat.schedulemaster.services;

import java.util.HashSet;
import java.util.Set;

import me.darrionat.schedulemaster.Employee;
import me.darrionat.schedulemaster.repositories.EmployeeRepository;
import me.darrionat.schedulemaster.repositories.FileRepository;

/**
 * The EmployeeService is used to manage all employees.
 * 
 * @author Darrion Thornburgh
 */
public class EmployeeService {

	private FileRepository fileRepository;
	private EmployeeRepository employeeRepository;

	public EmployeeService(FileRepository fileRepository, EmployeeRepository employeeRepository) {
		this.fileRepository = fileRepository;
		this.employeeRepository = employeeRepository;
		setup();
	}

	/**
	 * Creates the employee directory if one does not exist
	 */
	private void setup() {
		if (!employeeRepository.employeeDirectoryExists())
			fileRepository.createDirectory(employeeRepository.getEmployeeDirPath());
	}

	public boolean add(Employee employee) {
		return employeeRepository.createEmployeeFile(employee);
	}

	public boolean remove(Employee employee) {
		return employeeRepository.deleteEmployeeFile(employee);
	}

	public boolean exists(Employee employee) {
		return employeeRepository.employeeFileExists(employee);
	}

	public Set<Employee> search(String name) {
		Set<Employee> toReturnEmployees = new HashSet<>();
		for (Employee e : employeeRepository.getAllEmployees()) {
			if (e.getName().contains(name)) {
				toReturnEmployees.add(e);
			}
		}
		return toReturnEmployees;
	}

}