package com.s4n.technicalTest.app.service.impl;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4n.technicalTest.app.service.DronService;
import com.s4n.technicalTest.app.utils.LinkedList;
import com.s4n.technicalTest.app.utils.Utils;

public class DronServiceImpl implements DronService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DronServiceImpl.class);
	private LinkedList cardinalPoints;
	private int x = 0;
	private int y = 0;

	public DronServiceImpl(LinkedList linkedList) {
		this.cardinalPoints = linkedList;
	}

	public void moveDron(String movement) {
		LOGGER.trace(Utils.INSIDE_METHOD);
		moveDron.accept(movement);
		LOGGER.trace(Utils.FINISHING_METHOD);
	}

	Consumer<String> moveAhead = cardinalPoint -> {
		switch (cardinalPoint) {
		case Utils.N:
			x = x + 1;
			break;
		case Utils.OR:
			y = y + 1;
			break;
		case Utils.S:
			x = x - 1;
			break;
		case Utils.OC:
			y = y - 1;
			break;
		default:
			LOGGER.warn(Utils.NOT_A_VALID_OPERATION);
			break;
		}
	};

	Consumer<String> moveDron = movement -> {
		switch (movement) {
		case "D":
			LOGGER.debug("Moving from {} to {}", cardinalPoints.getCurrent().data, cardinalPoints.getCurrent().next.data);
			cardinalPoints.setCurrent(cardinalPoints.getCurrent().next);
			break;
		case "I":
			LOGGER.debug("Moving from {} to {}", cardinalPoints.getCurrent().data, cardinalPoints.getCurrent().previous.data);
			cardinalPoints.setCurrent(cardinalPoints.getCurrent().previous);
			break;
		case "A":
			moveAhead.accept(cardinalPoints.getCurrent().data);
			break;
		default:
			LOGGER.warn(Utils.NOT_A_VALID_OPERATION);
			break;
		}
	};

}
