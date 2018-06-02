package game.key;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import game.util.Util;

public class KeyB extends HashMap<KeyEvent,Boolean[]> implements Key {

	@Override
	public void keyPressed(KeyEvent e) {
		Util.updateMapForEach(this, (k,v)->new Boolean[]{false, false});
		if (containsKey(e)) {
			Boolean[] v = super.get(e);
			v[0] = true;
			put(e, v);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (containsKey(e)) {
			Boolean[] v = super.get(e);
			v[1] = true;
			put(e, v);
		}
	}

	@Override
	public boolean get(KeyEvent e, int ud) {
		if (!containsKey(e)) {
			put(e, new Boolean[]{false, false});
			return false;
		}
		return super.get(e)[ud];
	}
}
