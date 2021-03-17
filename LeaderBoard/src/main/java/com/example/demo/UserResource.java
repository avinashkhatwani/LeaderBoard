package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import model.User;
//import model.UserRepository;
//import service.UserService;

import com.example.demo.subModel.*;
import com.example.demo.service.*;

@RestController
public class UserResource{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		System.out.println("HERE again");
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/add/{id}/{name}")
	@ResponseBody
	public User add(@PathVariable("id") final String id,
					@PathVariable("name") final String name) {
		return userService.add(new User(id, name, 20000L, 0L));
		
	}
	
	@GetMapping("/addZSet/{id}/{score}")
	@ResponseBody
	public Set<ZSetOperations.TypedTuple<String>> addZSet(@PathVariable("id") final String id,
					@PathVariable("score") final String score) {
		return userService.addZSet(id,score);
		
	}
	
	@GetMapping("/getRank/{id}/{score}")
	@ResponseBody
	public ResponseObject getRank(@PathVariable("id") final String id,
			@PathVariable("score") final String score) {
		return userService.getRank(id, score);
		
	}
	
	@GetMapping("/getZSetTop")
	@ResponseBody
	public ResponseObject addZSet() {
		return userService.getTop();
		
	}
	
	@GetMapping("update/{id}/{name}")
	public User update(@PathVariable("id") final String id,
					@PathVariable("name") final String name) {
		
		return userService.update(new User(id, name, 20000L, 0L));		
	}
	
	@GetMapping("/all")
	@ResponseBody
	public Map<String, User> update() {
		return userService.findAll();
	}
	
	@GetMapping("/doOp")
	@ResponseBody
	public void doOp() {
		userService.doOp();
	}
	
}
