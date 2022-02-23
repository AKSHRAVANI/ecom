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
import org.springframework.web.bind.annotation.RestController;

import com.abc.ecom.entity.Customer;
import com.abc.ecom.service.CustomerService;


@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
     
	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		
		Customer newCustomer = customerService.saveCustomer(customer);
		
		ResponseEntity<Customer> rc = new ResponseEntity<>(newCustomer,HttpStatus.CREATED);

		return rc; 

	}
	@GetMapping("/all")
	public List<Customer> fetchAllProducts(){
		
		List<Customer> customers = customerService.getAllCustomers();
		
		return customers;
	}
	@GetMapping("/get/{cid}")
	public ResponseEntity<?> fetchCustomerDetails(@PathVariable("cid") int customerId){
		ResponseEntity<?> responseEntity= null;
		Customer customer = customerService.getCustomerById(customerId);
		if(customer!=null) {
			responseEntity = new ResponseEntity<>(customer,HttpStatus.OK);
		}
		else {
			responseEntity =  new ResponseEntity<>("Customer not available with id"+customerId,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("cid") int customerId){
		
		customerService.deleteCustomer(customerId);
		
		return new ResponseEntity<>("Customer Deleted with id"+customerId,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?>  modifyCustomer(@RequestBody Customer customer){
		
		Customer updateCustomer = customerService.updateCustomer(customer);
		
		return new ResponseEntity<>(updateCustomer,HttpStatus.OK);
		
	}
}
