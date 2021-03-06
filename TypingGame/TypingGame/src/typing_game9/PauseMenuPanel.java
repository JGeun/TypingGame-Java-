package typing_game9;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PauseMenuPanel extends JPanel{
	
	private JLabel pauseMenuTitle = new JLabel("Pause Menu");
	private JButton resumeBtn = new JButton("Resume");
	private JButton restartBtn = new JButton("Restart");
	private JButton exitToMainBtn = new JButton("Exit To Main");
	
	private int titleWidth = 400;
	private int titleHeight = 200;
	private int btnWidth = 400;
	private int btnHeight = 90;
	
	public PauseMenuPanel(Container contentPane, GamePanel gamePanel, PausePanel pausePanel) {
		setSize(480, 560);
		setBackground(new Color(170, 154, 91));
		setLayout(null);

		setMenu(contentPane, gamePanel, pausePanel, this);
	}
	
	private void setMenu(Container contentPane, GamePanel gamePanel, PausePanel pausePanel, PauseMenuPanel menuPanel) {
		pauseMenuTitle.setSize(titleWidth, titleHeight);
		pauseMenuTitle.setFont(new Font("Serif", Font.BOLD, 60));
		pauseMenuTitle.setLocation(80, 5);
		add(pauseMenuTitle);
		
		resumeBtn.setSize(btnWidth, btnHeight);
		resumeBtn.setLocation(40, 170);
		resumeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				contentPane.remove(resumeBtn.getParent());
				contentPane.remove(pausePanel);
				gamePanel.setVisible(true);
				repaint();
			}
		});
		add(resumeBtn);
		
		restartBtn.setSize(btnWidth, btnHeight);
		restartBtn.setLocation(40, 300);
		add(restartBtn);
		
		exitToMainBtn.setSize(btnWidth, btnHeight);
		exitToMainBtn.setLocation(40, 430);
		exitToMainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
//				menuPanel.removeAll();
//				//contentPane.remove(menuPanel);
//				pausePanel.removeAll();
				contentPane.remove(pausePanel);
				//gamePanel.setVisible(true);
				//contentPane.remove(gamePanel);
				//contentPane.removeAll();
				contentPane.add(new MainPanel(contentPane));
				contentPane.validate();
				contentPane.repaint();
			}
		});
		add(exitToMainBtn);
	}
}
