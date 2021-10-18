package com.ers.driver;

import java.sql.SQLException;

import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.exceptions.SignUpFailedException;
import com.ers.logging.Logging;
import com.ers.models.user;

public class ersDriver {
	private static userDaoDB uDao = new userDaoDB();

	public static void main(String[] args) {
		
		user newUser = new user("Taylor", "Jones", "taylor.jones@revature.net", "tbjones", "nopass", 1);
		try {
		uDao.userCreate(newUser);
		} catch(SignUpFailedException e) {
			Logging.logger.warn("User signup has entered a duplicate email or username.");
			System.out.println("Your signup has failed. Please try again.");
		}
	}

}
