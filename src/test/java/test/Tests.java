package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import game.map.MapDataA;
import game.map.MapDataB;
import game.map.data.CellClassA;
import game.map.data.CellEnumA;
import game.util.BaseDao;
import game.util.Create;
import game.util.Generator;
import game.util.Mouse2;
import game.util.Util;

class Tests {

	@Test
	void test01() {
//		MapData t = Generator.typeInstance1();
//		System.out.println("w:" + t.w + " h:" + t.h);
//		t.w = 123;
//		t.h = 321;
//		System.out.println("w:" + t.w + " h:" + t.h);
	}

	@Test
	void test02() {
		Tests t;
		t = Generator.typeInstance();
		System.out.println(t);
	}

	@Test
	void test03() {
		Tests t;
		t = new Generator<Tests>().instance();
		System.out.println(t);
	}

	@Test
	void test04() {
		//		Tests t;
		//		System.out.println(t);
	}

	@Test
	void test05() {
//		MapData ts[] = Generator.typeArray1(1);
//		for (MapData t : ts) {
//			System.out.println("w:" + t.w + " h:" + t.h);
//		}
	}

	@Test
	void test06() {
		String[][] sss = Generator.createMapData(3, 3);
		Util.printArray(sss);
		for (int x = 0; x < sss.length; x++) {
			for (int y = 0; y < sss[x].length; y++) {
				sss[x][y] = x + ":" + y;
			}
		}
		Util.printArray(sss);
	}

	@Test
	void test07() {
		String[][] sss = Generator.createArray(Util.toArray(3, 3));
		Util.printArray(sss);
		for (int x = 0; x < sss.length; x++) {
			for (int y = 0; y < sss[x].length; y++) {
				sss[x][y] = x + ":" + y;
			}
		}
		Arrays.toString(sss);
		Arrays.deepToString((Object[]) sss);
		Util.printArray(sss);
	}

	@Test
	void test08() {
		String[][][][] sss = Generator.createArray(Util.toArray(2, 3, 3, 3));
		Util.printArray(sss);
		Util.printArrayToJson(sss);
	}

	@Test
	void test09() {
		{
			String s = new Create<String>(){}.instance();
			Util.printArray(s);
			System.out.println();
		}
		{
			Object s = new Create<String>(){}.array(2,3,4);
			Util.printArray(s);
			Arrays.deepToString((Object[]) s);
		}
		{
			String s = new BaseDao<String>(){}.instance();
			Util.printArray(s);
			System.out.println();
		}
		{
			Object s = new BaseDao<String>(){}.array(2,3,4);
			Util.printArray(s);
		}
	}

	@Test
	void test10() {
		HashMap<String, BaseDao<String>> obj = new HashMap<String, BaseDao<String>>(){};
		obj.put("kkkk", new BaseDao<String>() {});
		System.out.println(obj.getClass().getGenericSuperclass());
		for (Object o : obj.getClass().getClasses()) {
			System.out.println(o);
		}
		System.out.println(obj.getClass().toGenericString());
		for (TypeVariable<?> o : obj.getClass().getTypeParameters()) {
			System.out.println(o);
			System.out.println(o.getGenericDeclaration());
			System.out.println(obj.getClass().getGenericSuperclass());
			for (Object t : o.getBounds()) {
				System.out.println(t);
			}
		}
		for (Type t : ((ParameterizedType)(obj.getClass().getGenericSuperclass())).getActualTypeArguments()) {
			System.out.println(t);
			if (t instanceof ParameterizedType) {
				for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
					System.out.println(t1);
				}
			}
		}
	}

	@Test
	void test11() {
		String[][][] s = getIn();
		Util.printArray(s);
	}
	<T> T getIn(){
		return new BaseDao<T>(){}.array(2,3,4);
	}

	@SuppressWarnings("static-access")
	@Test
	void test12() {
		String k = System.lineSeparator();
		String s = "abc.gif" + k + "32,32" + k + "abc";
		MapDataA<CellEnumA> mdb = new MapDataA<CellEnumA>(s);
		mdb.getCell(0, 0);
		System.out.println("-----------------------------------------------------------");
		System.out.println(CellEnumA.Ground.get());
		System.out.println(CellEnumA.Air.get());
		System.out.println(CellEnumA.Height.get());
		System.out.println("-----------------------------------------------------------");
		CellEnumA ca = mdb.getCell(CellEnumA.Ground, 0, 0);
		System.out.println(ca.Ground.get());
		System.out.println(ca.Air.get());
		System.out.println(ca.Height.get());
		System.out.println("-----------------------------------------------------------");
		System.out.println(mdb.getCell(CellEnumA.Ground, 0, 0).get());
		System.out.println(mdb.getCell(CellEnumA.Air, 0, 0).get());
		System.out.println(mdb.getCell(CellEnumA.Height, 0, 0).get());
	}

	@Test
	void test13() {
		String k = System.lineSeparator();
		String s = "abc.gif" + k + "32,32" + k + "abc";
		MapDataB<CellClassA> mdb = new MapDataB<CellClassA>(s);
		CellClassA cca = mdb.getCell(0, 0, CellClassA.class);
		System.out.println(cca.getAir());
		System.out.println(cca.getHeight());
		System.out.println(cca.getGround());
	}

	@Test
	void test14() {
//		{
//			long time = System.currentTimeMillis();
//			Util.forEach(Util::printArray, 7,6,5,4,3,2);
//			System.out.println(System.currentTimeMillis() - time);
//		}
//		{
//			long time = System.currentTimeMillis();
//			Util.forEach2(Util::printArray, 7,6,5,4,3,2);
//			System.out.println(System.currentTimeMillis() - time);
//		}

//		{
//			long time = System.currentTimeMillis();
//			Util.forEach(Tests::func, 99,99,99,99);
//			System.out.println(System.currentTimeMillis() - time);
//		}
//		{
//			long time = System.currentTimeMillis();
//			Util.forEach2(Tests::func, 99,99,99,99);
//			System.out.println(System.currentTimeMillis() - time);
//		}

		{
			long time = System.currentTimeMillis();
			Util.forEach(Tests::func, 9999,99999);
			System.out.println(System.currentTimeMillis() - time);
		}
		{
			long time = System.currentTimeMillis();
			Util.forEach(Tests::func, 9999,99999);
			System.out.println(System.currentTimeMillis() - time);
		}
		{
			long time = System.currentTimeMillis();
			Util.forEach2(9999, 99999, Tests::func2);
			System.out.println(System.currentTimeMillis() - time);
		}
		{
			long time = System.currentTimeMillis();
			Util.forEach2(9999, 99999, Tests::func2);
			System.out.println(System.currentTimeMillis() - time);
		}
	}
	public static void func(int[] arg) {
//		for (int i = 0; i < 999999; i++);
	}
	public static void func2(int xMax, int yMax) {
//		for (int i = 0; i < 999999; i++);
	}
	@Test
	void test15() {

		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0000, 0)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0001, 0)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0002, 0)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0000, 0)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0001, 0)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0002, 0)));

		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0000, 4)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0010, 4)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0020, 4)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0000, 4)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0010, 4)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0020, 4)));

		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0000, 8)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0100, 8)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0x0200, 8)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0000, 8)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0100, 8)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0x0200, 8)));

		System.out.println(Integer.toHexString(Mouse2.push((short) 0xFFF0, 0)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0xFFF1, 0)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0xFFF2, 0)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xFFF0, 0)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xFFF1, 0)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xFFF2, 0)));

		System.out.println(Integer.toHexString(Mouse2.push((short) 0xFF0F, 4)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0xFF1F, 4)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0xFF2F, 4)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xFF0F, 4)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xFF1F, 4)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xFF2F, 4)));

		System.out.println(Integer.toHexString(Mouse2.push((short) 0xF0FF, 8)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0xF1FF, 8)));
		System.out.println(Integer.toHexString(Mouse2.push((short) 0xF2FF, 8)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xF0FF, 8)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xF1FF, 8)));
		System.out.println(Integer.toHexString(Mouse2.free((short) 0xF2FF, 8)));
	}
}
