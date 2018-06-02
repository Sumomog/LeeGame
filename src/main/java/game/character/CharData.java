package game.character;

import java.awt.Image;

public interface CharData {

	int U = 0;
	int D = 1;
	int L = 2;
	int R = 3;

	int X = 0;
	int Y = 1;
	int W = 2;
	int H = 3;

	Image getImage();

	int getX();
	int getY();
	int getW();
	int getH();

	int[][] getData();

	default int[] getUpPose() {
		return getData()[U];
	}
	default int[] getDownPose() {
		return getData()[D];
	}
	default int[] getLeftPose() {
		return getData()[L];
	}
	default int[] getRightPose() {
		return getData()[R];
	}

	default Image getI() {
		return getImage();
	}

	default int[] getUP() {
		return getData()[U];
	}
	default int[] getDP() {
		return getData()[D];
	}
	default int[] getLP() {
		return getData()[L];
	}
	default int[] getRP() {
		return getData()[R];
	}

	default int[] getUPR(int no) {
		return getRectangle(getUpPose()[no]);
	}
	default int[] getDPR(int no) {
		return getRectangle(getDownPose()[no]);
	}
	default int[] getLPR(int no) {
		return getRectangle(getLeftPose()[no]);
	}
	default int[] getRPR(int no) {
		return getRectangle(getRightPose()[no]);
	}

	default int[] getRectangle(int no) {
		int x = no % getX() * getW();
		int y = no / getY() * getH();
		int w = x + getW();
		int h = y + getH();
		return new int[]{x,y,w,h};
	}
	default int[] getRectangle(int pose, int no) {
		return getRectangle(getData()[pose][no]);
	}
}
