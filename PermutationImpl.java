public PermutationImpl extends Permutation {
	
	/*
	 * Block by Andy and Sebastian
	 */
	public Permutation getInverse(){
			//inverse:  List<Integer> --> List<Integer> --- gibt die Inverse Darstellung von Sigma aus (als Liste)
			//Bsp.:     [1,2,3]->[1,2,3]; [3,4,2,1] -> [4,3,1,2] [1] ->[1]; [] -> []

				Map<Integer,Integer> funktion = new HashMap<Integer,Integer>();
				Map<Integer,Integer> inverse = new HashMap<Integer,Integer>();
				List<Integer> result = new ArrayList<Integer>();

				//stellt Sigma als Map dar
				//Bsp.:
				//[2,1,4,3] -->  {1->2;2->1;3->4;4->3}
				for(int i = 0; i < this.getInternList().size();i++){
					funktion.put(i+1,this.getInternList().get(i));
				}

				//map funktion invertieren, d.h. keys werden values und values werden keys
				inverse = invert(funktion);

				//result erzeugen mit der nötigen groeße, gefellt mit Nullen
				result = createAry(this.getInternList().size());

				//inverse in Array gießen

				for(Map.Entry<Integer, Integer> entry : inverse.entrySet()){
					result.set(entry.getKey() - 1,entry.getValue());
				}

				return new PermutationImpl(result);	

		}

		// erzeugeArray: int --> List<Integer>  -- erzeugt einen Array mit der Laenge n, gefuellt mit Nullen
		//Bsp.: erzeugeArray(3) -->[0,0,0]
		public static List<Integer> createAry(int n){
			List<Integer> result = new ArrayList<Integer>();
			for(int i = 0; i < n; i++){
				result.add(0);
			}
			return result;
		}


		//invert Map<Integer,Integer> --> Map<Integer,Integer> -- vertaischt die keys und values
		//Bsp.:  {1->2; 2->3; 3->1} --> {2->1; 3->2; 1->3}
		public static Map<Integer,Integer> invert(Map<Integer,Integer> m){
			Map<Integer,Integer> result = new HashMap<Integer,Integer>();
			for(Map.Entry<Integer, Integer> entry : m.entrySet()){
				result.put(entry.getValue(),entry.getKey());
			}
			return result;
		}
	/*
	 * Block by Daniel and Fenja
	 */ 
	//Var für Permutation
	private List<Integer> perm = new ArrayList<Integer>();

	//Wandelt Permutation in Cycle Notation um
	//Bsp.: [2,1,3] -> [[2,1][3]]
	public List<List<Integer>> getAllCycles(){
		List<Integer> permList = perm; //Permutationsvariable
		List<List<Integer>> totalCycle = new ArrayList<List<Integer>>();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();

		//Array zu Map für Bearbeitung
		for (ListIterator<Integer> Iter = permList.listIterator(); Iter.hasNext(); ) {
			int index = Iter.nextIndex();
			map.put(index+1, permList.get(index));
			Iter.next();
		}
		return getAllCycles_(totalCycle,map,1);
	}

	//Hilfsfunktion für getAllCycles
	private List<List<Integer>> getAllCycles_(List<List<Integer>> totalCycle,Map<Integer,Integer> map, int currentKey){
		int newCurrentKey;
		List<Integer> singleCycle = new ArrayList<Integer>();
		//Einzelnen Cycle bestimmen
		while(map.containsKey(currentKey)){
			 newCurrentKey = map.get(currentKey);	//Wert bestimmen durch Key
			 singleCycle.add(newCurrentKey); 		//Wert zum Cycle hinzufügen
			 map.remove(currentKey);				//Wert aus Map entfernen
			 currentKey = newCurrentKey;			//Wert für nächsten Key festlegen
		}
		//Wenn singleCycle leer ist nicht zum Endergebnis hinzufügen
		if(!singleCycle.isEmpty()){
			totalCycle.add(singleCycle);	
		}		
		//Wenn Map nicht leer ist weiter suchen
		if(!map.isEmpty()){
			getAllCycles_(totalCycle,map, currentKey+1);
		}
		return totalCycle;
	}

	//Gibt i-ten Cycle zurück
	public List<Integer> getCycle(int index){
		List<List<Integer>> allCycles = getAllCycles();
		if(allCycles.size()<index || index <= 0){
			throw new RuntimeException();
		}
		return allCycles.get(index-1);
	}

	//Gibt Cycle Notation als String zurück
	public String cycleToString(){
		return getAllCycles().toString();
	}
	/*
	 * Block by Ben and Patrick
	 */
	/* (non-Javadoc)
		 * @see Permutation#compose(Permutation)
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
			for(Integer element: this) {
				resultList.add(other.sigma(element));
			}
			Permutation result = new PermutationImpl(resultList);
			return result;
		}

		public int hashCode() {
			// Delegate HashCode to element list
			return getElements().hashCode();
		}

		public boolean equals(Object other) {
			boolean result = false;

			// Reference Test
			if (this == other) {
				result = true;
			}
			// Type test (instanceof)
			else if (other instanceof Permutation) {
				// Attribute test
				if (this.getSize() == ((Permutation)other).getSize()
					&& this.getElements().equals(((Permutation)other).getElements())) {
					result = true;
				}
			}
			return result;
		}

		@Override
		public Iterator<Integer> iterator() {
			return getElements().iterator();
		}

		@Override
		public Integer getSize() {
			return getElements().size();
		}
}