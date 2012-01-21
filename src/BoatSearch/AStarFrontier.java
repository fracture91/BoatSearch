package BoatSearch;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A frontier sorted by f(n) = g(n) + h(n), where g(n) is the cost to reach node n
 * and h(n) is the estimated cost of reaching the goal state from node n.
 * Always gives you the next cheapest node in the frontier.
 */
public class AStarFrontier implements Frontier {

	private SortedSet<SearchNode> contents;
	
	public AStarFrontier() {
		reset();
	}

	@Override
	public boolean isEmpty() {
		return contents.isEmpty();
	}

	@Override
	public boolean add(SearchNode node) {
		return contents.add(node);
	}

	@Override
	public SearchNode getNext() {
		SearchNode next = contents.first();
		contents.remove(next);
		return next;
	}

	@Override
	public void reset() {
		contents = new TreeSet<SearchNode>();
	}

}
