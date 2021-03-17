package com.example.demo.subModel;
//package model;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//public class UserResource {
//	
//	private UserRepository userRepository;
//
//	public UserResource(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
//	@RequestMapping("/hello")
//	public String helloWorld() {
//		return "";
//	}
//	
//	@GetMapping("add/{id}/{name}")
//	public User add(@PathVariable("id") final String id,
//					@PathVariable("name") final String name) {
//						
//		userRepository.save(new User(id, name, 20000L, 0L));
//		return userRepository.findById(id);
//		
//	}
//	
//	@GetMapping("update/{id}/{name}")
//	public User update(@PathVariable("id") final String id,
//					@PathVariable("name") final String name) {
//						
//		userRepository.update(new User(id, name, 20000L, 0L));
//		return userRepository.findById(id);
//		
//	}
//	
//	@GetMapping("all")
//	public Map<String, User> update() {
//						
//		return userRepository.findAll();
//		
//		
//	}
//
//}
