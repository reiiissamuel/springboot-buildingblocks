package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public Order createOrder(Long userId, Order order) throws UserNotFoundException, OrderNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("It seems you are trying add a order to user that does not exists!");
		}else {
			User user = userOptional.get();
			if(order != null) {
				order.setUser(user);
				return orderRepository.save(order);
			}else {
				throw new OrderNotFoundException("There's no order to save in repo!");
			}
			
		}
	}
	
	public List<Order> getAllOrders(Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found!");
		} else {
			return userOptional.get().getOrders();
		}
	}
	
	public Order getOrderById(List<Order> orders, Long orderId) throws OrderNotFoundException {
		//Optional<Order> order = orderRepository.findAll(orderId);
		//TODO consertar esse metodo que so retorna 404
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("Order not found!");
		}else {
			for(int i = 0; i < orders.size(); i++) {
				System.out.println(orders.get(i).getOrderid());
				if(orders.get(i).getOrderid().equals(orderId)) {
					System.out.println("Entrou aqui");
					return orders.get(i);
					
				}
			}
			
			throw new OrderNotFoundException("Order not found!");
		}
	}
}
