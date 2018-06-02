package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestStream {
	//インスタンス生成
	public static BufferedReader L00 = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] A00) {
		String str;
		while(!"exit".equals(str = read())){
			System.out.println(str);
		}
		System.out.println("終了");
	}
	public static String read() {
		try {
			System.out.println("文字列を入力してください：");
			//入力を代入
			return L00.readLine();
		} catch (IOException L02) {
			System.out.println("入力エラー");
		}
		return "";
	}
}