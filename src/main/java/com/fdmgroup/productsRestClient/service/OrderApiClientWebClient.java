package com.fdmgroup.productsRestClient.service;

import org.springframework.stereotype.Service;

import com.fdmgroup.productsRestClient.model.Order;

@Service("ordersApiClient2")
public class OrderApiClientWebClient implements OrderApiClient {

	@Override
	public Order postOrder(Order order, int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

}
