package com.s4n.technicalTest.app.DAO;

import java.util.List;


public interface FileDAO {

	public List<String> readFile();
	
	public void appendToFile(String filename, String text);
}
