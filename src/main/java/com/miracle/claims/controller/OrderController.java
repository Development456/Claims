package com.miracle.claims.controller;

import com.miracle.claims.beans.Order;
import com.miracle.claims.exception.ErrorDetails;
import com.miracle.claims.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderServices;
	
	// get the list of all
	@GetMapping("/getallorders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> order = orderServices.getAllOrders();
		return new ResponseEntity<>(order , new HttpHeaders(), HttpStatus.OK);
	}
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create Order", notes = "JSON Supported", response = Order.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Order.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Orders service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@PostMapping("/addorders")
	public ResponseEntity<Order> createOrders(
			@ApiParam(value = "Order Request", required = true) @RequestBody Order order) {
		return orderServices.createOrders(order);
	}
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Order By Order Id", notes = "JSON Supported", response = Order.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Order.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Orders service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(
			@ApiParam(value = "Order Id", required = true) @PathVariable int orderId) {
		return new ResponseEntity<>(orderServices.getOrderById(orderId), new HttpHeaders(),
				HttpStatus.OK);
	}
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Order", notes = "JSON Supported", response = Order.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Order.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Order service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@PutMapping("/{orderId}")
	public ResponseEntity<Order> updateOrder(
			@ApiParam(value = "Order Id", required = true) @PathVariable int orderId,
			@ApiParam(value = "Order Request", required = true) @RequestBody Order order) {
		return orderServices.updateOrder(orderId, order);
	}
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Order", notes = "JSON Supported", response = Order.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Order.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Orders service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@DeleteMapping("/{orderId}")
	public String deleteOrders(
			@ApiParam(value = "Order Id", required = true) @PathVariable int orderId) {
		return orderServices.deleteOrders(orderId);
	}

}
