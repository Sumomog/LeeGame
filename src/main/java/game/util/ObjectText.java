package game.util;

import java.lang.reflect.Field;

public class ObjectText {
	public static <T> T toObject(String text, Class<T> type) {
		try {
			T o = type.newInstance();
			String[] ss = text.split(" ");
			Field[] f = type.getDeclaredFields();
			for (int i = 0; i < f.length; i++) {
				f[i].set(o, ss[i]);
			}
			return o;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String text, T... t) {
		try {
			Class<?> c = t.getClass().getComponentType();
			T o = (T) c.newInstance();
			String[] ss = text.split(" ");
			Field[] f = c.getDeclaredFields();
			for (int i = 0; i < f.length; i++) {
				f[i].set(o, ss[i]);
			}
			return o;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}
}
