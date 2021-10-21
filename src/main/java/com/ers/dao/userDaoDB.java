package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ers.exceptions.SignUpFailedException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.models.user;
import com.ers.utils.ConnectingUtil;

public class userDaoDB implements userDao {
	ConnectingUtil conUtil = ConnectingUtil.getConnectionUtil();

	
		// crud operations: CREATE
	public void createUser(user u) throws SQLException{
		Connection con = conUtil.getConnection();

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

	}

	
		// crud operations: READ
	public user getUser(String username, String password) throws UserDoesNotExistException {
		Connection con = conUtil.getConnection();
		
		

		user u = new user();

		try {
			String sql = "SELECT * from ers_users where ers_username = '" + username + "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
	
			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setUsername(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setRole(rs.getInt(7));

			}
			System.out.println(u);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
		// crud operations: UPDATE
		// this one will need careful restrictions of input on "fieldname" - will need a switch statement for user_ and ers_.
	public user updateUser(String username, String fieldname, String change) throws UserDoesNotExistException {
		Connection con = conUtil.getConnection();
		
		user u = new user();
		
		try {
			String sql = "UPDATE ers_users set user_" + fieldname + " = '" + change + "' where ers_username = '" + username + "';";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
	
			String sql2 = "SELECT * from ers_users where ers_username = '" + username + "'";
			
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql2);
	
			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setUsername(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setRole(rs.getInt(7));

			}
			if (u.getId() == 0) {
				throw new UserDoesNotExistException();
			}
			
			return u;
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
			return null;

}
	
	public void deleteUser(String username) {
		Connection con = conUtil.getConnection();
		
		try {
			
			String sql = "DELETE from ers_users where ers_username = '" + username + "';";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
