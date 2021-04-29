package com.prishita.jobportal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String accountId;

	private String roles;

	private String password;

	@Column(unique = true)
	private String username;

	private Boolean active;

	public User() {
	}

	public User(String password, String username, String role, String accountId, String name) {
		this.password = password;
		this.username = username;
		this.roles = role;
		this.accountId = accountId;
		this.active = true;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String role) {
		this.roles = role;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", accountId='" + accountId + '\'' +
				", roles='" + roles + '\'' +
				", password='" + password + '\'' +
				", username='" + username + '\'' +
				", active=" + active +
				'}';
	}
}
