package com.example.demo.subModel;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String name;
	private Long score;
	private Long rank;
	
	public User(String id, String name, Long score, Long rank) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.rank = rank;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}

}
