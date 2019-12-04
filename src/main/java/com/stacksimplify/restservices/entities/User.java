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

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//Entity
@ApiModel(description = "Entity that represents user")
@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname", "lastName"}) --Static Filtering JsonIgnore
//@JsonFilter(value = "userFilter") //notation to enable the mapping filter
public class User extends ResourceSupport {
	
	@ApiModelProperty(notes = "Auto generated unique id", required = true, position = 1)
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long userId;
	
	@ApiModelProperty(notes = "username should be in format flname", example = "samuel", required = false, position = 2)
	@Size(min=2, max = 50)
	@NotEmpty(message = "Username is a mandatory field.")
	@Column(name = "USER_NAME", length=50, nullable=false, unique=true)
	@JsonView(Views.External.class)
	private String username;
	
	@Size(min=2, max = 50, message="Firstname should have at least 2 characters")
	@Column(name = "FIRST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String firstname;
	
	@Column(name = "LAST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String lastName;

	@Column(name = "EMAIL", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name = "ROLE", length=50, nullable=false)
	@JsonView(Views.Internal.class)
	private String role;
	
	
	@Column(name = "SSN", length=50, nullable=false, unique=true)
	@JsonView(Views.Internal.class)
	//@JsonIgnore --Static Filtering JsonIgnore
	private String ssn;
	
	@OneToMany(mappedBy="user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name = "ADDRESS")
	private String address;
	
	//No Argument Constructor 
	public User() {
	}
	
	//Fields Constructor
	public User(Long userId, @NotEmpty(message = "Username is a mandatory field.") String username,
			@Size(min = 2, message = "Firstname should have at least 2 characters") String firstname, String lastName,
			String email, String role, String ssn, List<Order> orders, String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}
	
	//Getters and Setters
		public Long getUserId() {
			return userId;
		}
	

	public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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
		return "User [userId=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastName="
				+ lastName + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders
				+ ", address=" + address + "]";
	}
	
	
}
