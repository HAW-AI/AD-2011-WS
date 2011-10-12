import java.util.List;
import java.util.Set;

/**
* @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
* @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
* @author Till Theis
* @author Raimund Wege
* @author Andreas Wimmer
* @author Sebastian Krome
* @author Daniel Liesener
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
     * @return int the image
     * @throws IllegalArgumentException unless 1 <= inverseImage <= permutationClass()
     */
    int sigma(int inverseImage) throws IllegalArgumentException;
    
    /**
     * Return the n-th cycle of the permutation.
     * 
     * @param index the cycle index (beginning at 1)
     * @return List the cycle
     * @throws IllegalArgumentException unless 1 <= index <= permutationClass()
     */
    List<Integer> cycle(int index) throws IllegalArgumentException;
    
    /**
     * 
     * @return Set
     */
    Set<List<Integer>> allCycles();
    
    /**
     * 
     * @return List
     */
    Set<Integer> fixedPoints();
    
    /**
     * 
     * @return Permutation
     */
    Permutation inverse();
    
    /**
     * 
     * @param other Permutation
     * @return Permutation
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    Permutation compose(Permutation other) throws IllegalArgumentException, NullPointerException;
    
    /**
     *
     * @return String the permutation in mathematical notation
     * @see    #toCycleNotationString()
     */
    public String toString();

    /**
     * 
     * @return String in CycleNotation (See: http://en.wikipedia.org/wiki/Cycle_notation )
     */
    String toCycleNotationString();
    
    /**
     * @return Integer of Elements, aka Sn-Class of Permutation
     */
    int permutationClass();

    /**
     * @return boolean
     */
    public boolean equals(Object other);
}
