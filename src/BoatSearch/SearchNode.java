package BoatSearch;

import javax.swing.tree.DefaultMutableTreeNode;

public class SearchNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1845825236979052915L;
	private final int totalCost;
	private final State state;
	
	/**
	 * Creates a SearchNode with no parent or children
	 * @param state The state to associate with this node
	 * @param totalCost The total cost to reach this node's state
	 */
	public SearchNode(State state, int totalCost) {
		this.state = state;
		this.totalCost = totalCost;
	}

	/**
	 * @return the total cost to reach this node's state
	 */
	public int getTotalCost() {
		return totalCost;
	}

	/**
	 * @return the state associated with this node
	 */
	public State getState() {
		return state;
	}
	
}
