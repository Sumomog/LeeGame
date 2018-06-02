package game.map.data;

public enum CellEnumA implements MapCellEnumBase<CellEnumA> {

	Ground,
	Air,
	Height,
	;
	private static String data;

	@Override
	public void setData(String data) {
		CellEnumA.data = data;
	}

	@Override
	public int get() {
		return data.charAt(ordinal());
	}

	@Override
	public CellEnumA getEnumCell() {
		return this;
	}

	@Override
	public int get(int index) {
		return data.charAt(index);
	}
}
