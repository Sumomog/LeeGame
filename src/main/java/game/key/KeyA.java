package game.key;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;

public class KeyA implements Key {

	Object[][] key = {};

	@Override
	public void keyPressed(KeyEvent e) {
		for (Object[] k : key) {
			if (e == k[0]) {
				k[KEY_DO+1] = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Object[] k : key) {
			if (e == k[0]) {
				k[KEY_UP+1] = true;
			}
		}
	}

	@Override
	public boolean get(KeyEvent e, int ud) {
		for (Object[] k : key) {
			if (e == k[0]) {
				return (boolean) k[ud+1];
			}
		}
		Object[][] k = (Object[][]) Array.newInstance(Object.class, key.length, 3);
		//Arrays.
		key = k;
		return false;
	}
}
