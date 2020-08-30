package com.s4n.technicalTest.app;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClass {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainClass.class);
	private static final String VERSION = "0.1";

	public static void main(String[] args) {

		LOGGER.info("******* LAUNCHING S4N TECHNICAL TEST ********");
		String dateInit = new Date().toString();
		LOGGER.info("********** VERSION {} - {} **********", VERSION, dateInit);
		
	}
}
