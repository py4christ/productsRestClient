package com.fdmgroup.productsRestClient.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fdmgroup.productsRestClient.model.Order;

@Service("ordersApiClient1")
public class OrderApiClientRestTemplate implements OrderApiClient {
	
	private RestTemplate restTemplate;
	
	public OrderApiClientRestTemplate(@Qualifier("ordersApi") RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public Order postOrder(Order inputedOrder, int cartId) {
		return restTemplate.postForEntity("/api/v1/orders/" + cartId, inputedOrder, Order.class).getBody();
	}
	


}
