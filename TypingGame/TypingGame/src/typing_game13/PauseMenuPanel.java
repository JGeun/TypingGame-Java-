package typing_game13;

import java.awt.*;
import javax.swing.*;

import typing_game11.GamePanel.WordThread;

import java.awt.event.*;

public class PauseMenuPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private JLabel pauseMenuTitle = new JLabel("Pause Menu");
	private JButton resumeBtn = new JButton("Resume");
	private JButton restartBtn = new JButton("Restart");
	private JButton exitToMainBtn = new JButton("Exit To Main");
	
	private int titleWidth = 400;
	private int titleHeight = 200;
	private int btnWidth = 400;
	private int btnHeight = 90;
	
	public PauseMenuPanel(Container contentPane, GamePanel gamePanel, PausePanel pausePanel, Music gameMusic) {
		setSize(480, 560);
		setBackground(new Color(170, 154, 91));
		setLayout(null);
		
		setMenu(contentPane, gamePanel, pausePanel, this, gameMusic);
	}
	
	private void setMenu(Container contentPane, GamePanel gamePanel, PausePanel pausePanel, PauseMenuPanel menuPanel, Music gameMusic) {
		pauseMenuTitle.setSize(titleWidth, titleHeight);
		pauseMenuTitle.setFont(new Font("Serif", Font.BOLD, 60));
		pauseMenuTitle.setLocation(80, 5);
		add(pauseMenuTitle);
		
		resumeBtn.setSize(btnWidth, btnHeight);
		resumeBtn.setLocation(40, 170);
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
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				gamePanel.wordThread.threadRestart();
				gamePanel.wordThread.threadPause(false);
				resumeBtn.getParent().setVisible(false);
				contentPane.remove(resumeBtn.getParent());
				pausePanel.setVisible(false);
				contentPane.remove(pausePanel);
				gamePanel.setVisible(true);
				contentPane.invalidate();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		add(restartBtn);
		
		exitToMainBtn.setSize(btnWidth, btnHeight);
		exitToMainBtn.setLocation(40, 430);
		exitToMainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				gamePanel.wordThread.threadStop();
				if(gamePanel.wordThread.isAlive())
					System.out.println("Why?");
				
				gameMusic.interrupt();
				gameMusic.close();
				((JFrame)SwingUtilities.getWindowAncestor(gamePanel)).dispose();
				/*
				exitToMainBtn.getParent().setVisible(false);
				contentPane.remove(exitToMainBtn.getParent());
				pausePanel.setVisible(false);
				contentPane.remove(pausePanel);
				
				contentPane.remove(gamePanel);
				*/
				//contentPane.invalidate();
				Main.background = new ImageIcon("../images/backgroundTitle.png").getImage();
				
				new TypingGame();
				//contentPane.repaint();
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
		//revalidate();
		//repaint();
	}
}
