package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.Signup;
import com.user.service.CustomerService;
@CrossOrigin
@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	CustomerService service;
	@GetMapping("/retrive/{custid}")

	//@RequestMapping
	//@ApiOperation(value="Returns the product entity ")
	
	public Signup fetchcustomer(@PathVariable long custid) {
		return service.getCustomer(custid);
		
	}
	@GetMapping
	@RequestMapping("/retrieve/all")
	public ResponseEntity<List<Signup>> fetchcustomer(){
return ResponseEntity.ok().body(service.getCustomers());
		
	}
@PostMapping
@RequestMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> addCustomer(@RequestBody Signup signup){
	Exception exception=null;
	Signup tempProduct=null;
	try {
		tempProduct=service.custadd(signup);
	}
	catch(Exception e) {
		exception=e;
	}
	if(tempProduct!=null) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tempProduct);
	}
	else {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	}
	}
	
@DeleteMapping
@RequestMapping(value="delete/{custtId}")

public void deleteCustomer(@PathVariable long custId) {
	service.deleteCustomer(custId);
	
}
@PutMapping("/update/{id}")
public ResponseEntity<Signup> updateCustomer(@PathVariable long id,@RequestBody Signup cs){
	  
	  return service.updateCustomerById(id, cs);

}
@GetMapping("/getbyname/{name}")
public ResponseEntity<Signup> getCustomerByName(@PathVariable String name){
	  return service.getCustomerByName(name);
}
}
