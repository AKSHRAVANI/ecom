package com.abc.ecom.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.ecom.entity.Order;
import com.abc.ecom.entity.Product;
import com.abc.ecom.exception.ProductNotFoundException;
import com.abc.ecom.repository.OrderRepository;
import com.abc.ecom.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Order addOrder(Order order)
	{
		order.setOrderDate(LocalDate.now());
		int productId=order.getProductId();
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundException("Product not Existing with id "+productId);
		}
		else {
			Product product=optionalProduct.get();
			double unitPrice= product.getProductPrice();
			double orderAmount=order.getQuantity()*unitPrice;
			order.setOrderAmount(orderAmount);
		}
		
		Order newOrder = orderRepository.save(order);
		return newOrder;
	}
}
