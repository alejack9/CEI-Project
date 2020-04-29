package behavioural_analysis;

/**
 * Represents the behavior of an atom (assign, delete, print) 
 */
public class BTAtom extends BTBase {
	
	int cost;

	public int getCost() {
		return cost;
	}

	/**
	 * The cost of this atom (assign: 0 or 1, delete: -1, print:0)
	 * @param cost
	 */
	public BTAtom(int cost) {
		this.cost = cost;
	}
	
}
