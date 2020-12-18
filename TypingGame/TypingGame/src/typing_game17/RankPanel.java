package typing_game17;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.*;

public class RankPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	
	private int[][] rankBorderPos = {{400,50},{900,600}};
	
	private JLabel rankTitle = new JLabel("Best Player");
	
	private int playerIndex = 1;
	private JLabel[] playerIndexLabel = new JLabel[3];
	
	private int titleWidth = 400;
	private int titleHeight = 200;
	
	
	public RankPanel(Container contentPane){
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		rankTitle.setLocation(510, 10);
		rankTitle.setSize(titleWidth, titleHeight);
		rankTitle.setFont(new Font("Serif", Font.BOLD, 60));
		add(rankTitle);

	}
	
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		updateScreen(g);
	}
	
	public void updateScreen(Graphics g) {
		screenGraphic.drawImage(Main.pauseBackground, 0, 0, null);
		
		screenGraphic.setColor(Color.YELLOW);
		screenGraphic.drawLine(rankBorderPos[0][0], rankBorderPos[0][1], rankBorderPos[0][0], rankBorderPos[1][1]);
		screenGraphic.drawLine(rankBorderPos[0][0], rankBorderPos[0][1], rankBorderPos[1][0], rankBorderPos[0][1]);
		screenGraphic.drawLine(rankBorderPos[1][0], rankBorderPos[1][1], rankBorderPos[1][0], rankBorderPos[0][1]);
		screenGraphic.drawLine(rankBorderPos[1][0], rankBorderPos[1][1], rankBorderPos[0][0], rankBorderPos[1][1]);
		
		for(int i=0; i<3; i++) {
			AttributedString atString = new AttributedString(Integer.toString(i+1));
			atString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 60));
			screenGraphic.drawString(atString.getIterator(), 450, 230+70*i);
		}
		
		
		paintComponents(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
	}
}

