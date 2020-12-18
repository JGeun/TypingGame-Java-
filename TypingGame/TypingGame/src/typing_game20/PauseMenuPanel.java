package typing_game20;

import java.awt.*;
import javax.swing.*;

import typing_game11.GamePanel.WordThread;

import java.awt.event.*;

public class PauseMenuPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private JLabel pauseMenuTitle = new JLabel("Pause Menu");
	
	//버튼 이미지들
	private JButton resumeBtn = new JButton("Resume");
	private JButton restartBtn = new JButton("Restart");
	private JButton exitToMainBtn = new JButton("Exit To Main");
	
	//버튼 설정
	private int btnWidth = 400;
	private int btnHeight = 90;
	
	//menuTitleLabel 설정
	private int titleWidth = 400;
	private int titleHeight = 200;
	
	
	public PauseMenuPanel(Container contentPane, GamePanel gamePanel, PausePanel pausePanel) {
		setSize(480, 560);
		setLayout(null);
		
		setMenu(contentPane, gamePanel, pausePanel, this);
	}
	
	//각 버튼과 메뉴타이틀 설정
	private void setMenu(Container contentPane, GamePanel gamePanel, PausePanel pausePanel, PauseMenuPanel menuPanel) {
		pauseMenuTitle.setSize(titleWidth, titleHeight);
		pauseMenuTitle.setFont(new Font("Serif", Font.BOLD, 60));
		pauseMenuTitle.setLocation(80, 5);
		if(gamePanel.getDifficulty() == 3) {
			pauseMenuTitle.setForeground(Color.WHITE);
		}
		add(pauseMenuTitle);
		
		resumeBtn.setSize(btnWidth, btnHeight);
		resumeBtn.setLocation(40, 170);
		
		//resume을 누르면 멈춰진 스레드 다시 시작
		resumeBtn.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e){
				gamePanel.wordThread.threadPause(false);
				resumeBtn.getParent().setVisible(false);
				contentPane.remove(resumeBtn.getParent());
				pausePanel.setVisible(false);
				contentPane.remove(pausePanel);
				gamePanel.setVisible(true);
			}
		});
		add(resumeBtn);
		
		restartBtn.setSize(btnWidth, btnHeight);
		restartBtn.setLocation(40, 300);
		
		//restart를 누르면 스레드가 처음부터 다시 시작
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(!gamePanel.wordThread.isAlive()) {
					gamePanel.wordThread = gamePanel.setWordThread(gamePanel);
				}else {
					gamePanel.wordThread.threadRestart();
					gamePanel.wordThread.threadPause(false);
				}
				resumeBtn.getParent().setVisible(false);
				contentPane.remove(resumeBtn.getParent());
				pausePanel.setVisible(false);
				contentPane.remove(pausePanel);
				gamePanel.setVisible(true);
				gamePanel.resetGame(gamePanel);
				
				contentPane.invalidate();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		add(restartBtn);
		
		
		exitToMainBtn.setSize(btnWidth, btnHeight);
		exitToMainBtn.setLocation(40, 430);
		
		//frame을 새로 만듭니다
		exitToMainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				gamePanel.wordThread.threadStop();
				if(gamePanel.wordThread.isAlive())
					System.out.println("Why?");
				
				Music music = gamePanel.getMusic();
				if(music != null) {
					music.interrupt();
					music.close();
				}
				
				((JFrame)SwingUtilities.getWindowAncestor(gamePanel)).dispose();
				
				Main.background = new ImageIcon("../images/backgroundTitle.png").getImage();
				
				new TypingGame();
			}
		});
		add(exitToMainBtn);
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		updateScreen(g);
	}
	
	public void updateScreen(Graphics g) {
		screenGraphic.drawImage(Main.background, 0, 0, this);
		paintComponents(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
	}
}
