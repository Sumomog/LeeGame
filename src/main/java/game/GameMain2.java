package game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.manager.StepManager;
import game.step.Step;
import game.util.KeyEnum;

public class GameMain2 extends JFrame implements KeyListener {
	static boolean flag = true;
	static Step step = StepManager.getManager().getData("game.step.StepB");
	static GameMain2 gm = new GameMain2();

	GameMain2(){
		this.addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.gray);
		setSize(496, 518);
		addKeyListener(KeyEnum.UP);
		setVisible(true);
	}

	public static void main(String[] args) throws InterruptedException {

		//
		final long FPS = 1000L / 60L;

		//
		long time = System.currentTimeMillis() + FPS;

		while (step != null) {
			if (flag && step != null) {
				step = step.run(gm);
			}
			long t = time - System.currentTimeMillis();
			Thread.sleep(t < 0 ? 0 : t > FPS ? FPS : t);
			time += FPS;
		}
		System.exit(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			flag = !flag;
			break;
		case KeyEvent.VK_SPACE:

			break;
		case KeyEvent.VK_ESCAPE:

			break;
		}
	}
}
