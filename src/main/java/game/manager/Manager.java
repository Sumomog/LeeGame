package game.manager;

import java.util.HashMap;
import java.util.Map;

public abstract class Manager<T> {
	private Map<String, T> data = new HashMap<>();
	public T getData(String fileName) {
		T item = data.get(fileName);
		if (item == null) {
			item = load(fileName);
			data.put(fileName, item);
		}
		return item;
	}
	protected abstract T load(String fileName);
}
