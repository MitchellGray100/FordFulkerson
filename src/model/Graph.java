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
		graph.get(numOne).edges.put(graph.get(numTwo), new Edge(graph.get(numTwo)));
	}

	public void removeEdge(int numOne, int numTwo) {
		graph.get(numOne).edges.remove(graph.get(numTwo));
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
				if (visited[v] == false && graph.get(u).edges.contains(graph.get(v))
						&& graph.get(u).edges.get(graph.get(v)).flow < graph.get(u).edges.get(graph.get(v)).capacity) {
					// If we find a connection to the sink
					// node, then there is no point in BFS
					// anymore We just have to set its parent
					// and can return true
					if (v == graph.get(1).num) {
						path[v] = u;
						return true;
					}
					queue.add(v);
					path[v] = u;
					visited[v] = true;
				}
			}
		}
		return false;

	}

	public int maxFlow() {
		int u, v;

		// Create a residual graph and fill the residual
		// graph with given capacities in the original graph
		// as residual capacities in residual graph

		// Residual graph where rGraph[i][j] indicates
		// residual capacity of edge from i to j (if there
		// is an edge. If rGraph[i][j] is 0, then there is
		// not)

		int max_flow = 0; // There is no flow initially

		// Augment the flow while there is path from source
		// to sink
		while (bfs(path)) {
			// Find minimum residual capacity of the edhes
			// along the path filled by BFS. Or we can say
			// find the maximum flow through the path found.
			int path_flow = Integer.MAX_VALUE;
			for (v = 1; v != 0; v = path[v]) {
				u = path[v];
				path_flow = (int) Math.min(path_flow,
						graph.get(u).edges.get(graph.get(v)).capacity - graph.get(u).edges.get(graph.get(v)).flow);
			}

			// update residual capacities of the edges and
			// reverse edges along the path
			for (v = 1; v != 0; v = path[v]) {
				u = path[v];
				graph.get(u).edges.get(graph.get(v)).flow += path_flow;
				if (!graph.get(v).edges.contains(graph.get(v))) {
					graph.get(v).edges.put(graph.get(u), new Edge(graph.get(u)));
				}

				graph.get(v).edges.get(graph.get(u)).flow += path_flow;

			}

			// Add path flow to overall flow
			max_flow += path_flow;
		}

		// Return the overall flow
		return max_flow;
	}
}
