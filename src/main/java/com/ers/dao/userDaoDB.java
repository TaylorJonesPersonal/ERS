package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.ers.exceptions.SignUpFailedException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.models.user;
import com.ers.utils.ConnectingUtil;

public class userDaoDB implements userDao {
	ConnectingUtil conUtil = ConnectingUtil.getConnectionUtil();

	
		// crud operations: CREATE
	public void createUser(user u) throws SQLException{
		Connection con = conUtil.getConnection();

			String sql = "INSERT into ers_users(user_first_name, user_last_name, user_email, user_username, user_password, user_role_id, user_pending) values"
					+ "(?,?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, u.getFirstname());
			ps.setString(2, u.getLastname());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getPassword());
			ps.setInt(6, u.getRoleID());
			ps.setString(7, u.getPending());
			ps.execute();

	}

	
		// crud operations: READ
	public user getUser(String username, String password) throws UserDoesNotExistException {
		Connection con = conUtil.getConnection();
	
		
		

		user u = new user();

		try {
			String sql = "SELECT * from ers_users where user_username = '" + username + "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
	
			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setUsername(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setRoleID(rs.getInt(7));

			}
			System.out.println(u);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<user> getAllUsers(){
		Connection con = conUtil.getConnection();
		
		try {
		user u = new user();
		ArrayList<user> uList = new ArrayList<user>();
		
		String sql = "select user_first_name, user_last_name, user_email, user_username, user_role from ers_users inner join ers_user_roles on ers_users.user_role_id = ers_user_roles.user_role_id; ";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while (rs.next()) {
			String firstName = rs.getString(1);
			String lastName = rs.getString(2);
			String email = rs.getString(3);
			String username = rs.getString(4);
			String role = rs.getString(5);
			
			uList.add(new user(firstName, lastName, email, username, role));
		}
		
		for (int i = 0; i < uList.size(); i++) {
			System.out.println(uList.get(i));
		}
		
		return uList;
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
		// crud operations: UPDATE
	public user updateUser(String username, String fieldname, String change) throws UserDoesNotExistException {
		Connection con = conUtil.getConnection();

		user u = new user();
		
		try {
			String sql = "UPDATE ers_users set user_" + fieldname + " = '" + change + "' where user_username = '" + username + "';";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			
			String sql2;
			if(fieldname == "username") {
	
			sql2 = "SELECT * from ers_users where user_username = '" + change + "'";
			} else {
			sql2 = "SELECT * from ers_users where user_username = '" + username + "'";
			}
			
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql2);
	
			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setUsername(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setRoleID(rs.getInt(7));
				u.setPending(rs.getString(8));
			}
			
			return u;
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
			return null;

}
	// crud operations: DELETE
	public void deleteUser(String username) {
		Connection con = conUtil.getConnection();
		
		try {
			
			String sql = "DELETE from ers_users where user_username = '" + username + "';";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void postInviteCode(String username, int inviteCode) {
		Connection con = conUtil.getConnection();
		
		try {
			
			String sql = "UPDATE ers_users set user_invite_code = '" + inviteCode + "' where user_username = '" + username + "';"; 
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkInviteCode(String username, int inviteCode){
		Connection con = conUtil.getConnection();
		
		int code = 0; 
		
		try {
			
			String sql = "SELECT user_invite_code from ers_users where user_username = '" + username + "';";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				code = rs.getInt(1);
			}
			
			System.out.println(inviteCode);
			System.out.println(code);
			
			if(code != inviteCode) {
				return false;
			}else {
			
			String sql2 = "UPDATE ers_users set user_pending = 'n' where user_username = '" + username + "';";
			
			PreparedStatement ps = con.prepareStatement(sql2);
			ps.execute();
			
			String sql3 = "UPDATE ers_users set user_invite_code = null where user_username = '" + username + "';";
			
			PreparedStatement ps2 = con.prepareStatement(sql3);
			ps2.execute();
			
			return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
