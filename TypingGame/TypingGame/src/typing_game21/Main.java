package typing_game21;

import java.awt.*;

public class Main {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static Image background;  //기본 배경
	public static Image	pauseBackground; //pause걸렸을 때 배경
	public static boolean userSetSoundOn = true;  //사용자의 음악 on/off
	
	public static void main(String[] args) {
		new TypingGame();
	}
}
