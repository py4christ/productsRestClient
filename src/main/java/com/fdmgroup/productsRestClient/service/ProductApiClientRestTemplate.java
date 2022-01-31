package com.fdmgroup.productsRestClient.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fdmgroup.productsRestClient.model.Product;

@Service("productsApiClient1")
public class ProductApiClientRestTemplate implements ProductApiClient {


	private RestTemplate restTemplate;

	@Autowired
	public ProductApiClientRestTemplate(@Qualifier("productsApi") RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Product> getProducts() {
		Product[] products = restTemplate.getForEntity("/api/v1/products", Product[].class).getBody();
		return Arrays.asList(products);
	}

	@Override
	public Product getProduct(int id) {
		return restTemplate.getForEntity("/api/v1/products/" + id, Product.class).getBody();
	}

	@Override
	public Product postProduct(Product inputProduct) {
		return restTemplate.postForEntity("/api/v1/products", inputProduct, Product.class).getBody();
	}
	

	@Override
	public void putProduct(Product updatedProduct) {
		restTemplate.put("/api/v1/products", updatedProduct);
	}

	@Override
	public void deleteProduct(int id) {
		restTemplate.delete("/api/v1/products/" + id);
	}

	@Override
	public List<Product> getProductByName(String name) {
		Product[] products = restTemplate.getForEntity("/api/v1/products/productByName" , Product[].class).getBody();
		return Arrays.asList(products);
	}

}
