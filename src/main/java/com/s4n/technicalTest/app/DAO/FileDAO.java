package com.s4n.technicalTest.app.DAO;

import java.util.List;

/**
 * FileDAO interface, it is an interface in charge of doing reading and writing
 * operations on files
 * 
 * @author andresgonzalez
 *
 */
public interface FileDAO {
	/**
	 * Method in charge of reading a file
	 * 
	 * @return Returns every line the file has as a List
	 */
	public List<String> readFile();

	/**
	 * Method in charge of writing text on a file
	 * 
	 * @param filename The file Path including it's name
	 * @param text     The text to write on the file
	 */
	public void appendToFile(String filename, String text);
}
