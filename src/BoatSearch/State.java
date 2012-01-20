package BoatSearch;

import java.util.HashSet;
import java.util.Set;

/**
 * Immutable representation of the state of the people and the boat.
 * Can generate successor states.
 */
public class State {
	private final Set<Person> southernBank;
	private final Set<Person> northernBank;
	private final boolean boatNorth;
	
	public State(Set<Person> southernBank, Set<Person> northernBank, boolean boatNorth) {
		this.southernBank = southernBank;
		this.northernBank = northernBank;
		this.boatNorth = boatNorth;
	}

	public Set<Person> getSouthernBank() {
		return southernBank;
	}
	
	public Set<Person> getNorthernBank() {
		return northernBank;
	}

	public boolean isBoatNorth() {
		return boatNorth;
	}
	
	/**
	 * @return all possible successor states
	 */
	public Set<State> getAllSuccessors() {
		final Set<State> successors = new HashSet<State>();
		/*
		 * Any combination of 1-2 passengers can move from the bank with the boat
		 * to the bank without the boat.
		 */
		final Set<Person> source = boatNorth ? northernBank : southernBank;
		//kinda crappy, adds duplicates but Sets protect against that
		for(Person p1 : source) {
			HashSet<Person> passengers = new HashSet<Person>();
			passengers.add(p1);
			successors.add(getSuccessor(passengers));
			for(Person p2 : source) {
				@SuppressWarnings("unchecked")
				Set<Person> innerPassengers = (Set<Person>) passengers.clone();
				passengers.add(p2);
				successors.add(getSuccessor(innerPassengers));
			}
		}
		
		return successors;
	}

	/**
	 * Gives you a successor where the given passengers have crossed the lake
	 * @returns one successor state
	 * @param passengers The passengers to move
	 */
	@SuppressWarnings("unchecked")
	private State getSuccessor(Set<Person> passengers) {
		final Set<Person> nextSouthern = (Set<Person>) ((HashSet<Person>) southernBank).clone();
		final Set<Person> nextNorthern = (Set<Person>) ((HashSet<Person>) northernBank).clone();
		final Set<Person> source = boatNorth ? nextNorthern : nextSouthern;
		final Set<Person> destination = boatNorth ? nextSouthern : nextNorthern;
		source.removeAll(passengers);
		destination.addAll(passengers);
		return new State(nextSouthern, nextNorthern, !boatNorth);
	}

	/* 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (boatNorth ? 1231 : 1237);
		result = prime * result
				+ ((northernBank == null) ? 0 : northernBank.hashCode());
		result = prime * result
				+ ((southernBank == null) ? 0 : southernBank.hashCode());
		return result;
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		final State other = (State) obj;
		if (boatNorth != other.boatNorth) {
			return false;
		}
		if (northernBank == null) {
			if (other.northernBank != null) {
				return false;
			}
		} else if (!northernBank.equals(other.northernBank)) {
			return false;
		}
		if (southernBank == null) {
			if (other.southernBank != null) {
				return false;
			}
		} else if (!southernBank.equals(other.southernBank)) {
			return false;
		}
		return true;
	}
	
}