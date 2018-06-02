package game.util;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * http://d.hatena.ne.jp/Nagise/20131121/1385046248
 * http://nodamushi.hatenablog.com/entry/20100628/1277685239
 * https://qiita.com/reki2000/items/6c54099f6d90b4099a3e
 */
public class Generator<T> {
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
		return (T)Array.newInstance(entityClass.getComponentType(), dimensions);
	}
	@SuppressWarnings("unchecked")
	public static <T> T typeInstance1(T... ta) {
		try {
			Class<?> clazz = ta.getClass();
			Class<?> componentType = clazz.getComponentType();
			return (T) componentType.newInstance();
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T typeArray1(int... dimensions) {
		Class<T> c = typeClass();
		return (T) Array.newInstance(c.getComponentType(), dimensions);
	}
	@SuppressWarnings("unchecked")
	public static <T> Class<T> typeClass(T... ta) {
		return (Class<T>) ta.getClass().getComponentType();
	}
	public static <T> T typeInstance() {
		return new Generator<T>().instance();
	}
	public static <T> T typeArray(int... dimensions) {
		return new Generator<T>().array(dimensions);
	}
	@SuppressWarnings("unchecked")
	public static <T> T[][] createMapData(int x, int y, T... ta) {
		return (T[][]) Array.newInstance(ta.getClass().getComponentType(), x, y);
	}
	@SafeVarargs
	@SuppressWarnings("unchecked")
	public static <T> T createArray(int[] dimensions, T... t) {
		return (T) Array.newInstance(Util.getComponentType(t.getClass()), dimensions);
	}
}
