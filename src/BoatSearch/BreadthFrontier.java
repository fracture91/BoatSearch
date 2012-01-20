package BoatSearch;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * FIFO frontier for breadth-first search
 */
public class BreadthFrontier implements Frontier {

	private LinkedHashSet<SearchNode> contents;
	
	public BreadthFrontier() {
		contents = new LinkedHashSet<SearchNode>();
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

}
