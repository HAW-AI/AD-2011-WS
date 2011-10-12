import java.util.List;
import java.util.Set;

/**
 * @author      Ben Rexin <benjamin.rexin@haw-hamburg.de>
 * @author     	Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
 * @author 		Till Theis
 * @author 		Raimund Wege
 * @author		Andreas Wimmer
 * @author		Sebastian Krome
 * @author		Daniel Liesener
 * @author		Fenja Harbke
 * @version     0.1
 * @since       2011-10-12
 */

public interface Permutation extends Iterable<Integer> {
    /**
     * Documentation...
     * 
     * @param i
     * @return
     * @throws IllegalArgumentException
     */
    int sigma(int index) throws IllegalArgumentException;
    
    /**
     * 
     * @param i
     * @return
     * @throws IllegalArgumentException
     */
    List<Integer> cycle(int i) throws IllegalArgumentException;
    
    /**
     * 
     * @return
     */
    Set<List<Integer>> allCycles();
    
    /**
     * 
     * @return
     */
    Set<Integer> fixedPoints();
    
    /**
     * 
     * @return
     */
    Permutation inverse();
    
    /**
     * 
     * @param p
     * @return
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    Permutation compose(Permutation p) throws IllegalArgumentException, NullPointerException;
    
    /**
     * 
     * @return
     */
    String toCycleNotationString();
    
    /**
     * @return Integer number of Elements, aka Sn-Class of Permutation
     */
    Integer permutationClass();
}