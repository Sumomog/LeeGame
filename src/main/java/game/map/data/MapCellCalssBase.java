package game.map.data;

public abstract class MapCellCalssBase<T extends MapCellCalssBase<T>> implements MapCell {
	private String data;

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int get(int index) {
		return data.charAt(index);
	}
}
