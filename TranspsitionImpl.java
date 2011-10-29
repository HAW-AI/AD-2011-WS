import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TranspsitionImpl extends CycleImpl implements Transposition {
	private TranspsitionImpl(List<Integer> intList) {
		super(intList);
	}
	
	public static Transposition generate(Iterable<Integer> listIn){
		List<Integer> list = new ArrayList<Integer>();
		int size=0;
		
		for(int i : listIn){
			list.add(i);
			++size;
		}
		
		if(size != 2) return NoPermutation.valueOf();
		
		return new TranspsitionImpl(list);
	}
	
	@Override
	public String toString() {
		return "TranspositionImpl: "+ super.toString();
	}

	@Override
	public String toShortString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> i = getElems();
		
		sb.append("(");
		sb.append(i.next());
		sb.append(" ");
		sb.append(i.next());
		sb.append(")");
		
		return sb.toString();
	}
}
