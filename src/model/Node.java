package model;

import java.util.Hashtable;

public class Node {
	int num;
	Hashtable<Node, Edge> edges;

	public Node(int num) {
		this.num = num;
		edges = new Hashtable<Node, Edge>();
	}
}
