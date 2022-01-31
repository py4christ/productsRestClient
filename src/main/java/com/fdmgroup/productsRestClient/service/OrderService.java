package com.fdmgroup.productsRestClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fdmgroup.productsRestClient.model.Order;

@Service
public class OrderService {

	private OrderApiClient orderApiClient;

	@Autowired
	public OrderService(@Qualifier("ordersApiClient1") OrderApiClient orderApiClient) {
		super();
		this.orderApiClient = orderApiClient;
	}
	
	public Order createOrder(Order inputedOrder, int cartId) {
		return orderApiClient.postOrder(inputedOrder, cartId);
	}

}
