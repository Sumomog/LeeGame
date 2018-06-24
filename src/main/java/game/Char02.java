package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import game.character.CharData;
import game.manager.CharManager;
import game.util.Constant;

public class Char02 extends JFrame implements Constant {
	/** マップデータ */
	private CharData charData = CharManager.getManager().getData("../char/02.char");

	public void print(Graphics g, int x, int y, int p, int n) {
		Image img = charData.getImage();
		int dx = x; // * charData.getW();
		int dy = y; // * charData.getH();
		int dw = dx + charData.getW();
		int dh = dy + charData.getH();
		int sx = charData.getRectangle(p, n)[CharData.X];
		int sy = charData.getRectangle(p, n)[CharData.Y];
		int sw = charData.getRectangle(p, n)[CharData.W];
		int sh = charData.getRectangle(p, n)[CharData.H];

		g.drawImage(img, dx, dy, dw, dh, sx, sy, sw, sh, this);
	}
//	@Override
//	public void print(Graphics g) {
//		super.print(g);
//	}
}
