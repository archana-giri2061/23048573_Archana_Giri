package com.Savore.model;

public class UserModel {
	private Integer userId;
	private String userName;
	private String userEmail;
	private String Password;
	private String Address;
	private String Role;
	
	public UserModel() {
	}

	public UserModel(String userName, String password) {
		super();
		this.userName = userName;
		this.Password = password;
	}

	public UserModel(Integer userId, String userName, String userEmail, String password, String address, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.Password = password;
		this.Address = address;
		this.Role = role;
	}

	public UserModel(String userName, String userEmail, String password, String address, String role) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.Password = password;
		this.Address = address;
		this.Role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
	
	
}
