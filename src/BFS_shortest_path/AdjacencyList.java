package BFS_shortest_path;

import java.util.ArrayList;

/**
 * 
 * @author Dionysios Athanasopoulos
 *
 */
public class AdjacencyList {

	// Attributes.
	ArrayList<Integer>[] graph;

	// Constructor.
	/**
	 * Creates new array list with size
	 * @param size Number of nodes in the array list
	 */
	@SuppressWarnings("unchecked")
	AdjacencyList(int size) {

		graph = new ArrayList[size];
	}

	// Getters.
	/**
	 * 
	 * @return graph
	 */
	ArrayList<Integer>[] getGraph() {

		return graph;
	}

	// Methods.
	/**
	 * Add neighbor to the list of neighbors
	 * @param node     Current node
	 * @param neighbor neighbor node
	 */
	void addEdgeInternally(int node, int neighbor) {

		ArrayList<Integer> neighbors = graph[node];

		if (neighbors == null)// when neighbor list is null make the array list
			neighbors = new ArrayList<Integer>();

		neighbors.add(neighbor); // add the neighbor to the list of neighbor

		graph[node] = neighbors; //making the graph node as neighbors 

	}

	/**
	 * Adds the edges. 
	 * @param node     Current node
	 * @param neighbor neighbor node
	 */
	void addEdge(int node, int neighbor) {

		addEdgeInternally(node, neighbor);// from - to
		addEdgeInternally(neighbor, node);// to - from
	}
	/**
	 * Prints the adjacency list
	 */
	void print() {

		for (int i = 0; i < graph.length; ++i) {

			ArrayList<Integer> neighbors = graph[i];

			System.out.print(i + ": "); // node value

			for (int j = 0; j < neighbors.size(); ++j)
				System.out.print(neighbors.get(j) + " ");// current node value neighbor

			System.out.println();
		}
	}

	/**
	 * Adds the edges and prints adjacency list if class ran.
	 * 
	 * @param args
	 * @author Smeet
	 */
	public static void main(String[] args) {

		AdjacencyList obj = new AdjacencyList(10);// size of AdjacencyMatrix
		// adding the pair of nodes which make a edges (node, neighbor)
		obj.addEdge(0, 1);
		obj.addEdge(0, 3);
		obj.addEdge(0, 8);
		obj.addEdge(1, 7);
		obj.addEdge(2, 3);
		obj.addEdge(2, 7);
		obj.addEdge(2, 5);
		obj.addEdge(3, 4);
		obj.addEdge(4, 8);
		obj.addEdge(4, 9);
		obj.addEdge(5, 6);
		obj.addEdge(5, 9);

		obj.print();
	}
}
