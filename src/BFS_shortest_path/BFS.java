package BFS_shortest_path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Using BFS to traverse nodes and find the shortest path.
 *
 * @author Dionysios Athanasopoulos (BFS code)
 * @author Smeet Patel (Shortest Path search)
 * @version 27.09.18 V1.2
 */
public class BFS {
	private Queue<Integer> pathofNodes = new LinkedList<Integer>();
	private static int source = 0;// the start node
	private static int target = 6;// target node
	private static AdjacencyList obj = new AdjacencyList(10);// AdjacencyList with a size of 10
	private Queue<Integer> queue = new LinkedList<Integer>();// Queue for the BFS
	private int countLength = 0;// count of the length to get to start to target
	private ArrayList<Integer>[] graph; // store the neighbors of the node
	private ArrayList<Integer> sameNum; // list which matches the visited nodes and current node neighbors
	private ArrayList<Integer> path = new ArrayList<Integer>();// list of the path for the shortest path search.
	private Scanner sc; // scanner for user input

	/**
	 * Ask the user for start and target inputs.
	 */
	public void ask() {
		sc = new Scanner(System.in);// setting up scanner
		System.out.println("Welcome to BFS shortest Path search program");// Welcome message
		System.out.println("This program will enable the you to choose a start and target point.");// purpose of the
																									// program
		System.out.println("Based on the 2 points the you, this program will search the shortest path.");
		System.out.println();
		// asking for Start and targetNode for the search
		System.out.println("Enter number from 0 to 9 for the start point");
		System.out.println("For example a start node of: 0");
		source = sc.nextInt();
		System.out.println("Enter number from 0 to 9 for the Target point");
		System.out.println("For example a target node of : 6");
		target = sc.nextInt();
	}

	/**
	 * Traverse the edges by BFS logic
	 *
	 * @param sourceNode Start node for BFS
	 * @param obj        Passing Adjacency List size
	 */
	public void traverse(int sourceNode, AdjacencyList obj) {
		if( (obj != null && (sourceNode >= obj.getGraph().length || sourceNode < 0)) || obj == null  ) return;


		boolean[] visited = new boolean[obj.getGraph().length];
		queue.add(sourceNode);
		while (!queue.isEmpty()) { // while queue list is not empty
			int currentNode = queue.remove();

			if (visited[currentNode])
				continue;
			visited[currentNode] = true;
			pathofNodes.add(currentNode);// adding the current node list of BFS path

			graph = obj.getGraph();
			ArrayList<Integer> neighbors = graph[currentNode]; // shows the neighbors of the current node

			for (int i = 0; i < neighbors.size(); ++i) {
				if (!visited[neighbors.get(i)] && !queue.contains(neighbors.get(i)))
					queue.add(neighbors.get(i));
			}
			// System.out.println("Neighbors of " + currentNode + " : " +
			// neighbors.toString()); //Neighbors of current Node
			// System.out.println("Node in the queue currently " + queue.toString()); //Node
			// in the queue currently
		}
	}

	/**
	 * Finds the shortest path.
	 *
	 * @return Return the result to the Results Class
	 */
	public Result reverseFind() {
		if( (graph != null && (target >= graph.length || target < 0)) || graph == null  ) return null;

		int currentNodeReverse = target;// assigning the target node as current node
		sameNum = new ArrayList<>(pathofNodes);// assigning the path of nodes for the search to same number list.
		// System.out.println("Working Backwards from current node " +
		// currentNodeReverse + " valid neighbors are: " + graph[currentNodeReverse]);
		sameNum.retainAll(graph[currentNodeReverse]);// sameNum will only retain neighbor of the next nodes
		countLength++; // counting the length of the shortest path
		// System.out.println("Neighbor of current node going reverse: " + sameNum); //
		// print the next possible node/s
		path.add(currentNodeReverse); // add current valid node as path
		// System.out.println("currentNodeReverse " + currentNodeReverse);

		while ( pathofNodes != null && sameNum.size() > 0 ) { // while the path of nodes is not empty/ null, loop.
			if (sameNum.get(0) == source) { // When the source equals the current value.
				path.add(source); // adding the source to the path
				Collections.reverse(path);
				// reversing the path, for when printed or shown
				/* System.out.println("done"); */ break; // exit the while loop
			}
			pathofNodes.remove(currentNodeReverse); // remove the current node
			currentNodeReverse = sameNum.get(0); // new current node as per path
			// System.out.println("Working Backwards from current node " +
			// currentNodeReverse + " valid neighbor are: " + graph[currentNodeReverse]);
			sameNum = new ArrayList<>(pathofNodes);// assigning the path of nodes for the search to same number list.
			sameNum.retainAll(graph[currentNodeReverse]);// sameNum will only retain neighbor of the next nodes
			// System.out.println("same number " + sameNum); // print the next possible
			// node/s
			countLength++; // add current valid node as path
			path.add(currentNodeReverse); // add current valid node as path
		}
		return new Result(source, target, countLength, path); // Passes the value to the new class
	}

	/**
	 * // no longer needed method which prints out the findings for BFS shortest
	 * path. // See Results Class public void find(int source, int target,
	 * AdjacencyList obj) { Collections.reverse(path); System.out.println("The
	 * shortest path from " + source + " to " + target + " is " + path);
	 * System.out.println("The number of paths required to go from " + source + " to
	 * " + target + " is " + countLength); }
	 */

	/**
	 * inputs the edges
	 */
	public void initalse() {
		// adding the pair of nodes which make a edges (node, neighbor)
		obj.addEdge( 0, 1 );
		obj.addEdge( 0, 2 );
		obj.addEdge( 2, 3 );
		obj.addEdge( 2, 4 );
		obj.addEdge( 3, 5 );
		obj.addEdge( 4, 5 );
		obj.addEdge( 6, 7 );
		obj.addEdge( 6, 8 );
		obj.addEdge( 7, 9 );
		obj.addEdge( 8, 9 );
		// System.out.println(obj.getGraph().length); size of nodes 9
	}

	private void reset() {

		pathofNodes = new LinkedList<Integer>();
		queue = new LinkedList<Integer>();
		countLength = 0;
		path = new ArrayList<Integer>();
	}

	/**
	 * Runs the program
	 *
	 * @param args
	 */
	/*public static void main(String[] args) {
		BFS bfs = new BFS();
		bfs.ask();
		bfs.initalse();// inputs the edges for the program.
		bfs.traverse(source, obj); // using the BFS class to traverse the edges from starting node and adjacency
									// list
		bfs.reverseFind();// once the the target has been found. The path is found in the reverse order.
	}*/

	public static void main(String[] args) {

		BFS bfs = new BFS();
		bfs.initalse();// inputs the edges for the program.


		//Case 1: source node does not exist

			System.out.println( "\nCase 1: Source node does not exist." );
			//TODO: The program does not check if the source and the target nodes do not exist and consequently, the program throws an exception.
			//That's the reason that I added the lines 54 and 87.

			source = 11;
			target = 3;

			bfs.reset();
			bfs.traverse(source, obj);
			bfs.reverseFind();


		//Case 2: null graph

			System.out.println( "\nCase 2: The graph is null." );
			//TODO: Your program does not check if the input graph is null. To this end, I added the conditions in lines 54 and 87.

			bfs.reset();
			bfs.traverse(source, null);
			bfs.reverseFind();


		//Case 3: source node = target node

			System.out.println( "\nCase 3: Path -- and distance = 0 or -1" );
			//TODO: If the source node equals to the target node, then the program throws an exception.
			//To improve that, I added the second condition in line 100.

			source = 3;
			target = 3;

			bfs.reset();
			bfs.traverse(source, obj);
			bfs.reverseFind();


		//Case 4: multiple identical answers

			System.out.println( "\nCase 4: path = 5 3 2 0 1 and distance = 4" );

			source = 1;
			target = 5;

			bfs.reset();
			bfs.traverse(source, obj);
			bfs.reverseFind();


		//Case 5: multiple different answers -- only one the correct

			System.out.println( "\nCase 5: path = 3 2 0 1 and distance = 3" );

			source = 1;
			target = 3;

			bfs.reset();
			bfs.traverse(source, obj);
			bfs.reverseFind();


		//Case 6: nodes without path

			System.out.println( "\nCase 6: path = -- and distance = 0 or -1" );
			//TODO: If a path does not exist, then the program throws an exception.
			//To improve that, I added the second condition in line 100.

			source = 0;
			target = 6;

			bfs.reset();
			bfs.traverse(source, obj);
			bfs.reverseFind();
	}
}