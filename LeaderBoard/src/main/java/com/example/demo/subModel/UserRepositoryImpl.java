package com.example.demo.subModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	int i = 0;
	JSONObject JSON = new JSONObject();
	
    @Autowired
    private StringRedisTemplate redisTemplate1;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate2;

    public static final String SCORE_RANK = "score_rank";
    public static final String Leader_Board = "leader_board";

	
	
	private RedisTemplate<String, User> redisTemplate;
	
	private HashOperations hashOperations;
	private HashOperations hashOperations1;

	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate, RedisTemplate<String, String> redisTemplate2, StringRedisTemplate stringRedisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations= redisTemplate.opsForHash();
		hashOperations1= redisTemplate2.opsForHash();
		this.redisTemplate1 = stringRedisTemplate;
		this.redisTemplate2 = stringRedisTemplate;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		//redisTemplate.opsForZSet().add("USER", user, user.getRank());
		hashOperations.put("USER", user.getId(), user);
	}
	
	@Override
	public ResponseObject getRanking(String id, Double score) {
        
		ResponseObject responseObject = new ResponseObject();
		redisTemplate1.opsForZSet().add(Leader_Board, id, score);
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate1.opsForZSet().reverseRangeWithScores(Leader_Board, 0, -1);

		String value = "1";
		Long curValL = 0L;
		Map map = hashOperations1.entries("Played");
		if(hashOperations1.hasKey("Played", id)) {
			String curValue = (String)hashOperations1.get("Played", id);
			curValL = Long.valueOf(curValue);
			System.out.println("IT Exists!" + curValL);
		}
		value = String.valueOf(curValL+1);
		hashOperations1.put("Played", id, value);
		
		responseObject.setUserId(id);
		responseObject.setTable(rangeWithScores);
		responseObject.setNumOfTimesPlayed(value);
		
		return responseObject;
	}
	
	
	@Override
	public Set<ZSetOperations.TypedTuple<String>> saveZSet(String id, Double score) {
		// TODO Auto-generated method stub

		redisTemplate1.opsForZSet().add(Leader_Board,id, score);
		Long rankNum = redisTemplate1.opsForZSet().reverseRank(Leader_Board, id);
        Double score1 = redisTemplate1.opsForZSet().score(Leader_Board, id);
        
		Set<String> range = redisTemplate1.opsForZSet().reverseRange(Leader_Board, 0, 10);
        System.out.println("Get list of rankings:" + range);
        
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate1.opsForZSet().reverseRangeWithScores(Leader_Board, 0, -1);
//		System.out.println(rankNum+" ->"+score1);
		//List<String> arr = new ArrayList<>(rangeWithScores);
		//redisTemplate.opsForZSet().add(SCORE_RANK, user, 1D+(i++));
//        
//        for(TypedTuple<String> typedTuple : rangeWithScores) {
//        	System.out.println(typedTuple.getValue() +"  "+typedTuple.getScore());
//        }
        
        return rangeWithScores;
	}
	
	@Override
	public ResponseObject getTop() {
        
		ResponseObject responseObject = new ResponseObject();
		
		Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate1.opsForZSet().reverseRangeWithScores(Leader_Board, 0, -1);
		List<Object> res = new ArrayList<>();
        res.add(rangeWithScores);
        
		String value = "1";
		Long curValL = 0L;
		Map map = hashOperations1.entries("Played");
		if(hashOperations1.hasKey("Played", "A")) {
			String curValue = (String)hashOperations1.get("Played", "A");
			curValL = Long.valueOf(curValue);
			System.out.println("IT Exists!" + curValL);
		}
		value = String.valueOf(curValL+1);
		hashOperations1.put("Played", "A", value);
		
		responseObject.setUserId("userId");
		responseObject.setTable(rangeWithScores);
		responseObject.setNumOfTimesPlayed(value);
		
        res.add(value);
		return responseObject;
	}

	@Override
	public Map<String, User> findAll() {
		// TODO Auto-generated method stub
//		return hashOperations.entries("USER");
		
//        Set<User> set = redisTemplate.opsForZSet().reverseRange("score_rank", 0, 10);
//        //System.out.println("Get list of rankings:"+set.size()+" " + JSON.toJSONString());
		
		String k = "USER";
//		Map<String, User> map= hashOperations.entries("1");
		
		return hashOperations.entries("USER");
//		
//		System.out.println("HERE "+map);
//		for(String key : map.keySet()) {
//			System.out.println(map.get(key).getName());
//		}
//		return null;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		save(user);
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		hashOperations.delete("USER", id);
		
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return (User)hashOperations.get("USER", id);
	}

	@Override
	public void doOp() {

		redisTemplate1.opsForZSet().add("zSetValue","A",1);
		redisTemplate1.opsForZSet().add("zSetValue","B",3);
		redisTemplate1.opsForZSet().add("zSetValue","C",0);
		redisTemplate1.opsForZSet().add("zSetValue","D",5);
		
//		hashOperations1.put("Played", "A", "1");
//		hashOperations1.put("Played", "B", "2");
//		hashOperations1.put("Played", "C", "3");
		
		String value = "1";
		Long curValL = 0L;
		Map map = hashOperations1.entries("Played");
		System.out.println("before = "+map);
//		System.out.println(hashOperations1.get("Played", "A"));
		if(hashOperations1.hasKey("Played", "A")) {
			String curValue = (String)hashOperations1.get("Played", "A");
			curValL = Long.valueOf(curValue);
			//value= Long.toString(curValue);
			System.out.println("IT Exists!" + curValL);
		}
		value = String.valueOf(curValL+1);
		
		hashOperations1.put("Played", "A", value);
		map = hashOperations1.entries("Played");
		System.out.println("after = "+map);
		
		
//		Map map = hashOperations1.entries("Played");
//		hashOperations1.get("Played", "A");
//		hashOperations.delete("USER", userId);
//		
//		System.out.println("HERE "+map);
//		for(Object key : map.keySet()) {
//			System.out.println(key);
//			System.out.println(map.get(key));
//		}
//		
//		Set zSetValue = redisTemplate1.opsForZSet().range("zSetValue",0,-1);
//		System.out.println("Get the element of the specified interval by the range(K key, long start, long end) method: " + zSetValue);
//		
//		Set<String> range = redisTemplate1.opsForZSet().reverseRange("zSetValue", 0, 10);
//        System.out.println("Get list of rankings:" + range);
//        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate1.opsForZSet().reverseRangeWithScores("zSetValue", 0, 10);
//        //System.out.println("List of rankings and scores obtained:" + JSON.toJSONString(rangeWithScores));
//        List<String> lsts = new ArrayList<>();
//        if (rangeWithScores != null && rangeWithScores.size() > 0) {
//            Iterator<ZSetOperations.TypedTuple<String>> it = rangeWithScores.iterator();
//            while (it.hasNext()) {
//                ZSetOperations.TypedTuple<String> curr = it.next();
//                if (curr.getScore() <= System.currentTimeMillis()) {
//                    lsts.add(curr.getValue()+" "+curr.getScore());
//                } else {
//                    break;
//                }
//            }
//            // String jobMsgId = Objects.toString(sets.toArray()[0]);
//            System.out.println(lsts);
//        }
        
        
		
	}


}
