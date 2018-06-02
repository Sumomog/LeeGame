package game.map.data;

public class CellClassB extends MapCellCalssBase<CellClassB> {
	public int getGround(){
		return get(0);
	}
	public int getAir(){
		return get(1);
	}
	public int getHeight(){
		return get(2);
	}
}
