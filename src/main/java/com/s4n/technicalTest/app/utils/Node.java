package com.s4n.technicalTest.app.utils;

/**
 * Class node for the double linked list
 * 
 * @author andresgonzalez
 *
 */
public class Node {

	private String data;
	private Node next;
	private Node previous;

	/**
	 * Node public constructos which sets the node's data
	 * 
	 * @param data
	 */
	public Node(String data) {
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * @return the previous
	 */
	public Node getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(Node previous) {
		this.previous = previous;
	}

}
