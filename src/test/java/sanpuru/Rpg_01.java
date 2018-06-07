package sanpuru;

//★ 背景画像をバックに矢印キーでキャラクタを動かす    前田 稔
//タイムラグがあり、ちらつきます
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.Char;
import game.Map;
import game.util.Constant;
import game.util.Draw;
import game.util.KeyEnum;
import game.util.Mouse2;

public class Rpg_01 extends JFrame implements KeyListener, Constant {
	CARD card;
	Image bg;
	Point pos = new Point(100, 80); //キャラクタの座標
	int dir = 0, num = 0;
	// ---- test ------------------------------------------------
	// Keys k = new Keys(this);
	Mouse2 u = new Mouse2(this);
	char i = 0;
	Map m = new Map();
	Char c = new Char();
	// ---- test ------------------------------------------------

	// Main
	public static void main(String args[]) {
		new Rpg_01();
	}

	// Constructor
	Rpg_01() {
		super("Character");
		bg = getToolkit().getImage("../img/map.gif");
		card = new CARD("../img/Chr47.gif", 32, 32);
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.gray);
		// ---- test ------------------------------------------------
//		setSize(280+8*2, 210+30+8);
		setSize(m.getW(), m.getH());
		addKeyListener(KeyEnum.UP);
		// ---- test ------------------------------------------------
		setVisible(true);
	}

	// Paint
	public void paint(Graphics g) {
		// ---- test ------------------------------------------------
//		g.drawImage(bg, 8, 30, 280, 210, this);
		m.paint(g);
		c.print(g, pos.x, pos.y, dir, i++ % 4);

		Draw.rect(SX, SY, 32*15, 32*15, (Graphics2D)g, Color.BLACK, 1.5f);

//		c.print(g, m.getX(pos.x/m.getW()), m.getY(pos.y/m.getH())-16, dir, i++ % 4);
//	    Graphics2D g2 = (Graphics2D)g;

//	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//
//	    g2.setStroke(new BasicStroke(2.0f));
//
//	    g2.draw(new Rectangle2D.Double(30.0d, 50.0d, 150.0d, 90.0d));
//
//	    g2.setStroke(new BasicStroke(1.0f));
//
//	    g2.setPaint(Color.PINK);
//	    g2.fill(new Rectangle2D.Double(60.0d, 70.0d, 220.0d, 50.0d));

//	    Draw.hp(100, pos.x/10, 32+8, 32*5+30, 32*10, 12, (Graphics2D) g, Color.GREEN, Color.RED);
//	    ((Graphics2D) g).setPaint(Color.BLACK);
//	    g.drawString("Hello Java2D", 32+8, 32*7+25);
//	    Draw.txt(32+8, 32*6+25, (Graphics2D) g, Color.RED);

		// ---- test ------------------------------------------------
//		card.View(g, dir * 2 + num, pos);
	}

	// KeyEvent Listener
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			pos.y -= 10;
			dir = 0;
			break;
		case KeyEvent.VK_RIGHT:
			pos.x += 10;
			dir = 3; //1;
			break;
		case KeyEvent.VK_DOWN:
			pos.y += 10;
			dir = 1; //2;
			break;
		case KeyEvent.VK_LEFT:
			pos.x -= 10;
			dir = 2; //3;
			break;
		}
		num ^= 1;
		repaint();
	}

	public void keyReleased(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//			pos.y -= 10;
//			dir = 0;
//			break;
//		case KeyEvent.VK_RIGHT:
//			pos.x += 10;
//			dir = 3; //1;
//			break;
//		case KeyEvent.VK_DOWN:
//			pos.y += 10;
//			dir = 1; //2;
//			break;
//		case KeyEvent.VK_LEFT:
//			pos.x -= 10;
//			dir = 2; //3;
//			break;
//		}
//		num ^= 1;
//		repaint();
	}

	public void keyTyped(KeyEvent e) {
	}
}