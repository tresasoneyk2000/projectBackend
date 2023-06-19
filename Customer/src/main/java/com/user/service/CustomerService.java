package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user.entity.Signup;
import com.user.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository custRepo;
	
	
	public Signup custadd(Signup cust) {
		
		return custRepo.save(cust);
	}
	
	public List<Signup> getCustomers(){
		return custRepo.findAll();	
	}
public void deleteCustomer(long userId) {
	custRepo.deleteById(userId);
}
public Signup getCustomer(long productId) {
	Optional<Signup> optional=
			custRepo.findById(productId);
			if(optional.isPresent()) {
				return optional.get();
			}
			else {
				return null;
			}
}
public ResponseEntity<Signup> updateCustomerById(long id, Signup cs) {
	
	Optional<Signup> c= custRepo.findById(id);
	if(c.isPresent()) {
		c.get().setUsername(cs.getUsername());
		c.get().setPassword(cs.getPassword());
		c.get().setEmail(cs.getEmail());//		c.get().setPhoneno(cs.getPhoneno());
//		c.get().setAddress(cs.getAddress());
		custRepo.save(c.get());
		return ResponseEntity.ok().body(c.get());
	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<Signup> getCustomerByName(String name){
	
	Optional<Signup> c= custRepo.findByUsername(name);
	if(c.isPresent()) {
		return ResponseEntity.ok().body(c.get());
	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
}
