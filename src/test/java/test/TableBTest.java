package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import game.db.table.TableB;
import game.util.Util;

public class TableBTest {

	@Test
	public void insertTest() {
		TableB tb = new TableB();
		tb.create("ACS", String.class, "BCI", Integer.class, "CCF", Float.class);

		tb.insert("ACS1", 2221, 33.1f);
		tb.insert("ACS2", 2222, 33.2f);
		tb.insert("ACS3", 2223, 33.3f);
		tb.insert("ACS4", 2224, 33.4f);
		tb.insert("ACS5", 2225, 33.5f);
		tb.insert("ACS6", 2226, 33.5f);
		tb.insert("ACS7", 2225, 33.7f);
		Map<String, Object> mapv = new HashMap<>();
		mapv.put("ACS", "ACS8");
		mapv.put("BCI", 2225);
		tb.insert(mapv);

		Map<String, Object> map = new HashMap<>();
//		map.put("ACS", "ACS3");
		map.put("BCI", 2225);
//		map.put("CCF", 33.5f);
		tb.select(map).forEach(os->Util.printArray(os));
		System.out.println("----------------------------");

		Map<String, Object> map1 = new HashMap<>();
//		map1.put("ACS", "ACS3");
//		map1.put("BCI", 2225);
		map1.put("CCF", 33.5f);
		tb.update(map, map1);
		tb.select().forEach(os->Util.printArray(os));
		System.out.println("----------------------------");

		Map<String, Object> map2 = new HashMap<>();
		map2.put("ACS", "ACS7");
		Map<String, Object> map3 = new HashMap<>();
		map3.put("BCI", null);
		tb.update(map2, map3);
		tb.select(new HashMap<>()).forEach(os->Util.printArray(os));
		System.out.println("----------------------------");

		tb.delete(map3);
		tb.select().forEach(os->Util.printArray(os));
	}

	@Test
	public void test1() {
		int as[] = new int[] {1,2,3};
		int bs[] = as;
		bs[0] = 0;
		bs[1] = 0;
		bs[2] = 0;
		Util.printArray(as);
	}

	@Test
	public void test2() {
		Object as[] = new Object[] {1,2,3};
		for (Object bs[] : new Object[][] {as}) {
			bs[0] = 0;
			bs[1] = 0;
			bs[2] = 0;
		}
		Util.printArray(as);
	}

	List<String> list1 = new ArrayList<String>();
	List<?> list2 = null;
	List<? extends Number> list3 = null;
	IF<? extends IF> i = null;
	@Test
	public void test0() {
		name(1,1d,1f,'1',(short)1, (byte) 1);
		name(1,1f,1d,(short)1, (byte) 1);
		name(1f);
		name(1);
		name("");
	}

	public void name(Integer i, Number n, Float f, Character c, Short s, Byte b) {
		System.out.println(n.getClass());
	}

//	public <T extends Integer, String> void name(T... t) {
//		System.out.println(t.getClass());
//	}

	public <T extends Number, CharSequence> void name(T... t) {
		System.out.println(t.getClass());
	}

	public <T extends Integer, String> void name(T... t) {
		System.out.println(t.getClass());
	}

	public <T extends CharSequence> void name(T... t) {
		System.out.println(t.getClass());
	}

//	public void name(Number... t) {
//		System.out.println(t.getClass());
//	}

	public static class DBObj {

	}
	class Static2<T extends Calendar> {
		void method() {
			Calendar cal = T.getInstance();
			System.out.println(cal.getClass());
		}
	}
	interface IF <T extends Number> {

	}
	abstract class Ccc extends Number implements IF<Ccc> {
	}

	class C1 {  }
	interface I2 {  }
	interface I3 {  }

	static class SampleExtends<T extends C1 & I2 & I3> {
		public void test(T arg) {
			C1 i1 = arg;	//argのクラスであるTはC1から派生しているのが保証されるので、C1に代入可能
			I2 i2 = arg;	//argのクラスであるTはI2から派生しているのが保証されるので、I2に代入可能
			I3 i3 = arg;	//argのクラスであるTはI3から派生しているのが保証されるので、I3に代入可能
		}
	}
}
