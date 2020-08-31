package com.s4n.technicalTest.app.DAO.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.s4n.technicalTest.app.DAO.FileDAO;
import com.s4n.technicalTest.app.config.Config;
import com.s4n.technicalTest.app.utils.Utils;

public class FileDAOImpl implements FileDAO {

	

	private static final Logger LOGGER = LoggerFactory.getLogger(FileDAOImpl.class);

	public static final String FILES_PATH = Config.getPropertie.apply("files.path");


	@Override
	public List<String> readFile() {
		List<String> lines = new ArrayList<>();
		try (InputStream inputFS = java.nio.file.Files.newInputStream(Paths.get(FILES_PATH));
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));) {

			lines = br.lines().skip(0).collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error(Utils.EXCEPTION, e);
		}
		return lines;
	}
	
	public void appendToFile(String filename, String text) {
		LOGGER.trace(Utils.INSIDE_METHOD);
		String header = Utils.REPORTE_DE_ENTREGAS.concat("\n");
		try {
			LOGGER.debug(Utils.CREATING_FILE, filename);
			File file = new File(filename);
			Boolean isNewFile = file.createNewFile();
			if (Boolean.TRUE.equals(isNewFile)) {
				Files.write(Paths.get(filename), header.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
			}else {
				LOGGER.debug(Utils.FILE_ALREADY_EXISTS);
			}
			Files.write(Paths.get(filename), text.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (Exception e) {
			LOGGER.error(Utils.EXCEPTION, e);
		}
		LOGGER.trace(Utils.FINISHING_METHOD);
	}
	
}
