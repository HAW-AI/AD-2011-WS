import java.util.ArrayList;
import java.util.List;


public class TranspsitionImpl extends CycleImpl implements Transposition {
	private TranspsitionImpl(List<Integer> intList) {
		super(intList);
	}
	
	public static Transposition generate(Iterable<Integer> listIn){
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i : listIn){
			list.add(i);
		}
		
		return new TranspsitionImpl(list);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() +": "+ super.toString();
	}
}
