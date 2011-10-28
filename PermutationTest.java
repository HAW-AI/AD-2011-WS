import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.*;
import org.omg.CORBA.portable.ValueOutputStream;

import static org.junit.Assert.*;

public class PermutationTest {
	
	@Test
	public void testRankUnrank(){
		List<Integer> permList = new ArrayList<Integer>();
		
		int upTo = 1;
		for(int i=1; i<=5; ++i){
			permList.add(i);
			upTo*= i;
		}
		
		Permutation p = PermutationImpl.valueOf(permList);
		
//		for(int i=0; i<upTo; ++i){
//			System.out.println("Rank["+i+"]: "+p.rankToPerm(i)+" ==> "+p.rankToPerm(i).rank());
//		}
		
		for(int i=0; i<upTo; ++i){
			assertEquals(i, p.rankToPerm(i).rank());
		}
	}
	
	@Test
	public void testCreateCycle(){
		assertEquals(pInput41, PermutationImpl.valueOf("(1)(2)(3)(4)"));
		assertEquals(pInput41, PermutationImpl.valueOf("(4)(2)(1)(3)"));
		
		assertEquals(pInput42, PermutationImpl.valueOf("(1)(2,4)(3)"));
		
		assertEquals(pInput71, PermutationImpl.valueOf("(1)(2)(3,5)(4)(7,6)"));
		
		assertEquals(pInput72, PermutationImpl.valueOf("(1)(2)(3)(4)(5)(6)(7)"));
		
		assertEquals(pInput101, PermutationImpl.valueOf("(1)(2)(3)(4)(5)(6)(7)(8)(9)(10)"));
		assertEquals(pInput101, PermutationImpl.valueOf("(10)(8)(2)(4)(6)(5)(7)(3)(9)(1)"));
		
		
		assertEquals(pInput102, PermutationImpl.valueOf("(1)(2)(3)(5,4)(8,6)(7)(9)(10)"));
		String s=null;
		assertEquals(NoPermutation.valueOf(),PermutationImpl.valueOf(s));
		assertEquals(NoPermutation.valueOf(),PermutationImpl.valueOf(""));
		assertEquals(NoPermutation.valueOf(),PermutationImpl.valueOf("1"));
		assertEquals(NoPermutation.valueOf(),PermutationImpl.valueOf("(1)(3)(3)(4)"));	
		assertEquals(NoPermutation.valueOf(), PermutationImpl.valueOf("()(3)(3)(4)"));
		assertEquals(NoPermutation.valueOf(), PermutationImpl.valueOf("(1,,2)(3)(4)"));
		assertEquals(NoPermutation.valueOf(), PermutationImpl.valueOf("(1)(2)(3)((4)"));

	}
  
	// Erstellen häufig verwendeter Permutationen
	// pInput41: [1, 2, 3, 4]
	// pInput42: [1, 4, 3, 2]
	// pInput71: [1, 2, 5, 4, 3, 7, 6] 
	// pInput72: [1, 2, 3, 4, 5, 6, 7]
	// pInput101: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
	// pInput102: [1, 2, 3, 5, 4, 8, 7, 6, 9, 10]
	// pOutput41: [1, 2, 3, 4]
	// pOutput71: [1, 2, 3, 4, 5, 6, 7]
	// pOutput101: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  
	static List<Integer> input41 = new ArrayList<Integer>();
	static List<Integer> input42 = new ArrayList<Integer>();
	static List<Integer> output41 = new ArrayList<Integer>();
	static List<Integer> output42 = new ArrayList<Integer>();
	
	static List<Integer> input71 = new ArrayList<Integer>();
	static List<Integer> input72 = new ArrayList<Integer>();
	static List<Integer> output71 = new ArrayList<Integer>();
	static List<Integer> output72 = new ArrayList<Integer>();
	
	static List<Integer> input101 = new ArrayList<Integer>();
	static List<Integer> input102 = new ArrayList<Integer>();
	static List<Integer> output101 = new ArrayList<Integer>();
	static List<Integer> output102 = new ArrayList<Integer>();
	

	
	@BeforeClass
	public static void setUpClass() throws Exception{
	
		input41.add(1);
		input41.add(2);
		input41.add(3);
		input41.add(4);
			
		input42.add(1);
		input42.add(4);
		input42.add(3);
		input42.add(2);
		
		output41.add(1);
		output41.add(2);
		output41.add(3);
		output41.add(4);
			
		output42.add(1);
		output42.add(2);
		output42.add(3);
		output42.add(4);
		
		input71.add(1);
		input71.add(2);
		input71.add(5);
		input71.add(4);
		input71.add(3);
		input71.add(7);
		input71.add(6);
		
		input72.add(1);
		input72.add(2);
		input72.add(3);
		input72.add(4);
		input72.add(5);
		input72.add(6);
		input72.add(7);
		
		output71.add(1);
		output71.add(2);
		output71.add(3);
		output71.add(4);
		output71.add(5);
		output71.add(6);
		output71.add(7);
		
		output72.add(1);
		output72.add(2);
		output72.add(3);
		output72.add(4);
		output72.add(5);
		output72.add(6);
		output72.add(7);
			
		input101.add(1);
		input101.add(2);
		input101.add(3);
		input101.add(4);
		input101.add(5);
		input101.add(6);
		input101.add(7);
		input101.add(8);
		input101.add(9);
		input101.add(10);
		
		input102.add(1);
		input102.add(2);
		input102.add(3);
		input102.add(5);
		input102.add(4);
		input102.add(8);
		input102.add(7);
		input102.add(6);
		input102.add(9);
		input102.add(10);
		
		output101.add(1);
		output101.add(2);
		output101.add(3);
		output101.add(4);
		output101.add(5);
		output101.add(6);
		output101.add(7);
		output101.add(8);
		output101.add(9);
		output101.add(10);
		
		output102.add(1);
		output102.add(2);
		output102.add(3);
		output102.add(4);
		output102.add(5);
		output102.add(6);
		output102.add(7);
		output102.add(8);
		output102.add(9);
		output102.add(10);
	

	
	}

	static Permutation pInput41 = PermutationImpl.valueOf(input41);
	static Permutation pInput42 = PermutationImpl.valueOf(input42);
	static Permutation pOutput41 = PermutationImpl.valueOf(output41);
	static Permutation pOutput42 = PermutationImpl.valueOf(output42);
	
	static Permutation pInput71 = PermutationImpl.valueOf(input71);
	static Permutation pInput72 = PermutationImpl.valueOf(input72);
	static Permutation pOutput71 = PermutationImpl.valueOf(output71);
	static Permutation pOutput72 = PermutationImpl.valueOf(output72);
	
	static Permutation pInput101 = PermutationImpl.valueOf(input101);
	static Permutation pInput102 = PermutationImpl.valueOf(input102);
	static Permutation pOutput101 = PermutationImpl.valueOf(output101);
	static Permutation pOutput102 = PermutationImpl.valueOf(output102);

	// Positivtest von sigma()

	@Test
	public void testSigma(){
		assertEquals(3, pInput41.getPermElement(3));		
		assertEquals(4, pInput42.getPermElement(2));
		
		assertEquals(5, pInput71.getPermElement(3));		
		assertEquals(2, pInput72.getPermElement(2));
		
		assertEquals(3, pInput101.getPermElement(3));		
		assertEquals(2, pInput102.getPermElement(2));
		
	}

	// Negativtest von sigma()

	@Test (expected=IllegalArgumentException.class)
	public void testSigmaException1(){
		assertEquals(3, pInput41.getPermElement(5));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSigmaException2(){
		assertEquals(3, pInput42.getPermElement(-1));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSigmaException3(){
		assertEquals(3, pInput71.getPermElement(8));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSigmaException4(){
		assertEquals(3, pInput72.getPermElement(-1));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSigmaException5(){
		assertEquals(3, pInput101.getPermElement(11));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSigmaException6(){
		assertEquals(3, pInput102.getPermElement(-1));
	}
	
	// Positivtest von cycle()
	
	@Test
	public void testCycle(){
		assertEquals(pInput42, pInput42.cycle(2));
		assertEquals(pInput41, pInput42.cycle(1));
		
		assertEquals(PermutationImpl.valueOf("(1)(2)(3,5)(4)(6)(7)"), pInput71.cycle(3));
		assertEquals(PermutationImpl.valueOf("(1)(2)(3)(4)(5)(7,6)"), pInput71.cycle(5));
		assertEquals(PermutationImpl.valueOf("(1)(2)(3)(4)(5)(6)(7)"), pInput72.cycle(5));

		assertEquals(pInput101, pInput101.cycle(4));
		assertEquals(PermutationImpl.valueOf("(1)(2)(3)(5,4)(6)(7)(8)(9)(10)"), pInput102.cycle(4));
	}
	
	// Negativtest von cycle()
	
	@Test
	public void testCycleException1(){
		assertEquals(NoPermutation.valueOf(), pInput41.cycle(5));
	}
	@Test
	public void testCycleException2(){
		assertEquals(NoPermutation.valueOf(), pInput42.cycle(-1));
	}
	@Test
	public void testCycleException3(){
		assertEquals(NoPermutation.valueOf(), pInput71.cycle(8));
	}
	@Test
	public void testCycleException4(){
		assertEquals(NoPermutation.valueOf(), pInput72.cycle(-1));
	}
	@Test
	public void testCycleException5(){
		assertEquals(NoPermutation.valueOf(), pInput101.cycle(11));
	}
	@Test
	public void testCycleException6(){
		assertEquals(NoPermutation.valueOf(), pInput102.cycle(-1));
	}
	
	// Test von allCycles()
	
	@Test
	public void testAllCycles(){
		
		// Erstellen zusätzlicher Ergebniswerte
		//erg41: [[3], [4], [1], [2]]
		//erg42: [[3], [1], [4, 2]]
		//erg71: [[4], [5, 3], [1], [7, 6], [2]]
		//erg72: [[3], [4], [1], [2], [7], [5], [6]]
		//erg101: [[3], [4], [1], [2], [7], [8], [5], [6], [9], [10]]
		//erg102: [[3], [1], [2], [7], [9], [10], [8, 6], [5, 4]]
		
		List<Integer> inErg411 = new ArrayList<Integer>();
		List<Integer> inErg412 = new ArrayList<Integer>();
		List<Integer> inErg413 = new ArrayList<Integer>();
		List<Integer> inErg414 = new ArrayList<Integer>();
		Set<List<Integer>> erg41 = new HashSet<List<Integer>>();
		
		List<Integer> inErg421 = new ArrayList<Integer>();
		List<Integer> inErg422 = new ArrayList<Integer>();
		List<Integer> inErg423 = new ArrayList<Integer>();
		Set<List<Integer>> erg42 = new HashSet<List<Integer>>();
		
		List<Integer> inErg711 = new ArrayList<Integer>();
		List<Integer> inErg712 = new ArrayList<Integer>();
		List<Integer> inErg713 = new ArrayList<Integer>();
		List<Integer> inErg714 = new ArrayList<Integer>();
		List<Integer> inErg715 = new ArrayList<Integer>();
		Set<List<Integer>> erg71 = new HashSet<List<Integer>>();
		
		List<Integer> inErg721 = new ArrayList<Integer>();
		List<Integer> inErg722 = new ArrayList<Integer>();
		List<Integer> inErg723 = new ArrayList<Integer>();
		List<Integer> inErg724 = new ArrayList<Integer>();
		List<Integer> inErg725 = new ArrayList<Integer>();
		List<Integer> inErg726 = new ArrayList<Integer>();
		List<Integer> inErg727 = new ArrayList<Integer>();
		Set<List<Integer>> erg72 = new HashSet<List<Integer>>();
		
		List<Integer> inErg1011 = new ArrayList<Integer>();
		List<Integer> inErg1012 = new ArrayList<Integer>();
		List<Integer> inErg1013 = new ArrayList<Integer>();
		List<Integer> inErg1014 = new ArrayList<Integer>();
		List<Integer> inErg1015 = new ArrayList<Integer>();
		List<Integer> inErg1016 = new ArrayList<Integer>();
		List<Integer> inErg1017 = new ArrayList<Integer>();
		List<Integer> inErg1018 = new ArrayList<Integer>();
		List<Integer> inErg1019 = new ArrayList<Integer>();
		List<Integer> inErg10110 = new ArrayList<Integer>();
		Set<List<Integer>> erg101 = new HashSet<List<Integer>>();
		
		List<Integer> inErg1021 = new ArrayList<Integer>();
		List<Integer> inErg1022 = new ArrayList<Integer>();
		List<Integer> inErg1023 = new ArrayList<Integer>();
		List<Integer> inErg1024 = new ArrayList<Integer>();
		List<Integer> inErg1025 = new ArrayList<Integer>();
		List<Integer> inErg1026 = new ArrayList<Integer>();
		List<Integer> inErg1027 = new ArrayList<Integer>();
		List<Integer> inErg1028 = new ArrayList<Integer>();
		Set<List<Integer>> erg102 = new HashSet<List<Integer>>();
		
		inErg411.add(1);
		inErg412.add(2);
		inErg413.add(3);
		inErg414.add(4);
			
		inErg421.add(1);
		inErg422.add(4);
		inErg423.add(3);
		inErg422.add(2);
		
		inErg711.add(1);
		inErg712.add(2);
		inErg713.add(5);
		inErg714.add(4);
		inErg713.add(3);
		inErg715.add(7);
		inErg715.add(6);
		
		inErg721.add(1);
		inErg722.add(2);
		inErg723.add(3);
		inErg724.add(4);
		inErg725.add(5);
		inErg726.add(6);
		inErg727.add(7);
			
		inErg1011.add(1);
		inErg1012.add(2);
		inErg1013.add(3);
		inErg1014.add(4);
		inErg1015.add(5);
		inErg1016.add(6);
		inErg1017.add(7);
		inErg1018.add(8);
		inErg1019.add(9);
		inErg10110.add(10);
		
		inErg1021.add(1);
		inErg1022.add(2);
		inErg1023.add(3);
		inErg1024.add(5);
		inErg1024.add(4);
		inErg1025.add(8);
		inErg1028.add(7);
		inErg1025.add(6);
		inErg1026.add(9);
		inErg1027.add(10);

		erg41.add(inErg411);
		erg41.add(inErg412);
		erg41.add(inErg413);
		erg41.add(inErg414);
		
		erg42.add(inErg421);
		erg42.add(inErg422);
		erg42.add(inErg423);
		
		erg71.add(inErg711);
		erg71.add(inErg712);
		erg71.add(inErg713);
		erg71.add(inErg714);
		erg71.add(inErg715);
		
		erg72.add(inErg721);
		erg72.add(inErg722);
		erg72.add(inErg723);
		erg72.add(inErg724);
		erg72.add(inErg725);
		erg72.add(inErg726);
		erg72.add(inErg727);
		
		erg101.add(inErg1011);
		erg101.add(inErg1012);
		erg101.add(inErg1013);
		erg101.add(inErg1014);
		erg101.add(inErg1015);
		erg101.add(inErg1016);
		erg101.add(inErg1017);
		erg101.add(inErg1018);
		erg101.add(inErg1019);
		erg101.add(inErg10110);
		
		erg102.add(inErg1021);
		erg102.add(inErg1022);
		erg102.add(inErg1023);
		erg102.add(inErg1024);
		erg102.add(inErg1025);
		erg102.add(inErg1026);
		erg102.add(inErg1027);
		erg102.add(inErg1028);
		
		assertEquals(erg41, pInput41.allCycles());
		assertEquals(erg42, pInput42.allCycles());
		
		assertEquals(erg71, pInput71.allCycles());
		assertEquals(erg72, pInput72.allCycles());
		
		assertEquals(erg101, pInput101.allCycles());
		assertEquals(erg102, pInput102.allCycles());
	}
	
	// Test von fixedPoints()
	
	@Test
	public void testFixedPoints(){
		
		// Erstellen zusätzlicher Ergebniswerte
		// erg41: [1, 2, 3, 4]
		// erg42: [1, 3]
		// erg71: [1, 2, 4]
		// erg72: [1, 2, 3, 4, 5, 6, 7]
		// erg101: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		// erg102: [1, 2, 3, 7, 9, 10]

		Set<Integer> erg41 = new HashSet<Integer>();
		erg41.add(4);
		erg41.add(2);
		erg41.add(1);
		erg41.add(3);
		
		Set<Integer> erg42 = new HashSet<Integer>();
		erg42.add(1);
		erg42.add(3);
		
		assertEquals(erg41, pInput41.fixedPoints());
		assertEquals(erg42, pInput42.fixedPoints());
		
		Set<Integer> erg71 = new HashSet<Integer>();
		erg71.add(1);
		erg71.add(2);
		erg71.add(4);
		
		Set<Integer> erg72 = new HashSet<Integer>();
		erg72.add(1);
		erg72.add(2);
		erg72.add(3);
		erg72.add(4);
		erg72.add(5);
		erg72.add(6);
		erg72.add(7);
		
		assertEquals(erg71, pInput71.fixedPoints());
		assertEquals(erg72, pInput72.fixedPoints());

		Set<Integer> erg101 = new HashSet<Integer>();
		erg101.add(1);
		erg101.add(2);
		erg101.add(3);
		erg101.add(4);
		erg101.add(5);
		erg101.add(6);
		erg101.add(7);
		erg101.add(8);
		erg101.add(9);
		erg101.add(10);
		
		Set<Integer> erg102 = new HashSet<Integer>();
		erg102.add(1);
		erg102.add(2);
		erg102.add(3);
		erg102.add(7);
		erg102.add(9);
		erg102.add(10);
		
		assertEquals(erg101, pInput101.fixedPoints());
		assertEquals(erg102, pInput102.fixedPoints());
		
	}
	
	//Erstellen zusätzlicher Ergebniswerte
	// ipInput42: [1, 4, 3, 2]
	// ipInput72: [1, 2, 5, 4, 3, 7, 6]
	// ipInput102: [1, 2, 3, 5, 4, 8, 7, 6, 9, 10]

	static List<Integer> iInput42 = new ArrayList<Integer>();
	static Permutation ipInput42 = PermutationImpl.valueOf(iInput42);

	static List<Integer> iInput71 = new ArrayList<Integer>();
	static Permutation ipInput72 = PermutationImpl.valueOf(iInput71);

	static List<Integer> iInput101 = new ArrayList<Integer>();
	static Permutation ipInput102 = PermutationImpl.valueOf(iInput101);
	
	// Test von inverse()
	
	@Test
	public void testInverse() {
		

		iInput42.add(1);
		iInput42.add(4);
		iInput42.add(3);
		iInput42.add(2);
		
		iInput71.add(1);
		iInput71.add(2);
		iInput71.add(5);
		iInput71.add(4);
		iInput71.add(3);
		iInput71.add(7);
		iInput71.add(6);

		iInput101.add(1);
		iInput101.add(2);
		iInput101.add(3);
		iInput101.add(5);
		iInput101.add(4);
		iInput101.add(8);
		iInput101.add(7);
		iInput101.add(6);
		iInput101.add(9);
		iInput101.add(10);
		
		assertEquals(pInput42.inverse(),ipInput42);
		assertEquals(pInput71.inverse(),ipInput72);
		assertEquals(pInput102.inverse(),ipInput102);

	}
	
	// Erstellen zusätzlicher Ergebniswerte
	// pInput43: [4, 3, 1, 2]
	// pInput44: [4, 2, 1, 3]
	// pInput73: [4, 6, 2, 1, 7, 5, 3]
	// pInput74: [4, 6, 7, 1, 2, 3, 5]
	// pInput103: [4, 7, 9, 2, 6, 8, 10, 1, 3, 5]
	// pInput104: [4, 7, 9, 6, 2, 1, 10, 8, 3, 5]
	
	static List<Integer> Input43 = new ArrayList<Integer>();
	static Permutation pInput43 = PermutationImpl.valueOf(Input43);

	static List<Integer> Input73 = new ArrayList<Integer>();
	static Permutation pInput73 = PermutationImpl.valueOf(Input73);

	static List<Integer> Input103 = new ArrayList<Integer>();
	static Permutation pInput103 = PermutationImpl.valueOf(Input103);
	
	static List<Integer> Input44 = new ArrayList<Integer>();
	static Permutation pInput44 = PermutationImpl.valueOf(Input44);

	static List<Integer> Input74 = new ArrayList<Integer>();
	static Permutation pInput74 = PermutationImpl.valueOf(Input74);

	static List<Integer> Input104 = new ArrayList<Integer>();
	static Permutation pInput104 = PermutationImpl.valueOf(Input104);
	
	// Test von compose()
	
	@Test
	public void testCompose() {

		Input43.add(4);
		Input43.add(3);
		Input43.add(1);
		Input43.add(2);

		Input44.add(4);
		Input44.add(2);
		Input44.add(1);
		Input44.add(3);
		

		Input73.add(4);
		Input73.add(6);
		Input73.add(2);
		Input73.add(1);
		Input73.add(7);
		Input73.add(5);
		Input73.add(3);

		Input74.add(4);
		Input74.add(6);
		Input74.add(7);
		Input74.add(1);
		Input74.add(2);
		Input74.add(3);
		Input74.add(5);
		

		Input103.add(4);
		Input103.add(7);
		Input103.add(9);
		Input103.add(2);
		Input103.add(6);
		Input103.add(8);
		Input103.add(10);
		Input103.add(1);
		Input103.add(3);
		Input103.add(5);

		Input104.add(4);
		Input104.add(7);
		Input104.add(9);
		Input104.add(6);
		Input104.add(2);
		Input104.add(1);
		Input104.add(10);
		Input104.add(8);
		Input104.add(3);
		Input104.add(5);

		assertEquals(pInput42.compose(pInput43),pInput44);
		assertEquals(pInput71.compose(pInput73),pInput74);
		assertEquals(pInput102.compose(pInput103),pInput104);
		

	}
	
    	@Test
    	public void testComposeException1() {
    		assertEquals(pInput42.compose(pInput73),NoPermutation.valueOf());
	}
    	@Test
    	public void testComposeException2() {
    		assertEquals(pInput71.compose(pInput103),NoPermutation.valueOf());
    	}
    	@Test
    	public void testComposeException3() {
    		assertEquals(pInput102.compose(pInput43),NoPermutation.valueOf());
    	}
	
	// Test von permutationClass()
	
	@Test
	public void testPermutationClass(){
		
		assertEquals(4, pInput41.permutationClass());
		assertEquals(4, pInput42.permutationClass());
		
		assertEquals(7, pInput71.permutationClass());
		assertEquals(7, pInput72.permutationClass());
		
		assertEquals(10, pInput101.permutationClass());
		assertEquals(10, pInput102.permutationClass());
		
	}
	
	// Test von equals()
	
	@Test
	public void testEquals(){
		
		assertTrue(pInput41.equals(pInput41));
		assertTrue(pOutput41.equals(pInput41));
		assertTrue(pInput72.equals(pOutput71));
		assertTrue(pOutput102.equals(pInput101));
		assertTrue(pInput41.equals(pOutput42));
		
		assertFalse(pInput42.equals(pOutput42));
		assertFalse(pInput72.equals(pOutput42));
		assertFalse(pInput102.equals(pOutput71));
		assertFalse(pInput42.equals("Hallo Welt"));
		assertFalse(pInput42.equals(7));
		assertFalse(pInput42.equals(input42));
		assertFalse(pInput42.equals(-1337));
		
	}
	
		// Test von toString()
	
	@Test
	public void testToString(){
		assertEquals("(1 2 3 4)", pInput41.toString());
		assertEquals("(1 4 3 2)", pInput42.toString());
		assertEquals("(1 2 5 4 3 7 6)", pInput71.toString());
		assertEquals("(1 2 3 4 5 6 7)", pInput72.toString());
		assertEquals("(1 2 3 4 5 6 7 8 9 10)", pInput101.toString());
		assertEquals("(1 2 3 5 4 8 7 6 9 10)", pInput102.toString());
	}
	
//	@Test
//	public void testKgv() {
//		List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(12,36,6,36));
//		assertEquals(36,PermutationImpl.kgv(l1));
//	}
	
	@Test
	public void testOrder() {
		Permutation p1 = PermutationImpl.s(1,2,3,4,5);
		Permutation p2 = PermutationImpl.s(2,1,3,5,6,4);
		Permutation p3 = PermutationImpl.s(1,2,3,4,5,6);
		Permutation p4 = PermutationImpl.s();
		
		// Order = 1
		assertEquals(1, p1.order());
		// Order = 6
		assertEquals(6, p2.order());
		// p2^6 = id 
		assertEquals(p3, p2.compose(p2.compose(p2.compose(p2.compose(p2.compose(p2))))));
		// Order einer leeren Permutation = 0
		assertEquals(0, p4.order());		
	}
	
	@Test
	public void testPermPower(){
		Permutation p1 = PermutationImpl.s(2,1,3,5,6,4);
		Permutation p2 = PermutationImpl.s(1,2,3,6,4,5);
		Permutation p3 = PermutationImpl.s(2,1,3,4,5,6);
		Permutation p4 = PermutationImpl.s(1,2,3,5,6,4);
		Permutation p5 = PermutationImpl.s(2,1,3,6,4,5);
		Permutation id = PermutationImpl.s(1,2,3,4,5,6);
		assertEquals(p1,p1.permPower(1));
		assertEquals(p2,p1.permPower(2));
		assertEquals(p3,p1.permPower(3));
		assertEquals(p4,p1.permPower(4));
		assertEquals(p5,p1.permPower(5));
		assertEquals(id,p1.permPower(6));
		assertEquals(p1,p1.permPower(7));
		assertEquals(p1,p1.permPower(14));
		assertEquals(p1,p1.permPower(7000000));
		
		assertEquals(p1.id(), p1.permPower(0));
		
		assertEquals(p1.inverse(), p1.permPower(-1));
		
		assertEquals(p1.inverse().permPower(100), p1.permPower(-100));
	
	}
	@Test
	  public void testCycleType(){
	        Permutation p1 = PermutationImpl.s(2,1,3,5,6,4);
	        Permutation p2 = PermutationImpl.s(1,2,3,4,5,6);
	        Permutation p3 = PermutationImpl.s(6,4,1,3,2,5);
	        Map<Integer,Integer> erg1 = new HashMap<Integer,Integer>();
	        Map<Integer,Integer> erg2 = new HashMap<Integer,Integer>();
	        Map<Integer,Integer> erg3 = new HashMap<Integer,Integer>();
	        erg1.put(1,1);
	        erg1.put(2,1);
	        erg1.put(3,1);
	        erg2.put(1,6);
	        erg3.put(6,1);
	        assertEquals(erg1,p1.cycleType());
	        assertEquals(erg2,p2.cycleType());
	        assertEquals(erg3,p3.cycleType());
	  }
	  @Test
	  public void testToCycleTypeString(){
	        Permutation p1 = PermutationImpl.s(2,1,3,5,6,4);
	        Permutation p2 = PermutationImpl.s(1,2,3,4,5,6);
	        assertEquals("[1^1, 2^1, 3^1]",p1.toCycleTypeString());
	        assertEquals("[1^6]",p2.toCycleTypeString());
	  }
	  
	  @Test
	  	public void testToTranspositions() {
//		  	Permutation p1 = PermutationImpl.s(3,4,6,5,7,1,2);
//		  	Permutation p2 = PermutationImpl.s(2,3,1);
//		  	Permutation p3 = PermutationImpl.s(1,2,3);
//		  	Permutation p4 = PermutationImpl.s(4,6,3,1,2,5);
//		  	Permutation p5 = PermutationImpl.s(1);
//		  	String s=null;
//		  	Permutation p6 = PermutationImpl.valueOf(s);
//		  	Permutation p7 = PermutationImpl.s();
//		  	assertEquals("((3 1)(6 3)(4 2)(7 4)(5 4))",p1.toTranspositionString());
//		  	assertEquals("((2 1)(3 2))",p2.toTranspositionString());
//		  	assertEquals("NoTransposition",p3.toTranspositionString());
//		  	assertEquals("((4 1)(6 2)(6 5))",p4.toTranspositionString());
//		  	assertEquals("NoTransposition",p5.toTranspositionString());
//		  	assertEquals("NoTransposition",p6.toTranspositionString());
//		  	assertEquals("NoTransposition",p7.toTranspositionString());
	} //TODO: fix
		
		@Test
		public void testSign() {
			Permutation p1 = PermutationImpl.s(3,4,6,5,7,1,2);
			Permutation p2 = PermutationImpl.s(2,3,1);
			Permutation p3 = PermutationImpl.s(1,2,3);
			Permutation p4 = PermutationImpl.s(4,6,3,1,2,5);
			Permutation p5 = PermutationImpl.s(1);
			Permutation p6 = PermutationImpl.s();
			String s=null;
			Permutation p7 = PermutationImpl.valueOf(s);
			
			assertEquals(-1, p1.sign());
			assertEquals(1, p2.sign());
			assertEquals(1, p3.sign());
			assertEquals(-1, p4.sign());
			assertEquals(1, NoPermutation.valueOf().sign());
		}
	@Test
    public void testId() {
        Permutation p1 = PermutationImpl.s(1, 2, 3, 4, 5);
        Permutation p2 = PermutationImpl.s(2, 1, 3, 5, 4);

        Permutation p3 = PermutationImpl.s(3, 4, 6, 5, 7, 1, 2);
        Permutation p4 = PermutationImpl.s(2, 3, 1);
        Permutation p5 = PermutationImpl.s(1, 2, 3);
        Permutation p6 = PermutationImpl.s(4, 6, 3, 1, 2, 5);
        Permutation p7 = PermutationImpl.s(2, 1, 3);
        Permutation p8 = PermutationImpl.s(3, 2, 1);

        assertEquals(p1, p1.id());
        assertEquals(p1, p2.id());
       
        assertEquals(p5, p4.id());
        assertEquals(p5, p7.id());
        assertEquals(p5, p8.id());
    }
	   @Test
       public void testRankToPerm() {
           Permutation p1 = PermutationImpl.s(1,2,3,4);
           Permutation p2 = PermutationImpl.s(2,1,4,3);
           Permutation p3 = PermutationImpl.s(4,3,2,1);

           assertEquals(p1, p1.rankToPerm(0));
           assertEquals(p2, p1.rankToPerm(7));
           assertEquals(p3, p1.rankToPerm(23));

           assertEquals(NoPermutation.valueOf(), p1.rankToPerm(24));
       }

       @Test
       public void testRank() {
           Permutation p1 = PermutationImpl.s(1,2,3,4);
           Permutation p2 = PermutationImpl.s(2,1,4,3);

           assertEquals(0, p1.rank());
           assertEquals(7, p2.rank());

           assertEquals(-1, NoPermutation.valueOf().rank());
       }	  
}

