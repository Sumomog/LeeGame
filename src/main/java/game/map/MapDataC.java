package game.map;

import game.map.data.MapCellObjectBase;

public class MapDataC<T extends MapCellObjectBase<T>> extends MapDataBase<T> {
	public MapDataC(String data) {
		super(data);
	}
	public T getCell(int x, int y, Class<T> type) {
		try {
			T cell = type.newInstance();
			cell.setData(data[x][y]);
			return cell;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("第３引数(type)不正", e);
		}
	}
	@Override
	public T getCell(int x, int y) {
		T cell = instance();
		cell.setData(data[x][y]);
		return cell;
	}
	@Override
	public int getCell(int x, int y, int index) {
		MapCellObjectBase<T> mcob = new MapCellObjectBase<T>(){};
		mcob.setData(data[x][y]);
		return mcob.get(index);
	}
}
