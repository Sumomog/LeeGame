package sanpuru;

//★ CARD をクリックする    前田 稔
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Card_03 extends JFrame {
	CARD card;
	// CARD のシャッフル,  0x100:裏,  0x200:非表示
	int T[] = new int[48];

	// Main
	public static void main(String args[]) {
		new Card_03();
	}

	// Constructor
	public Card_03() {
		super("Image View");
		card = new CARD("E:\\pleiades\\workspace\\img\\hanafuda.gif", 51, 75);
		card.Shuffle(T, 48);
		addMouseListener(new inMouseListener());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720, 400);
		setBackground(Color.gray);
		setVisible(true);
	}

	// Paint Method
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, size.width, size.height);
		for (int i = 0; i < 48; i++) {
			if (T[i] < 0x100)
				card.view(g, T[i], (i % 12) * 56 + 20, (i / 12) * 80 + 46);
			else if (T[i] < 0x200)
				card.view(g, 48, (i % 12) * 56 + 20, (i / 12) * 80 + 46);
		}
	}

	// InnerClass MouseListener
	class inMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int x, y;
			x = (e.getX() - 20) / 56;
			y = (e.getY() - 46) / 80;
			if (T[y * 12 + x] < 0x100)
				T[y * 12 + x] |= 0x200; //非表示
			else if (T[y * 12 + x] < 0x200)
				T[y * 12 + x] &= 0xff; //表
			repaint();
		}
	}

	static class CARD extends JFrame {
		private Image Img;
		private int Height, Width, Hnum, Wnum;
		int frameNum;
		Random rand;

		// Constructor
		CARD(String filename, int ws, int hs) {
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
			rand = new Random(); //乱数の初期化
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

		// n 枚のカードをシャッフルする
		public void Shuffle(int t[], int n) {
			int i, j;
			for (i = 0; i < n; i++)
				t[i] = -1;
			for (i = 0; i < n; i++) {
				for (j = rand.nextInt(n); t[j] != -1; j = (j + 1) % n)
					;
				t[j] = i | 0x100;
			}
		}
	}
}
