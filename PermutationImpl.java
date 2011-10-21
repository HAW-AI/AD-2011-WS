import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

/**
 * Gruppe1
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
 * @author Oliver Behncke <oliver.behncke@haw-hamburg.de>
 * @author Panagiotis Filippidis <panagiotis.filippidis@haw-hamburg.de>
 * @version 0.3
 * @since 2011-10-12
 * 
 * @author Kai Bielenberg
 * @author Tobias Meurer
 * @author Stephan Berngruber
 * @author Aleksandr Nosov
 * @author Kathrin Kahlhöfer
 */


public class PermutationImpl implements Permutation, Iterable<Integer> {
	private List<Integer> elements;
	
	/**
     * Create a new permutation, based on cycle-notation
     *
     * @author Tobias Meurer
     * @author Stephan Berngruber
     *
     * @param String in cycle format. Space ignored, cycle opens with "(" and closes with ")". Elements in one cycle seperated by ",". e.g.: "(2) ( 3,1) (4,6,7)(5) "
     * @return a new permutation object from symmetric group S(n) where \u03c3(i)=ai for all 1\u2264i\u2264n
     * 		   NoPermutation if \u03c3(i)=\u03c3(j) for i\u2260j or if not 1\u2264\u03c3(i)\u2264n for all 1<\u2264i\u2264n
     *         NoPermutation if parameter cString does not match cycle notation
     *         NoPermutation if the argument is null
     *
     */
	public static Permutation valueOf(String cString) {
        if (cString == null) {
        	return NoPermutation.valueOf();
        }
        
        //result: Wird während dem Parsen mit Listen gefüllt, eine 'Sub-Liste' wird einem Cycle entsprechen: (1,2)(3) -> [[1,2],[3]]
        //        Diese Liste wird an cycle(List<List<Integer>> cycles) übergeben
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //Löscht die Leerzeichen, dann löscht das erste und das lezte Zeichen, danach splittet den String bei )( und macht daraus 
        //eine Liste wie z.B. "(1,2,3)" -> ["1,2"],["3"] 
        List<String> preSplit=Arrays.asList(cString.replaceAll(" ", "").substring(1, cString.length()-1).split("\\)\\("));
        for (String string : preSplit) {
        	//Schau ob der String like "1,2,3" aussieht
			if(!string.matches("^(\\d+,)*\\d+$"))
				return NoPermutation.valueOf();
			List<Integer> l=new ArrayList<Integer>();
			//Spliten nach , und wandeln in Integer um.
			for (String i : string.split(",")) {
				l.add(Integer.valueOf(i));	
			};
			result.add(l);
		}
        
        return valueOfCycleList(result);
    }  
	/**
	 * @author Aleksandr Nosov
	 * @param list List<List<Integer>>
	 * @return list List<Integer>
	 */
	private static List<Integer> flatt(List<List<Integer>> list){
		List<Integer> l=new ArrayList<Integer>();
		for (List<Integer> list2 : list) {
			for (Integer integer : list2) {
				l.add(integer);
			}
		}
		return l;
	}
	/**
     * Create a new permutation, based on cycle-notation
     *
     * @author Tobias Meurer
     * @author Stephan Berngruber
     * 
     * @param Cycle "Sub"-Lists in a List. 
     * @return a new permutation object from symmetric group S(n) where \u03c3(i)=ai for all 1\u2264i\u2264n
     *			or NoPermutation if not valid
     */
	public static Permutation valueOfCycleList(List<List<Integer>> cycles) {
        List<Integer> result=PermutationImpl.flatt(cycles);
        if(cycles == null || !checkPreconditionList(result, result.size())) return NoPermutation.valueOf();
        //n: Wert für Sn, also größe der Permutation
        
        //Eigentliche Umwandlung in Standard-Notation für valueOf-Methode 
        for (List<Integer> currentCycle : cycles) {
            // Das erste Element des Cycles an die Position des letzten Cycle-Elments setzen
            result.set(currentCycle.get(currentCycle.size()-1)-1, currentCycle.get(0));
            
            // Wenn der Cycle mehr als ein Element hat (also kein fixpunkt ist), dann andere Werte setzen
            for (int i = 1; i < currentCycle.size(); i++) {
                result.set(currentCycle.get(i-1)-1, currentCycle.get(i));
            }
        }  
        
        //Aufruf der valueOf-Methode von Gruppe 1 mit der aus der Cycle-Notation umgewandelten Liste.
        return valueOf(result);
    }
	private PermutationImpl(List<Integer> imageList) {
		this.elements = imageList;
	}

	/**
     * Create a new permutation
     * 
     * @param imageList a n-size list of integer [a1, ..., an]
     * @return a new permutation object from symmetric group S(n) where \u03c3(i)=ai for all 1\u2264i\u2264n
     * @throws IllegalArgumentException if \u03c3(i)=\u03c3(j) for i\u2260j or if not 1\u2264\u03c3(i)\u2264n for all 1<\u2264i\u2264n
     * @throws NullPointerException if the argument is null 
     * 
     */
    /**
     * @param imageList
     * @return  Permutation or NoPermutation if not valid
     */
	public static Permutation valueOf(List<Integer> imageList){
        if (imageList == null || !checkPreconditionList(imageList, imageList.size()))
            return NoPermutation.valueOf();
        return new PermutationImpl(imageList);
    }
    
    public static Permutation s(int...ints){
    	List<Integer> result = new ArrayList<Integer>();
    	for(int elem : ints){
    		result.add(elem);
    	}
    	return PermutationImpl.valueOf(result);
    }
    
    private static boolean checkForDuplicatesInList(List<Integer> list) {
        boolean result = true;
        if (list.size() != (new HashSet<Integer>(list)).size()) {
            result = false;
        }
        return result;
    }

    private static boolean checkForElementsOutOfRange(List<Integer> list, int size) {
        boolean result = true;
        for (int i : list) {
            if (i < 1 || i > size) {
                	result = false;
            	}
        }
        	return result;
    	}

  	  private static boolean checkPreconditionList(List<Integer> list, int size) {
        	return checkForDuplicatesInList(list) && checkForElementsOutOfRange(list, size);
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

		// result erzeugen mit der noetigen groesse, gefuellt mit Nullen
		result = createArray(this.getElements().size());

		// inverse in Array gießen

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
		// invert Map<Integer,Integer> --> Map<Integer,Integer> -- vertauscht
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

        return new HashSet<List<Integer>>(getAllCycles_(new ArrayList<List<Integer>>(),
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
		// Hilfsfunktion fuer getAllCycles() , getAllCyclesAsList()
		int newCurrentKey;
		List<Integer> singleCycle = new ArrayList<Integer>();
		// Einzelnen Cycle bestimmen
		while (map.containsKey(currentKey)) {
			newCurrentKey = map.get(currentKey); 	// Wert bestimmen durch Key
			singleCycle.add(newCurrentKey); 	// Wert zum Cycle hinzufügen
			map.remove(currentKey); 		// Wert aus Map entfernen
			currentKey = newCurrentKey; 		// Wert fuer naechsten Key festlegen
		}
		// Wenn singleCycle leer ist nicht zum Endergebnis hinzufuegen
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
	public List<Integer> cycle(int index){
		try {
			return getAllCyclesAsList().get(index - 1);
		} catch (Exception e) {
			return new ArrayList<Integer>();
		}
	}

	/**
	 * @author Ben Rexin <benjamin.rexin@haw-hamburg.de>
	 * @author Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
	 */
	@Override
	public Permutation compose(Permutation other){
		// Checks:
		// Same cardinality
		// Same range (1...n)
		// -> Both are Permutation objects, should be valid

		// Example:
		// [2,4,5,1,3] this
		// [3,5,1,4,2] other
		// [5,4,2,3,1] composite
		if (other == null) {
			return NoPermutation.valueOf();
		}
		
		if (permutationClass() != other.permutationClass()) {
			return NoPermutation.valueOf();
		}

		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for (Integer element : this) {
			resultList.add(other.getPermElement(element));
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
	public int getPermElement(int index) throws IllegalArgumentException {
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
	public int permutationClass() {
		return getElements().size();
	}

    /**
     * @author Raimund Wege
     * @author Till Theis
     */
    @Override
	public String toString() {
        return listToString(getElements());
	}

	/**
	 * @author Raimund Wege
	 * @author Till Theis
	 */
	@Override
	public String toCycleNotationString() {
        // use the list variant to have an order wich is 'nicer' to read
        // (cycles which include lower values are put first).
        //
        // where allCycles() would return (5 3)(2 4 1), getAllCyclesAsList()
        // returns (2 4 1)(5 3).
        List<List<Integer>> cycles = getAllCyclesAsList();

        // use StringBuilder for faster String construction.
        StringBuilder builder = new StringBuilder();

        for (List<Integer> cycle : cycles) {
            builder.append(listToString(cycle));
        }

        return builder.toString();
	}

    /**
     * Return a the String representation of a List that is compatible with
     * the cycle notation.
     *
     * Arrays.asList(1,2,3).toString() == "[1, 2, 3]"
     * listToString(Arrays.asList(1,2,3)) == "(1 2 3)"
     */
    private String listToString(List<Integer> elems) {
        // use StringBuilder for faster String construction.
        StringBuilder builder = new StringBuilder();

        builder.append("("); // open surrounding parentheses
        for (int elem : elems) {
            builder.append(elem);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length()-1); // remove space at the end
        builder.append(")"); // close surrounding parentheses

		return builder.toString();
    }
    
    
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * 
	 * Gibt die Order der Permutation aus.
	 * Order als KGV der Größe der einzelnen Cycles implementiert
	 * BSP: (1 2 3)(4 5)(6) = KGV(3, 2, 1) = 6
	 * @return int (order of the permutation)
	 */
    public int order(){
    	// Order = 0 bei leerer Permutation
    	if (this.getElements().isEmpty()) 
    		return 0;
    	else{
    		// pCycle enthällt alle Cycle als Liste.
    		Set<List<Integer>> pCycle = this.allCycles();
    		// Liste mit allen Cyclelängen
    		List<Integer> cycleLength = new ArrayList<Integer>();
    	for(List<Integer> cycle : pCycle){
    		cycleLength.add(cycle.size());
    	}
    	//KGV aller Cycle Längen entspricht der Ordnung
    	return kgv(cycleLength);}
    }
    
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * 
	 * Kleinstes gemeinsames Vielfaches, Berechnung aus mehrerern Integer in einer Liste
	 * @return int (KGV aller Integer der Liste)
	 */
    //
    private int kgv(List<Integer> l){
    	Iterator<Integer> it = l.iterator();	
    	int result = it.next();   	
    	
    	while(it.hasNext()) {
    		result = kgv(result, it.next());
    	}	
    	return result;   	
    }
    
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * 
	 * KGV Berechnung 2er ganzer Zahlen
	 * Implementation mittels GGT-KGV Beziehung: ggt(m,n) * kgv(m,n) = |m*n| -> kgv(m,n) = |m*n| / ggt(m,n)
	 * @return int 
	 */
    //
    private int kgv(int m, int n){
    	return (m*n) / ggt(m,n);	
    }
    
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * 
	 * Berechnet ggt von 2 Zahlen, benötigt zur KGV Berechnung.
	 * @return int  (ggt(m,n))
	 */
    
    private int ggt(int m, int n) {
    	if(n == 0) return m;
    	else return ggt(n,m%n);
    }

    
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * 
	 * Performante Implementation von Potenzen z.b. (1, 2, 3, 4)^4
	 *  Keine Änderungen bei: n == 1
	 *  					  id bei n == 0
	 */

    public Permutation permPower(int n){
    	Permutation result = PermutationImpl.valueOf(this.getElements());
    	if(n>1){
    		// p1.PermPower(order(p1) + 1) = p1
    		for(int i = 1; i < (n % (this.order() + 1)); i++) {
    			result = result.compose(this);
    		}
    	}
    	else if(n == 0) {
    		result = result.id();
    	}
    	else if(n<0){
    			result =  this.inverse().permPower(n*(-1));
    	}
    	return result;
    }
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * 
	 * Angabe der ID der Permutationsklasse
	 */
    public Permutation id(){
    	return this.compose(this.inverse());
    }
    /**
     * @author Kathrin Kahlhöfer
     * @author Aleksandr Nosov
     */
    public Map<Integer,Integer> cycleType(){
      Map<Integer,Integer> typeMap = new HashMap<Integer,Integer>();
      for (List<Integer> c: allCycles()) {
            int type = c.size();
            if (typeMap.containsKey(type)) {
                  typeMap.put(type, typeMap.get(type) + 1);
            } else {
                  typeMap.put(type, 1);
            }
      }
      return typeMap;
    }
    /**
     * @author Kathrin Kahlhöfer
     * @author Aleksandr Nosov
     */
    public String toCycleTypeString(){
      String cycleTypeString = "[";
      int i = 1;
      for(Map.Entry<Integer,Integer> e: cycleType().entrySet()){
            cycleTypeString += e.getKey() + "^" + e.getValue();
            if (i < cycleType().size()){
                  cycleTypeString += ", ";
                  i += 1;
            }
      }
      cycleTypeString += "]";
      return cycleTypeString;
    }
    
    /**
	 * @author Andrej Braining
	 * @author Marc W�seke
	 */
    public List<List<Integer>> toTranspositions() {
    	
    	List<List<Integer>> list = this.getAllCyclesAsList(); 
    	List<List<Integer>> result = new ArrayList<List<Integer>>(); 
    	
    	for (int i = 0; i < list.size(); i++) {
    		
    			for (int j = list.get(i).size(); j > 1; j--) {
					List<Integer> tempList = new ArrayList<Integer>();
					tempList.add(list.get(i).get(0));
					tempList.add(list.get(i).get(j-1));
					result.add(tempList);
				}
    		
		}
    	
		return result;
	}
    
    /**
     * @author Marc W�seke
     * 
     */
    public String toTranspositionString(){

    	List<List<Integer>> list = this.toTranspositions();
    	StringBuilder resStr = new StringBuilder();

        resStr.append("(");
        for (List<Integer> listInt : list){
        	resStr.append("("); //braket for every 2-cycle
        	for (int elem : listInt) {
        		resStr.append(elem);
        	}
        	resStr.append(")");
        	
    	}
        resStr.append(")");

		return resStr.toString();
    }
    
    /**
     * @author Marc W�seke
     */
    public int sign(){
    	int s = this.toTranspositions().size();
    	//s==0 : if the permutation is like (1,2,3,4,5,...)
    	return (int) (this.equals(this.id()) ? NoPermutation.valueOf().sign() : Math.pow((-1.0), s));
    }
    
    
}
