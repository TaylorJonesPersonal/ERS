package com.ers.dao;

import java.sql.SQLException;

import com.ers.models.user;

public interface userDao {
	void userCreate(user u) throws SQLException;
	user login(String username, String password);
}
