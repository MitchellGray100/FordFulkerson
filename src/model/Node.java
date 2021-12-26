package model;

import java.util.HashSet;

public class Node {
	int num;
	HashSet<Edge> edges;

	public Node(int num) {
		this.num = num;
		edges = new HashSet<Edge>();
	}
}
