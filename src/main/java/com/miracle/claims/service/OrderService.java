package com.miracle.claims.service;

import com.miracle.claims.beans.Order;
import com.miracle.claims.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("OrderService")
@Service
public class OrderService implements OrderServiceImpl {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	MongoOperations mongoOperations; 
	
	@Override
	public Order getOrderById(int orderId) {
		return orderRepository.findByOrderId(orderId);
	}
	@Override
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	@Override
	public ResponseEntity<Order> updateOrder(int orderId, Order order) {
		try {
			Order orders = orderRepository.findByOrderId(orderId);
			System.out.println("this is service pro" + orders.getOrderId());
			// log.info("this is the output" + claims.getClaimId());
			orders.setOrderId(order.getOrderId());
			orders.setItem(order.getItem());
			orders.setDescription(order.getDescription());
			orders.setDate(order.getDate());
			orders.setLot(order.getLot());
			orders.setQuantity(order.getQuantity());
			orders.setLpn(order.getLpn());
			orders.setNet(order.getNet());
            orders.setClaimQty(order.getClaimQty());
			orders.setCategory(order.getCategory());
			orders.setUnitCost(order.getUnitCost());
			orders.setUom(order.getUom());
			orders.setTotal(order.getTotal());
			orders.setEstUnitCost(order.getEstUnitCost());
			orders.setEstTotalCost(order.getEstTotalCost());
			orders.setAmountBasis(order.getAmountBasis());
			orderRepository.save(orders);
			return new ResponseEntity<Order>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	@Override
	public ResponseEntity<Order> createOrders(Order order) {
		Order newOrder = orderRepository.save(order);
		return new ResponseEntity<>(newOrder, new HttpHeaders(), HttpStatus.OK);
	}
	// delete
	@Override
	public String deleteOrders(int orderId) {
		orderRepository.deleteByOrderId(orderId);
		return "claim deleted with id : " + orderId;
	}

}
