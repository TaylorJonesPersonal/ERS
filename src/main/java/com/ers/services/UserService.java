package com.ers.services;

import java.sql.SQLException;

import com.ers.dao.userDao;
import com.ers.exceptions.SignUpFailedException;
import com.ers.logging.Logging;
import com.ers.models.user;

public class UserService {

	private userDao uDao;
	public UserService(userDao uDao) {
		this.uDao = uDao;
	}
	
	public user signUp(String firstname, String lastname, String email, String username, String password) throws SignUpFailedException {
		user u = new user(firstname, lastname, email, username, password);
		
		try {
			uDao.userCreate(u);
		} catch(SQLException e) {
			Logging.logger.warn("User is either already signed up or has provided invalid data for signup.");
			throw new SignUpFailedException();
		}
		
		return u;
	}

}
