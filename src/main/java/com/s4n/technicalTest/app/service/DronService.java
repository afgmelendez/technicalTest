package com.s4n.technicalTest.app.service;

/**
 * Interface DronService, it is an interface in charge of making every action the
 * dron is supposed to
 * 
 * @author andresgonzalez
 *
 */
public interface DronService {
	/**
	 * Method in charge of moving the dron based on a movement received
	 * 
	 * @param movement The movement
	 */
	public void moveDron(String movement);

	/**
	 * Method in charge of returning the final dron's coordinates
	 * 
	 * @return The final dron's coordinates
	 */
	public String printDeliveryAddress();

}
