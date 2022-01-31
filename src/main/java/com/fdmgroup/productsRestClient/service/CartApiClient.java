package com.fdmgroup.productsRestClient.service;

import java.util.List;

import com.fdmgroup.productsRestClient.model.Cart;

public interface CartApiClient {
	
	List<Cart> getCarts();
	
	Cart getCart(int cartId);
	
	Cart postCart(Cart inputedCart);
	
	void removeCartItem(int productId, int cartId);
	
	void clearAllCartItems(int cartId);
	
	void addCartItem(int productId, int cartId);
	

	
	

}
