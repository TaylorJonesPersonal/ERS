package com.ers.test.userservice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.models.user;
import com.ers.services.UserService;

public class userServiceTests {

@BeforeClass
public static void setup() {
userDao uDao = new userDaoDB();
UserService uServ = new UserService(uDao);
user User = new user();
User = uServ.signUp("Taylor", "Jones", "jonesftwingp@outlook.com", "tbjones", "nopass", 1);
uServ.postInvite("tbjones", 444);
}

@Test
public void testSignUp() {
	
	userDao uDao = new userDaoDB();
	UserService uServ = new UserService(uDao);
	user User = new user();
	User = uServ.signUp("Test", "Test", "test@test.com", "test2", "test2", 1);
	System.out.println(User.getFirstname());
	assertEquals("Test", User.getFirstname());
	assertEquals("Test", User.getLastname());
	assertEquals("test@test.com", User.getEmail());
	assertEquals("test2", User.getUsername());
	assertEquals("test2", User.getPassword());
	assertEquals(1, User.getRoleID());
}

@Test
public void testLogin() {
	userDao uDao = new userDaoDB();
	UserService uServ = new UserService(uDao);
	user u = uServ.login("tbjones", "nopass");
	System.out.println(u);
	assertEquals("Taylor", u.getFirstname());
	assertEquals("Jones", u.getLastname());
	assertEquals("jonesftwingp@outlook.com", u.getEmail());
	assertEquals("tbjones", u.getUsername());
	assertEquals("nopass", u.getPassword());
	assertEquals(1, u.getRoleID());
}

@Test
public void testInviteCode() {
	userDao uDao = new userDaoDB();
	UserService uServ = new UserService(uDao);
	user u = new user();
	u = uServ.checkCode("tbjones"
			+ "", 444);
	System.out.println(u);
	assertEquals("tbjones", u.getUsername());
	assertEquals(444, u.getInviteCode());
}

}
