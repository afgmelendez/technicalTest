package com.s4n.technicalTest.app.processor;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.s4n.technicalTest.app.DAO.FileDAO;
import com.s4n.technicalTest.app.config.Config;
import com.s4n.technicalTest.app.service.DronService;
import com.s4n.technicalTest.app.service.impl.DronServiceImpl;
import com.s4n.technicalTest.app.utils.CardinalLinkedList;

public class Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
	public static final Integer MAX_OPERATION_BLOCKS = Config.getPropertie.andThen(Config.parseToInt)
	    .apply("max.operation.blocks");
	public static final Integer MAX_ORDERS_PER_DRON = Config.getPropertie.andThen(Config.parseToInt)
	    .apply("max.orders.per.drone");
	private FileDAO fileDAO;

	public Processor(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	/**
	 * Constructor for Junit Teste
	 */
	public Processor() {
		
	}

	public void process() {
		List<String> allMovements = fileDAO.readFile();
// Setting up conditions to read the number of lines each drone should do
		int min = 0;
		int max = MAX_ORDERS_PER_DRON;
		int maxLines = (MAX_ORDERS_PER_DRON * 20);
		while (max <= maxLines && max <= allMovements.size()) {
			List<String> ordersPerDrone = allMovements.subList(min, max);
			CardinalLinkedList cardinalPoints = new CardinalLinkedList();
			DronService dron = new DronServiceImpl(cardinalPoints);
			ordersPerDrone.forEach(movementsList -> {
				List<String> movements = Arrays.asList(movementsList.split("(?!^)"));
				processDron(movements, dron);
			});
			min = min + MAX_ORDERS_PER_DRON;
			max = max + MAX_ORDERS_PER_DRON;
		}

	}

	public void processDron(List<String> movements, DronService dron) {
		movements.forEach(movement -> dron.moveDron(movement));
		String dronDeliveryAddress = dron.printDeliveryAddress();
		LOGGER.debug("Dron finished at ---> {}", dronDeliveryAddress);
	}

}
