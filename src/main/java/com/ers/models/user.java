package com.ers.models;

public class user {
 // private variables defining the attributes of the user class
	
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int roleID;
	private String role;
	private String pending;
	private int inviteCode;
	
	public user() {
		
	}
	
	
	public user(String firstname, String lastname, String email, String username, String password, int roleID) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roleID = roleID;
		this.pending = "y";
		this.inviteCode = 0;
	}
	
	public user(String firstname, String lastname, String email, String username, String role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.role = role;
	}
	
	public user(String username, int inviteCode) {
		this.username = username;
		this.inviteCode = inviteCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", roleID=" + roleID + ", role=" + role + ", pending="
				+ pending + ", inviteCode=" + inviteCode + "]";
	}


	public String getPending() {
		return pending;
	}


	public void setPending(String pending) {
		this.pending = pending;
	}



	public int getRoleID() {
		return roleID;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}


	public int getInviteCode() {
		return inviteCode;
	}


	public void setInviteCode(int inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	
}
