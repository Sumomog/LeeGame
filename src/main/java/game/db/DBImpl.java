package game.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.db.table.Table;

public class DBImpl implements DB {

	private Map<String, Table> tables = new HashMap<>();

	@Override
	public boolean insert(String tableName, Map<String, Object> data) {

		return false;
	}

	@Override
	public int update(String tableName, Map<String, Object> data, Terms terms) {

		return 0;
	}

	@Override
	public List<Map<String, Object>> select(String tableName, List<String> data, Terms terms) {

		return null;
	}

	@Override
	public int delete(String tableName, Terms terms) {

		return 0;
	}
}
