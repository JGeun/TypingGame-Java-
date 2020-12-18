package typing_game15_test;

import java.awt.*;
import javax.swing.*;

public class Game {
	private int difficulty;
	private long delay;
	
	public Game(int difficulty, long delay) {
		this.difficulty = difficulty;
		this.delay = delay;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public long getDelay() {
		return delay;
	}
}
