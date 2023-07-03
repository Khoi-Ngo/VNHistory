package com.swp.vnhistory.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" })

})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@NotBlank
	@Size(max = 100, min = 3)
	@Column(unique = true)
	private String username;

	private LocalDate date;
	private String typeUser;

	public User(long userId, @NotBlank @Size(max = 100, min = 3) String username, LocalDate date, String typeUser,
			@NotBlank @Size(max = 100) @Email String email, @NotBlank @Size(min = 6, max = 100) String password,
			String avatar, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.date = date;
		this.typeUser = typeUser;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	@NotBlank
	@Size(max = 100)
	@Email
	private String email;

	@JsonIgnore
	@NotBlank
	@Size(min = 6, max = 100)
	private String password;

	@Lob
	private String avatar;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private String role;

	public User(@NotBlank @Size(max = 100, min = 3) String username, @NotBlank @Size(max = 100) @Email String email,
			@NotBlank @Size(min = 6, max = 100) String password, String avatar, String role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
		this.date = LocalDate.now();


	}

	public User(@NotBlank @Size(max = 100, min = 3) String username, LocalDate date,
			@NotBlank @Size(max = 100) @Email String email, @NotBlank @Size(min = 6, max = 100) String password,
			String avatar, String role) {
		super();
		this.username = username;
		this.date = date;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() {

	}

}
