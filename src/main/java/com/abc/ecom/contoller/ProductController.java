package com.abc.ecom.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.ecom.entity.Product;
import com.abc.ecom.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
     
	@PostMapping("/save")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		
		Product newProduct = productService.saveProduct(product);
		
		ResponseEntity<Product> rp = new ResponseEntity<>(newProduct,HttpStatus.CREATED);

		return rp; 

	}
	@GetMapping("/all")
	public List<Product> fetchAllProducts(){
		
		List<Product> products = productService.getAllProducts();
		
		return products;
	}
	@GetMapping("/get/{pid}")
	public ResponseEntity<?> fetchProductDetails(@PathVariable("pid") int productId){
		ResponseEntity<?> responseEntity= null;
		Product product = productService.getProductById(productId);
		if(product!=null) {
			responseEntity = new ResponseEntity<>(product,HttpStatus.OK);
		}
		else {
			responseEntity =  new ResponseEntity<>("Product not available with id"+productId,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@GetMapping("/getbyname/{pname}")
	public ResponseEntity<?> fetchProductDetails(@PathVariable("pname") String productName){
		ResponseEntity<?> responseEntity= null;
		Product product = productService.getProductByName(productName);
		if(product!=null) {
			responseEntity = new ResponseEntity<>(product,HttpStatus.OK);
		}
		else {
			responseEntity =  new ResponseEntity<>("Product not available with id"+productName,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> deleteProduct(@PathVariable("pid") int productId){
		
		productService.deleteProduct(productId);
		
		return new ResponseEntity<>("Product Deleted with id :"+productId,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?>  modifyProduct(@RequestBody Product product){
		
		Product updateProduct = productService.updateProduct(product);
		
		return new ResponseEntity<>(updateProduct,HttpStatus.OK);
		
	}
	
}
	

