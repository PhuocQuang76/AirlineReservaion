package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private Long userId;

	@NotEmpty
	@Column(name="name")
	String username;

	@NotEmpty
	String password;

	@Email
	@NotEmpty
	String email;

//	@JsonManagedReference
//	@JsonBackReference
//@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
			joinColumns = { @JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")})
	List<Role> roles = new ArrayList<>();

	public User(){}

	public User(Long userId, String username, String password, String email, List<Role> roles) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
