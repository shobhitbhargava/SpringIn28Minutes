package com.spring.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	
	//GET /Users will give all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//GET /Users/{ID} will give specific record
	@GetMapping("/users/{id}")
	public User retrieveOneUsers(@PathVariable Integer id){
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException("ID: "+id);
		}
		
		return user;
	}
	
	//POST /users
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		// If we want to return the location of the newly created user
		// return this - /users/4 => /users /{id}, user.getID
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//DELETE /Users/{ID} will give specific record
	@DeleteMapping("/users/{id}")
	public void deleteOneUsers(@PathVariable Integer id){
		service.deleteByID(id);
	}
	
}
