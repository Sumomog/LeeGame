package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.manager.StepManager;
import game.step.Step;

public class GameMain extends JFrame implements KeyListener {
	static boolean flag = true;

	GameMain(){
		this.addKeyListener(this);
	}

	public static void main(String[] args) throws InterruptedException {

		//
		final long FPS = 1000L / 60L;

		//
		long time = System.currentTimeMillis() + FPS;

		//
		// Step step = new StepA();
		Step step = StepManager.getManager().getData("game.step.StepB");
		while (step != null) {
			if (flag) {
//				step = step.run();
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
