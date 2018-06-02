package game.util;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * http://d.hatena.ne.jp/Nagise/20131121/1385046248
 * http://nodamushi.hatenablog.com/entry/20100628/1277685239
 */
public abstract class BaseDao<T> {
	@SuppressWarnings("unchecked")
	public T instance() {
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
	public T array(int... dimensions) {
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
