package typing_game6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectLevelPanel extends JPanel{
	public final int SCREEN_WIDTH = 480;
	public final int SCREEN_HEIGHT = 320;
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	public SelectLevelPanel() {
		
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
