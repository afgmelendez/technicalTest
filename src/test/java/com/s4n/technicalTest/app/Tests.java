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
import com.s4n.technicalTest.app.service.DronService;
import com.s4n.technicalTest.app.service.impl.DronServiceImpl;
import com.s4n.technicalTest.app.utils.LinkedList;



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
	
	@Test
	public void testMoveCardinalPoint() {
		LOGGER.debug("*** MOVE CARDINAL POINT TEST START ***");
		LinkedList cardinalPoints = new LinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		LOGGER.debug("Starting Cardinal Point- {}", cardinalPoints.getCurrent().data);
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point Or - {}", cardinalPoints.getCurrent().data);
		assertThat("Or",is(cardinalPoints.getCurrent().data));
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point S - {}", cardinalPoints.getCurrent().data);
		assertThat("S",is(cardinalPoints.getCurrent().data));
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point Oc - {}", cardinalPoints.getCurrent().data);
		assertThat("Oc",is(cardinalPoints.getCurrent().data));
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point N - {}", cardinalPoints.getCurrent().data);
		assertThat("N",is(cardinalPoints.getCurrent().data));
		LOGGER.debug("It did a whole round!!");
		LOGGER.debug("*** MOVE CARDINAL POINT TEST END ***");
	}

}
