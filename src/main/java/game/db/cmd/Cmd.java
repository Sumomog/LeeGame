package game.db.cmd;

import game.db.DB;

public interface Cmd {
	void conect(DB db);
	void exe(String cmd);
	boolean condition(String cmd);
	boolean and(Object o1, Object o2);
}
