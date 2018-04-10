package edu.csbsju.ntc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import interaction.StudentInteraction;

public class SearchControllerTest {
	
	StudentInteraction si;
	@Before
	public void setUp() throws Exception {
	si = new StudentInteraction("juser", "password");	
	}

	//@Test
	//public void invalidSchoolNametest() {
	//	AssertTrue(si.search("brad",
	//			"-1", 
	//			"-1", 
	//			"-1", 
	//			"-1", 
	//			"-1", 
	//			"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1").equals());
	//}

}
