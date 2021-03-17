package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.subModel.*;
//import model.UserRepository;

//@RequestMapping
@SpringBootApplication
public class LeaderBoardApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(LeaderBoardApplication.class, args);
	}
	
	private UserRepository userRepository;
	
	
//	@RequestMapping("/")
//	@ResponseBody
//	public String index() {
//		System.out.println("HERE again");
//		return "Greetings from Spring Boot!";
//	}
//	
//	@GetMapping("add/{id}/{name}")
//	@ResponseBody
//	public String add(@PathVariable("id") final String id,
//					@PathVariable("name") final String name) {
//						
//		System.out.println("HERE1");
//		return id+" "+name;
//		
//	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate() {
	    RedisTemplate<String, User> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

}
