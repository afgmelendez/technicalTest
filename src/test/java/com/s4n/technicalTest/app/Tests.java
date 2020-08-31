package com.s4n.technicalTest.app;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
import com.s4n.technicalTest.app.utils.CardinalLinkedList;
import com.s4n.technicalTest.app.utils.Utils;

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
		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		LOGGER.debug("Starting Cardinal Point- {}", cardinalPoints.getCurrent().getData());
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point Or - {}", cardinalPoints.getCurrent().getData());
		assertThat(Utils.OR, is(cardinalPoints.getCurrent().getData()));
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point S - {}", cardinalPoints.getCurrent().getData());
		assertThat(Utils.S, is(cardinalPoints.getCurrent().getData()));
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point Oc - {}", cardinalPoints.getCurrent().getData());
		assertThat(Utils.OC, is(cardinalPoints.getCurrent().getData()));
		dron.moveDron("D");
		LOGGER.debug("Cardinal Point N - {}", cardinalPoints.getCurrent().getData());
		assertThat(Utils.N, is(cardinalPoints.getCurrent().getData()));
		LOGGER.debug("It did a whole round!!");
		LOGGER.debug("*** MOVE CARDINAL POINT TEST END ***");
	}

	@Test
	public void firstRouteTest() {
		LOGGER.debug("*** FIRST MOVEMENT TEST START ***");
		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		List<String> movements = Arrays.asList("A", "A", "A", "A", "I", "A", "A");
		movements.forEach(movement -> dron.moveDron(movement));
		assertThat("(-2,4) dirección Occidente", is(dron.printDeliveryAddress()));
		LOGGER.debug("*** FIRST MOVEMENT TEST END ***");
	}

	@Test
	public void firstRouteWrongTest() {
		LOGGER.debug("*** FIRST MOVEMENT WRONG TEST START ***");
		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		List<String> movements = Arrays.asList("A", "A", "A", "A", "I", "A", "A");
		movements.forEach(movement -> dron.moveDron(movement));
		assertThat("(-2,4) dirección Norte", is(not(dron.printDeliveryAddress())));
		LOGGER.debug("*** FIRST MOVEMENT WRONG TEST END ***");
	}

	@Test
	public void secondRouteTest() {
		LOGGER.debug("*** SECOND MOVEMENT TEST START ***");

		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		List<String> movements = Arrays.asList("A", "A", "A", "A", "I", "A", "A", "D", "D", "D", "A", "I", "A", "D");
		movements.forEach(movement -> dron.moveDron(movement));
		assertThat("(-1,3) dirección Sur", is(dron.printDeliveryAddress()));
		LOGGER.debug("*** SECOND MOVEMENT TEST END ***");
	}

	@Test
	public void secondRouteWrongTest() {
		LOGGER.debug("*** SECOND MOVEMENT WRONG TEST START ***");

		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		List<String> movements = Arrays.asList("A", "A", "A", "A", "I", "A", "A", "D", "D", "D", "A", "I", "A", "D");
		movements.forEach(movement -> dron.moveDron(movement));
		assertThat("(-3,3) dirección Sur", is(not(dron.printDeliveryAddress())));
		LOGGER.debug("*** SECOND MOVEMENT WRONG TEST END ***");
	}

	@Test
	public void thirdRouteTest() {
		LOGGER.debug("*** THIRD MOVEMENT TEST START ***");
		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		List<String> movements = Arrays.asList("A", "A", "A", "A", "I", "A", "A", "D", "D", "D", "A", "I", "A", "D", "A",
		    "A", "I", "A", "D", "A", "D");
		movements.forEach(movement -> dron.moveDron(movement));
		assertThat("(0,0) dirección Occidente", is(dron.printDeliveryAddress()));
		LOGGER.debug("*** THIRD MOVEMENT TEST END ***");
	}

	@Test
	public void thirdRouteWrongTest() {
		LOGGER.debug("*** THIRD MOVEMENT WRONG TEST START ***");

		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		List<String> movements = Arrays.asList("A", "A", "A", "A", "I", "A", "A", "D", "D", "D", "A", "I", "A", "D", "A",
		    "A", "I", "A", "D", "A", "D");
		movements.forEach(movement -> dron.moveDron(movement));
		assertThat("(-4,2) dirección Oriente", is(not(dron.printDeliveryAddress())));
		LOGGER.debug("*** THID MOVEMENT WRONG TEST END ***");
	}

}
