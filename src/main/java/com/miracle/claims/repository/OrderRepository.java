package com.miracle.claims.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.miracle.claims.beans.Order;


@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

	@Query("{order_id : ?0}")
	public Order findByOrderId(int orderId);
	
	public Order deleteByOrderId(int orderId);

}
