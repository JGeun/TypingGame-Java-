package typing_game9;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	public PausePanel(Container contentPane, GamePanel gamePanel, ImageIcon pauseBgIcon) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		background = pauseBgIcon.getImage();
		
		setLayout(null);
		PauseMenuPanel menuPanel = new PauseMenuPanel(contentPane, gamePanel, this);
		menuPanel.setLocation(Main.SCREEN_WIDTH/2-240, 70);
		add(menuPanel);
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
}
