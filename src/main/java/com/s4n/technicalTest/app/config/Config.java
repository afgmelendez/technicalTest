package com.s4n.technicalTest.app.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4n.technicalTest.app.utils.Utils;

/**
 * Config Class
 * 
 * @author andresgonzalez
 *
 */
public class Config {

	private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

	/**
	 * Since the class only has static methods it is best to make sure it can't be
	 * instantiated
	 */
	private Config() {
	}

	/**
	 * Function in charge of parsing a STring to Integer
	 */
	public static final Function<String, Integer> parseToInt = Integer::parseInt;
	/**
	 * Function In charge of reading a propertie from the properties file
	 */
	public static final UnaryOperator<String> getPropertie = key -> {
		Properties properties = new Properties();
		try (FileInputStream file = new FileInputStream(Utils.PROPERTIES_FILE_PATH);
		    InputStreamReader reader = new InputStreamReader(file, StandardCharsets.UTF_8)) {
			properties.load(reader);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		String value = properties.getProperty(key);
		LOGGER.debug("{}: {}", key, value);
		return value;
	};

}