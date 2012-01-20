package BoatSearch;

/**
 * Represents a person who can travel on the boat.
 * Didn't want to use plain ints because multiple people can have the same weight,
 * but still be considered different people (!p1.equals(p2)).
 */
public class Person {
	private final int weight;
	
	/**
	 * Create a person
	 * @param weight The person's weight
	 */
	public Person(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
}
