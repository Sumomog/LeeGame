package game.map;

import game.map.data.MapCellCalssBase;

public class MapDataB<T extends MapCellCalssBase<T>> extends MapDataBase<T> {
	public MapDataB(String data) {
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
}
