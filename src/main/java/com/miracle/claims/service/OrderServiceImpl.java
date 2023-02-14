package com.miracle.claims.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.miracle.claims.beans.Order;

public interface OrderServiceImpl {
	public Order getOrderById(int orderId);
	public List<Order> getAllOrders();
	public ResponseEntity<Order> createOrders(Order order);
	public ResponseEntity<Order> updateOrder(int orderId, Order order);
	String deleteOrders(int orderId);
	

}
