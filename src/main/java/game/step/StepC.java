package game.step;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import game.util.Constant;
import game.util.Draw;

public class StepC implements Step, Constant {

	static String STR = "GAME OVER";
	static final int CNT = 60*3;
	static int cnt = CNT;

	@Override
	public Step run(JFrame j) {
		switch (cnt--) {
		case 0:
			return null;
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
		Graphics2D g = (Graphics2D)j.getGraphics();
		Draw.rect(0, 0, 32*15, 32*15, (Graphics2D)g, Color.BLACK, 0.4f);
		g.setPaint(Color.RED);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	    g.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 64));
	    g.drawString(STR, (480-STR.length()*32)/2, (480)/2);
	}
}
