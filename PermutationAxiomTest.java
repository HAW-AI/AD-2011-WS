import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PermutationAxiomTest {

      	static List<Integer> test = new ArrayList<Integer>();
			static Permutation p = PermutationImpl.valueOf(test);

    		static List<Integer> test4 = new ArrayList<Integer>();
			static Permutation p4 = PermutationImpl.valueOf(test4);
			
    		static List<Integer> test7 = new ArrayList<Integer>();
			static Permutation p7 = PermutationImpl.valueOf(test7);
			
    		static List<Integer> test10 = new ArrayList<Integer>();
			static Permutation p10 = PermutationImpl.valueOf(test10);
			
    		static List<Integer> test41 = new ArrayList<Integer>();
			static Permutation p41 = PermutationImpl.valueOf(test41);
			
    		static List<Integer> test71 = new ArrayList<Integer>();
			static Permutation p71 = PermutationImpl.valueOf(test71);
			
    		static List<Integer> test101 = new ArrayList<Integer>();
			static Permutation p101 = PermutationImpl.valueOf(test101);
			
    		static List<Integer> test42 = new ArrayList<Integer>();
			static Permutation p42 = PermutationImpl.valueOf(test42);
			
    		static List<Integer> test72 = new ArrayList<Integer>();
			static Permutation p72 = PermutationImpl.valueOf(test72);
			
    		static List<Integer> test102 = new ArrayList<Integer>();
			static Permutation p102 = PermutationImpl.valueOf(test102);
			
    		static List<Integer> test42i = new ArrayList<Integer>();
			static Permutation p42i = PermutationImpl.valueOf(test42i);
			
    		static List<Integer> test4c = new ArrayList<Integer>();
			static Permutation p4c = PermutationImpl.valueOf(test4c);

		
    @BeforeClass
    public static void setUpClass() throws Exception {
    	
			test.add(1);
			test4.add(1);
			test7.add(1);
			test10.add(1);
			test4.add(2);
			test7.add(2);
			test10.add(2);
			test4.add(3);
			test7.add(3);
			test10.add(3);
			test4.add(4);
			test7.add(4);
			test10.add(4);
			test7.add(5);
			test10.add(5);
			test7.add(6);
			test10.add(6);
			test7.add(7);
			test10.add(7);
			test10.add(8);
			test10.add(9);
			test10.add(10);
			

			test41.add(3);
			test71.add(3);
			test101.add(3);
			test41.add(2);
			test71.add(2);
			test101.add(2);
			test41.add(4);
			test71.add(4);
			test101.add(4);
			test41.add(1);
			test71.add(1);
			test101.add(1);
			test71.add(7);
			test101.add(7);
			test71.add(6);
			test101.add(6);
			test71.add(5);
			test101.add(5);
			test101.add(10);
			test101.add(9);
			test101.add(8);
			

			test42.add(2);
			test72.add(2);
			test102.add(2);
			test42.add(3);
			test72.add(3);
			test102.add(3);
			test42.add(1);
			test72.add(1);
			test102.add(1);
			test42.add(4);
			test72.add(4);
			test102.add(4);
			test72.add(7);
			test102.add(7);
			test72.add(5);
			test102.add(5);
			test72.add(6);
			test102.add(6);
			test102.add(9);
			test102.add(8);
			test102.add(10);
			
			test42i.add(3);
			test42i.add(1);
			test42i.add(2);
			test42i.add(4);
			
			test4c.add(3);
			test4c.add(1);
			test4c.add(2);
			test4c.add(4);
			
    }
    
		//Axioms
		@Test
		public void testAxioms(){
			
//			s1
//			inverse(sgm) = sgm
//			inverse(sgm) = compose(sgm,sgm)
//			compose(sgm,inverse(sgm)) = sgm
//			equals(sgm,sgm) = true
			
			assertEquals(p.inverse(), p);
			assertEquals(p.inverse(), p.compose(p));
			assertEquals(p.inverse().compose(p),p);
			assertEquals(p,p);
			
//			compose(sgm1,compose(sgm2,sgm3)) = compose(compose(sgm1,sgm2),sgm3)
			assertEquals(p4.compose(p41.compose(p42)),p4.compose(p41).compose(p42));
			assertEquals(p71.compose(p72.compose(p7)),p71.compose(p72).compose(p7));
			assertEquals(p102.compose(p10.compose(p101)),p102.compose(p10).compose(p101));
			
//			equals(sgm1,id) = true & (sgm1 != sgm2) = false => fixedPoints(sgm1) != fixedPoints(sgm2)
			assertTrue((p4.equals(p4)&&(!(p4 == p42)))&& (!(p4.fixedPoints().equals(p42.fixedPoints()))));
			assertTrue((p7.equals(p7)&&(!(p7 == p72)))&& (!(p7.fixedPoints().equals(p72.fixedPoints()))));
			assertTrue((p10.equals(p10)&&(!(p10 == p102)))&& (!(p10.fixedPoints().equals(p102.fixedPoints()))));
			
//			inverse(inverse(sgm)) = sgm
			assertEquals(p41.inverse().inverse(),p41);
			assertEquals(p72.inverse().inverse(),p72);
			assertEquals(p10.inverse().inverse(),p10);
			
//			equals(sgm1,sgm2) = equals(sgm2,sgm1)
			assertEquals(p41.equals(p4),p4.equals(p41));
			assertEquals(p72.equals(p71),p71.equals(p72));
			assertEquals(p10.equals(p102),p102.equals(p10));
			
//			equals(sgm1,sgm2) => equals(cycle(sgm1), cycle(sgm2))
			assertTrue(p42.equals(p42) && p42.cycle(1).equals(p42.cycle(1)));

//			equals(sgm1,sgm2) => equals(inverse(sgm1), inverse(sgm2))
			assertTrue(p42.equals(p42) && p42.inverse().equals(p42.inverse()));
			assertTrue(p72.equals(p72) && p72.inverse().equals(p72.inverse()));

//			equals(sgm * inverse(sgm), id) (falls "id" ein literal ist)
			assertEquals(p42.compose(p42.inverse()),p4);
			assertEquals(p71.compose(p71.inverse()),p7);
			assertEquals(p102.compose(p102.inverse()),p10);
			
//			equals(inverse(sgm) * sgm, id)
			assertEquals(p42.inverse().compose(p42),p4);
			assertEquals(p71.inverse().compose(p71),p7);
			assertEquals(p102.inverse().compose(p102),p10);
			
//			equals(inverse(sgm1),sgm2) => equals(inverse(sgm2), sgm1)
			assertTrue(p42.inverse().equals(p42i) && p42i.inverse().equals(p42));

//			equals(inverse(inverse(sgm)), sgm)
			assertTrue(p41.inverse().inverse().equals(p41));
			assertTrue(p72.inverse().inverse().equals(p72));
			assertTrue(p10.inverse().inverse().equals(p10));

//			equals(id, inverse(id))
			assertTrue(p4.equals(p4.inverse()));
			assertTrue(p7.equals(p7.inverse()));
			assertTrue(p10.equals(p10.inverse()));

//			equals(sgm * id, sgm)
			assertTrue(p42.compose(p4).equals(p42));
			assertTrue(p72.compose(p7).equals(p72));
			assertTrue(p102.compose(p10).equals(p102));

//			equals(id * sgm, sgm)
			assertTrue(p4.compose(p42).equals(p42));
			assertTrue(p7.compose(p72).equals(p72));
			assertTrue(p10.compose(p102).equals(p102));

//			equals(sgm1* (sgm2 * sgm3), (sgm1 * sgm2) * sgm3)
			assertTrue(p4.compose(p42.compose(p41)).equals((p4.compose(p42)).compose(p41)));
			assertTrue(p72.compose(p71.compose(p7)).equals((p72.compose(p71)).compose(p7)));
			assertTrue(p101.compose(p10.compose(p102)).equals((p101.compose(p10)).compose(p102)));

			
          System.out.println(p4c);
	        System.out.println(p42);
	        System.out.println(p4c.allCycles());
	        System.out.println(p42.allCycles());
			assertTrue(p4c.equals(p42));
			}

		
		}