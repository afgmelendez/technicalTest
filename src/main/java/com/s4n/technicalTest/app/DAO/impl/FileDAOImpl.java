package com.s4n.technicalTest.app.DAO.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.s4n.technicalTest.app.DAO.FileDAO;
import com.s4n.technicalTest.app.config.Config;
import com.s4n.technicalTest.app.utils.Utils;

public class FileDAOImpl implements FileDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileDAOImpl.class);

//	public static final Integer MAX_OPERATION_BLOCKS = Config.getPropertie.andThen(Config.parseToInt)
//	    .apply("max.operation.blocks");
//	public static final Integer MAX_ORDERS_PER_DRON = Config.getPropertie.andThen(Config.parseToInt)
//	    .apply("max.orders.per.drone");
	public static final String FILES_PATH = Config.getPropertie.apply("files.path");

	@Override
	public List<String> readFile() {
		return readCSV.apply(Paths.get(FILES_PATH));
	}

	private Function<Path, List<String>> readCSV = path -> {
		List<String> lines = new ArrayList<>();
		try (InputStream inputFS = java.nio.file.Files.newInputStream(path);
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));) {

			lines = br.lines().skip(0).collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error(Utils.EXCEPTION, e);
		}
		return lines;
	};
}
