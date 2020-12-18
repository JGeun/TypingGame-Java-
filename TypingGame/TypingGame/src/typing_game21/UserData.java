package typing_game21;

public class UserData{
	private String name;
	private int score;
	
	public UserData(String name, int score) {
		this.name = name;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
}
