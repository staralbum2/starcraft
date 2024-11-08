package starcraft;

public class StarcraftGame {
	private static StarcraftGame starcraftGame= new StarcraftGame();
	private StarcraftGame() {
	}
	static StarcraftGame getInstance() {
		return starcraftGame;
	}
}
