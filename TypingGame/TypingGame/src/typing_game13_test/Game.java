package typing_game13_test;

import java.awt.*;
import javax.swing.*;

public class Game {
	private int difficulty;
	//private Image background;
	/*public Game(Image background){
		this.background = background;
	}
	
	public Image getBackground() {
		return background;
	}*/
	
	public Game(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}