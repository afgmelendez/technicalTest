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
import com.s4n.technicalTest.app.utils.Utils;

public class Processor implements Runnable {


	private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
	private List<String> allMovements;
	private int min;
	private int max;
	private String threadName;
	private FileDAO fileDAO;
	public static final String OUTPUT_PATH = Config.getPropertie.apply("files.output.path");


	public Processor(List<String> allMovements, int min, int max, String threadName,FileDAO fileDAO) {
		this.allMovements = allMovements;
		this.min = min;
		this.max = max;
		this.threadName = threadName;
		this.fileDAO = fileDAO;
	}

	@Override
	public void run() {
		LOGGER.trace(Utils.INSIDE_METHOD);
		Thread.currentThread().setName(threadName);
		List<String> ordersPerDrone = allMovements.subList(min, max);
		CardinalLinkedList cardinalPoints = new CardinalLinkedList();
		DronService dron = new DronServiceImpl(cardinalPoints);
		ordersPerDrone.forEach(movementsList -> {
			List<String> movements = Arrays.asList(movementsList.split("(?!^)"));
			processDron(movements, dron);
		});
		LOGGER.trace(Utils.FINISHING_METHOD);
	}
	
	private String getFileName() {
		return OUTPUT_PATH + Thread.currentThread().getName()+Utils.TXT;
	}

	private void processDron(List<String> movements, DronService dron) {
		movements.forEach(movement -> dron.moveDron(movement));
		String dronDeliveryAddress = dron.printDeliveryAddress();
		LOGGER.debug("Dron finished at ---> {}", dronDeliveryAddress);
		fileDAO.appendToFile(getFileName(), dronDeliveryAddress.concat("\n"));
	}

}
