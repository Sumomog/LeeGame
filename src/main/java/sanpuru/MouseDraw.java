package sanpuru;

// AWTを使うため，
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
// イベント駆動関係のクラスを用いるため
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// 線分のクラスを定義する．
class Line {
	// 始点，終点のX座標，Y座標を int で保持する．
	public int start_x, start_y, end_x, end_y;

	// Lineのコンストラクタ
	public Line(int x1, int x2, int x3, int x4) {
		start_x = x1;
		start_y = x2;
		end_x = x3;
		end_y = x4;
	}
}

// インタフェースの実装では 「implements インタフェース名」と書く
// 複数書く場合は，カンマで区切る
class MouseDraw extends Frame implements KeyListener, MouseListener, MouseMotionListener {
	// Lineの配列を保持する変数 linesの宣言
	public Line[] lines;
	// linesのどこまで使われているかを示す変数 lineCountの宣言
	int lineCount;
	// マウスをドラッグ中かどうかを示す boolean型(真偽値)の変数draggingの宣言
	boolean dragging;
	// 表示する色を保持する変数
	Color lineColor;

	// コンストラクタの宣言
	public MouseDraw(String title) {
		// 親クラス Frameのコンストラクタの宣言
		super(title);
		// 大きさ10のLineの配列を作成してlinesに代入
		lines = new Line[10];
		// linesは何も使われていない
		lineCount = 0;
		// ドラッグ中ではない
		dragging = false;
		// 線の色は黒に
		lineColor = Color.black;
		// GUI部品と，Event Listenerを関連づける
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public static void main(String args[]) {
		// MouseDrawのインスタンスを作成 frameに代入
		MouseDraw frame = new MouseDraw("MouseDraw");
		// サイズを 600x400に設定
		frame.setSize(600, 400);
		// 表示する．
		frame.setVisible(true);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		int i;
		// 白で(0,0)-(600,400)を塗り潰す
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 400);
		// 色を設定
		g.setColor(lineColor);
		// lineCount個，linesに登録されているLineを描画する
		for (i = 0; i < lineCount; i++) {
			g.drawLine(lines[i].start_x, lines[i].start_y,
					lines[i].end_x, lines[i].end_y);
		}
		// マウスをドラッグ中の時は
		if (dragging) {
			// 赤い色で
			g.setColor(Color.red);
			// lines[lineCount] を描画する．
			g.drawLine(lines[i].start_x, lines[i].start_y,
					lines[i].end_x, lines[i].end_y);
		}
	}

	// KeyListenerを実装するためのメソッド
	public void keyPressed(KeyEvent e) {
		// イベントからキーのコードを取り出す
		int key = e.getKeyChar();
		// デバッグ用の表示
		System.out.println("keyPressed(" + e + "," + key + ")");
		// 入力が 'q'の時は終了する
		if (key == 'q')
			System.exit(0);
	}

	// 要らないイベントに対応するメソッドも中身は空で書いておく必要がある．
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	// MouseListenerを実装するためのメソッド
	public void mousePressed(MouseEvent e) {
		// 押された時のマウスカーソルの位置を得る
		int mx = e.getX(), my = e.getY();
		// デバッグ用の表示
		System.out.println("mousePressed(" + e + "," + mx + "," + my + ")");
		// 配列linesのlineCount番目に線分を登録
		lines[lineCount] = new Line(mx, my, mx, my);
		// ドラッグ中であることを示す
		dragging = true;
		// 再表示をおこなう
		repaint(mx, my, 1, 1);
	}

	// マウスのボタンが離された時のイベント
	public void mouseReleased(MouseEvent e) {
		// マウスカーソルの位置を得る
		int mx = e.getX(), my = e.getY();
		// デバッグ用の表示
		System.out.println("mouseUp(" + e + "," + mx + "," + my + ")");
		// 配列linesのlineCount番目の始点を変更
		int oldx = lines[lineCount].end_x;
		int oldy = lines[lineCount].end_y;
		lines[lineCount].end_x = mx;
		lines[lineCount].end_y = my;
		// lineCountを増やす
		dragging = false;
		// 再表示をおこなう
		int minx = Math.min(oldx, Math.min(mx, lines[lineCount].start_x));
		int maxx = Math.max(oldx, Math.max(mx, lines[lineCount].start_x));
		int miny = Math.min(oldy, Math.min(my, lines[lineCount].start_y));
		int maxy = Math.max(oldy, Math.max(my, lines[lineCount].start_y));
		lineCount++;
		repaint(minx, miny, maxx - minx + 1, maxy - miny + 1);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	// MouseMotionListenerを実装するためのメソッド
	public void mouseDragged(MouseEvent e) {
		// マウスカーソルの位置を得る
		int mx = e.getX(), my = e.getY();
		// デバッグ用の表示
		System.out.println("mouseDrag(" + e + "," + mx + "," + my + ")");
		// 配列linesのlineCount番目の始点を変更
		int oldx = lines[lineCount].end_x;
		int oldy = lines[lineCount].end_y;
		lines[lineCount].end_x = mx;
		lines[lineCount].end_y = my;
		// 再表示をおこなう
		int minx = Math.min(oldx, Math.min(mx, lines[lineCount].start_x));
		int maxx = Math.max(oldx, Math.max(mx, lines[lineCount].start_x));
		int miny = Math.min(oldy, Math.min(my, lines[lineCount].start_y));
		int maxy = Math.max(oldy, Math.max(my, lines[lineCount].start_y));
		repaint(minx, miny, maxx - minx + 1, maxy - miny + 1);
	}

	public void mouseMoved(MouseEvent e) {
	}
}