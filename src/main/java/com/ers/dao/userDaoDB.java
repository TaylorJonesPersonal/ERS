package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ers.exceptions.SignUpFailedException;
import com.ers.models.user;
import com.ers.utils.ConnectingUtil;

public class userDaoDB implements userDao {
	ConnectingUtil conUtil = ConnectingUtil.getConnectionUtil();

	public void userCreate(user u) throws SignUpFailedException {
		Connection con = conUtil.getConnection();

		try {
		String sql = "INSERT into ers_users(user_first_name, user_last_name, user_email, ers_username, ers_password, user_role_id) values"
				+ "(?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getFirstname());
			ps.setString(2, u.getLastname());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getPassword());
			ps.setInt(6, u.getRole());
			
			ps.execute();
			
		}catch(SQLException e) {
			throw new SignUpFailedException();
		}

	}

	public user login(String username, String password) {
		Connection con = conUtil.getConnection();
		
		user u = new user();
		
		try {
			String sql = "SELECT from ers_users where ers_username = '" + username + "';";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
		}catch(SQLException e) {
			
		}

	}

}
