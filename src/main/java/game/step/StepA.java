//package game.step;
//
//import java.awt.Graphics;
//
//public class StepA implements Step {
//
//	//
//	long time = System.currentTimeMillis();
//	long t1 = time;
//	//
//	int i = 0;
//
//	@Override
//	public Step run(Graphics g) {
//		long l1 = System.currentTimeMillis() - time;
//		long l2 = (System.currentTimeMillis() - t1) / ++i;
//		System.out.println(String.format("%04d %02d %02d", i, l1, l2));
//		time = System.currentTimeMillis();
//		return this;
//	}
//}
