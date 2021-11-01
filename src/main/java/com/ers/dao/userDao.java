package com.ers.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ers.models.user;

public interface userDao {
	void createUser(user u) throws SQLException;
	user getUser(String username, String password);
	user updateUser(String username, String fieldname, String change);
	void deleteUser(String username);
	public void postInviteCode(String username, int inviteCode);
	public boolean checkInviteCode(String username, int inviteCode);
	public ArrayList<user> getAllUsers();
	
}
