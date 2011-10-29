import java.util.ArrayList;
import java.util.List;


public class CycleImpl extends PermutationImpl implements Cycle {
	
	protected CycleImpl(List<Integer> intList) {
		super(intList);
	}
	
	public static Cycle generate(Iterable<Integer> listIn){
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i : listIn){
			list.add(i);
		}
		
		return new CycleImpl(list);
	}
	
	@Override
	public String toString() {
		return "CycleImpl: "+ super.toString();
	}
}
