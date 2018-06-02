package game.manager;

import game.step.Step;

public final class StepManager extends Manager<Step> {
	private static final StepManager manager = new StepManager();
	private StepManager() {}
	public static StepManager getManager() {
		return manager;
	}
	@Override
	public Step load(String fileName) {
		try {
			return (Step) Class.forName(fileName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
