package game.map.data;

public class CellObjectA extends MapCellObjectBaseEx<CellObjectA> {
	public int getGround(){
		return get(getMethodNo(new Object(){}));
	}
	public int getAir(){
		return get(getMethodNo(new Object(){}));
	}
	public int getHeight(){
		return get(getMethodNo(new Object(){}));
	}
}
