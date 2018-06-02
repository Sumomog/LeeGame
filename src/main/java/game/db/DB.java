package game.db;

import java.util.List;
import java.util.Map;

public interface DB {

	boolean insert(String tableName, Map<String, Object> data);
	int update(String tableName, Map<String, Object> data, Terms terms);
	List<Map<String, Object>> select(String tableName, List<String> data, Terms terms);
	int delete(String tableName, Terms terms);
}
