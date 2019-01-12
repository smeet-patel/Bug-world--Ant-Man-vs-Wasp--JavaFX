package BFS_shortest_path;

/**
 * @author Dionysios Athanasopoulos
 *
 */
public class AdjacencyMatrix {

	// Attributes.
	private boolean[][] graph; // boolean pos f or t

	// Constructor.
	/**
	 * Constructor for the graph matrix.
	 * 
	 * @param size count of nodes
	 */
	AdjacencyMatrix(int size) {

		graph = new boolean[size][size];// the row and col depending on the count of nodes
	}

	// Getters.
	boolean[][] getGraph() {

		return graph;
	}

	// Methods.
	/**
	 * Adds the edges both directions.
	 * 
	 * @param node     Current node
	 * @param neighbor neighbor node
	 */
	void addEdge(int node, int neighbor) {

		graph[node][neighbor] = true;// from -to
		graph[neighbor][node] = true;// to - from
	}

	/**
	 * Prints the adjacency matrix
	 */
	void print() {

		for (int i = 0; i < graph.length; ++i) {

			System.out.print(i + ": ");

			for (int j = 0; j < graph[0].length; ++j) {// nested for loop

//				if( graph[ i ][ j ] ) //to show the false as well as true edges
				System.out.print(graph[i][j] + " ");// print f or t as per the 2d matrix
			}

			System.out.println();
		}
	}

	/**
	 * Adds the edges and prints adjacency matrix if class ran.
	 * 
	 * @param args Run
	 * @author Smeet
	 */
	public static void main(String[] args) {

		AdjacencyMatrix obj = new AdjacencyMatrix(10);// size of AdjacencyMatrix
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

		obj.print();// Adjacency list function
	}
}
