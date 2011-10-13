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
     * @return the image
     * @throws IllegalArgumentException unless 1 <= inverseImage <= permutationClass()
     */
    int sigma(int inverseImage) throws IllegalArgumentException;
    
    /**
     * Return the n-th cycle of the permutation.
     * 
     * @param index the cycle index (beginning at 1)
     * @return the cycle
     * @throws IllegalArgumentException unless 1 <= index <= permutationClass()
     */
    List<Integer> cycle(int index) throws IllegalArgumentException;
    
    /**
     * All the cycles of the image.
     * 
     * @return the cycles
     */
    Set<List<Integer>> allCycles();
    
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
     * @return the composition
     * @throws IllegalArgumentException if the classes of both permutations are
     *                                  not the same
     * @throws NullPointerException     if the argument is null
     */
    Permutation compose(Permutation other) throws IllegalArgumentException, NullPointerException;
    
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
}
