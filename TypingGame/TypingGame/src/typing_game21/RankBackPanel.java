package typing_game21;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//RankPanel뒤에서 꾸며주는 배경 Panel
public class RankBackPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private JButton exitBtn; 
	
	public RankBackPanel(Container contentPane, GamePanel gamePanel, int difficulty, int score) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	
		setLayout(null);
		
		//배경에 따라 이미지 보이게끔 선택
		if(difficulty == 3) {
			exitBtn = new JButton(new ImageIcon(Main.class.getResource("../images/exitRankPanelBtnWhite.png")));
		}else {
		   exitBtn = new JButton(new ImageIcon(Main.class.getResource("../images/exitRankPanelBtn.png")));
		}
		exitBtn.setSize(60, 60);
		exitBtn.setLocation(1170, 20);
		exitBtn.setBorderPainted(false); //버튼 테두리 설정
		exitBtn.setContentAreaFilled(false); //버튼 영역 배경 표시 설정
		exitBtn.setFocusPainted(false); //포커스 표시 설정
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) { //게임종료
				Music music = gamePanel.getMusic();
				if(music != null) {
					music.interrupt();
					music.close();
				}
				
				//frame재생성
				((JFrame)SwingUtilities.getWindowAncestor(exitBtn.getParent())).dispose();
				Main.background = new ImageIcon("../images/backgroundTitle.png").getImage();
				new TypingGame();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		
		add(exitBtn);
		
		RankPanel rankPanel = new RankPanel(contentPane, difficulty, score);
		repaint();
		rankPanel.setLocation(Main.SCREEN_WIDTH/2-240, 70);
		add(rankPanel);
	}


	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		updateScreen(g);
	}

	public void updateScreen(Graphics g) {
		screenGraphic.drawImage(Main.pauseBackground, 0, 0, null);
		paintComponents(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
		revalidate();
		repaint();
	}
}
