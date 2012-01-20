package BoatSearch;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

public class GraphSearch {
	
	private final SearchNode root;
	private SearchNode current;
	private final Class<? extends Frontier> frontierClass;
	private Frontier frontier;
	private Set<SearchNode> explored;
	private Solution solution;
	
	
	public GraphSearch(State initialState, Class<? extends Frontier> frontierClass) {
		root = new SearchNode(initialState, 0);
		this.frontierClass = frontierClass;
	}
	
	//breadth first for now - LIFO
	/**
	 * Based on the GRAPH-SEARCH algorithm in the book (Russell p. 77)
	 * @return A Solution containing the path to a goal state,
	 * or null if not found.
	 */
	public Solution findSolution() {
		solution = new Solution();
		
		//eugh
		try {
			frontier = frontierClass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
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
