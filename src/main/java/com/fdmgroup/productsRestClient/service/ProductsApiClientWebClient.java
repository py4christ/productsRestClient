package com.fdmgroup.productsRestClient.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.productsRestClient.exception.ProductNotFoundException;
import com.fdmgroup.productsRestClient.model.Product;

import reactor.core.publisher.Mono;

@Service("productsApiClient2")
public class ProductsApiClientWebClient implements ProductApiClient {
	
	private WebClient webClient;
	

	public ProductsApiClientWebClient(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public List<Product> getProducts() {
		return webClient.get()
						.uri(builder -> builder.path("/api/v1/products").build())
						.retrieve()
						.bodyToFlux(Product.class)
						.collectList()
						.block();
	}

	@Override
	public Product getProduct(int id) {
		return webClient.get()
						.uri(builder -> builder.path("/api/v1/products/{id}").build(id))
						.retrieve()
						.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(), 
								response -> Mono.error(new ProductNotFoundException("Product not found for id = " + id)))
						.bodyToMono(Product.class)
						.block();
	}

	@Override
	public Product postProduct(Product inputProduct) {
		return webClient.post()
						.uri(builder -> builder.path("/api/v1/products").build())
						.body(Mono.just(inputProduct), Product.class)
						.retrieve()
						.bodyToMono(Product.class)
						.block();
	}

	@Override
	public void putProduct(Product updatedProduct) {
		webClient.put()
				.uri(builder -> builder.path("/api/v1/products").build())
				.body(Mono.just(updatedProduct), Product.class)
				.retrieve()
				.toBodilessEntity()
				.block();	
	}

	@Override
	public void deleteProduct(int id) {
		webClient.delete()
				 .uri(builder -> builder.path("/api/v1/products/{id}").build(id))
				 .retrieve()
				 .toBodilessEntity()
				 .block();	 		
	}

	@Override
	public List<Product> getProductByName(String name) {
		return webClient.get()
				.uri(builder -> builder.path("/api/v1/products/productByName").build())
				.retrieve()
				.bodyToFlux(Product.class)
				.collectList()
				.block();
	}

}
