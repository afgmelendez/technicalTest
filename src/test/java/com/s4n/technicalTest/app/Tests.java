package com.s4n.technicalTest.app;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4n.technicalTest.app.DAO.FileDAO;
import com.s4n.technicalTest.app.DAO.impl.FileDAOImpl;



public class Tests {

	private static final Logger LOGGER = LoggerFactory.getLogger(Tests.class);
	
	@Test
	public void testReadFile() {
		LOGGER.debug("*** READ CSV TEST START ***");
		FileDAO fileDAO = new FileDAOImpl();
		List<String> expected = Arrays.asList("AAAAIAA", "DDDAIAD", "AAIADAD");
		LOGGER.debug("ASSERTING");
		LOGGER.debug("EXPECTED: {}", expected);
		List<String> actual = fileDAO.readFile();
		LOGGER.debug("ACTUAL:   {}", actual);
		assertThat(expected, is(actual));
		LOGGER.debug("*** READ CSV TEST END ***");
	}

}
