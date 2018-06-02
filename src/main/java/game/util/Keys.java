package game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Keys implements KeyListener {

	KeyEvent e;

	public Keys(JFrame jf) {
		jf.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:

			break;
		case KeyEvent.VK_RIGHT:

			break;
		case KeyEvent.VK_DOWN:

			break;
		case KeyEvent.VK_LEFT:

			break;
		case KeyEvent.VK_ESCAPE:

			break;
		case KeyEvent.VK_ENTER:

			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyReleased(KeyEvent e){}
}
