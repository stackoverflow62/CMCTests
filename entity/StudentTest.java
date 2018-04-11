package entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {
	Student s = new Student("user", "pw", 'Y');
	@Test
	public void testStudent() {
		Student stu = new Student("u", "p", 'Y');
		assertTrue("Student was created", stu != null);
	}

	@Test
	public void testSetAccountStatus() {
		assertTrue("Status set to N", s.setAccountStatus('N'));
		
	}

	@Test
	public void testGetUserName() {
		assertTrue("Username is user", s.getUserName().equals("user"));
	}

	

	@Test
	public void testSetPassword() {
		assertTrue("Password is now password", s.setPassword("password"));
	}

}
