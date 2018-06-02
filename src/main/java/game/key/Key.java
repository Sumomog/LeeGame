package game.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface Key extends KeyListener {
	int KEY_UP = 0;
	int KEY_DO = 1;
	boolean get(KeyEvent e, int ud);
	default void keyTyped(KeyEvent e){}
}
