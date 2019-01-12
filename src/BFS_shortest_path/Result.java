package BFS_shortest_path;

import java.util.ArrayList;

/**
 * Result of the shortest path is presented here
 * @author Smeet Patel
 * @version 27.09.18 V1.2
 * @param source Start Node
 * @param target End Node
 * @param countLength Length of the path changes
 * @param path List of the path of the search
 *
 */
/**
 * Uses the values from the BFS to display the finding of the shortest path.
 */
public class Result {
	// Constructor which passes the source, length. These are predetermined by the
	// user.
	// the countLength and dependent on the BFS path which is stored in the array
	// path.
	public Result(int source, int target, int countLength, ArrayList<Integer> path) {
		System.out.println("The shortest path from " + source + " to " + target + " is " + path);
		System.out
				.println("The number of nodes required to go from " + source + " to " + target + " is " + countLength);
	}
}