package game.offscreen;

import java.awt.Graphics;
import java.awt.Image;

public interface OffScreen {
	Graphics getGraphics();
	Graphics getG();
	Image getI();
	int getW();
	int getH();
}
