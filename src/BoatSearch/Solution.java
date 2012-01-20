package BoatSearch;

import java.util.List;

/**
 * A solution to a GraphSearch problem, containing the list of states,
 * the total travel time, and total number of node expansions.
 */
public class Solution {
	private List<State> states;
	private int totalTime = 0;
	private int totalExpansions = 0;
	
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getTotalExpansions() {
		return totalExpansions;
	}
	public void setTotalExpansions(int totalExpansions) {
		this.totalExpansions = totalExpansions;
	}
	@Override
	public String toString() {
		String str = totalTime + " " + totalExpansions;
		for(State i : states) {
			str += "\n" + i;
		}
		return str;
	}
	
	
}
