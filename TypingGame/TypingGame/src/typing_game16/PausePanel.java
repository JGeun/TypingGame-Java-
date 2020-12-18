package typing_game16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PausePanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	
	public PausePanel(Container contentPane, GamePanel gamePanel) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		setLayout(null);
		PauseMenuPanel menuPanel = new PauseMenuPanel(contentPane, gamePanel, this);
		repaint();
		menuPanel.setLocation(Main.SCREEN_WIDTH/2-240, 70);
		add(menuPanel);
		
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
