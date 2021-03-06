package typing_game17;

public class Word {
	private String word;
	private int x, y;
	private int effect;
	
	public Word(String word, int x, int y,  int effect) {
		this.word = word;
		this.x = x;
		this.y = y;
		
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
