package game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Mouse2 implements MouseListener, MouseMotionListener, Constant {

	public int x, y;
	short b;

	public Mouse2(JFrame jf) {
		jf.addMouseListener(this);
		jf.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e){
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			b = push(b, 0);
			b = free(b, 4);
			b = free(b, 8);
			break;
		case MouseEvent.BUTTON2:
			b = free(b, 0);
			b = push(b, 4);
			b = free(b, 8);
			break;
		case MouseEvent.BUTTON3:
			b = free(b, 0);
			b = free(b, 4);
			b = push(b, 8);
			break;
		default:
			b = free(b, 0);
			b = free(b, 4);
			b = free(b, 8);
			break;
		}
	}
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX() - SX;
		y = e.getY() - SY;
	}

	public static short push(short b, int s) {
		if ((b & (0x000F << s)) == FREE_KEY << s) {
			b &= ~(0x000F << s);
			return b |= PUSH_KEY << s;
		} else {
			b &= ~(0x000F << s);
			return b |= HOLD_KEY << s;
		}
	}

	public static short free(short b, int s) {
		if ((b & (0x000F << s)) == PUSH_KEY << s || (b & (0x000F << s)) == HOLD_KEY << s) {
			b &= ~(0x000F << s);
			return b |= PULL_KEY << s;
		} else {
			b &= ~(0x000F << s);
			return b |= FREE_KEY << s;
		}
	}
}
