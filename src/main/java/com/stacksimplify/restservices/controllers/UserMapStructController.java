package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController implements UserMapper { 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUserDtos(){
		return userMapper.usersToUserDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found!");
		}
		User user = userOptional.get();
		return userMapper.usertoToUserMsDto(user);
	}

	@Override
	public UserMsDto usertoToUserMsDto(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserMsDto> usersToUserDtos(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}
}
