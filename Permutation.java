import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
* @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
* @author Till Theis
* @author Raimund Wege
* @author Andreas Wimmer
* @author Sebastian Krome
* @author Daniel
* @author Fenja Harbke
* @author Felix Schmidt
* @author Berthold Wiblishauser
* @version 0.2
* @since 2011-10-12
*/

public interface Permutation extends Iterable<Integer> {
    /**
     * Return the image of a value.
     * 
     * @param  inverseImage the inverse image
     * @return the image or -1 if is a NoPermution
     * @throws IllegalArgumentException unless 1 <= inverseImage <= permutationClass()
     */
    int getPermElement(int inverseImage) throws IllegalArgumentException;
    
    /**
     * Return the permutation that is built of the n-th cycle of the original permutation and its class.
     * 
     * @param index the cycle index (beginning at 1)
     * @return the cycle Permutation or NoPermution
     */
    Cycle cycle(int index);
    
    /**
     * All the cycles of the image.
     * 
     * @return the cycles
     */
    List<Cycle> allCycles();
    
    /**
     * 
     * @return all cycle-states as permutation
     */
    List<Cycle> allCyclesAsPermutaion();
    
    /**
     * All the fixed points.
     * 
     * @return the fixed points
     */
    Set<Integer> fixedPoints();
    
    /**
     * The inverse permutation.
     * 
     * @return the inverse permutation
     */
    Permutation inverse();
    
    /**
     * Compose this permutation with the other one.
     * 
     * @param other the Permutation to compose with
     * @return the composition or NoPermutation if exception
     */
    Permutation compose(Permutation other);
    
    /**
     * The permutation in mathematical notation.
     *
     * @return the mathematical notation
     * @see    #toCycleNotationString()
     */
    public String toString();

    /**
     * The mathematical cycle notation ({@link http://en.wikipedia.org/wiki/Cycle_notation})
     * 
     * @return the cycle notation
     */
    String toCycleNotationString();
    
    /**
     * The permutation class (i.e. the number of its images, aka Sn-class of
     * Permutation)
     *
     * @return the permutation class
     */
    int permutationClass();

    /**
     * Test for structural equality. 
     * Two permutations are considered equal if they describe the same
     * relation.
     *
     * @return boolean
     */
    public boolean equals(Object other);

    /**
     * Calculates the Order of a given Permutation
     * @return int
     */
	int order();
	
	  /**
     * Calculates the Potentenz of the Permutation
     * @return Permutation
     */
	Permutation permPower(int n);
	
    /**
     * CycleType as String
     * @return String
     */
	public String toCycleTypeString();
    
	/**
	 * CylceType as Map
	 * @return Map<Integer,Integer>
	 */
    public Map<Integer,Integer> cycleType();

    /**
     * cycle notation will be formed into transposition notation.
     * 
     * @return the transposition notation
     */
    public List<Transposition> toTranspositions();
    
    /**
     * transposition notation as String
     * 
     * @return the transposition notation
     */
    public String toTranspositionString();
    
    /**
     * make the signature of transposition
     * 
     * @return int
     */
    public int sign();

    /**
    * Returns the n-th permutation in lexical order (rank) of a given permutation-class
    *
    * @return Permutation
    * @see http://bytes.com/topic/java/insights/632857-permutations-b
    */
    public Permutation unRank(int rank);

	/**
    * Returns the rank of a given Permutation or -1, if the permutation is invalid
    *
    * @return int
    */
    public int rank();
}
