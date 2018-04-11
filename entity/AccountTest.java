package entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	Account acct = new Account("jon", "first", "jf", "pw", 'a', 'Y');
	@Test
	public void testAccountStringStringStringStringCharChar() {
		Account a = new Account("j", "user", "juser", "password", 'a', 'Y');
		assertTrue("Account is created", a != null);
	}

	@Test
	public void testAccountStringString() {
		Account a = new Account("batman", "password");
		assertTrue("Account is created", a != null);
	}

	@Test
	public void testLogin() {
		assertTrue("Login account acct", acct.login());
	}

	@Test
	public void testSetFirstname() {
		acct.setFirstname("bill");
		assertTrue("First name of acct changed to bill", acct.getFirstName().equals("bill"));

	}

	@Test
	public void testGetFirstName() {
		acct.setFirstname("bill");
		assertTrue("First name of acct changed to bill", acct.getFirstName().equals("bill"));
	}

	@Test
	public void testSetLastname() {
		acct.setLastname("bob");
		assertTrue("Last name of acct changed to bob", acct.getLastName().equals("bob"));

	}

	@Test
	public void testGetLastName() {
		acct.setLastname("bob");
		assertTrue("Last name of acct changed to bob", acct.getLastName().equals("bob"));

	}

	@Test
	public void testLogOut() {
		assertFalse("Account acct is logged out", acct.logOut());
	}

	@Test
	public void testGetUsername() {
		
		assertTrue("The username of acct is jf", acct.getUsername().equals("jf"));
	}

	@Test
	public void testSetUserName() {
		acct.setUserName("bb");
		assertTrue("Account acct username is now bb", acct.getUsername().equals("bb"));
	}

	@Test
	public void testSetPassword() {
		acct.setPassword("pass");
		assertTrue("Account acct password is now pass", acct.getPassword().equals("pass"));
	}

	@Test
	public void testGetPassword() {
		acct.setPassword("pass");
		assertTrue("Account acct password is pass", acct.getPassword().equals("pass"));
	}

	@Test
	public void testSetType() {
		acct.setType('u');
		assertTrue("Account acct is now type u", acct.getType() == 'u');
	}

	@Test
	public void testGetType() {
		acct.setType('u');
		assertTrue("Account acct is type u", acct.getType() == 'u');
	}

	@Test
	public void testSetStatus() {
		acct.setStatus('N');
		assertTrue("Account acct status is now N", acct.getStatus() == 'N');
	}

	@Test
	public void testGetStatus() {
		acct.setStatus('N');
		assertTrue("Account acct status is N", acct.getStatus() == 'N');
	}

}
