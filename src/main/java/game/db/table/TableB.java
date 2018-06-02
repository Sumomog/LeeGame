package game.db.table;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TableB implements Table {

	private Map<String, Class<?>> columns = new LinkedHashMap<>();
	private Map<String, Object[]> rows = new TreeMap<>();
	private char num;

	/**
	 * @param arg A名, A型, B名, B型
	 */
	@Override
	public int create(Object... objects) {
		if (objects.length == 0 || objects.length%2 != 0) return 0;
		for (int i = 0; i < objects.length; i += 2) {
			columns.put((String)objects[i], (Class<?>)objects[i+1]);
		}
		return 1;
	}

	@Override
	public int insert(Object... objects) {
		if (columns.values().size() != objects.length) return 0;
		if (!colEqulas(columns.values(), objects)) return 0;
		rows.put(createPK(num, objects), objects);
		return 1;
	}

	@Override
	public int insert(Map<String, Object> objects) {
		Object[] os = new Object[columns.values().size()];
		int i = 0;
		for(Map.Entry<String, Class<?>> e : columns.entrySet()) {
			Object o = objects.get(e.getKey());
			if (o == null) continue;
			if (e.getValue() != o.getClass()) return 0;
			os[i++] = o;
		}
		rows.put(createPK(num, objects.values().toArray(new Object[]{})), os);
		return 1;
	}

	@Override
	public int update(Map<String, Object> conditions, Map<String, Object> objects) {
		int i = 0;
		for (Object[] os : select(conditions)) {
			for (Condition cs : condition(objects, columns)) {
				os[cs.no] = cs.value;
				i++;
			}
		}
		return i;
	}

	@Override
	public List<Object[]> select() {
		List<Object[]> list = new ArrayList<>();
		for (Object os[] : rows.values()) {
			list.add(os);
		}
		return list;
	}

	@Override
	public List<Object[]> select(Map<String, Object> conditions) {
		List<Object[]> list = new ArrayList<>();
		Condition[] cs = condition(conditions, columns);
		for (Object os[] : rows.values()) {
			if (flag(os, cs)) {
				list.add(os);
			}
		}
		return list;
	}

	@Override
	public int delete(Map<String, Object> conditions) {
		Condition[] cs = condition(conditions, columns);
		List<String> kl = new ArrayList<>();
		for (Map.Entry<String, Object[]> e : rows.entrySet()) {
			if (flag(e.getValue(), cs)) {
				kl.add(e.getKey());
			}
		}
		kl.forEach(k->rows.remove(k));
		return kl.size();
	}
}
