package BoatSearch;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class GraphSearch {
	/*
	function GRAPH-SEARCH(prohdeml returns a solution. or failure
	initialize the frontier using the initial stale of problem
	initialize the explored set to be empty
	loop do
		if the frontier is empty then return failure
		choose a leaf node and remove it from the frontier
		if the node contains a goal state then return the corresponding solution
		add the node to the explored set
		expand the chosen nude, adding the resulting nodes to the frontier only if not in the frontier or explored set 
	 */
	
	private SearchNode root;
	private SearchNode current;
	private Set<SearchNode> frontier;
	private Set<SearchNode> explored;
	private Solution solution;
	
	
	public GraphSearch(State initialState) {
		this.root = new SearchNode(initialState, 0);
	}
	
	//breadth first for now - LIFO
	public Solution findSolution() {
		solution = new Solution();
		frontier = new LinkedHashSet<SearchNode>();
		frontier.add(this.root);
		explored = new HashSet<SearchNode>();
		
		while(!frontier.isEmpty()) {
			Iterator<SearchNode> it = frontier.iterator();
			current = it.next();
			it.remove();
			
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
