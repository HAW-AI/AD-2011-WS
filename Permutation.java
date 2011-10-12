import java.util.List;
import java.util.Set;

// import Set, List, int, Integer, String


public interface Permutation {
    /**
     * Documentation...
     * 
     * @param i
     * @return
     * @throws IllegalArgumentException
     */
    int sigma(int i) throws IllegalArgumentException;
    
    /**
     * 
     * @return
     */
    List<Integer> getElements();
    
    /**
     * 
     * @param i
     * @return
     * @throws IllegalArgumentException
     */
    List<Integer> getCycle(int i) throws IllegalArgumentException;
    
    /**
     * 
     * @return
     */
    Set<List<Integer>> getAllCycles();
    
    /**
     * 
     * @return
     */
    Set<Integer> getFixedPoints();
    
    /**
     * 
     * @return
     */
    Permutation getInverse();
    
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
    String cycleToString();
}