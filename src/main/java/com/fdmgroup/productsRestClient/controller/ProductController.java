package com.fdmgroup.productsRestClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.productsRestClient.model.Cart;
import com.fdmgroup.productsRestClient.model.Order;
import com.fdmgroup.productsRestClient.model.Product;
import com.fdmgroup.productsRestClient.service.CartService;
import com.fdmgroup.productsRestClient.service.OrderService;
import com.fdmgroup.productsRestClient.service.ProductService;

@Controller
public class ProductController {

	
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@RequestMapping({"/", "/{cartId}"})
	public String goToShowProductsPage(Model model, @PathVariable(required = false) Integer cartId) {
		model.addAttribute("products", productService.readProducts());
		if (cartId == null) {
			cartId = 0;
		}
		model.addAttribute("cartId", cartId);
		return "show-products";
	}

	@RequestMapping("/show/{id}")
	public String goToSingleProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", productService.readProductById(id));
		return "single-product";
	}

	@RequestMapping("/new")
	public String goToNewProductPage(Model model, @Autowired Product product) {
		model.addAttribute("newProduct", product);
		return "new-product";
	}

	@RequestMapping("/addProduct")
	public String goAddProduct(Product inputProduct, Model model) {
		Product createdProduct = productService.addProduct(inputProduct);
		model.addAttribute("product", createdProduct);
		return "single-product";
	}
	
	@RequestMapping("/update/{id}")
	public String goToUpdateProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", productService.readProductById(id));
		return "update-product";
	}
	
	@RequestMapping("/saveUpdate")
	public String goSaveUpdateProduct(Product updatedProduct, Model model) {
		productService.updateProduct(updatedProduct);
		model.addAttribute("book", productService.readProductById(updatedProduct.getId()));
		return "single-product";
	}
	
	@RequestMapping("/remove/{id}")
	public String goRemoveProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}
	
	@RequestMapping("/productByName")
	public String goToShowNamesFilteredByName(Model model, @RequestParam String name) {
		List<Product> products = productService.findProductByName(name);
		model.addAttribute("products", products);
		return "show-products";	
	}
	
	@RequestMapping("/carts")
	public String goToShowCarts(Model model) {
		List<Cart> carts = cartService.readCarts();
		model.addAttribute("carts", carts);
		return "all-carts";
	}
	
	@RequestMapping("/carts/{cartId}")
	public String goToSingleCart(Model model, @PathVariable int cartId) {
		model.addAttribute("cart", cartService.readCartById(cartId));
		return "single-cart";
	}
	
	
	@RequestMapping({"/addCartItem/{productId}","/addCartItem/{productId}/{cartId}"})
	public String goAddCartItem(Model model, @Autowired Cart cart, @PathVariable int productId, @PathVariable(required = false) Integer cartId) {
		int id;
		if (cartId == null || cartId == 0) {
			cart = cartService.createCart(cart);
			id = cart.getId();
		}else {
			id = cartId;
		}
		cartService.addCartItem(productId, id);
		model.addAttribute("cart", cartService.readCartById(id));
		return "single-cart";
	}
	
	@RequestMapping("/removeCartItem/{productId}/{cartId}")
	public String goRemoveCartItem(Model model, @PathVariable int productId, @PathVariable int cartId) {
		cartService.removeCartItem(productId, cartId);
		model.addAttribute("cart", cartService.readCartById(cartId));
		return "single-cart";
	}
	
	@RequestMapping("/clearAllCartItems/{cartId}")
	public String goClearAllCartItems(Model model, @PathVariable int cartId) {
		cartService.clearAllCartItems(cartId);
		model.addAttribute("cart", cartService.readCartById(cartId));
		return "single-cart";
	}
	
	@RequestMapping("/custmerInfo/{cartId}") 
	public String goToCustomerInfoPage(Model model, @PathVariable int cartId) {
		model.addAttribute("cartId", cartId);
		return "customer-info-input";
	}
		
	
	@RequestMapping(value="/addOrder/{cartId}", method=RequestMethod.POST)
	public String goAddOrder(Model model, @Autowired Order order, @PathVariable int cartId, @RequestParam String inputName) {
		order.setCustomerName(inputName);
		Order savedOrder = orderService.createOrder(order, cartId);
		model.addAttribute("SavedOrder", savedOrder);
		model.addAttribute("cartId", cartId);
		return "order-confirmation";
	}
	

}
