package typing_game3;

import javax.swing.*;
import java.awt.*;

public class TypingGame extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image backgroundTitle;
	
	public TypingGame() {
		setTitle("Typing Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		backgroundTitle = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
		
		Music titleMusic = new Music("titleMusic.mp3", true);
		titleMusic.start();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(backgroundTitle, 0,0, null);
		this.repaint();
	}
}
