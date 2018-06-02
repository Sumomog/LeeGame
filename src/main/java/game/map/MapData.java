package game.map;

import java.awt.Image;

public interface MapData<T> {
	Image getImage();
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	int getImageWidth();
	int getImageHeight();
	default Image getI() {
		return getImage();
	}
	default int getW() {
		return getWidth();
	}
	default int getH() {
		return getHeight();
	}
	default int getIW() {
		return getImageWidth();
	}
	default int getIH() {
		return getImageHeight();
	}
	T getCell(int x, int y);
	int getCell(int x, int y, int index);
}
