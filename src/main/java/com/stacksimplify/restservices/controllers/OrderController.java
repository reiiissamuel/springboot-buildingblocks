package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.OrderService;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") Long userId){
		try {
			return orderService.getAllOrders(userId);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PostMapping("/{userid}/orders")
	public ResponseEntity<Void> createOrder(@Valid @PathVariable("userid") Long userid, @RequestBody Order order, UriComponentsBuilder builder){
		try {
			orderService.createOrder(userid, order);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/orders/{id}").buildAndExpand(order.getOrderid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (OrderNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
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



