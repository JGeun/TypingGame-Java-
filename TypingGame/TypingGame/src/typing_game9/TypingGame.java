package typing_game9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingGame extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image backgroundTitle = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
	
	public TypingGame() {
		//�⺻ ����
		setTitle("Typing Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		Container c = getContentPane();
		c.add(new MainPanel(c));
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);

	}
}
