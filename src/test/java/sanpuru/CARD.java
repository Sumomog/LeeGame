package sanpuru;

//★ CARD Object Class(JFrame を継承)    前田 稔
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class CARD extends JFrame {
	private Image Img;
	private Dimension size; // Sprite Size
	private Dimension num; // Sprite 並び数
	int frameNum;

	// Constructor
	public CARD(String filename, int ws, int hs) {
		Init(filename, ws, hs);
	}

	public CARD(String filename, Dimension siz) {
		Init(filename, siz.width, siz.height);
	}

	// Initialize
	void Init(String filename, int ws, int hs) {
		size = new Dimension(ws, hs);
		num = new Dimension(1, 1);
		//画像ロード
		File infile = new File(filename);
		Img = loadImage(infile);
		num.width = Img.getWidth(null) / size.width;
		num.height = Img.getHeight(null) / size.height;
		frameNum = num.width * num.height;
		if (num.width < 1 || num.height < 1)
			System.out.println("Image File Error" + filename);
	}

	// Sprite View
	public void View(Graphics g, int n, int dx, int dy) {
		int sx, sy;
		if (n >= frameNum) {
			System.out.println("Sprite Number Error" + n);
			return;
		}
		sx = (n % num.width) * size.width;
		sy = (n / num.width) * size.height;
		if (Img != null) {
			g.drawImage(Img, dx, dy, dx + size.width, dy + size.height, sx, sy, sx + size.width, sy + size.height,
					this);
		}
	}

	public void View(Graphics g, int n, Point pnt) {
		View(g, n, pnt.x, pnt.y);
	}

	// Load Image
	public static Image loadImage(File f) {
		try {
			Image img = ImageIO.read(f);
			return img;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
