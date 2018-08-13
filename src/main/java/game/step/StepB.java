package game.step;

import javax.swing.JFrame;

import game.Char;
import game.Map2;
import game.character.CharData;
import game.manager.StepManager;
import game.util.Constant;
import game.util.KeyEnum;
import game.util.Util;

public class StepB implements Step, Constant {

	Map2 m = new Map2();
	Char c = new Char();

	int x=32*0, y=32*0, d=0, r=0;
	boolean uf=false, df=false, lf=false, rf=false;

	@Override
	public Step run(JFrame j) {

		KeyEnum.UP.is(HOLD_KEY, ()->uf=true, ()->d=CharData.U);
		KeyEnum.DO.is(HOLD_KEY, ()->df=true, ()->d=CharData.D);
		KeyEnum.LE.is(HOLD_KEY, ()->lf=true, ()->d=CharData.L);
		KeyEnum.RI.is(HOLD_KEY, ()->rf=true, ()->d=CharData.R);

		Util.runFunc(uf, ()->y--, ()->r++);
		Util.runFunc(df, ()->y++, ()->r++);
		Util.runFunc(lf, ()->x--, ()->r++);
		Util.runFunc(rf, ()->x++, ()->r++);

		Util.runFunc(x%32==0, ()->lf=false, ()->rf=false);
		Util.runFunc(y%32==0, ()->uf=false, ()->df=false);

		if (r%32 != 0) repaint(j);

		return !KeyEnum.ES.is(PULL_KEY) ? this : StepManager.getManager().getData("game.step.StepC");
	}

	@Override
	public void repaint(JFrame j) {
		m.paint(j.getGraphics());
		c.print(j.getGraphics(), x, y-16, d, r%32/8);
	}
}
