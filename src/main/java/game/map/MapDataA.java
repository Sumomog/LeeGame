package game.map;

import game.map.data.MapCellEnumBase;

public class MapDataA<T extends  Enum<T> & MapCellEnumBase<T>> extends MapDataBase<T> {
	public MapDataA(String data) {
		super(data);
	}
	public T getCell(T cell, int x, int y) {
		cell.setData(this.data[x][y]);
		return cell.getEnumCell();
	}
	@Override
	public T getCell(int x, int y) {
		T cell = instance();
		cell.setData(this.data[x][y]);
		return cell.getEnumCell();
	}
}
