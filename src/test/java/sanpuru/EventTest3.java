package sanpuru;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/*
<applet code="EventTest3.class" codebase="class" width="300" height="300">
</applet>
*/

public class EventTest3 extends Applet implements MouseMotionListener {
	int x, y;
	int type;
	int width;

	public void init() {
		x = 0;
		y = 0;
		type = 0;
		width = 20;

		addMouseMotionListener(this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if (type == 1) {
			g.setColor(Color.red);
			g.drawOval(x - width, y - width, width * 2, width * 2);
		} else if (type == 2) {
			g.setColor(Color.blue);
			g.drawRect(x - width, y - width, width * 2, width * 2);
		}
	}

	public void mouseDragged(MouseEvent e) {
		Point point = e.getPoint();
		x = point.x;
		y = point.y;
		type = 2;

		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		Point point = e.getPoint();
		x = point.x;
		y = point.y;
		type = 1;

		repaint();
	}
}