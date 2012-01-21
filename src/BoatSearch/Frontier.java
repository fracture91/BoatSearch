package BoatSearch;

public interface Frontier {
	
	/**
	 * @return true if the frontier is empty, false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Add the given node to the frontier, if it isn't in it already
	 * @param node The node to add
	 * @return true If the frontier didn't already contain the given node
	 */
	public boolean add(SearchNode node);
	
	/**
	 * Get the next node from the frontier and remove it
	 * @return The next node to examine
	 */
	public SearchNode getNext();
	
	/**
	 * Reset the Frontier to the state it started in.
	 */
	public void reset();
}
