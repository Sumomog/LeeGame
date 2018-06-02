package sanpuru;

//★ CARD Class で切り分ける    前田 稔
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Card_01 extends JFrame {
	CARD card;

	// Main
	public static void main(String args[]) {
		new Card_01();
	}

	// Constructor
	public Card_01() {
		super("Image View");
		card = new CARD("E:\\pleiades\\workspace\\img\\hanafuda.gif", 51, 75);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 460);
		setVisible(true);
	}

	// Paint Method
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 10; j++)
				card.view(g, i * 10 + j, j * 56 + 20, i * 80 + 46);
	}

	//★ CARD Object Class
	static class CARD extends JFrame {
		private Image Img;
		private int Height, Width, Hnum, Wnum;
		int frameNum;

		// Constructor
		public CARD(String filename, int ws, int hs) {
			Width = ws;
			Height = hs;
			Wnum = Hnum = 1;
			//画像ロード
			File infile = new File(filename);
			Img = loadImage(infile);
			Wnum = Img.getWidth(null) / Width;
			Hnum = Img.getHeight(null) / Height;
			frameNum = Wnum * Hnum;
			if (Wnum < 1 || Hnum < 1)
				System.out.println("Image File Error" + filename);
		}

		// Sprite View
		public void view(Graphics g, int n, int dx, int dy) {
			int sx, sy;
			if (n >= frameNum) {
				System.out.println("Sprite Number Error" + n);
				return;
			}
			sx = (n % Wnum) * Width;
			sy = (n / Wnum) * Height;
			if (Img != null) {
				g.drawImage(Img, dx, dy, dx + Width, dy + Height, sx, sy, sx + Width, sy + Height, this);
			}
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

}
