package me.darrionat.schedulemaster;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScheduleMaster {

	public static void main(String[] args) {

		// GOAL
		// Set<HashMap<Integer, char>>
		HashMap<Integer, char[]> table = new HashMap<>();
//		intCharMap.put(1, new char[] { 'B', 'B', 'C' });
//		intCharMap.put(2, new char[] { 'A', 'E', 'C' });
//		intCharMap.put(3, new char[] { 'F', 'D', 'E' });
//		intCharMap.put(4, new char[] { 'X', 'A', 'E' });
//		intCharMap.put(5, new char[] { 'C', 'A', 'E' });
//		intCharMap.put(6, new char[] { 'A', 'B', 'E' });
//		intCharMap.put(7, new char[] { 'C', 'A', 'I' });
//		intCharMap.put(8, new char[] { 'H', 'J', 'E' });
//		intCharMap.put(9, new char[] { 'C', 'A', 'C' });
//		intCharMap.put(10, new char[] { 'P', 'A', 'J' });
//		intCharMap.put(11, new char[] { 'C', 'Z', 'Y' });

		// prints 50 random characters from alphabet
		ScheduleMaster scheduleMaster = new ScheduleMaster();

		// Usage
		Set<char[]> permutations = scheduleMaster.getPermutations(table);

		// Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		// bootstrapper.initialize(new ScheduleMaster());
		new ScheduleMaster().createWindow();
	}

	private Set<char[]> permutations;

	public ScheduleMaster() {
		permutations = new HashSet<>();
	}

	/**
	 * Generates a set of all permutations of a given input table Each entry into
	 * the @param slotFillMap is considered a row within a table. The key of the map
	 * is a slot, and the value are the potential values for that slot.
	 * 
	 * @param table the table to generate permutations for
	 * @return all permutations of the table's columns, indexed in sequential order
	 *         by row
	 */
	public Set<char[]> getPermutations(HashMap<Integer, char[]> table) {
		return getPermutations(table, 1, null);

	}

	/**
	 * A recursive method to return all possible permutations for a given table Both
	 * parameters are used within the method for recursion and should be given the
	 * initial as defined below.
	 * 
	 * @param table the table to define all permutations for
	 * @param depth the row to work with. Initial value should be 1
	 * @param array the array called in recursion that will be added to the final
	 *              Set that will be returned. Initial value when should be null
	 * @return the set containing all possible permutations of the table's columns
	 */
	private Set<char[]> getPermutations(HashMap<Integer, char[]> table, int tableRow, char[] array) {
		if (array == null) {
			permutations.clear();
			array = new char[table.keySet().size()];
		}
		// Replace 'x' with null
		// System.out.println(array);
		for (char choice : getPossibleChoices(table, tableRow)) {
			char[] arr = array.clone();
			arr[tableRow - 1] = choice;
			if (tableRow + 1 > table.keySet().size()) {
				permutations.add(arr);
				continue;
			}
			getPermutations(table, tableRow + 1, arr);
		}
		if (tableRow == 1) {
			removeRepeatedValuePermutations();
		}
		return permutations;
	}

	/**
	 * Removes the permutations that contain a repeated value
	 */
	public void removeRepeatedValuePermutations() {
		Set<char[]> removeSet = new HashSet<>();
		for (char[] arr : permutations) {
			if (arrayContainsDuplicates(arr)) {
				removeSet.add(arr);
			}
		}
		for (char[] arr : removeSet) {
			permutations.remove(arr);
		}
	}

	/**
	 * Returns true if an array has duplicate values
	 * 
	 * @param arr the array that is being checked for duplicates
	 */
	public boolean arrayContainsDuplicates(char[] arr) {
		Set<Character> set = new HashSet<>();
		for (char c : arr)
			set.add(c);
		return arr.length != set.size();
	}

	/**
	 * Fetches all possible values of the row below the current row
	 * 
	 * @param table    the table that contains the rows
	 * @param tableRow the row of which is being
	 * @return the potential children of the tableRow above
	 */
	public char[] getPossibleChoices(HashMap<Integer, char[]> table, int tableRow) {
		if (tableRow > table.keySet().size()) {
			return null;
		}
		int firstSlot = (int) table.keySet().toArray()[tableRow - 1];
		char[] allPossible = table.get(firstSlot);
		return allPossible;
	}

	@SuppressWarnings("unused")
	private void createWindow() {
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel("I'm a label in the window", SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(500, 100));

		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		// Display the window
		frame.setLocationRelativeTo(null);

		// iconURL is null when not found
		ImageIcon icon = new ImageIcon("res/icon.png");
		frame.setIconImage(icon.getImage());
		// ImageIcon icon = new ImageIcon("res/icon.png");
		// frame.setIconImage(ImageIO.read(new File("res/icon.png")));

		frame.pack();
		frame.setVisible(true);

		JPanel mainPanel = new JPanel();
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO:
			}
		});
		// add mainPanel to the JFrame...
	}
}