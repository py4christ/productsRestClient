package com.fdmgroup.productsRestClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ProductsRestClientApplication {

	public static final String PRODUCT_API_URL = "http://localhost:8084";
	public static final String CART_API_URL = "http://localhost:8087";
	public static final String ORDER_API_URL = "http://localhost:8088";

	public static void main(String[] args) {
		SpringApplication.run(ProductsRestClientApplication.class, args);
	}

	@Bean("productsApi")
	public RestTemplate productsApiRestTemplate() {
		return new RestTemplateBuilder().rootUri(PRODUCT_API_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@Bean
	public WebClient productsApiWebClient() {
		return WebClient.builder().baseUrl(PRODUCT_API_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
	

	@Bean("cartsApi")
	public RestTemplate cartsApiRestTemplate() {
		return new RestTemplateBuilder().rootUri(CART_API_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	
	@Bean("ordersApi")
	public RestTemplate ordersApiRestTemplate() {
		return new RestTemplateBuilder().rootUri(ORDER_API_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}


}
