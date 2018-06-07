package game.util;

public interface Constant {

	/** 描画開始位置X */
	int SX = 8;
	/** 描画開始位置Y */
	int SY = 30;

	int FREE_KEY = 0;
	int PUSH_KEY = 1;
	int HOLD_KEY = 2;
	int PULL_KEY = 3;

	String CR = new String(new char[] {0x0D});
	String LF = new String(new char[] {0x0A});
	String CRLF = new String(CR+LF);
}
