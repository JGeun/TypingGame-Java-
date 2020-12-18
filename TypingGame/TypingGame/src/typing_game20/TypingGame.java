package typing_game20;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingGame extends JFrame{
			
	public TypingGame() {
		setTitle("Typing Game");   
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		add(new MainPanel(getContentPane())); //MainPanel에 contentPane을 넘겨주고 
											 //기본화면을 MainPanel로 설정
		
		setResizable(false);  //창 크기 조절 불가
		setLocationRelativeTo(null);   //창 위치를 가운데로 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		setLayout(null);
	}
}
