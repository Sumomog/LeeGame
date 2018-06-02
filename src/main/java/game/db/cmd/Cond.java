package game.db.cmd;

public class Cond {
	public class Clm extends Number {

		@Override
		public int intValue() {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public long longValue() {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public float floatValue() {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public double doubleValue() {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

	}

	private static int typeA = 0;
	private static int typeB = 1;
	private static int typeC = 2;

	Object o1;
	Object o2;

	public Cond(String cond) {
		char cs[] = cond.toCharArray();
		int type = 0;
		for (int i = 0; i < cs.length; i++) {
			switch (cs[i]) {
			case 'a':
			case 'A':

				break;
			default:
				break;
			}
		}
	}
	public Cond(Object o1) {
		this.o1 = o1;
	}
	public Cond(Object o1, Object o2) {
		this.o1 = o1;
		this.o2 = o2;
	}
}
