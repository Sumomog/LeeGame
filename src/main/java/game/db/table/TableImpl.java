package game.db.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.util.Util;

public class TableImpl implements Table {

	private Map<String, List<Object>> columns = new HashMap<>();

	public Map<String, List<Object>> getColumns() {
		return columns;
	}

	@Override
	public int create(Object... objects) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int insert(Object... objects) {
		if (columns.values().size() != objects.length) throw new RuntimeException();
		Util.forEach(columns.values(), (i, v) -> v.add(objects[i]));
		return 1;
	}

	@Override
	public int insert(Map<String, Object> objects) {

		return 0;
	}

	@Override
	public int update(Map<String, Object> conditions, Map<String, Object> objects) {

		return 0;
	}

	@Override
	public List<Object[]> select(Map<String, Object> objects) {

		return null;
	}

	@Override
	public int delete(Map<String, Object> objects) {

		return 0;
	}

	@Override
	public List<Object[]> select() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
