package game.step;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import game.manager.StepManager;
import game.util.Constant;
import game.util.Draw;

public class StepD implements Step, Constant {

	static String STR = "GAME START";
	static final int CNT = 60*1;
	static int cnt = CNT;

	@Override
	public Step run(JFrame j) {
		switch (cnt--) {
		case 0:
			return StepManager.getManager().getData("game.step.StepB");
		case CNT:
			repaint(j);
			break;
		default:
			break;
		}
		return this;
	}

	@Override
	public void repaint(JFrame j) {
		Graphics g = j.getGraphics();
		Draw.rect(0, 0, 32*15, 32*15, (Graphics2D)g, Color.BLACK, 1f);
		((Graphics2D)g).setPaint(Color.RED);
	    g.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 64));
	    g.drawString(STR, (480-STR.length()*32)/2, (480)/2);
	}
}
