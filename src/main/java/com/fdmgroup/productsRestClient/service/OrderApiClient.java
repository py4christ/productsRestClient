package com.fdmgroup.productsRestClient.service;

import com.fdmgroup.productsRestClient.model.Order;

public interface OrderApiClient {
	
	Order postOrder(Order inputedOrder, int cartId);

}
