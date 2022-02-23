package com.abc.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.ecom.entity.Customer;
import com.abc.ecom.exception.CustomerNotFoundException;
import com.abc.ecom.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {

		Customer savedCustomer =	customerRepository.save(customer);
	     
	    return savedCustomer;
	}
	@Override
	public List<Customer> getAllCustomers(){
		
		List<Customer> customers = customerRepository.findAll();
		
		return customers;
	}
	
	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException{
		
		 Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		 if(optionalCustomer .isEmpty()) {
			 throw new CustomerNotFoundException("Customer is not existing with id:"+customerId);
		 }
		return optionalCustomer.get();
	}
	@Override
	public void deleteCustomer(int customerId) {
		
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer .isEmpty()) {
			throw new CustomerNotFoundException("Customer is not existing with id:"+customerId);
	
		}
		else {
			customerRepository.deleteById(customerId);
		 }
		
	}
	@Override
	public Customer updateCustomer(Customer customer) {
		
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		 if(optionalCustomer .isEmpty()) {
			 throw new CustomerNotFoundException("Product is not existing with id:"+customer.getCustomerId());
		 }
		 Customer uc = customerRepository.save(customer);
		 
		 return uc;
	}
}
