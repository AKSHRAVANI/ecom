package com.abc.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.ecom.entity.Product;
import com.abc.ecom.exception.ProductNotFoundException;
import com.abc.ecom.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {

	   Product savedProduct =	productRepository.save(product);
	     
	    return savedProduct;
	}
	@Override
	public List<Product> getAllProducts(){
		
		List<Product> products = productRepository.findAll();
		
		return products;
	}
	
	@Override
	public Product getProductById(int productId) throws ProductNotFoundException{
		
		 Optional<Product> optionalProduct = productRepository.findById(productId);
		 if(optionalProduct .isEmpty()) {
			 throw new ProductNotFoundException("Product is not existing with id:"+productId);
		 }
		return optionalProduct.get();
	}
	@Override
	public void deleteProduct(int productId) {
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct .isEmpty()) {
			
			throw new ProductNotFoundException("Product is not existing with id:"+productId);
		}
		else {
			productRepository.deleteById(productId);
			 
		 }
		
	}
	@Override
	public Product updateProduct(Product product) {
		
		Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
		 if(optionalProduct .isEmpty()) {
			 throw new ProductNotFoundException("Product is not existing with id:"+product.getProductId());
		 }
		 Product up = productRepository.save(product);
		 
		 return up;
	}
	@Override
	public Product getProductByName(String productName) {
		// TODO Auto-generated method stub
		Product product =productRepository.findByProductName(productName);
		return product;
	}
}

