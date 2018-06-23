package game.util;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Util {
	public static void printArray(Object obj) {
		if (obj == null || !obj.getClass().isArray()) {
			System.out.print(obj);
			System.out.print(" ");
			return;
		}
		for (int i = 0; i < Array.getLength(obj); i++) {
			printArray(Array.get(obj, i));
		}
		System.out.println();
	}
	public static void printArrayToJson(Object obj) {
		if (obj == null || !obj.getClass().isArray()) {
			System.out.print(obj);
			System.out.print(",");
			return;
		}
		System.out.println("{");
		for (int i = 0; i < Array.getLength(obj); i++) {
			printArrayToJson(Array.get(obj, i));
		}
		System.out.println("}");
	}
	public static Class<?> getComponentType(Class<?> c) {
		return c == null || c.getComponentType() == null ? c : getComponentType(c.getComponentType());
	}
	@SafeVarargs
	public static <T> T[] toArray(T... t){
		return t;
	}
	@SafeVarargs
	public static int[] toArray(int... i){
		return i;
	}
	/**
	 * 実行中のメソッド名を取得します。
	 * @return メソッド名
	 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	private static int cnt = -1;
	public static void forEach(Func func, int... max) {
		if (++cnt > max.length - 1)return;
		int[] arg = new int[max.length];
		System.arraycopy(max, 0, arg, 0, max.length);
		for (int j = 0; j < max[cnt]; j++) {
			arg[cnt] = j;
			forEach(func, arg);
			if (max.length-1 >= cnt--) continue;
			func.func(arg);
		}
	}
	public static <V> void forEach(Collection<V> values, BiConsumer<Integer, V> func) {
		int i = 0;
		for (V v : values) func.accept(i++, v);
	}
	/** forEachより遅い */
	public static void forEach2(Func func, int... max) {
		int i = max.length;
		int[] arg = new int[i];
//		Function<Integer, Integer>[] fs = Generator.createArray(toArray(i));
//		@SuppressWarnings("unchecked")
//		Function<Integer, Integer>[] fs = (Function[]) Array.newInstance(Function.class, i);
		@SuppressWarnings("unchecked")
		Function<Integer, Integer>[] fs = new Function[i];
		int m = max[--i];
		int c1 = m;
		fs[i--] = (t) -> {return t % c1;};
		for ( ; i >= 0; i--) {
			int c2 = m;
			m*=max[i];
			int c3 = m;
			fs[i] = (t) -> {return t % c3 / c2;};
		}
		for (i = 0; i < m; i++) {
			for (int j = 0; j < fs.length; j++) {
				arg[j] = fs[j].apply(i);
			}
			func.func(arg);
		}
	}
	public static void forEach2(int xMax, int yMax, BiConsumer<Integer, Integer> func) {
		for (int x = 0; x < xMax; x++) {
			for (int y = 0; y < yMax; y++) {
				func.accept(x, y);
			}
		}
	}
	public static interface Func {
		void func(int... max);
	}
	public static void splitFor(String data, String regex, Consumer<String> func) {
		if (data == null) return;
		for (String s : data.split(regex)) {
			func.accept(s);
		}
	}
	public static <R> R[] splitToArray(String data, String regex, Function<String, R> func) {
		if (data == null) return null;
		String[] ss = data.split(regex);
		R[] ds = getLambdaGenericArray(func, ss.length);
		for (int i = 0; i < ss.length; i++) {
			ds[i] = func.apply(ss[i]);
		}
		return ds;
	}
	public static int[] splitToArray(String data, String regex) {
		if (data == null) return null;
		String[] ss = data.trim().split(regex);
		int[] ds = new int[ss.length];
		for (int i = 0; i < ss.length; i++) {
			ds[i] = Integer.valueOf(ss[i]);
		}
		return ds;
	}
	public static Type getLambdaGeneric(Object func, int index) {
		if (func == null) return null;
		Type t = func.getClass().getGenericInterfaces()[0];
		return ((ParameterizedType)t).getActualTypeArguments()[index];
	}
	public static Class<?> getLambdaGenericEx(Object func, int index) {
		return (Class<?>) getLambdaGeneric(func, index);
	}
	@SuppressWarnings("unchecked")
	public static <R> R getLambdaGenericNew(Object func, int index) {
		try {
			return (R) getLambdaGenericEx(func, index).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public static <R> R getLambdaGenericArray(Object func, int index, int... dimensions) {
		Class<?> lg = getLambdaGenericEx(func, 1);
		return (R) Array.newInstance(lg, dimensions);
	}
	public static <K,V> void updateMapForEach(Map<K,V> map, BiFunction<K,V,V> func) {
		for(Map.Entry<K, V> e : map.entrySet()) {
			map.put(e.getKey(), func.apply(e.getKey(), e.getValue()));
		}
	}
	public interface BiBiConsumer <K,V> {
		void func(K k, V v, int i);
	}
	public static <K,V> void mapForEach(Map<K,V> map, BiBiConsumer<K,V> func) {
		int i = 0;
		map.forEach((k,v)->{
			func.func(k, v, i);
		});
	}
	public interface BiBiBiConsumer <K,V,V2> {
		void func(K k, V v, int i, V2 v2);
	}
	public static <K,V,V2> void mapForEach(Map<K,V> map, Map<K,V2> map2, BiBiBiConsumer<K,V,V2> func) {
		int i = 0;
		map.forEach((k,v)->{
			func.func(k, v, i, map2.get(k));
		});
	}
	public static void runFunc(boolean flag, Runnable runner) {
		if(flag) runner.run();
	}
	public static void runFunc(boolean flag, Runnable... runner) {
		if(flag) for(Runnable r : runner) r.run();
	}
/**
  * https://www.yoheim.net/blog.php?q=20160410
  *	java.util.functionパッケージ
  * Java8では、よく使われそうな関数型インターフェースがいくつか提供されています。それらはjava.util.functionパッケージで提供されていて、以下のような種類が存在します。
  */
	// Supplier系
	// 引数なしで値を返す
	@SuppressWarnings("rawtypes")
	Supplier lambda1 = () -> "aaa";

	// Consumer系
	// 値を受け取るが、返却しない（処理を行う）
	@SuppressWarnings("rawtypes")
	Consumer lambda2 = (str) -> System.out.println(str);

	// Predicate系
	// 値を受け取って判定を返す
	@SuppressWarnings("rawtypes")
	Predicate lambda3 = (str) -> str == "hello";

	// Function系
	// 値を受け取って、何か値を返す
	@SuppressWarnings("rawtypes")
	Function lambda4 = (str) -> str;

	// 上記それぞれで、
	// 引数を2つ受け取る場合には「BiXXX」を使う
	@SuppressWarnings("rawtypes")
	BiFunction lambda5 = (str, num) -> str + "" + num;

	// 上記それぞれで、
	// プリミティブ型に特化したものが存在する（int, long, double）
/*	IntPredicate lambda6 = (num) -> num > 10;
	詳細は、Java関数型インターフェースメモ(Hishidama's Java8 Functional Interface Memo)がわかりやすくまとめられています。
*/
	public static <T> T[] newArray(T[] array, Supplier<T> get) {
		for (int i = 0; i < array.length; i++) {
			array[i] = get.get();
		}
		return array;
	}
//	public static <T> T[] newArray(int size, Supplier<T> get) {
//		@SuppressWarnings("unchecked")
//		T[] array = (T[]) Array.newInstance(getLambdaGenericEx(get,1), size);
//		for (int i = 0; i > array.length; i++) {
//			array[i] = get.get();
//		}
//		return array;
//	}

//	public static <T extends Number> T abs(T a) {
//		return a> ? a : -a;
//	}
	public static byte abs(byte a) { return (byte) (a>0 ? a : -a); }
	public static short abs(short a) { return (short) (a>0 ? a : -a); }
	public static int abs(int a) { return a>0 ? a : -a; }
	public static long abs(long a) { return a>0 ? a : -a; }

	public static byte abs(byte a, byte b) { return (byte) (a>b ? a-b : b-a); }
	public static short abs(short a, short b) { return (short) (a>b ? a-b : b-a); }
	public static int abs(int a, int b) { return a>b ? a-b : b-a; }
	public static long abs(long a, long b) { return a>b ? a-b : b-a; }
}
