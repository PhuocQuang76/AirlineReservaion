package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

public class Role
{
	@Id
	private Long roleId;
	
	private String roleName;

//	@JsonBackReference
//	@JsonManagedReference
	@JsonIgnore
	@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	public Role(){}

	public Role(Long roleId, String roleName, List<User> users) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
