package game.manager;

import game.map.MapData;
import game.map.MapDataC;
import game.map.data.CellObjectB;
import game.util.Loader;

public final class MapManager extends Manager<MapData<CellObjectB>> {
	private static final MapManager manager = new MapManager();
	private MapManager() {}
	public static MapManager getManager() {
		return manager;
	}
	@Override
	public MapData<CellObjectB> load(String fileName) {
		String data = Loader.load(fileName);
		return new MapDataC<CellObjectB>(data);
	}
}
