package BoatSearch;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * FIFO frontier for breadth-first search
 */
public class BreadthFrontier implements Frontier {

	private LinkedHashSet<SearchNode> contents;
	
	public BreadthFrontier() {
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
		Iterator<SearchNode> it = contents.iterator();
		SearchNode next = it.next();
		it.remove();
		return next;
	}

	@Override
	public void reset() {
		contents = new LinkedHashSet<SearchNode>();
	}

}
