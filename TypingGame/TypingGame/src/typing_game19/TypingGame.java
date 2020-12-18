package typing_game19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingGame extends JFrame{
			
	public TypingGame() {
		//�⺻ ����
		setTitle("Typing Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		add(new MainPanel(getContentPane()));
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
	}
}
