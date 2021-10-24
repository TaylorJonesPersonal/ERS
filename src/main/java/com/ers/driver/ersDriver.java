package com.ers.driver;

import java.sql.SQLException;

import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.exceptions.SignUpFailedException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.logging.Logging;
import com.ers.models.user;
import com.ers.services.UserService;

public class ersDriver {
	private static userDaoDB uDao = new userDaoDB();
	private static UserService uServ = new UserService(uDao);

	public static void main(String[] args) {
		
		uServ.sendInvite("jonesftwingp@outlook.com");

		/*
		user newUser = new user("Taylor", "Jones", "taylor.jones@revature.net", "tbjones", "nopass");
		try {
			uServ.signUp("Taylor", "Jones", "taylor.jones@revature.net", "tbjones", "nopass");
		} catch (SignUpFailedException e) {
			Logging.logger.warn("User signup has entered a duplicate email or username.");
			System.out.println("Your signup has failed. Please try again.");
		}
		*/

		/*
		 * try { newUser = uDao.getUser("tbjones", "nopass");
		 * System.out.println("You are now logged in as: " + newUser.getUsername()); }
		 * catch(UserDoesNotExistException e) {
		 * System.out.println("No user by that username exists."); }
		 * 
		 * try { newUser = uDao.updateUser("tbjones", "email", "newemail@mail.com"); }
		 * catch(UserDoesNotExistException e) {
		 * System.out.println("That user does not exist."); }
		 * 
		 * uDao.deleteUser("tbjones");
		 * 
		 * }
		 */

	}
}
