package game.system;

import game.offscreen.OS;
import game.offscreen.OffScreen;

public interface GameSystem {
	/** 描画(0:地面、1:空中、3:システム) */
	OffScreen[] getOffScreen();
	/** 描画(0:地面、1:空中、3:システム) */
	OffScreen getOffScreen(OS o);
}
