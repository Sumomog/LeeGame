package sanpuru;

//★ キーの操作を検出する    前田 稔
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyCheck extends Frame implements KeyListener {
	// Main
	public static void main(String[] args) {
		new KeyCheck();
	}

	// Constructor
	KeyCheck() {
		super("Key Check");
		TextField tf1 = new TextField();
		tf1.addKeyListener(this);
		add(tf1);
		setSize(200, 100);
		show();
	}

	// KeyEvent Listener
	public void keyPressed(KeyEvent e) {
		System.out.println("Press: " + e.getKeyText(e.getKeyCode()));
	}

	public void keyReleased(KeyEvent e) {
		System.out.println("Release: " + e.getKeyText(e.getKeyCode()));
	}

	public void keyTyped(KeyEvent e) {
		System.out.println("Type: " + e.getKeyChar());
	}
}
