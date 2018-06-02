package game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public enum KeyEnum implements KeyListener, Constant {
	UP(KeyEvent.VK_UP),
	RIGHT(KeyEvent.VK_RIGHT),
	DOWN(KeyEvent.VK_DOWN),
	LEFT(KeyEvent.VK_LEFT),
	ESCAPE(KeyEvent.VK_ESCAPE),
	ENTER(KeyEvent.VK_ENTER),
	SPACE(KeyEvent.VK_SPACE),
	;

	/** RIGHT */
	public static final KeyEnum RI = RIGHT;
	/** DOWN */
	public static final KeyEnum DO = DOWN;
	/** LEFT */
	public static final KeyEnum LE = LEFT;
	/** ESCAPE */
	public static final KeyEnum ES = ESCAPE;
	/** ENTER */
	public static final KeyEnum EN = ENTER;
	/** SPACE */
	public static final KeyEnum SP = SPACE;

	private static KeyEvent e;

	private int keyCode;
	private int status = 0;
	KeyEnum(int keyCocd) {
		this.keyCode = keyCocd;
	}

	public boolean is(int status) {
		return this.status == status;
	}

	public void is(int status, Runnable runner) {
		if (this.status == status) runner.run();
	}
	public void is(int status, Runnable... runner) {
		if (this.status == status) for(Runnable r : runner) r.run();
	}

	public void keyPressed(KeyEvent e) {
		KeyEnum.e = e;
	}

	public static void execution() {
		for (KeyEnum k : values()) {
			if (e != null && k.keyCode == e.getKeyCode()) {
				if (k.status == FREE_KEY) {
					k.status = PUSH_KEY;
				} else {
					k.status = HOLD_KEY;
				}
			} else {
				if (k.status == PUSH_KEY || k.status == HOLD_KEY) {
					k.status = PULL_KEY;
				} else {
					k.status = FREE_KEY;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		KeyEnum.e = null;
	}
	public void keyTyped(KeyEvent e) {}
}
