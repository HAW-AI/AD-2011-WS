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
     * All the cycles of the image.
     * 
     * @return Set the cycles
     */
    Set<List<Integer>> allCycles();
    
    /**
     * All the fixed points.
     * 
     * @return List the fixed points
     */
    Set<Integer> fixedPoints();
    
    /**
     * The inverse permutation.
     * 
     * @return Permutation the inverse permutation
     */
    Permutation inverse();
    
    /**
     * Compose this permutation with the other one.
     * 
     * @param other the Permutation to compose with
     * @return Permutation the composition
     * @throws IllegalArgumentException if the classes of both permutations are
     *                                  not the same
     * @throws NullPointerException     if the argument is null
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
