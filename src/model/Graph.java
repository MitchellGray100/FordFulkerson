package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	ArrayList<Node> graph;
	int currentNode;
	int[] path;

	public Graph() {
		graph = new ArrayList<Node>();
		graph.add(new Node(0));
		graph.add(new Node(1));
		currentNode = 1;
	}

	public void addNode() {
		currentNode++;
		graph.add(new Node(currentNode));
	}

	public void removeNode(int index) {
		currentNode--;
		graph.remove(index);
		reMap();
	}

	public void addEdge(int numOne, int numTwo) {
		graph.get(numOne).edges.add(new Edge(graph.get(numTwo)));
	}

	public void removeEdge(int numOne, int numTwo) {
		graph.get(numOne).edges.remove(new Edge(graph.get(numTwo)));
	}

	public void reMap() {
		for (int i = 2; i <= currentNode; i++) {
			graph.get(i).num--;
		}
	}

	public boolean bfs(int[] path) {
		boolean visited[] = new boolean[graph.size()];

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		visited[0] = true;
		path[0] = -1;

		while (queue.size() != 0) {
			int u = queue.poll();

			for (int v = 0; v < graph.size(); v++) {

			}
		}
		return false;

	}

	public int maxFlow() {
		return 0;
	}
}
