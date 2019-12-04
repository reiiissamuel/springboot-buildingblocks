package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.exceptions.UsernameNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//controller
@Api(tags = "User managemet RESTful Service", value = "UserController")
@RestController
@Validated 
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retrieve list of users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@ApiOperation(value = "Create new user")
	@PostMapping
	public ResponseEntity<Void> createUser(
			@ApiParam("User information for a new user to be created.") @Valid @RequestBody User user,
			UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@ApiOperation(value = "return an user by his id")
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional = userService.getUserById(id);
			return userOptional;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@ApiOperation(value = "update an user by his id")
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Delete an user by his id")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException{
		userService.deleteUserById(id);
	}
	
	@ApiOperation(value = "return an user by his username")
	@GetMapping("/users/byusername/{username}")
	public User getUserbyUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username: '"+username+ "' not found in user repository");
		}else {
			return user;
		}
	}
	
	
}

















