package UnitTest;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestAsserts {
	
	static String Status = null;

	
	@Test()
	public void testm() {
		
		testAsset();
			
	}
	
	
	public static void testAsset() {
		
		
		
		try {
			int x = 2+1;
			Assert.assertEquals(4, x);
			
			 Status = "Passed";
			
		}catch(java.lang.AssertionError e) {
			
			System.out.println("Exception message : "+e.getMessage());
			 Status = "Failed";
			
		}
		
		System.out.println("Status : "+Status);

		
		System.out.println("Hello sami you are enhancing the way you aseert expected vs actual results then updating results to excel");
	}

}
