package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Graph;

public class MaxFlowTests {
	@Test
	public void singlePath() {
		Graph graph = new Graph();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addEdge(0, 1, 5);
		assertEquals(5, graph.maxFlow());
	}

	@Test
	public void bottleNeckedPath() {
		Graph graph = new Graph();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addEdge(0, 2, 5);
		graph.addEdge(2, 1, 3);
		assertEquals(3, graph.maxFlow());
	}

	@Test
	public void doublePath() {
		Graph graph = new Graph();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addEdge(0, 2, 5);
		graph.addEdge(2, 1, 3);
		graph.addEdge(0, 1, 3);
		assertEquals(6, graph.maxFlow());
	}

	@Test
	public void triplePathNoBenefit() {
		Graph graph = new Graph();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addEdge(0, 2, 5);
		graph.addEdge(2, 1, 3);
		graph.addEdge(0, 1, 3);
		graph.addEdge(0, 3, 10);
		graph.addEdge(3, 2, 10);
		assertEquals(6, graph.maxFlow());
	}

	@Test
	public void triplePathWithBenefit() {
		Graph graph = new Graph();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addEdge(0, 2, 5);
		graph.addEdge(2, 1, 13);
		graph.addEdge(0, 1, 3);
		graph.addEdge(0, 3, 10);
		graph.addEdge(3, 2, 10);
		assertEquals(16, graph.maxFlow());
	}

	@Test
	public void complexProblem() {
		Graph graph = new Graph();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addNode();
		graph.addEdge(0, 2, 10);
		graph.addEdge(2, 3, 4);
		graph.addEdge(2, 5, 9);
		graph.addEdge(5, 1, 10);
		graph.addEdge(5, 6, 15);
		graph.addEdge(2, 6, 15);
		graph.addEdge(6, 1, 10);
		graph.addEdge(6, 7, 15);
		graph.addEdge(0, 3, 5);
		graph.addEdge(3, 4, 4);
		graph.addEdge(3, 6, 8);
		graph.addEdge(0, 4, 15);
		graph.addEdge(4, 7, 16);
		graph.addEdge(7, 3, 6);
		graph.addEdge(7, 1, 10);
		assertEquals(28, graph.maxFlow());
	}

}
