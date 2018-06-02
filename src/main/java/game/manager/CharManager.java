package game.manager;

import game.character.CharData;
import game.character.CharDataBase;
import game.util.Loader;

public final class CharManager extends Manager<CharData> {
	private static final CharManager manager = new CharManager();
	private CharManager() {}
	public static CharManager getManager() {
		return manager;
	}
	@Override
	public CharData load(String fileName) {
		String data = Loader.load(fileName);
		return new CharDataBase(data);
	}
}
