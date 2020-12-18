package typing_game20;

import javazoom.jl.*;
import javazoom.jl.player.*;
import java.io.*;

public class Music extends Thread{
	private Player player; 
	private boolean isLoop; //계속 반복할지
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI()); //mp3파일을 uri 형식으로 저장
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if(player == null) //음악이 설정되지 않았다면
			return 0;
		return player.getPosition(); 
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run(){
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
