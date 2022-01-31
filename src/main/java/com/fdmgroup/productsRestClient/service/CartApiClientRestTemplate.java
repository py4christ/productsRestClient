package com.fdmgroup.productsRestClient.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fdmgroup.productsRestClient.model.Cart;

@Service("cartsApiClient1")
public class CartApiClientRestTemplate implements CartApiClient {

	
	private RestTemplate restTemplate;
	
	@Autowired
	public CartApiClientRestTemplate(@Qualifier("cartsApi") RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
	

	@Override
	public List<Cart> getCarts() {
		Cart[] carts = restTemplate.getForEntity("/api/v1/carts", Cart[].class).getBody();
		return Arrays.asList(carts);
	}

	@Override
	public Cart getCart(int cartId) {
		return restTemplate.getForEntity("/api/v1/carts/" + cartId, Cart.class).getBody();
	}

	@Override
	public Cart postCart(Cart inputedCart) {
		return restTemplate.postForEntity("/api/v1/carts", inputedCart, Cart.class).getBody();
	}

	@Override
	public void removeCartItem(int productId, int cartId) {
		Cart cart = getCart(cartId);
		restTemplate.put("/api/v1/carts/removeItem/" + productId + "/" + cartId, cart);
	}

	@Override
	public void clearAllCartItems(int cartId) {
		Cart cart = getCart(cartId);
		restTemplate.put("/api/v1/carts/clearAllItems/" + cartId, cart);
	}

	@Override
	public void addCartItem(int productId, int cartId) {
		Cart cart = getCart(cartId);
		restTemplate.put("/api/v1/carts/addItem/" + productId + "/" + cartId, cart);
	}

}
