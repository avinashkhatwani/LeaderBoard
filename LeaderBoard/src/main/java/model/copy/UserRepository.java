package model.copy;


import java.util.List;
import java.util.Map;

public interface UserRepository {
	
	void save(User user);
	Map<String, User> findAll();
	void update(User user);
	void delete(String id);
	User findById(String id);
	
	
}
