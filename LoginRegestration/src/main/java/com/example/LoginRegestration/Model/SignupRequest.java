package com.example.LoginRegestration.Model;

import java.util.List;
import java.util.Set;

public class SignupRequest {

	private String email;
	private String username;
	private String password;
	Set<String> role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<String> getRole() {
		return role;
	}

	public void setRoles(Set<String> role) {
		this.role = role;
	}

}
