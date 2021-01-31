package me.darrionat.schedulemaster.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.darrionat.schedulemaster.Employee;

/**
 * The Utils class contains methods that are more abstract and can be used in
 * more than one class; therefore, all methods within the Utils class are
 * static.
 * 
 * @author Darrion Thornburgh
 */
public class Utils {

	/**
	 * Constrains an integer into a range
	 * 
	 * @param x   the integer being constrained
	 * @param min minimum value
	 * @param max maximum value
	 * @return the integer constrained within the minimum and maximum value
	 */
	public static int constrain(int x, int min, int max) {
		if (x < min) {
			x = min;
		} else if (x > max) {
			x = max;
		}
		return x;
	}

	/**
	 * Returns a HashMap that is sorted by the value. This is utilized in order to
	 * prioritize employees by how often they are available, putting those who have
	 * the least amount of availability first. HashMap sorting derived from:
	 * https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
	 * 
	 * @param hm the map being sorted by value
	 * @return a HashMap of employees with the least amount of available shifts
	 *         being moved to first in the map
	 */
	public static HashMap<Employee, Integer> sortByValue(HashMap<Employee, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Employee, Integer>> list = new LinkedList<Map.Entry<Employee, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Employee, Integer>>() {
			public int compare(Map.Entry<Employee, Integer> o1, Map.Entry<Employee, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Put data from sorted list to HashMap
		HashMap<Employee, Integer> temp = new LinkedHashMap<Employee, Integer>();
		for (Map.Entry<Employee, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	/**
	 * Searches an array and indicates if that array contains a particular value
	 * 
	 * @param arr   the array to search through
	 * @param value the value that is being searched for
	 * @return returns if an array contains a defined value
	 */
	public static boolean arrayContainsValue(Object[] arr, Object value) {
		for (Object object : arr) {
			if (object.equals(value))
				return true;
		}
		return false;
	}

	/**
	 * Removes a value from an array
	 * 
	 * @param arr       the array the object is being removed form
	 * @param removeObj the object to remove
	 * @return an array with an object removed
	 */
	public static Object[] removeValueFromArray(Object[] arr, Object removeObj) {
		List<Object> temp = new ArrayList<Object>();
		for (Object object : arr) {
			if (!removeObj.equals(object))
				temp.add(object);
		}
		Object[] newArr = new Object[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			newArr[i] = temp.get(i);
		}
		return newArr;
	}
}