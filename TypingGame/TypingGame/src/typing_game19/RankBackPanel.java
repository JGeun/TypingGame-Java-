package typing_game19;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RankBackPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private JButton exitBtn = new JButton(new ImageIcon(Main.class.getResource("../images/exitRankPanelBtn.png")));
	
	public RankBackPanel(Container contentPane, GamePanel gamePanel, int difficulty, int score) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	
		setLayout(null);
		
		exitBtn.setSize(60, 60);
		exitBtn.setLocation(1170, 20);
		exitBtn.setBorderPainted(false); //버튼 테두리 설정
		exitBtn.setContentAreaFilled(false); //버튼 영역 배경 
		exitBtn.setFocusPainted(false); //포커스링 삭제
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Music music = gamePanel.getMusic();
				if(music != null) {
					music.interrupt();
					music.close();
				}
				
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
