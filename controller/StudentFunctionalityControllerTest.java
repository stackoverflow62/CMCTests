package controller;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class StudentFunctionalityControllerTest {

	StudentFunctionalityController sfc = new StudentFunctionalityController();
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void testManageDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testConfirm() {
		
	}

	@Test
	public void testViewSavedUniversities() {
		assertTrue("juser has CSB saved in their universities", sfc.viewSavedUniversities("juser").contains("CSB"));
		
	}

	@Test
	public void testSaveUniversity() {
		sfc.saveUniversity("juser", "BROWN");
		assertTrue("just now has BROWN saved", sfc.viewSavedUniversities("juser").contains("BROWN"));
	}

	@Test
	public void testRemoveUniversity() {
		sfc.removeUniversity("juser", "BROWN");
		assertTrue("just now doesn't have BROWN saved", !sfc.viewSavedUniversities("juser").contains("BROWN"));
	}

}
