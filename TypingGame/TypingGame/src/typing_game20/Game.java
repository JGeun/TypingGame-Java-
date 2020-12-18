package typing_game20;

import java.awt.*;
import javax.swing.*;

public class Game {
	private int difficulty; //게임 난이도
	private long delay; //난이도에 따른 sleep delay변수
	
	public Game(int difficulty, long delay) {
		this.difficulty = difficulty;
		this.delay = delay;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public long getDelay(){
		return delay;
	}
}
