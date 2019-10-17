package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Entity
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"firstname", "lastName"})
public class User extends ResourceSupport {
	
	@Id
	@GeneratedValue
	private Long userId;
	
	@NotEmpty(message = "Username is a mandatory field.")
	@Column(name = "USER_NAME", length=50, nullable=false, unique=true)
	private String username;
	
	@Size(min=2, message="Firstname should have at least 2 characters")
	@Column(name = "FIRST_NAME", length=50, nullable=false)
	private String firstname;
	
	@Column(name = "LAST_NAME", length=50, nullable=false)
	private String lastName;

	@Column(name = "EMAIL", length=50, nullable=false)
	private String email;
	
	@Column(name = "ROLE", length=50, nullable=false)
	private String role;
	
	
	@Column(name = "SSN", length=50, nullable=false, unique=true)
	@JsonIgnore
	private String ssn;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	//No Argument Constructor 
	public User() {
	}
	
	//Fields Constructor
	public User(Long userId, String username, String firstname, String lastName, String email, String role, String ssn) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	//Getters and Setters
		public Long getUserId() {
			return userId;
		}


	public void setUserId(Long id) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

	
	
	
	
}
