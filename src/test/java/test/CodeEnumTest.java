package test;


import org.junit.Test;

import sanpuru.CodeEnum;
import sanpuru.F1Constructor;

public class CodeEnumTest {

	@Test
	void test01() {
		System.out.println("---- equalsByCodeメソッド --------------");
		System.out.println(F1Constructor.FERRARI.equalsByCode("01"));
		System.out.println("---- getOrderedListメソッド ------------");
		CodeEnum.getOrderedList(F1Constructor.class)
				.forEach(e -> System.out.println("code:" + e.getCode() + ",name:" + e.getName()));
		System.out.println("---- getMapメソッド --------------------");
		CodeEnum.getMap(F1Constructor.class)
				.entrySet()
				.forEach(e -> System.out.println("code:" + e.getKey() + ",Enum:" + e.getValue()));
		System.out.println("---- hasCodeメソッド -------------------");
		System.out.println(CodeEnum.hasCode(F1Constructor.class, "10"));
	}
}
