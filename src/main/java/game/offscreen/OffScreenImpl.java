package game.offscreen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class OffScreenImpl extends JFrame implements OffScreen {
	private Graphics g; //オフスクリーングラフィックス
	private Image i; //オフスクリーンイメージ
	private int w, h;

	public OffScreenImpl() {}
	public OffScreenImpl(Image image) {
		this.w = image.getWidth(null);
		this.h = image.getHeight(null);
		i = image;
		g = i.getGraphics();
//		Draw.rect(0, 0, w, h, (Graphics2D) g, Color.BLACK, 1f);
//		g.setColor(Color.BLACK);
	}

	@Override
	public Graphics getGraphics() {
		return g;
	}

	public Graphics getG() {
		return g;
	}
	public Image getI() {
		return i;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
}
