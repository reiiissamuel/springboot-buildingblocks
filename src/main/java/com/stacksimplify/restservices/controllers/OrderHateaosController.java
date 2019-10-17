package com.stacksimplify.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.OrderService;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping( value = "/hateaos/users")
@Validated
public class OrderHateaosController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable("userid") Long userId){
		try {
			List<Order> orders = orderService.getAllOrders(userId);
			Resources<Order> finalResources = new Resources<Order>(orders);
			return finalResources;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderById(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid){
		try {
			return orderService.getOrderById(userService.getUserById(userid).get().getOrders(), orderid);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (OrderNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
}
