package com.ers.models;

public class userRoles {
	private int roleID;
	private String role;

	public userRoles() {
		
	}
	
	public userRoles(int roleID, String role) {
		this.roleID = roleID;
		this.role = role;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "userRoles [roleID=" + roleID + ", role=" + role + "]";
	}
	
	
}