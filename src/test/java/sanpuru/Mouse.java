package sanpuru;

//
//マウス操作のデモです。
//
//FrameやCanvasを使うのに必要なimport文
import java.awt.AWTEvent;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
//FrameやCanvasのイベント処理に必要
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mouse {
	public static void main(String args[]) {
		//アプリはここから実行されます。
		Frame frame = new Frame();//Frameを作成
		AppCanvas canvas = new AppCanvas();//Canvasの継承クラスのAppCanvasを作成
		frame.add(canvas);//canvasをフレームに追加
		frame.addWindowListener(new Adapter());//Windowのイベントの処理先を指定
		frame.setSize(240, 240);//サイズを指定
		frame.setVisible(true);//表示
	}
}

//
//Canvasは継承クラスAppCanvas
//★Canvasの継承クラス内中の
//public void paint(Graphics g)
//は描画の必要が生じると、自動で呼びだれます。
class AppCanvas extends Canvas {
	//プロパティ
	String methodName;//イベントを感知したメソッド
	int eventId;//発生したイベントの番号
	int x, y;//マウスの位置
	int count;//Mouse関係
	int button;//操作されたボタン
	long time;//操作された時間

	//コンストラクタ
	AppCanvas() {
		//プロパティの初期化
		methodName = "";
		eventId = 0;
		x = y = 0;
		count = 0;
		button = 0;
		time = System.currentTimeMillis();

		//処理するイベントの種類は
		//enableEvents(イベントマスクID１（｜イベントマスク名ID2)...);で設定
		//　　MOUSE_MOTION_EVEMTとMOUSE_EVENTを処理するイベントとして表示
		enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK);
	}

	//以下に描画したい内容を書く
	public void paint(Graphics g) {
		//タイトルの表示
		g.drawString("---マウスのイベント---", 0, 12);

		//マウスイベントの表示
		g.drawString("Method=" + methodName, 0, 24);
		g.drawString("Ev=" + getEvTypeName(eventId), 0, 36);
		g.drawString("Button=" + getButtonName(button), 0, 48);
		g.drawString("ClickCount=" + count, 0, 60);
		g.drawString("time=" + time, 0, 72);

		//マウスの位置にイベント名を表示
		g.drawString(getEvTypeName(eventId), x, y);

	}

	//マウスイベントＩＤから、イベント名称を取得
	// 戻り値　イベント名
	// 引数　　イベント名称
	String getEvTypeName(int id) {
		switch (id) {
		case MouseEvent.MOUSE_CLICKED:
			return "MOUSE_CLICKED";
		case MouseEvent.MOUSE_DRAGGED:
			return "MOUSE_DRAGGED";
		case MouseEvent.MOUSE_ENTERED:
			return "MOUSE_ENTERED";
		case MouseEvent.MOUSE_EXITED:
			return "MOUSE_EXITED";
		case MouseEvent.MOUSE_MOVED:
			return "MOUSE_MOVED";
		case MouseEvent.MOUSE_PRESSED:
			return "MOUSE_PRESSED";
		case MouseEvent.MOUSE_RELEASED:
			return "MOUSE_RELEASED";
		case MouseEvent.MOUSE_WHEEL:
			return "MOUSE_WHELL";
		}
		return "unDefine";
	}

	//ボタンのＩＤから、ボタンの名前を取得
	//戻り値　ボタンの名前
	//引数　　ボタンのＩＤ　
	String getButtonName(int bt) {
		switch (bt) {
		case MouseEvent.BUTTON1:
			return "Button1";
		case MouseEvent.BUTTON2:
			return "Button2";
		case MouseEvent.BUTTON3:
			return "Button3";
		}
		return "No_Button";
	}

	// マウスが操作されと、自動で実行されるメソッド
	//　　引数　e - 発生したイベントの情報
	//  ここでは、単にプロパティにイベントの情報をセット
	public void processMouseEvent(MouseEvent e) {
		methodName = "MouseEvent";//処理をしたメソッド（自メソッド）名前をセット
		x = e.getX();//マウスの横位置をセット
		y = e.getY();//マウスの位置をセット
		eventId = e.getID();//イベントのＩＤをセット
		button = e.getButton();//操作されたボタンをセット
		count = e.getClickCount();//ボタンが押された回数をセット
		time = e.getWhen();//イベント発生時刻をセット
		repaint();//再描画
	}

	// マウスが移動されと、自動で実行されるメソッド
	//　　引数　e - 発生したイベントの情報
	//  ここでは、単にプロパティにイベントの情報をセット
	public void processMouseMotionEvent(MouseEvent e) {
		methodName = "MouseMotionEvent";//処理をしたメソッド（自メソッド）名前をセット
		x = e.getX();//マウスの横位置をセット
		y = e.getY();//マウスの位置をセット
		eventId = e.getID();//イベントのＩＤをセット
		button = e.getButton();//操作されたボタンをセット
		count = e.getClickCount();//ボタンが押された回数をセット
		time = e.getWhen();//イベント発生時刻をセット
		repaint();//再描画
	}

}

//
//WindowAdapterは、WindowListerの機能を持ちますが、
//必要なメソッドだけを書けば、WindowListenerとして使えます
class Adapter extends WindowAdapter {
	//右上の「×」クリックされると呼び出される
	public void windowClosing(WindowEvent e) {
		System.exit(0);//アプリの終了
	}
}