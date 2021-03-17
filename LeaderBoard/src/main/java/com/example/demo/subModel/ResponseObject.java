package com.example.demo.subModel;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public class ResponseObject {
	private String userId;
	private Set<TypedTuple<String>> table;
	private String numOfTimesPlayed;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<TypedTuple<String>> getTable() {
		return table;
	}
	public void setTable(Set<TypedTuple<String>> table) {
		this.table = table;
	}
	public String getNumOfTimesPlayed() {
		return numOfTimesPlayed;
	}
	public void setNumOfTimesPlayed(String numOfTimesPlayed) {
		this.numOfTimesPlayed = numOfTimesPlayed;
	}
}
