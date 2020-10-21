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

public class Utils {

	// HashMap sorting derived from
	// https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
	public static HashMap<Employee, Integer> sortByValue(HashMap<Employee, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Employee, Integer>> list = new LinkedList<Map.Entry<Employee, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Employee, Integer>>() {
			public int compare(Map.Entry<Employee, Integer> o1, Map.Entry<Employee, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<Employee, Integer> temp = new LinkedHashMap<Employee, Integer>();
		for (Map.Entry<Employee, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	/**
	 * Returns if an array contains a defined value
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
		List<Object> temp = new ArrayList<>();
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