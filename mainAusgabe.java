
public class mainAusgabe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(PermutationImpl.valueOf("(1,2,3)(4,5)"));
		System.out.println(PermutationImpl.valueOf("(2,3,1)(4)"));
		System.out.println(PermutationImpl.valueOf("(1,2,3)(4)(5,6)(7)(8)"));
		
		System.out.println("===");
		System.out.println(PermutationImpl.s(1,5,9,2,6,10,3,7,11,4,8,12).allCycles());
	    System.out.println(PermutationImpl.s(1,5,9,2,6,10,3,7,11,4,8,12).toCycleNotationString());
	    System.out.println(PermutationImpl.valueOf("(1,2,3)").toCycleTypeString());
	    System.out.println(PermutationImpl.s(1,5,9,2,6,10,3,7,11,4,8,12).toCycleTypeString());
	    System.out.println(PermutationImpl.valueOf("(1,2,3)(4,5)(6,7,8,9)").toCycleTypeString()) ; 
	    System.out.println(NoPermutation.valueOf().toCycleTypeString());
	    System.out.println(PermutationImpl.s(1,5,9,2,6,10,3,7,11,4,8,12).cycle(1));
	    
	    System.out.println("===");
	    Permutation p1 = PermutationImpl.s(2,4,5,1,3);
	    System.out.println(p1);
	    
	    System.out.println("p1**0: " + p1.permPower(0));
	    System.out.println("p1**1: " + p1.permPower(1));
	    System.out.println("p1**1*p1: "+ p1.permPower(1).compose(p1));
	    System.out.println("p1**2: " + p1.permPower(2));
	    System.out.println("p1**3: " + p1.compose(p1.compose(p1)));
	    System.out.println(p1.permPower(3) +" == "+ p1.compose(p1.compose(p1)));
	    System.out.println(p1.permPower(5) +" == "+ p1.compose(p1.compose(p1.compose(p1.compose(p1)))));
	    System.out.println(p1.order() + " => "+ p1.permPower(6) + " == "+ p1.compose(p1.compose(p1.compose(p1.compose(p1.compose(p1))))));
	    Permutation p2 = PermutationImpl.s(1,5,9,2,6,10,3,7,11,4,8,12);
	    System.out.println(p2);
	    System.out.println(p2.order() + " => "+ p2.permPower(5));  
	    System.out.println(p1.inverse().compose(p1.inverse()) +" == "+ (p1.compose(p1)).inverse() +" == "+ p1.permPower(-2));
	    System.out.println("===");
	    
	   long t1= java.lang.System.currentTimeMillis();
	    Permutation p3= PermutationImpl.s(1,2,3,4,5);
	    System.out.println();
	    for (int i = 0; i <= 10000; i++) {
	      p3= p1.permPower(1000000000);}
	      
	    long t2= java.lang.System.currentTimeMillis() -t1;
	    System.out.println(p3 + " in "+t2+"ms");
	}


}
