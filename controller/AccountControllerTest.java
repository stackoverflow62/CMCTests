package controller;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entity.Account;

public class AccountControllerTest {

	private Account a1, a2;
	private AccountController ac, ac1;
	
	@Before
	public void init() {
		a1 = new Account("John", "User","juser", "password", 'u', 'Y');
		a2 = new Account("Noreen", "Admin", "nadmin", "admin", 'a', 'Y');
		ac = new AccountController("juser", "password");
		ac1 = new AccountController("nadmin","admin");
	}
	
	@Test
	public void testAccountControllerString() {
		assertTrue("Username invalid", a1.getUsername().equals("juser"));
	}

	@Test
	public void testAccountControllerStringString() {
		assertTrue("Username incorrect", a1.getUsername().equals("juser"));
		assertTrue("Password incorrect", a1.getPassword().equals("password"));
	}

	@Test
	public void testLogin_BBT_Valid() {
		assertTrue("Invalid login", ac.login("juser","password"));
	}
	
	@Test
	public void testLogin_BBT_InvalidPassword() {
		assertFalse("Valid password", ac.login("juser","pass"));
	}
	
	@Test
	public void testLogin_BBT_InvalidUser() {
		assertFalse("Valid password", ac.login("juer","password"));
	}
	
	@Test
	public void testLogin_BBT_InvalidUserAndPassword() {
		assertFalse("Valid password", ac.login("juer","passord"));
	}
	
	@Test
	public void testCheckType() {
		assertTrue("Invalid Type", ac.checkType("juser")==true);
	}
	
	@Test
	public void testCheckType_WBT_ValidUsername() {
		assertTrue("Invalid Username", ac.checkType("juser")==true);
		assertTrue("Invalid Username", a1.getUsername().equals("juser"));
	}
	
	@Test
	public void testCheckType_WBT_InvalidUser() {
		assertFalse("Valid User", ac.checkType("juer"));
	}
	
	@Test
	public void testCheckType_WBT_ValidAdmin() {
		assertTrue("Invalid Admin", ac1.checkType("nadmin")==true);
		assertTrue("Invalid Admin", a2.getType()=='a');
	}
	
	@Test
	public void testCheckType_WBT_ValidUser() {
		assertTrue("Invalid User", ac.checkType("juser")==true);
		assertTrue("Invalid User", a1.getType()=='u');
	}

	@Test
	public void testActiveUser() {
		assertTrue("Not active user", ac.activeUser("juser") == true);
	}
	
	@Test
	public void testActiveUser_WBT_ValidUsername() {
		assertTrue("Not valid username", ac.activeUser("juser") == true);
		assertTrue("Not Valid Username", a1.getUsername().equals("juser"));
	}

	@Test
	public void testLogout() {
		assertTrue("Invalid Logout", ac.logout() == false);
	}

}
