Homework Assignment 1
CS 4341 C12 - Artificial Intelligence
Professor Chernova

Author: Andrew Hurle (andrew.d.hurle)
To run: //todo

Purpose
=====
Finds a solution to the river crossing problem using breadth-first, depth-first, and A* search.
Starting state is provided by input.txt, output written to output.txt.

Clarifications
=====
Person instances are considered different from each other even if they have the same weight.
This can lead to what looks like redundant expansions while searching.

States are printed like this:
State([Person(75), Person(105), Person(70)], [Person(70), Person(120)], true)
The first set of people are the people on the southern bank, the next set are on the northern bank,
and the boolean is true if the boat is on the northern bank.

Node children in BFS and DFS aren't traversed in any useful order.

Questions
=====

1)  Provide a problem definition for the above problem. Be sure to include the format of the states,
	the initial state, possible actions, a transition model, the goal state, and the cost function.

State: A set of people on the southern bank, northern bank, and a boolean to indicate boat position
State([10, 20, ...], [10, 60, ...], true)

Initial State: Some number of people on the southern bank, nobody on the northern bank, boat on
the southern bank.
State([10, 20, ...], [], false)

Actions: Can move one or two people from the current bank to the opposite bank.
Current and opposite bank determined by position of boat.
Move([10, 20])

Transition Model: Moving a set of passengers will remove them from the bank the boat is on,
add them to the opposite bank, and set the boat's position to the opposite bank.  Possible successors
can be found by moving all possible combinations of 1-2 people.
Ex: Result(State([10, 20], [], false), Move([10, 20])) = State([], [10, 20], true)

Goal State: No people are left on the southern bank: State([], ...).  I phrase it this way so that
State([], [], ...) is considered a goal state.  We don't really care about the position of the boat,
but if valid moves are performed with > 0 people you would expect to find it on the northern bank.

Cost function:  Step cost of Move([a, b]) is equal to max([a, b]).
Path cost is the sum of all steps costs along the path.


2)	If there are 4 men with weights 10, 20, 150, 200, how many possible states are reachable?
	What is the optimal time for this instance?

Each person can be on either bank, and the boat can also be on either bank.
This means there are 2^(n+1) possible states (including the starting state) where n is the number of people.
This is assuming that people with the same weight are considered unique.  Bob with weight 10 and Joe with
weight 10 on the north and south banks respectively is a different state than when their positions are
swapped.
In this case, there are 2^5 = 32 possible states.
An optimal solution would involve the lightest person ferrying the heavier people across:
	10, 20, 150, 200 | --    0
	20, 150 | 10, 200        200
	10, 20, 150 | 200        10
	20 | 10, 150, 200        150
	10, 20 | 150, 200        10
	-- | 10, 20, 150, 200    20
	
	Total time:              390
	

3)	Describe your heuristic for A* search.  Justify the heuristic’s admissibility.
h(n) = max(a) where a is the set of people left on the southern bank in state n
This heuristic was found by relaxing the rule of only allowing a maximum of 2 people on the boat.
What if everybody got on the boat at once?

This heuristic is optimistic, and consequently admissible, because the heaviest person will definitely
need to reach the other bank at some point to reach the goal state.  Since the path to the goal contains
a transition involving the heaviest person, the time to reach the goal must be greater than or equal to
the cost of the transition.

This isn't particularly good since it will prefer some obviously bad moves, but it's valid.
