package com.fdmgroup.productsRestClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fdmgroup.productsRestClient.model.Cart;

@Service
public class CartService {
	
	private CartApiClient cartApiClient;

	@Autowired
	public CartService(@Qualifier("cartsApiClient1") CartApiClient cartApiClient) {
		super();
		this.cartApiClient = cartApiClient;
	}
	
	public List<Cart> readCarts() {
		return cartApiClient.getCarts();
	}
	
	public Cart readCartById(int cartId) {
		return cartApiClient.getCart(cartId);
	}
	
	public Cart createCart(Cart inputedCart) {
		return cartApiClient.postCart(inputedCart);
	}
	
	public void addCartItem(int productId, int cartId) {
		cartApiClient.addCartItem(productId, cartId);
	}
	
	public void removeCartItem(int productId, int cartId) {
		cartApiClient.removeCartItem(productId, cartId);
	}
	
	public void clearAllCartItems(int cartId) {
		cartApiClient.clearAllCartItems(cartId);
	}

	
	

}
