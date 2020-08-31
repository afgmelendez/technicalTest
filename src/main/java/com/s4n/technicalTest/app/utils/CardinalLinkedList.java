package com.s4n.technicalTest.app.utils;

public class CardinalLinkedList {

	// I create the four cardinal point's nodes
	private Node nodeN = new Node(Utils.N);
	private Node nodeS = new Node(Utils.S);
	private Node nodeOr = new Node(Utils.OR);
	private Node nodeOc = new Node(Utils.OC);
	private Node current;

	// I align every node so it has this structure ...Oc,N,Or,S,Oc,N,Or....
	public CardinalLinkedList() {
		this.nodeN.setNext(nodeOr);
		this.nodeN.setPrevious(nodeOc);
		this.nodeOr.setNext(nodeS);
		this.nodeOr.setPrevious(nodeN);
		this.nodeS.setNext(nodeOc);
		this.nodeS.setPrevious(nodeOr);
		this.nodeOc.setNext(nodeN);
		this.nodeOc.setPrevious(nodeS);
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
