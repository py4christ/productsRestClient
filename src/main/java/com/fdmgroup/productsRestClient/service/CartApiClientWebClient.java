package com.fdmgroup.productsRestClient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.productsRestClient.model.Cart;

@Service("cartsApiClient2")
public class CartApiClientWebClient implements CartApiClient {

	public CartApiClientWebClient() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cart> getCarts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCart(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart postCart(Cart inputedCart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCartItem(int ProductId, int cartId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearAllCartItems(int cartId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCartItem(int productId, int cartId) {
		// TODO Auto-generated method stub

	}

}
