package controller;

import model.Graph;
import model.Node;

public class ControllerImpl {
	Graph graph = new Graph();

	public Node getNode(int index) {
		return graph.getNode(index);
	}

	public void addNode() {
		graph.addNode();
	}

	public void removeNode(int index) {
		graph.removeNode(index);
	}

	public void addEdge(int numOne, int numTwo, int capacity) {
		graph.addEdge(numOne, numTwo, capacity);
	}

	public void removeEdge(int numOne, int numTwo) {
		graph.removeEdge(numOne, numTwo);
	}
}
