import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Aleksandr Nosov
 * @author Kai Bielenberg
 */
public class NoPermutation implements Permutation {
	private static NoPermutation instance=new NoPermutation();
	private NoPermutation(){
		
	}
	public static NoPermutation valueOf(){
		return instance;
	}
	@Override
	public Iterator<Integer> iterator() {
		return new ArrayList<Integer>().iterator();
	}
	
	@Override
	public int getPermElement(int inverseImage){
		return 0;
	}

	@Override
	public List<Integer> cycle(int index){
		return new ArrayList<Integer>();
	}

	@Override
	public Set<List<Integer>> allCycles() {
		return new HashSet<List<Integer>>();
	}

	@Override
	public Set<Integer> fixedPoints() {
		return new HashSet<Integer>();
	}

	@Override
	public Permutation inverse() {
		return NoPermutation.valueOf();
	}

	@Override
	public Permutation compose(Permutation other){
		return NoPermutation.valueOf();
	}

	@Override
	public String toCycleNotationString() {
		return "NoPermutation";
	}

	@Override
	public int permutationClass() {
		return 0;
	}

	@Override
	public int order() {
		return 0;
	}

	@Override
	public Permutation id() {
		return NoPermutation.valueOf();
	}

	@Override
	public Permutation permPower(int n) {
		return NoPermutation.valueOf();
	}

	@Override
	public String toCycleTypeString() {
		return "[ ]";
	}

	@Override
	public Map<Integer, Integer> cycleType() {
		return new HashMap<Integer, Integer>();
	}
	@Override
	public List<List<Integer>> toTranspositions() {
		return new ArrayList<List<Integer>>();
	}
	@Override
	public String toTranspositionString() {
		return "NoTransposition";
	}
	@Override
	public int sign() {
		return 1;
	}
	
	@Override
	public String toString(){
		return "NaP";
	}

}
