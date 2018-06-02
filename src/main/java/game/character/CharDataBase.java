package game.character;

import java.awt.Image;

import game.manager.ImageManager;
import game.util.Util;

public class CharDataBase implements CharData {
	protected Image image;
	protected int x, y, w, h;
	protected int frameNum;
	protected int[][] data;

	public CharDataBase(String data) {
		if (data == null) throw new RuntimeException("キャラクタデータ不正");
		String line[] = data.split(System.lineSeparator());
		if (line.length < 3) throw new RuntimeException("マップデータ不正");
		int i = 0;
		String filename = line[i++];
		String[] wh = line[i++].split(",");
		this.image = ImageManager.getManager().getData(filename);
		this.w = Integer.valueOf(wh[0]);
		this.h = Integer.valueOf(wh[1]);
		this.x = image.getWidth(null) / this.w;
		this.y = image.getHeight(null) / this.h;
		this.frameNum = x * y;
		this.data = new int[x][y];
		this.data[0] = Util.splitToArray(line[i++].replace(" ", ""), ",");
		this.data[1] = Util.splitToArray(line[i++].replace(" ", ""), ",");
		this.data[2] = Util.splitToArray(line[i++].replace(" ", ""), ",");
		this.data[3] = Util.splitToArray(line[i++].replace(" ", ""), ",");
		if (this.x < 1 || this.y < 1)
			throw new RuntimeException("Image File Error" + filename);
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getW() {
		return this.w;
	}

	@Override
	public int getH() {
		return this.h;
	}

	@Override
	public int[][] getData() {
		return data;
	}

	@Override
	public int[] getUpPose() {
		return this.data[0];
	}

	@Override
	public int[] getDownPose() {
		return this.data[1];
	}

	@Override
	public int[] getLeftPose() {
		return this.data[2];
	}

	@Override
	public int[] getRightPose() {
		return this.data[3];
	}
}
