package edu.csbsju.ntc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import controller.*;
import interaction.*;
import entity.*;

public class DatabaseControllerTest {
	DatabaseController dbControl;
	AdminInteraction ai;
	University u;

	@Before
	public void setUp() throws Exception {
		dbControl = new DatabaseController(); 
		ai = new AdminInteraction("nadmin", "password");
		ai.addUniversity("ADELPHI", "NEW YORK", "-1", "PRIVATE", 15000, 70, 500, 475, 37437, 60, 5500, 70, 40, 2, 2, 2);
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 35000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		
	}

	//@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}
	
	@Test
	public void nullNamedeleteUniversity()
	{
		assertFalse(dbControl.deleteUniversity(null));
	}
	
	@Test
	public void validNameFailDeleteUniversity()
	{
		assertFalse("BARNARD will not be deleted", dbControl.deleteUniversity("BARNARD"));
	}
	
	@Test
	public void validNameDeleteUniversity()
	{
		assertTrue("Adelphi will be removed", dbControl.deleteUniversity("ADELPHI"));
	}
	
	@Test
	public void invalidNameGetEmphasis()
	{
		assertTrue("Emphasis list will be empty", dbControl.getEmphasis("BRAD").isEmpty());
	}
	
	@Test
	public void validNameNoEmphasis()
	{
		assertTrue("Emphasis will be empty", dbControl.getEmphasis("CSB").isEmpty());
	}
	
	@Test
	public void validNameWithEmphasis()
	{
		assertTrue("Emphasis length for Augsburg will be 6", dbControl.getEmphasis("AUGSBURG").size() == 6);
		assertTrue("Emphasis length for Auburn will be 12", dbControl.getEmphasis("AUBURN").size() == 12);
		assertTrue("Emphasis length for CAL TECH will be 2", dbControl.getEmphasis("CAL TECH").size() == 2);
		
	}
	
	@Test
	public void nullNameGetUniversity()
	{
		assertEquals("University object is null", dbControl.getUniversity(null), null);
	}
	
	@Test
	public void invalidNameGetUniversity()
	{
		assertEquals("University object is null", dbControl.getUniversity("AUBRUN"), null);
	}
	
	@Test
	public void validNameGetUniversity()
	{
		assertEquals("University is AUBURN", dbControl.getUniversity("AUBURN").getUniversityName(), "AUBURN");
		assertEquals("University is AUBURN, STATE IS ALABAMA", dbControl.getUniversity("AUBURN").getUniversityState(), "ALABAMA");
		assertEquals("University is AUBURN, LOCATION IS SMALL-CITY", dbControl.getUniversity("AUBURN").getLocationType(), "SMALL-CITY");
		assertEquals("University is AUBURN, CONTROL IS STATE", dbControl.getUniversity("AUBURN").getControl(), "STATE");
		assertTrue("University is AUBURN, NUMBEROFSTUDENTS IS 35000", dbControl.getUniversity("AUBURN").getNumOfStudents() == 35000);
		assertTrue("University is AUBURN, PERCENTAGEFEMALE IS 21", dbControl.getUniversity("AUBURN").getFemalePercentage() == 21);
		assertTrue("University is AUBURN, SATVerbal is 480", dbControl.getUniversity("AUBURN").getSATVerbal() == 480);
		assertTrue("University is AUBURN, SATMath is 545", dbControl.getUniversity("AUBURN").getSATMath() == 545);
		assertTrue("University is AUBURN, EXPENSES IS 12468", dbControl.getUniversity("AUBURN").getExpenses() == 12468);
		assertTrue("University is AUBURN, FINANCIALAID IS 50", dbControl.getUniversity("AUBURN").getFinancialAid() == 50);
		assertTrue("University is AUBURN, NUMBEROFAPPLICANTS IS 5500", dbControl.getUniversity("AUBURN").getNumApplicants() == 5500);
		assertTrue("University is AUBURN, PERCENTADMITTED IS 90", dbControl.getUniversity("AUBURN").getNumAdmitted() == 90);
		assertTrue("University is AUBURN, PERCENTENROLL IS 60", dbControl.getUniversity("AUBURN").getNumEnrolled() == 60);
		assertTrue("University is AUBURN, ACADEMICSCALE IS 2", dbControl.getUniversity("AUBURN").getAcademicScale() == 2);
		assertTrue("University is AUBURN, SOCIALSCALE IS 4", dbControl.getUniversity("AUBURN").getSocialScale() == 4);
		assertTrue("University is AUBURN, QUALITYOFLIFE IS 4", dbControl.getUniversity("AUBURN").getQualityOfLife() == 4);
	}
	
	@Test
	public void nullUsernameLogin()
	{
		assertFalse("Login will return false", dbControl.login(null, null));
	}
	
	@Test
	public void invalidUsernameLogin()
	{
		assertFalse("Login will return false", dbControl.login("juzer", null));
	}
	
	@Test
	public void validUsernameInvalidPasswordLogin()
	{
		assertFalse("Login will return false", dbControl.login("juser", "PASSword"));
	}
	
	@Test
	public void ValidUandPNotActiveLogin()
	{
		assertFalse("Login will return false", dbControl.login("luser", "user"));
	}
	
	@Test
	public void everythingValidLogin()
	{
		assertTrue("Login will return true", dbControl.login("juser",  "password"));
	}
	
	@Test
	public void nullUniversityRecommendedSchools()
	{
		u = null;
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void invalidNameRecommendedSchools()
	{
		u = new University("AUBRUN", "ALABAMA", "SMALL-CITY", "STATE", 35000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void invalidStateRecommendedSchools()
	{
		u = new University("AUBURN", "AL", "SMALL-CITY", "STATE", 35000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void invalidLocationRecommendedSchools()
	{
		u = new University("AUBURN", "ALABAMA", "SMALLCITY", "STATE", 35000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void invalidControlRecommendedSchools()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STAAATE", 35000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", -5, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", -1, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 0, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 1, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 4500000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3999999, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 4000000, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryNumStudents()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 4000001, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, -5, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, -0.1, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 0.0, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 0.1, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 105.0, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 99.9, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 100.0, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryFemale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 100.1, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundSATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, -5, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundarySATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 199, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundarySATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 200, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundarySATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 201, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundSATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 1000, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundarySATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 799, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundarySATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 800, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundarySATVerbal()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 801, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundSATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 50, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundarySATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 199, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundarySATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 200, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundarySATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 201, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundSATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 1000, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundarySATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 799, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundarySATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 800, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundarySATMath()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 801, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, -5000, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, -0.1, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 0.0, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 0.1, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 85000, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 79999.99, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 80000.00, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryExpenses()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 80000.01, 50, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, -5.0, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, -0.1, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 0.0, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 0.1, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 105.0, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 99.9, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 100.0, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryFinancialAid()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 100.1, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, -10, 5500, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, -1, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 0, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 1, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 30000, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 19999, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 20000, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryNumApplicants()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 20001, 90, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, -5.0, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, -0.1, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 0.0, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 0.1, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 105.0, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 99.9, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 100.0, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryPercentAdmitted()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 100.1, 60, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, -5.0, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, -0.1, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 0.0, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 0.1, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 105.0, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 99.9, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 100.0, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryPercentEnrolled()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 100.1, 2, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, -5, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 0, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 1, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 10, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 4, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 5, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryAcademicScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 6, 4, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundSocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, -5, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundarySocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 0, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundarySocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 1, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundarySocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 2, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundSocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 10, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundarySocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundarySocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 5, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundarySocialScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 6, 4);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, -5);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundlowerBoundaryQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 0);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundBoundaryQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 1);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void lowerBoundupperBoundaryQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 2);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 10);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
	
	@Test
	public void upperBoundlowerBoundaryQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 4);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));
	}
	
	@Test 
	public void upperBoundBoundaryQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 5);
		assertTrue("RecommendedSchools will return true, results will be different from all valid", dbControl.getRecommendedSchools(u));	
	}
	
	@Test
	public void upperBoundupperBoundaryQualityOfLifeScale()
	{
		u = new University("AUBURN", "ALABAMA", "SMALL-CITY", "STATE", 3500, 21, 480, 545, 12468, 50, 5500, 90, 60, 2, 4, 6);
		assertFalse("RecommendedSchools will return false", dbControl.getRecommendedSchools(u));
	}
}

