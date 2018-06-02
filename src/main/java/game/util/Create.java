package game.util;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * http://d.hatena.ne.jp/Nagise/20131121/1385046248
 * http://nodamushi.hatenablog.com/entry/20100628/1277685239
 */
public interface Create<T> {
//	@SuppressWarnings("unchecked")
//	default T instance() {
//		for (Type t : this.getClass().getGenericInterfaces()) {
//			if (t == Create.class) continue;
//			Class<T> tc = (Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];
//			try {
//				return (T) tc.newInstance();
//			} catch (ReflectiveOperationException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		return null;
//	}
//	default T instance() {
//		Type t = (Type) getCreateType(this.getClass());
//		Class<T> tc = (Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];
//		try {
//			return (T) tc.newInstance();
//		} catch (ReflectiveOperationException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	default T instance() {
//		Type t = this.getClass().getGenericSuperclass();
//		Class<T> tc = (Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];
//		try {
//			return (T) tc.newInstance();
//		} catch (ReflectiveOperationException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	@SuppressWarnings("unchecked")
//	default <E> E array(int... dimensions) {
//		// 実行時の型が取れる。ここではHogeDaoなど
//		Class<?> clazz = this.getClass();
//		// ここではBaseDao<Hoge>がとれる
//		Type type = clazz.getGenericInterfaces()[0];
//		ParameterizedType pt = (ParameterizedType)type;
//		// BaseDaoの型変数に対するバインドされた型がとれる
//		Type[] actualTypeArguments = pt.getActualTypeArguments();
//		Class<?> entityClass = (Class<?>)actualTypeArguments[0];
//		// リフレクションでインスタンスを生成
//		return (E) Array.newInstance(entityClass, dimensions);
//	}
//	default Class<?> getCreateType(Class<?> c) {
//		if (c == Create.class) return c;
//		if (c.getGenericInterfaces().length > 0) {
//			for (Type t : c.getGenericInterfaces()) {
//				Class<?> cls = getCreateType((Class<?>) t);
//				if (cls != null) {
//					return getCreateType(cls);
//				}
//			}
//		}
//		if (c.getSuperclass() != null) {
//			return getCreateType(c.getSuperclass());
//		}
//		return null;
//	}
	default Object getCreateType(Object c) {
		if (c == Create.class) return c;
		if (c instanceof Class && ((Class<?>) c).getGenericInterfaces().length > 0) {
			for (Type t : ((Class<?>) c).getGenericInterfaces()) {
				Object cls = getCreateType(t);
				if (cls != null) {
					return getCreateType(cls);
				}
			}
		}
		if (c instanceof Class && ((Class<?>) c).getSuperclass() != null) {
			return getCreateType(((Class<?>) c).getSuperclass());
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	default T instance() {
		try {
			// 実行時の型が取れる。ここではHogeDaoなど
			Class<?> clazz = this.getClass();
			// ここではBaseDao<Hoge>がとれる
			Type type = clazz.getGenericSuperclass();
			ParameterizedType pt = (ParameterizedType)type;
			// BaseDaoの型変数に対するバインドされた型がとれる
			Type[] actualTypeArguments = pt.getActualTypeArguments();
			Class<T> entityClass = (Class<T>)actualTypeArguments[0];
			// リフレクションでインスタンスを生成
			return (T) entityClass.newInstance();
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	default T array(int... dimensions) {
		// 実行時の型が取れる。ここではHogeDaoなど
		Class<?> clazz = this.getClass();
		// ここではBaseDao<Hoge>がとれる
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType)type;
		// BaseDaoの型変数に対するバインドされた型がとれる
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		Class<?> entityClass = (Class<?>)actualTypeArguments[0];
		// リフレクションでインスタンスを生成
		return (T)Array.newInstance(entityClass, dimensions);
	}
}
