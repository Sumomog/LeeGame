package game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public enum Mouse implements MouseListener, MouseMotionListener, Constant {
	BUTTON1(MouseEvent.BUTTON1),
	BUTTON2(MouseEvent.BUTTON2),
	BUTTON3(MouseEvent.BUTTON3),
	;
	/** BUTTON1 */
	public static final Mouse B1 = BUTTON1;
	/** BUTTON2 */
	public static final Mouse B2 = BUTTON2;
	/** BUTTON3 */
	public static final Mouse B3 = BUTTON3;

	private static int x, y;
	private static MouseEvent e;

	private int keyCode;
	private int status = 0;

	Mouse(int keyCocd) {
		this.keyCode = keyCocd;
	}

	public int getStatus() {
		return this.status;
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

	public static void execution() {
		for (Mouse k : values()) {
			if (e != null && k.keyCode == e.getButton()) {
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

	public static int getX() { return x; }
	public static int getY() { return y; }

	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e){ Mouse.e = e; }
	@Override
	public void mouseReleased(MouseEvent e){ Mouse.e = null; }
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mouseDragged(MouseEvent e){
		x = e.getX() - SX;
		y = e.getY() - SY;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX() - SX;
		y = e.getY() - SY;
	}
}
