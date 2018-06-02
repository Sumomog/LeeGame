package game.db.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableA implements Table {

	private Map<String, Class<?>> columns = new HashMap<>();
	private List<Object[]> rows;

	@Override
	public int create(Object... objects) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int insert(Object... objects) {
		if (columns.values().size() != objects.length) return 0;
		if (!colEqulas(columns.values(), objects)) return 0;
		rows.add(objects);
		return 1;
	}

	@Override
	public int insert(Map<String, Object> objects) {
		Object[] os = new Object[columns.values().size()];
		int i = 0;
		for(Map.Entry<String, Class<?>> e : columns.entrySet()) {
			Object o = objects.get(e.getKey());
			if (e.getValue() != o.getClass()) return 0;
			os[i++] = o;
		}
		rows.add(os);
		return 1;
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
