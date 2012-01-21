package BoatSearch;

import java.util.Stack;

/**
 * LIFO frontier for depth-first search
 */
public class DepthFrontier implements Frontier {

	private Stack<SearchNode> contents;
	
	public DepthFrontier() {
		reset();
	}
	
	@Override
	public boolean isEmpty() {
		return contents.isEmpty();
	}

	@Override
	public boolean add(SearchNode node) {
		if(!contents.contains(node)) {
			contents.push(node);
			return true;
		}
		return false;
	}

	@Override
	public SearchNode getNext() {
		return contents.pop();
	}

	@Override
	public void reset() {
		contents = new Stack<SearchNode>();
	}

}
