import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * @author Kathrin KahlhÃ¶fer
 */


public class PermutationImpl implements Permutation, Iterable<Integer> {
	private List<Integer> elements;
	protected static Map<Integer, Permutation> idPool = new HashMap<Integer, Permutation>();
	
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
        
        //result: Wird wÃ€hrend dem Parsen mit Listen gefÃŒllt, eine 'Sub-Liste' wird einem Cycle entsprechen: (1,2)(3) -> [[1,2],[3]]
        //        Diese Liste wird an cycle(List<List<Integer>> cycles) ÃŒbergeben
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //LÃ¶scht die Leerzeichen, dann lÃ¶scht das erste und das lezte Zeichen, danach splittet den String bei )( und macht daraus 
        //eine Liste wie z.B. "(1,2,3)" -> ["1,2"],["3"] 
        List<String> preSplit;
        try{
        	preSplit=Arrays.asList(cString.replaceAll(" ", "").substring(1, cString.length()-1).split("\\)\\("));
        }catch (Exception e) {
        	return NoPermutation.valueOf();
        }
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
        //n: Wert fÃŒr Sn, also grÃ¶Ãe der Permutation
        
        //Eigentliche Umwandlung in Standard-Notation fÃŒr valueOf-Methode 
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
	protected PermutationImpl(List<Integer> imageList) {
		this.elements = imageList;
	}

	/**
     * Create a new permutation
     * 
     * @param imageList a n-size list of integer [a1, ..., an]
     * @return a new permutation object from symmetric group S(n) where \u03c3(i)=ai for all 1\u2264i\u2264n
     * @throws IllegalArgumentException if \u03c3(i)=\u03c3(j) for i\u2260j or if not 1\u2264\u03c3(i)\u2264n for all 1<\u2264i\u2264n
     * @throws NullPointerException if the argument is null
     * @param imageList
     * @return  Permutation or NoPermutation if not valid
     */
	public static Permutation valueOf(List<Integer> imageList){
        if (imageList == null || !checkPreconditionList(imageList, imageList.size()))
            return NoPermutation.valueOf();
        return new PermutationImpl(imageList);
    }
	
	/**
	 * Create new Permutation, based on permutation class.
	 * The permutation is the identity.
	 * Ex.: valueOf(3) --> [1, 2, 3]
	 * @param permClass the class of the permutation. Should be > 0.
	 * @return the identity permutation if permClass is > 0, NoPermutation if permClass is <= 0
	 * 
	 * @author Philipp Gillé
	 */
    public static Permutation valueOf(int permClass){
    	if (permClass <= 0){
    		return NoPermutation.valueOf();
    	} else {
            if(!PermutationImpl.idPool.containsKey(permClass)) {
	    	List<Integer> result = new ArrayList<Integer>();
	    	for(int i = 1; i <= permClass; i++){
	    		result.add(i);
	    	}
	    	PermutationImpl.idPool.put(permClass, PermutationImpl.valueOf(result));
            }
            return PermutationImpl.idPool.get(permClass);
    	}
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

		// inverse in Array gieÃen

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
	 * Wandelt Permutation in Cycle Notation um
	 * Bsp.: [2,1,3,4] -> [[2,1][3][4]]
	 * 
	 * @author Daniel Liesener
	 * @author Fenja Harbke
	 * @author Philipp Gill�
	 */
	public Set<List<Integer>> allCyclesAsSetOfIntegerList() {
		Map<Integer, Integer> elementsMap = getElementsAsMap();

        return new HashSet<List<Integer>>(getAllCycles_(new ArrayList<List<Integer>>(),
                                          elementsMap, 1));
	}
	
	/**
	 * Ex.: [2,1,3,4] -> [[2,1,3,4][1,2,3,4][1,2,3,4]]
	 * @return List of complete cyclic permutations
	 * @author Philipp Gill�
	 */
	public List<Cycle> allCyclesAsPermutaion() {
		List<Cycle> result = new ArrayList<Cycle>();
		
		for (List<Integer> c: allCyclesAsSetOfIntegerList()) {
		   result.add(cycleToPermutation(c, this.permutationClass()));
		}
		
		return result;
	}
	
	/**
	 * @author Joerg Lischka
	 * 
	 * @return
	 */
	public List<Cycle> allCycles() {
		List<Integer> elemCopy = new ArrayList<Integer>();
		List<Boolean> elemTouched = new ArrayList<Boolean>();
		List<Integer> tmpCycle = new ArrayList<Integer>();
		
		if(elements.size() == 0) return new ArrayList<Cycle>();
		
		for(int elem : elements){
			elemCopy.add(elem);
			elemTouched.add(false);
		}
		
        return toCycles(0, elemCopy, elemTouched, tmpCycle);
	}

	/**
	 * @author Joerg Lischka
	 * 
	 * pure recursive
	 * ('getAllCycles_' is not pure recursive!)
	 * 
	 * @param elemId => index of elems
	 * @param elems => elems list (first unsorted)
	 * @param elemTouched => if elem is not in tmpCycle or in CycleList
	 * @param tmpCycle => list for int's before it will become a Cycle
	 * @return a list with all cycles
	 */
	private List<Cycle> toCycles(int elemId, List<Integer> elems, List<Boolean> elemTouched, List<Integer> tmpCycle) {
		if(elemTouched.get(elemId)){ //element is bound to a cycle
			if(elemId+1 < elems.size()){ //next elem recursive
				return new ArrayList<Cycle>(toCycles(elemId+1, elems, elemTouched, tmpCycle));
			}else{ //exit recursion
				return new ArrayList<Cycle>();
			}
		}else{ //new number to bound to cycle
			int nr = elems.get(elemId);
			if(tmpCycle.isEmpty()) tmpCycle.add(elemId+1);
			if(nr-1 == elemId){ //nr is on right position
				elemTouched.set(elemId, true);
				
				List<Cycle> tmpList = new ArrayList<Cycle>();
				tmpList.add( CycleImpl.generate(tmpCycle) ); //add new cycle
				tmpCycle.clear(); //clear tmp-cycleList
				tmpList.addAll( toCycles(0, elems, elemTouched, tmpCycle) ); //recursive search for new unbound nr's
				return tmpList;
			}else{ //nr must be cycled
				tmpCycle.add(nr);
				
				//change the two numbers
				elems.set(elemId, elems.get(nr-1));
				elems.set(nr-1, nr);
				
				elemTouched.set(nr-1, true);
				
				return toCycles(elemId, elems, elemTouched, tmpCycle);
			}
		}
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
			singleCycle.add(newCurrentKey); 	// Wert zum Cycle hinzufÃŒgen
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
	 * @author Philipp Gillé
	 */
	public Cycle cycle(int index){
		if (!(index>getAllCyclesAsList().size() || index<1)){
			return cycleToPermutation(getAllCyclesAsList().get(index - 1), permutationClass());
		}
		else{
			return NoPermutation.valueOf();
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
	 * Order als KGV der GrÃ¶Ãe der einzelnen Cycles implementiert
	 * BSP: (1 2 3)(4 5)(6) = KGV(3, 2, 1) = 6
	 * @return int (order of the permutation)
	 */
    public int order(){
    	// Order = 0 bei leerer Permutation
    	if (this.getElements().isEmpty()) 
    		return 0;
    	else{
    		// pCycle enthÃ€llt alle Cycle als Liste.
    		Set<List<Integer>> pCycle = this.allCyclesAsSetOfIntegerList();
    		// Liste mit allen CyclelÃ€ngen
    		List<Integer> cycleLength = new ArrayList<Integer>();
    	for(List<Integer> cycle : pCycle){
    		cycleLength.add(cycle.size());
    	}
    	//KGV aller Cycle LÃ€ngen entspricht der Ordnung
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
	 * Berechnet ggt von 2 Zahlen, benÃ¶tigt zur KGV Berechnung.
	 * @return int  (ggt(m,n))
	 */
    
    private int ggt(int m, int n) {
    	if(n == 0) return m;
    	else return ggt(n,m%n);
    }

    
	/**
	 * @author Kai Bielenberg
	 * @author Tobias Mainusch
	 * @author Philipp Gillé
	 * 
	 * Performante Implementation von Potenzen z.b. (1, 2, 3, 4)^4
	 *  Keine Ãnderungen bei: n == 1
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
    		result = id();
    	}
    	else if(n<0){
    			result =  this.inverse().permPower(n*(-1));
    	}
    	return result;
    }
//	/**
//    * @author Kai Bielenberg
//    * @author Tobias Mainusch
//    * @author Benjamin Kahlau
//    *
//    * Ausgabe der IdentitÃ€t einer Permutationsklasse
//    */
//    public Permutation id() {
//        // Wenn die IdentitÃ€t der Permutationsklasse nicht im Pool vorhanden ist, wird sie erzeugt
//        if (!PermutationImpl.idPool.containsKey(this.permutationClass())) {
//            Permutation elem = this;
//            // k = order
//            // Die IdentitÃ€t fÃŒr durch k maliges komponieren
//            for(int i = 0; i < this.order()-1; i++) {
//                elem = elem.compose(this);
//            }
//            PermutationImpl.idPool.put(this.permutationClass(), elem);
//        }
//        return PermutationImpl.idPool.get(this.permutationClass());
//    }
    /**
     * @author Philipp Gillé
     * 
     * Ausgabe der Identität einer Permutationsklasse
     */
    private Permutation id(){
    	return valueOf(this.permutationClass());
    }

    /**
     * @author Kathrin KahlhÃ¶fer
     * @author Aleksandr Nosov
     */
    public Map<Integer,Integer> cycleType(){
      Map<Integer,Integer> typeMap = new HashMap<Integer,Integer>();
      for (List<Integer> c: allCyclesAsSetOfIntegerList()) {
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
     * @author Kathrin KahlhÃ¶fer
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
	 * @author Marc WÃŒseke
	 */
    public List<Transposition> toTranspositions() {
    	
    	List<List<Integer>> list = this.getAllCyclesAsList(); 
    	List<Transposition> result = new ArrayList<Transposition>(); 
    	
    	for (int i = 0; i < list.size(); i++) {
    		
    			for (int j = list.get(i).size(); j > 1; j--) {
					List<Integer> tempList = new ArrayList<Integer>();
					tempList.add(list.get(i).get(0));
					tempList.add(list.get(i).get(j-1));
//                    Cycle tempCycle = cycleToPermutation(tempList, this.permutationClass());
					result.add(TranspsitionImpl.generate(tempList));
				}
    		
		}
    //	this.permutationClass();
		return result;
	}
    
    private Cycle cycleToPermutation(List<Integer> cycle, int permClass){
        List<Integer> result = new ArrayList<Integer>();
        
        for(int i=1; i<=permClass;i++){
            result.add(i);
        }
        for(int i=0; i<(cycle.size()-1);i++){
            result.remove(cycle.get(i)-1);
            result.add(cycle.get(i)-1, cycle.get(i+1));
        }
        
        result.remove(cycle.get(cycle.size()-1)-1);
        result.add((cycle.get(cycle.size()-1)-1),cycle.get(0));

        return CycleImpl.generate(result);
        
    }
    
    /**
     * @author Marc WÃŒseke
     * 
     */
    public String toTranspositionString(){
    	if (this.equals(unRank(0))) {return NoPermutation.valueOf().toTranspositionString();}
    	List<Transposition> list = this.toTranspositions();
    	StringBuilder resStr = new StringBuilder();
//    	List<List<Integer>> iList = new ArrayList<List<Integer>>();
//    	
//    	for (int i = 0; i < list.size(); i++) {
//    		for(List<Integer> cList: list.get(i).allCycles()){
//    			if (cList.size() > 1){
//    				iList.add(cList);
//    			}
//    		}
//    	}
        resStr.append("(");
        
        for(Transposition transpo : list){
        	resStr.append(transpo.toShortString());
        }
        
//        for (List<Integer> cycle : iList){
//        	resStr.append("("); //braket for every 2-cycle
//        	for (int elem : cycle) {
//        		resStr.append(elem);
//        		resStr.append(" ");
//        	}
//        	resStr.delete(resStr.length()-1, resStr.length());
//        	resStr.append(")");
//        	
//    	}
        resStr.append(")");

		return resStr.toString();
    }
    
    /**
     * @author Marc WÃŒseke
     */
    public int sign(){
    	int s = this.toTranspositions().size();
    	return (int) Math.pow((-1.0), s);
    }
    
	/**
    * @author Sebastian Bartels
    * @param int permutationClass, int rank
    * @return Permutation
    *
    * Gives the n-th permutation in lexical order (rank) of a given permutation-class
    */

   public Permutation unRank(int rank) {
       int permClass = this.permutationClass();
       //Create a list of all objects in the list as determined by the permutation-class
       List<Integer> classId = new ArrayList<Integer>();
       for(int i = 1; i <= permClass; i++) classId.add(i);
       //Create the list that will eventually become the wanted permutation
       List<Integer> result = new ArrayList<Integer>();

       if(rank >= 0 && rank < factorial(permClass)) {
               //Prepare an int needed in the calculation
               int f = factorial(permClass);

               while(permClass > 0) {
                       f /= permClass;
                       result.add(classId.remove(rank/f));
                       permClass--;
                       rank %= f;
               }
       }
       else result = null;

       return PermutationImpl.valueOf(result);
   }

   /**
    * @author Sebastian Bartels
    * @param Permutation p
    * @return int rank
    *
    * Returns the rank of a given Permutation
    */
   public int rank() {
       int result = 0;
       int wantedElement = 1;          //Wanted element in the Permutation; Can be Integer because our implementation only allows for permutations of Integers that include all numbers 1...|Elements|
       int currentElement = 0;         //index for elementCopy.get(index); will also count elements in front of the wanted element of the given Permutation
       List<Integer> elementCopy = new ArrayList<Integer>(this.elements); //Creates a copy of the permutations elements so we can remove elements
       List<Integer> id = new ArrayList<Integer>(); //Creates a copy of the permutations elements so we can remove elements
       
       for(int i=1; i<=permutationClass(); ++i){
    	   id.add(i);
       }
       
       
       while(elementCopy.size() > 0) {
           wantedElement = elementCopy.remove(0);
    	   
    	   while(id.get(currentElement) != wantedElement)
                       currentElement++;
    	   id.remove(currentElement);
           //Rank of Permutation = (PermutationClass - 1)! * (Number of elements in front of the 1st element of the Permutations id)
           //                      + (PermutationClass-2)! * (Number of Elements in front of the 2nd element of the id after removing the 1st)
           //                                       ...+ 1!                                        * 0             (-->Only 1 Element left)
           result += factorial(elementCopy.size()) * (currentElement);
           currentElement = 0;
       }
       return result;
   }

   /**
    * @param int n
    * @return int f
    *
    * Returns the factorial (n!) of a given n
    */

   private static int factorial(int n) {
       int f = 1;
       for(; n > 0; f*=n--);
       return f;
   }
   
   /**
    * Needs for Transpositions
    * @return
    */
   protected Iterator<Integer> getElems() {
	   return elements.iterator();
   }
    
}
