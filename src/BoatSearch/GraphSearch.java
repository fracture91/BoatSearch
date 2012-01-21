package BoatSearch;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

/**
 * Finds a path to a goal state from the initial state.
 */
public class GraphSearch {
	
	private final SearchNode root;
	private SearchNode current;
	private final Frontier frontier;
	private Set<SearchNode> explored;
	private Solution solution;
	
	/**
	 * Create a GraphSearch instance with the given initial State and Frontier
	 * @param initialState The initial state of the problem
	 * @param frontier The Frontier to use when searching.
	 * 			You can use frontiers that behave differently to use BFS, DFS, or A* search.
	 */
	public GraphSearch(State initialState, Frontier frontier) {
		root = new SearchNode(initialState, 0);
		this.frontier = frontier;
	}
	
	//breadth first for now - LIFO
	/**
	 * Based on the GRAPH-SEARCH algorithm in the book (Russell p. 77)
	 * @return A Solution containing the path to a goal state,
	 * or null if not found.
	 */
	public Solution findSolution() {
		solution = new Solution();
		frontier.reset();
		
		frontier.add(root);
		explored = new HashSet<SearchNode>();
		
		while(!frontier.isEmpty()) {
			current = frontier.getNext();
			
			if(current.getState().isGoal()) {
				TreeNode[] path = current.getPath();
				List<State> states = new ArrayList<State>();
				for(TreeNode i : path) {
					states.add( ((SearchNode) i).getState() );
				}
				solution.setStates(states);
				solution.setTotalTime(current.getTotalCost());
				return solution;
			}
			
			explored.add(current);
			current.expand();
			solution.setTotalExpansions(solution.getTotalExpansions() + 1);
			
			for(@SuppressWarnings("unchecked")
					Enumeration<SearchNode> e = current.children(); e.hasMoreElements();) {
				SearchNode i = e.nextElement();
				if(!explored.contains(i)) {
					frontier.add(i);
				}
			}
		}
		
		return null;
	}
}
