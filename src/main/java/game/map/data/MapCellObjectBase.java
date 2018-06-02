package game.map.data;

import game.util.Util;

public abstract class MapCellObjectBase<T extends MapCellObjectBase<T>> implements MapCell {
	private int[] data;

	@Override
	public void setData(String data) {
		this.data = Util.splitToArray(data, " ");
	}

	@Override
	public int get(int index) {
		return data[index];
	}
}
