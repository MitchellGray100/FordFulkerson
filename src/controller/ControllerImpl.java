package controller;

import java.util.Hashtable;

import model.Edge;
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

	public int getCurrentNode() {
		return graph.getCurrentNode();
	}

	public Hashtable<Node, Edge> getEdgesOFNode(int index) {
		return graph.getEdgesOfNode(index);
	}

	public int getCapacityOfEdge(int num1, int num2) {
		return graph.getCapacityOfEdge(num1, num2);
	}

	public int maxFlow() {
		return graph.maxFlow();
	}
}
