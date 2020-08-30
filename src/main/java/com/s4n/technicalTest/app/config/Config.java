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

public class Config {

	private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

	private Config() {
	}

	public static final Function<String, Integer> parseToInt = Integer::parseInt;
	
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