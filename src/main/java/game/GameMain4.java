package game;

import javax.swing.JFrame;

import game.manager.StepManager;
import game.offscreen.OS;
import game.offscreen.OffScreen;
import game.offscreen.OffScreenImpl;
import game.step.Step;
import game.system.GameSystem;
import game.util.Constant;
import game.util.KeyEnum;
import game.util.Mouse;
import game.util.Util;

public class GameMain4 extends JFrame implements GameSystem, Constant {

	static boolean flag = true;

	static OffScreen[]os=new OffScreenImpl[1];

	public static void main(String[] args) throws InterruptedException {

		Step step = StepManager.getManager().getData("game.step.StepD");
		GameMain4 gm = new GameMain4();

		gm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gm.setVisible(true);
		os=Util.newArray(os, ()->new OffScreenImpl(gm.createImage(32*15, 32*15)));
		gm.setSize(os[OS.OS1.ordinal()].getW() + SX*2, os[OS.OS1.ordinal()].getH() + SY+SX);

		gm.addKeyListener(KeyEnum.UP);
		gm.addMouseListener(Mouse.BUTTON1);
		gm.addMouseMotionListener(Mouse.BUTTON1);

		//
		final long FPS = 1000L / 60L;
		long time = System.currentTimeMillis() + FPS;
		long t1 = time;
		int i = 0;

		while (step != null) {
			long l1 = System.currentTimeMillis() - time;
			long l2 = (System.currentTimeMillis() - t1) / ++i;
			KeyEnum.execution();
			Mouse.execution();
			KeyEnum.SP.is(PUSH_KEY, ()->flag = !flag);
			if (flag) {
				Step step1 = step.run((JFrame) os[OS.OS1.ordinal()]);
				if (step1 != null && step != step1) step1.repaint((JFrame) os[OS.OS1.ordinal()]);
				step = step1;
				for (OffScreen o : os) gm.getGraphics().drawImage(o.getI(), SX, SY, gm);
			}
//			gm.setTitle(String.format("%08d %02d %02d", i, l1, l2));
//			gm.setTitle(String.format("X:%04d Y:%04d B1:%02d B2:%02d B3:%02d", Mouse.getX(), Mouse.getY(), Mouse.B1.getStatus(), Mouse.B2.getStatus(), Mouse.B3.getStatus()));
			gm.setTitle(String.format("%08d %03d %02d X:%04d Y:%04d B1:%02d B2:%02d B3:%02d", i, l1, l2, Mouse.getX(), Mouse.getY(), Mouse.B1.getStatus(), Mouse.B2.getStatus(), Mouse.B3.getStatus()));
			long t = time - System.currentTimeMillis();
			Thread.sleep(t < 0 ? 0 : t > FPS ? FPS : t);
			time += FPS;
		}

		System.exit(0);
	}

	@Override
	public OffScreen[] getOffScreen() {
		return os;
	}

	@Override
	public OffScreen getOffScreen(OS o) {
		return os[o.ordinal()];
	}
}
