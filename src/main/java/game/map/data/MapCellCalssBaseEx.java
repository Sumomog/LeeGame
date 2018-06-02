package game.map.data;

public abstract class MapCellCalssBaseEx<T extends MapCellCalssBaseEx<T>> extends MapCellCalssBase<T> {
	public int getMethodNo(Object o){
		for (int i = 0; i < this.getClass().getDeclaredMethods().length; i++) {
			if (this.getClass().getDeclaredMethods()[i].equals(o.getClass().getEnclosingMethod())) {
				return i;
			}
		}
		throw new RuntimeException("Method番号取得失敗");
	}
}
