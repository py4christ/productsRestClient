package com.fdmgroup.productsRestClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fdmgroup.productsRestClient.model.Product;

@Service
public class ProductService {

	private ProductApiClient productApiClient;

	@Autowired
	public ProductService(@Qualifier("productsApiClient1") ProductApiClient productApiClient) {
		super();
		this.productApiClient = productApiClient;
	}

	public List<Product> readProducts() {
		return productApiClient.getProducts();
	}

	public Product readProductById(int id) {
		return productApiClient.getProduct(id);
	}

	public Product addProduct(Product inputProduct) {
		return productApiClient.postProduct(inputProduct);
	}

	public void updateProduct(Product updatedProduct) {
		productApiClient.putProduct(updatedProduct);
	}

	public void deleteProduct(int id) {
		productApiClient.deleteProduct(id);
	}

	public List<Product> findProductByName(String name) {
		return productApiClient.getProductByName(name);
	}

}
