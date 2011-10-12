import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
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

public class PermutationImpl implements Permutation {
	private List<Integer> elements;

	private PermutationImpl(List<Integer> imageList) {
		this.elements = imageList;
	}

	public static PermutationImpl valueOf(List<Integer> imageList) {
		checkPreconditionList(imageList, imageList.size());
		return new PermutationImpl(imageList);
	}

	private static void checkForDuplicatesInList(List<Integer> list)
			throws RuntimeException {
		if (list.size() != (new HashSet(list)).size()) {
			throw new RuntimeException();
		}

	}

	private static void checkForElementsOutOfRange(List<Integer> list, int size)
			throws RuntimeException {
		for (int i : list) {
			if (i < 1 || i > size) {
				throw new RuntimeException();
			}
		}
	}

	private static void checkPreconditionList(List<Integer> list, int size) {
		checkForDuplicatesInList(list);
		checkForElementsOutOfRange(list, size);
	}

	/**
	 * accessor method for permutation elements
	 * 
	 * @return ArrayList<Integer>
	 */
	protected List<Integer> getElements() {
		return elements;
	}

	/**
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 */
	public Permutation inverse() {
		// inverse: List<Integer> --> List<Integer> --- gibt die Inverse
		// Darstellung von Sigma aus (als Liste)
		// Bsp.: [1,2,3]->[1,2,3]; [3,4,2,1] -> [4,3,1,2] [1] ->[1]; [] -> []
		Map<Integer, Integer> inverse = new HashMap<Integer, Integer>();
		List<Integer> result = new ArrayList<Integer>();

		// map funktion invertieren, d.h. keys werden values und values werden
		// keys
		inverse = invert(getElementsAsMap());

		// result erzeugen mit der nštigen grš§e, gefellt mit Nullen
		result = createArray(this.getElements().size());

		// inverse in Array gie§en

		for (Map.Entry<Integer, Integer> entry : inverse.entrySet()) {
			result.set(entry.getKey() - 1, entry.getValue());
		}

		return new PermutationImpl(result);

	}

	/**
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 */
	public static List<Integer> createArray(int n) {
		// erzeugeArray: int --> List<Integer> -- erzeugt einen Array mit der
		// Laenge n, gefuellt mit Nullen
		// Bsp.: erzeugeArray(3) -->[0,0,0]
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			result.add(0);
		}
		return result;
	}

	/**
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 */
	public static Map<Integer, Integer> invert(Map<Integer, Integer> m) {
		// invert Map<Integer,Integer> --> Map<Integer,Integer> -- vertaischt
		// die keys und values
		// Bsp.: {1->2; 2->3; 3->1} --> {2->1; 3->2; 1->3}
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
			result.put(entry.getValue(), entry.getKey());
		}
		return result;
	}

	/**
	 * @author Daniel Liesener
	 * @author Fenja Harbke
	 */
	public Set<List<Integer>> allCycles() {
		// Wandelt Permutation in Cycle Notation um
		// Bsp.: [2,1,3] -> [[2,1][3]]
		Map<Integer, Integer> elementsMap = getElementsAsMap();

		return listToSet(getAllCycles_(new ArrayList<List<Integer>>(),
				elementsMap, 1));
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 * @return
	 */
	private List<List<Integer>> getAllCyclesAsList() {
		Map<Integer, Integer> elementsMap = getElementsAsMap();

		return getAllCycles_(new ArrayList<List<Integer>>(), elementsMap, 1);
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 * @return
	 */
	private Map<Integer, Integer> getElementsAsMap() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		for (int i = 0; i < this.getElements().size(); i++) {
			result.put(i + 1, this.getElements().get(i));
		}
		return result;
	}

	/**
	 * @author Daniel Liesener
	 * @author Fenja Harbke
	 */
	private List<List<Integer>> getAllCycles_(List<List<Integer>> totalCycle,
			Map<Integer, Integer> map, int currentKey) {
		// Hilfsfunktion fŸr getAllCycles
		int newCurrentKey;
		List<Integer> singleCycle = new ArrayList<Integer>();
		// Einzelnen Cycle bestimmen
		while (map.containsKey(currentKey)) {
			newCurrentKey = map.get(currentKey); // Wert bestimmen durch Key
			singleCycle.add(newCurrentKey); // Wert zum Cycle hinzufŸgen
			map.remove(currentKey); // Wert aus Map entfernen
			currentKey = newCurrentKey; // Wert fŸr nŠchsten Key festlegen
		}
		// Wenn singleCycle leer ist nicht zum Endergebnis hinzufŸgen
		if (!singleCycle.isEmpty()) {
			totalCycle.add(singleCycle);
		}
		// Wenn Map nicht leer ist weiter suchen
		if (!map.isEmpty()) {
			getAllCycles_(totalCycle, map, currentKey + 1);
		}
		return totalCycle;
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 * @author Daniel Liesener
	 * @author Fenja Harbke
	 */
	public List<Integer> cycle(int index) throws IllegalArgumentException {
		try {
			return getAllCyclesAsList().get(index - 1);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @author Daniel Liesener
	 * @author Fenja Harbke
	 */
	@Override
	public String toCycleNotationString() {
		// Gibt Cycle Notation als String zurÃ¼ck
		return allCycles().toString();
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 */
	@Override
	public Permutation compose(Permutation other) {
		// Checks:
		// Same cardinality
		// Same range (1...n)
		// -> Both are Permutation objects, should be valid

		// Example:
		// [2,4,5,1,3] this
		// [3,5,1,4,2] other
		// [5,4,2,3,1] composite

		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for (Integer element : this) {
			resultList.add(other.sigma(element));
		}
		Permutation result = new PermutationImpl(resultList);
		return result;
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 */
	public int hashCode() {
		// Delegate HashCode to element list
		return getElements().hashCode();
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 */
	public boolean equals(Object other) {
		boolean result = false;

		// Reference Test
		if (this == other) {
			result = true;
		}
		// Type test (instanceof)
		else if (other instanceof Permutation) {
			// Attribute test
			if (this.permutationClass() == ((Permutation) other)
					.permutationClass()
					&& this.getElements().equals(
							((PermutationImpl) other).getElements())) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 */
	public Iterator<Integer> iterator() {
		return getElements().iterator();
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 */
	@Override
	public int sigma(int index) throws IllegalArgumentException {
		try {
			return getElements().get(index - 1); // -1 cause typicaly sigma
													// starts at 1, arrays at 0
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 * @author Andreas Wimmer
	 * @author Sebastian Krome
	 */
	@Override
	public Set<Integer> fixedPoints() {
		Set<Integer> result = new HashSet<Integer>();

		for (Map.Entry<Integer, Integer> e : getElementsAsMap().entrySet()) {
			if (e.getKey().equals(e.getValue())) {
				result.add(e.getValue());
			}
		}

		return result;
	}

	@Override
	public Integer permutationClass() {
		return getElements().size();
	}

	public String toString() {
		return this.getElements().toString();
	}

	private Set<List<Integer>> listToSet(List<List<Integer>> l) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		for (List<Integer> elem : l) {
			result.add(elem);
		}
		return result;
	}
}