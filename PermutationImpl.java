public PermutationImpl extends Permutation {
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
}