package me.darrionat.schedulemaster.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import me.darrionat.schedulemaster.Position;

/**
 * The PositionRepository is a class which fetches, saves, and edits data for
 * all positions of Schedule Master.
 * 
 * @author Darrion Thornburgh
 */
public class PositionRepository {

	private FileRepository fileRepository;

	public PositionRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	// Property Keys
	private final String name = "name";
	private final String abbreviation = "abbr";
	// End of Property Keys

	/**
	 * 
	 * @return The path to the employee directory
	 */
	public String getPositionDirPath() {
		return fileRepository.getPath() + "\\positions";
	}

	/**
	 * 
	 * @return The file of the employee directory
	 */
	public File getPositionDirFile() {
		return new File(getPositionDirPath());
	}

	/**
	 * 
	 * @return If the employee directory exists
	 */
	public boolean positionDirectoryExists() {
		return getPositionDirFile().exists();
	}

	/**
	 * Grabs what would be the file name for the position, including file type
	 * 
	 * @param position the position to generate the filename for
	 * @return the generated file name of the position
	 */
	public String getPositionFileName(Position position) {
		return position.getJobName().replace(" ", "_") + ".properties";
	}

	/**
	 * Gets the file of the position. Does not determine if the file exists or not.
	 * 
	 * @param position the position the file is being grabbed for
	 * @return the file of position
	 */
	public File getPositionFile(Position position) {
		return new File(getPositionDirFile() + "\\" + getPositionFileName(position));
	}

	/**
	 *
	 * @param position the position in order to check if the file exists
	 * @return If the file of the employee exists
	 */
	public boolean positionFileExists(Position position) {
		return getPositionFile(position).exists();
	}

	/**
	 * Creates a .properties file within the `Schedule Master\positions` folder of
	 * the defined position
	 * 
	 * @param position The position being saved
	 */
	public void createPositionFile(Position position) {
		File path = getPositionFile(position);
		try (OutputStream output = new FileOutputStream(path)) {

			Properties prop = new Properties();

			prop.setProperty(name, position.getJobName());
			prop.setProperty(abbreviation, position.getAbbreviation() + "");

			// Save properties to file
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * 
	 * @param filePath The filename is formatted to be have an position's name, all
	 *                 spaces are made underscores, in a properties file Example:
	 *                 Grill_Cook.properties Can be obtained from
	 *                 #PositionRepository.getPositionFileName(Position)
	 * @return Returns an Position object containing all information from the file
	 * @throws IOException Thrown when the InputStream fails to close
	 */
	public Position getPositionFromFile(String filePath) throws IOException {
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
		String abreviation = prop.getProperty(this.abbreviation);

		Position position = new Position();
		position.setJobName(name);
		position.setAbbreviation(abreviation.charAt(0));

		return position;
	}

	public Position[] getAllPositions() {
		File employeeDir = getPositionDirFile();

		// Array of all file names and directories
		String[] pathList = employeeDir.list();

		// Array to return, same size as the count of files, 1 for each position
		Position[] positions = new Position[pathList.length];

		// For each pathname in the pathnames array, create an position object for it
		for (int i = 0; i < pathList.length; i++) {
			String fileName = pathList[i];
			try {
				positions[i] = getPositionFromFile(getPositionDirPath() + "\\" + fileName);
			} catch (IOException e) {
				System.out.println("IOException: Couldn't read Position File " + fileName);
				positions[i] = null;
				e.printStackTrace();
			}
		}
		return positions;
	}
}