package game.step;

import javax.swing.JFrame;

public interface Step {
	Step run(JFrame j);
	void repaint(JFrame j);
}
