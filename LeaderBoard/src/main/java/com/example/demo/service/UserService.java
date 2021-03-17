package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.example.demo.subModel.*;

import exception.ApiRequestException;

import com.example.demo.service.*;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	public UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
	
	public void doOp() {
		
		userRepository.doOp();
		
	}
	
	public User add(User user) {
		userRepository.save(user);
		//userRepository.saveZSet(user);
		return userRepository.findById(user.getId());
	}
	
	public Set<ZSetOperations.TypedTuple<String>> addZSet(String id, String score) {
		Double scoreD = Double.parseDouble(score);
		return userRepository.saveZSet(id, scoreD);
	}
	
	public ResponseObject getTop() {
		return userRepository.getTop();
	}
	
	public User update(User user) {
		userRepository.update(user);
		return userRepository.findById(user.getId());
	}
	
	public Map<String, User> findAll(){
		return userRepository.findAll();
	}

	public ResponseObject getRank(String id, String score) {
		try {
			Double scoreD = Double.parseDouble(score);
			return userRepository.getRanking(id, scoreD);
		}
		catch (Exception e) {
			throw new ApiRequestException("Please provide a Integer/Double as a score");
		}
	}
	
}