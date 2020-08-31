package com.s4n.technicalTest.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4n.technicalTest.app.config.Config;
import com.s4n.technicalTest.app.service.DronService;
import com.s4n.technicalTest.app.utils.CardinalLinkedList;
import com.s4n.technicalTest.app.utils.Utils;

public class DronServiceImpl implements DronService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DronServiceImpl.class);
	public static final Integer MAX_OPERATION_BLOCKS = Config.getPropertie.andThen(Config.parseToInt)
	    .apply("max.operation.blocks");
	private CardinalLinkedList cardinalPoints;
	private int x = 0;
	private int y = 0;

	public DronServiceImpl(CardinalLinkedList cardinalLinkedList) {
		this.cardinalPoints = cardinalLinkedList;
	}

	public void moveDron(String movement) {
		LOGGER.trace(Utils.INSIDE_METHOD);
		switch (movement) {
		case Utils.D:
			LOGGER.debug(Utils.MOVING_FROM_TO, cardinalPoints.getCurrent().getData(),
			    cardinalPoints.getCurrent().getNext().getData());
			cardinalPoints.setCurrent(cardinalPoints.getCurrent().getNext());
			break;
		case Utils.I:
			LOGGER.debug(Utils.MOVING_FROM_TO, cardinalPoints.getCurrent().getData(),
			    cardinalPoints.getCurrent().getPrevious().getData());
			cardinalPoints.setCurrent(cardinalPoints.getCurrent().getPrevious());
			break;
		case Utils.A:
			moveAhead(cardinalPoints.getCurrent().getData());
			break;
		default:
			LOGGER.warn(Utils.NOT_A_VALID_OPERATION);
			break;
		}
		LOGGER.trace(Utils.FINISHING_METHOD);
	}

	public void moveAhead(String cardinalPoint) {
		switch (cardinalPoint) {
		case Utils.N:
			LOGGER.debug(Utils.MOVING_1_STEP_AHEAD_ON_DIRECTION, Utils.N);
			y = y + 1;
			break;
		case Utils.OR:
			LOGGER.debug(Utils.MOVING_1_STEP_AHEAD_ON_DIRECTION, Utils.OR);
			x = x + 1;
			break;
		case Utils.S:
			LOGGER.debug(Utils.MOVING_1_STEP_AHEAD_ON_DIRECTION, Utils.S);
			y = y - 1;
			break;
		case Utils.OC:
			LOGGER.debug(Utils.MOVING_1_STEP_AHEAD_ON_DIRECTION, Utils.OC);
			x = x - 1;
			break;
		default:
			LOGGER.warn(Utils.NOT_A_VALID_OPERATION);
			break;
		}
	}

	public String printDeliveryAddress() {
		String response;
		if (x > MAX_OPERATION_BLOCKS || y > MAX_OPERATION_BLOCKS) {
			response = "Delivery is outside of our delivery range, returning to the restaurant";
		} else {
			response = String.format("(%d,%d) dirección %s", x, y, cardinalPoints.getCurrent().getData());
		}
		return response;
	}

}
