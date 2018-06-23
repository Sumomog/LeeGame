package game.step;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

import game.Char;
import game.Char02;
import game.Map2;
import game.character.CharData;
import game.manager.StepManager;
import game.util.Constant;
import game.util.KeyEnum;
import game.util.Mouse;
import game.util.Util;

public class StepE implements Step, Constant {

	Map2 m = new Map2();
	Char c = new Char();
	Char02 c02 = new Char02();

	int x=32*1, y=32*0, d=CharData.D;
	int cx=x, cy=y;
	int mx=x, my=y;
	boolean mf=false;

	@Override
	public Step run(JFrame j) {

		Mouse.B1.is(HOLD_KEY, ()->cx=Mouse.getX(32), ()->cy=Mouse.getY(32), ()->getMD());

		if (cx==x&&cy==y) {
			KeyEnum.UP.is(HOLD_KEY, ()->mf=false, ()->d=CharData.U, ()->cy-=32);
			KeyEnum.DO.is(HOLD_KEY, ()->mf=false, ()->d=CharData.D, ()->cy+=32);
			KeyEnum.LE.is(HOLD_KEY, ()->mf=false, ()->d=CharData.L, ()->cx-=32);
			KeyEnum.RI.is(HOLD_KEY, ()->mf=false, ()->d=CharData.R, ()->cx+=32);

		} else {
			Util.runFunc(y>cy, ()->y--);
			Util.runFunc(y<cy, ()->y++);
			Util.runFunc(x>cx, ()->x--);
			Util.runFunc(x<cx, ()->x++);

//			if ((x+y)%32 != 0) repaint(j);
			repaint(j);
			if(mf)((Graphics2D)j.getGraphics()).draw(new Rectangle2D.Double(mx, my, 32, 32));
		}

		Util.runFunc(mx!=Mouse.getX(32)||my!=Mouse.getY(32),()->{
			mx=Mouse.getX(32); my=Mouse.getY(32); mf=true;
			repaint(j);
			//Draw.rect(mx, my, 32, 32, (Graphics2D) j.getGraphics(), Color.BLACK, 0.75f);
			((Graphics2D)j.getGraphics()).draw(new Rectangle2D.Double(mx, my, 32, 32));
		});

		return !KeyEnum.ES.is(PULL_KEY) ? this : StepManager.getManager().getData("game.step.StepC");
	}

	@Override
	public void repaint(JFrame j) {
		m.paint(j.getGraphics());
		c.print(j.getGraphics(), x, y-16, d, (Util.abs(x,cx)>=Util.abs(y,cy)?x+8:y+8)%32/8);
		c02.print(j.getGraphics(), 32*5, 32*5-16, CharData.D, 1);
	}

	public void getMD() {
		d=Util.abs(x,cx)>=Util.abs(y,cy) ? x>cx?CharData.L:CharData.R : y>cy?CharData.U:CharData.D;
	}
}
