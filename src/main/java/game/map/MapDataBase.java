package game.map;

import java.awt.Image;

import game.manager.ImageManager;
import game.util.Create;

public abstract class MapDataBase<T> implements MapData<T>, Create<T> {
	protected Image image;
	protected int iw, ih, frameNum;
	protected int w, h, x, y;
	protected String[][] data;
	public MapDataBase(String data) {
		String line[] = data.split(System.lineSeparator());
		if (line.length < 3) {
			throw new RuntimeException("マップデータ不正");
		}
		int i = 0;
		String filename = line[i++];
		String[] wh = line[i++].split(",");
		this.w = Integer.valueOf(wh[0]);
		this.h = Integer.valueOf(wh[1]);
		this.x = line[2].split(",").length;
		this.y = line.length - 2;
		this.data = new String[x][y];
		this.image = ImageManager.getManager().getData(filename);
		this.iw = image.getWidth(null) / this.w;
		this.ih = image.getHeight(null) / this.h;
		frameNum = iw * ih;
		if (this.iw < 1 || this.ih < 1)
			throw new RuntimeException("Image File Error" + filename);
		for (; i < line.length; i++) {
			int j = 0;
			for (String cell : line[i].split(",")) {
				this.data[j++][i-2] = cell;
			}
			if (x != j) {
				throw new RuntimeException("マップデータ不正("+i+"行目)");
			}
		}
	}

	@Override
	public Image getImage() {
		return this.image;
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
	public int getWidth() {
		return this.w;
	}

	@Override
	public int getHeight() {
		return this.h;
	}

	@Override
	public int getImageWidth() {
		return this.iw;
	}

	@Override
	public int getImageHeight() {
		return this.ih;
	}

	@Override
	public int getCell(int x, int y, int index) {
		return this.data[x][y].charAt(index);
	}
}
