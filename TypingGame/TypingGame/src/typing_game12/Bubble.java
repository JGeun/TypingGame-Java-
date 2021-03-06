package typing_game12;

public class Bubble {
	private int x, y;
	private String word;
	private int effect;
	
	public Bubble(int x, int y, String word, int effect) {
		this.x = x;
		this.y = y;
		this.word = word;
		this.effect = effect;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getEffect() {
		return effect;
	}
}
