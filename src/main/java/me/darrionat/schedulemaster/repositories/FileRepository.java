package me.darrionat.schedulemaster.repositories;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 * The FileRepository is a class which provides methods to set up the main
 * directory for Schedule Master.
 * 
 * @author Darrion Thornburgh
 */
public class FileRepository {

	public FileRepository() {
		setup();
	}

	private void setup() {
		if (!directoryExists())
			createDirectory(getPath());
	}

	/**
	 * Checks to see if the directory of the program exists
	 * 
	 * @return if the main directory of Schedule Master exists
	 */
	public boolean directoryExists() {
		return new File(getPath()).exists();
	}

	public String getPath() {
		JFileChooser fr = new JFileChooser();
		FileSystemView fw = fr.getFileSystemView();
		return fw.getDefaultDirectory().getPath() + "\\Schedule Master";
	}

	/*
	 * Creates the directory of the program
	 */
	public void createDirectory(String path) {
		File file = new File(path);
		try {
			file.mkdir();
			file.createNewFile();
		} catch (IOException e) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Could not create" + path + "Full hard drive?", "Critical Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
