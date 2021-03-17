package com.example.demo.subModel;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public interface UserRepository {
	
	void save(User user);
	Map<String, User> findAll();
	void update(User user);
	void delete(String id);
	User findById(String id);
	ResponseObject getTop();
	//void saveZSet(User user);
	void doOp();
	Set<TypedTuple<String>> saveZSet(String id, Double score);
	ResponseObject getRanking(String id, Double score);
	
	
}
