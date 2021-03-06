package typing_game10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PausePanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	private JButton btn = new JButton("click");
	
	public PausePanel(Container contentPane, GamePanel gamePanel, ImageIcon pauseBgIcon) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		setLayout(null);
		PauseMenuPanel menuPanel = new PauseMenuPanel(contentPane, gamePanel, this);
		menuPanel.setLocation(Main.SCREEN_WIDTH/2-240, 70);
		add(menuPanel);
		
		/*
		btn.setSize(300, 300);
		btn.setLocation(100, 200);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				btn.getParent().setVisible(false);
				//contentPane.remove(btn.getParent());
				contentPane.add(new MainPanel(contentPane));
			}
		});
		add(btn);
		*/
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
		//repaint();
	}
}
