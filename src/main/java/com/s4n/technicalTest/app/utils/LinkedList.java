package com.s4n.technicalTest.app.utils;

public class LinkedList {

	// I create the four cardinal point's nodes
	private Node nodeN = new Node(Utils.N);
	private Node nodeS = new Node(Utils.S);
	private Node nodeOr = new Node(Utils.OR);
	private Node nodeOc = new Node(Utils.OC);
	private Node current;

	// I align every node so it has this structure ...Oc,N,Or,S,Oc,N,Or....
	public LinkedList() {
		this.nodeN.next = nodeOr;
		this.nodeN.previous = nodeOc;
		this.nodeOr.next = nodeS;
		this.nodeOr.previous = nodeN;
		this.nodeS.next = nodeOc;
		this.nodeS.previous = nodeOr;
		this.nodeOc.next = nodeN;
		this.nodeOc.previous = nodeS;
		this.current = nodeN;
	}

	/**
	 * @return the current
	 */
	public Node getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Node current) {
		this.current = current;
	}

}
