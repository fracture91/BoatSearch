package BoatSearch;

import java.util.HashSet;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Person> people = new HashSet<Person>();
		people.add(new Person(10));
		people.add(new Person(20));
		people.add(new Person(30));
		State initialState = new State(people, new HashSet<Person>(), false);
		GraphSearch searcher = new GraphSearch(initialState);
		System.out.print(searcher.findSolution());
	}

}
