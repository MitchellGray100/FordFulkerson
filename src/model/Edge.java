package model;

public class Edge {
	Node node;
	int capacity;
	int flow;

	public Edge(Node node, int capacity) {
		this.capacity = capacity;
		this.node = node;
	}

	public Edge(Node node) {
		this.node = node;
	}
}
