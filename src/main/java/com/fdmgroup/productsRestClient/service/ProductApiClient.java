package com.fdmgroup.productsRestClient.service;

import java.util.List;

import com.fdmgroup.productsRestClient.model.Product;

public interface ProductApiClient {

	List<Product> getProducts();

	Product getProduct(int id);

	Product postProduct(Product inputProduct);

	void putProduct(Product updatedProduct);

	void deleteProduct(int id);

	List<Product> getProductByName(String name);

}
