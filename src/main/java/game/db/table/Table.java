package game.db.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Table {

	int create(Object... objects);
	int insert(Object... objects);
	int insert(Map<String, Object> objects);
	int update(Map<String, Object> conditions, Map<String, Object> objects);
	List<Object[]> select();
	List<Object[]> select(Map<String, Object> conditions);
	int delete(Map<String, Object> conditions);

	default boolean colEqulas(Collection<Class<?>> clas, Object[] valuse) {
		int i = 0;
		for (Class<?> c : clas) {
			if (c != valuse[i++].getClass()) return false;
		}
		return true;
	}

	default String createPK(char size, Object[] valuse) {
		String pk = "" + valuse[0];
		for (char i = 1; i < size; i++) {
			pk += ":"+valuse[i];
		}
		return pk;
	}

	default Condition[] condition(Map<String, Object> conditions, Map<String, Class<?>> columns) {
		int i = 0;
		List<Condition> list = new ArrayList<>();
		for (String key : columns.keySet()) {
			if (conditions.containsKey(key)) {
				Condition c = new Condition();
				c.no = i;
				c.value = conditions.get(key);
				list.add(c);
			}
			i++;
		}
		return list.toArray(new Condition[]{});
	}

	default boolean flag(Object os[], Condition cs[]) {
		for (Condition c : cs) {
			if (os[c.no] == null && c.value == null) {
				continue;
			}
			if (!os[c.no].equals(c.value)) {
				return false;
			}
		}
		return true;
	}

	default boolean flag(Object os[], String condition) {
		return true;
	}

	static class Condition {
		int no;
		Object value;
	}

	static class aaa {

	}
}
