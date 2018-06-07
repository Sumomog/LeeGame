package sanpuru;

//Written by JavaMaker
//AnimTest.java
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class AnimTest extends Applet
		implements
		Runnable {
	//TODO: この位置に変数の宣言をしてください
	Graphics OSG; //オフスクリーングラフィックス
	Image OSI; //オフスクリーンイメージ
	Thread th = null; //スレッドオブジェクト宣言
	int delay = 100; //スレッドの待ち時間

	public void init() {
		//TODO: ここに初期化の処理を書いてください

		OSI = createImage(getSize().width, getSize().height);
		OSG = OSI.getGraphics();
		OSG.setColor(Color.black);

	}

	public void paint(Graphics g) {
		//TODO: ここに描画用のコードを書いてください
		OSG.clearRect(0, 0, getSize().width, getSize().height); //オフスクリーンの初期化
		//TODO: この下にプログラムを書いてください
		//例:OSG.drawString("描画例",10,10);

		//ここまでにプログラムを書いてください
		g.drawImage(OSI, 0, 0, this);

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void start() {
		//TODO: アプレットが開始する時の処理を書いてください
		th = new Thread(this);
		th.start();
	}

	public void stop() {
		//TODO: アプレットが停止する時の処理を書いてください
		th = null;
	}

	public void run() {
		//TODO: スレッドに処理させるプログラムを書いてください
		Thread me = Thread.currentThread();
		while (th == me) {
			try {
				Thread.currentThread().sleep(delay);
			} catch (InterruptedException e) {
			}
			//アプレットの場合はrepaint()をすることが多いです

			repaint();
		}
	}
}