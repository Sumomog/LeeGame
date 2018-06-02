//package game;
//
//import java.awt.Color;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JFrame;
//
//import game.manager.StepManager;
//import game.offscreen.OffScreenImpl;
//import game.step.Step;
//import game.util.KeyEnum;
//
//public class GameMain3 extends JFrame implements KeyListener {
//
//	/** 描画開始位置X */
//	static int SX = 8;
//	/** 描画開始位置Y */
//	static int SY = 30;
//
//	static boolean flag = true;
//	static Step step = StepManager.getManager().getData("game.step.StepB");
//	static OffScreenImpl os = new OffScreenImpl(32*15, 32*15);
//	static GameMain3 gm = new GameMain3();
//
//	GameMain3(){
//		this.addKeyListener(this);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setBackground(Color.gray);
//		this.setSize(os.getW() + SX*2, os.getH() + SY+SX);
//		this.addKeyListener(KeyEnum.UP);
//		this.setVisible(true);
//	}
//
//	public static void main(String[] args) throws InterruptedException {
//
//		//
//		final long FPS = 1000L / 60L;
//
//		//
//		long time = System.currentTimeMillis() + FPS;
//
//		while (step != null) {
//			if (flag) {
//				step = step.run(os);
//				gm.getGraphics().drawImage(os.getI(), SX, SY, gm);
////				gm.getGraphics().drawImage(os.i, SX, SY, os.w, os.h, gm);
//			}
//			long t = time - System.currentTimeMillis();
//			Thread.sleep(t < 0 ? 0 : t > FPS ? FPS : t);
//			time += FPS;
//		}
//		System.exit(0);
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {}
//
//	@Override
//	public void keyPressed(KeyEvent e) {}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_ENTER:
//			flag = !flag;
//			break;
//		case KeyEvent.VK_SPACE:
//
//			break;
//		case KeyEvent.VK_ESCAPE:
//
//			break;
//		}
//	}
//}
