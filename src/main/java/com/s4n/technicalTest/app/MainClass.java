package com.s4n.technicalTest.app;

import java.util.Date;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4n.technicalTest.app.DAO.FileDAO;
import com.s4n.technicalTest.app.DAO.impl.FileDAOImpl;
import com.s4n.technicalTest.app.config.Config;
import com.s4n.technicalTest.app.processor.Processor;
import com.s4n.technicalTest.app.utils.Utils;

public class MainClass {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainClass.class);
	private static final String VERSION = "0.1";
	public static final Integer MAX_ORDERS_PER_DRON = Config.getPropertie.andThen(Config.parseToInt)
	    .apply("max.orders.per.drone");

	private static Integer threadPoolMaxThreads = Config.getPropertie.andThen(Config.parseToInt)
	    .apply("thread.pool.max.threads");

	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0, threadPoolMaxThreads, 0, TimeUnit.SECONDS,
	    new SynchronousQueue<Runnable>());

	public static void main(String[] args) {

		LOGGER.info("******* LAUNCHING S4N TECHNICAL TEST ********");
		String dateInit = new Date().toString();
		LOGGER.info("********** VERSION {} - {} **********", VERSION, dateInit);
		// Setting up conditions to read the number of lines each drone should read
		int min = 0;
		int max = MAX_ORDERS_PER_DRON;
		int maxLines = (MAX_ORDERS_PER_DRON * 20);
		int dronNumber = 1;
		FileDAO fileDAO = new FileDAOImpl();
		List<String> allMovements = fileDAO.readFile();
		while (max <= maxLines && max <= allMovements.size()) {
			String threadName = Utils.FILENAME + String.valueOf(dronNumber);
			Processor processor = new Processor(allMovements, min, max, threadName,fileDAO);
			threadPool.submit(processor);
			min = min + MAX_ORDERS_PER_DRON;
			max = max + MAX_ORDERS_PER_DRON;
			dronNumber++;
		}

	}
}
