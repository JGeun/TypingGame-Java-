package typing_game13_test;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectLevelPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	
	//private Image background = new ImageIcon(Main.class.getResource("../images/selectBackground.png")).getImage();
	private ImageIcon selectArrowIcon = new ImageIcon(Main.class.getResource("../images/selectArrow.png"));
	private JLabel selectArrow = new JLabel(selectArrowIcon);
	
	private ImageIcon backArrowIconBasic = new ImageIcon(Main.class.getResource("../images/backArrow.png"));
	private JLabel backArrow = new JLabel(backArrowIconBasic);
	
	private int levelImageWidth = 300;
	private int levelImageHeight = 450;
	
	private Image level1 = new ImageIcon(Main.class.getResource("../images/spring.png")).getImage();
	private Image level2 = new ImageIcon(Main.class.getResource("../images/autumn.png")).getImage();
	private Image level3 = new ImageIcon(Main.class.getResource("../images/winter.png")).getImage();
	
	private int level1_PosX = 85;
	private int level_posY = 200;
	
	private JLabel label_L1 = new JLabel("Level 1");
	private JLabel label_L2 = new JLabel("Level 2");
	private JLabel label_L3 = new JLabel("Level 3");
	
	private int label_L1_posX = 165;
	private int label_posY = 120;
	
	private int labelWidth= 200;
	private int labelHeight= 100;
	
	private JButton btn = new JButton("test");
	
	public SelectLevelPanel(Container contentPane){
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		setGameSelectArrow(contentPane);
		setBackArrow(contentPane, this);
		
		contentPane.setFocusable(true);
		contentPane.requestFocus();
		
		contentPane.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				int arrowPosX = selectArrow.getX();
				switch(keyCode) {
				case KeyEvent.VK_LEFT:
					System.out.println("ddd");
					if(arrowPosX >= 405)
						selectArrow.setLocation(arrowPosX-407, selectArrow.getY());
					
					break;
				case KeyEvent.VK_RIGHT:
					if(arrowPosX <= 800)
						selectArrow.setLocation(selectArrow.getX()+407, selectArrow.getY());
					break;
				case KeyEvent.VK_ENTER:
					Game game;
					if(arrowPosX < 405)
						game = new Game(1);
					else if(arrowPosX > 900)
						game = new Game(3);
					else
						game = new Game(2);
					
					contentPane.setVisible(false);
					contentPane.remove(selectArrow.getParent());
					GamePanel gamePanel = new GamePanel(contentPane, game);
					contentPane.add(gamePanel);
					selectArrow.getParent().setVisible(false);
					contentPane.repaint();
					contentPane.setVisible(true);
					break;
				}
			}
		});
		
		setLevelLabel();
		
		
	}
	
	public void setGameSelectArrow(Container contentPane) {
		selectArrow.setSize(250, 150);
		selectArrow.setLocation(100, 30);
		add(selectArrow);
	}
	
	public void setBackArrow(Container contentPane, SelectLevelPanel levelPanel) {
		backArrow.setSize(60, 100);
		backArrow.setLocation(20, 0);
		backArrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				backArrow.getParent().setVisible(false);
				contentPane.remove(backArrow.getParent());
				contentPane.add(new MainPanel(contentPane));
				//contentPane.validate();
				//contentPane.repaint();
			}
		});
		add(backArrow);
	}

	public void setLevelLabel() {
		label_L1.setSize(labelWidth, labelHeight);
		label_L1.setLocation(label_L1_posX, label_posY);
		label_L1.setFont(new Font("Serif", Font.BOLD, 40));
		add(label_L1);
		
		label_L2.setSize(labelWidth, labelHeight);
		label_L2.setLocation(label_L1_posX+410, label_posY);
		label_L2.setFont(new Font("Serif", Font.BOLD, 40));
		add(label_L2);
		
		label_L3.setSize(labelWidth, labelHeight);
		label_L3.setLocation(label_L1_posX+810, label_posY);
		label_L3.setFont(new Font("Serif", Font.BOLD, 40));
		add(label_L3);
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //버퍼링용 이미지
		screenGraphic = screenImage.getGraphics(); //버퍼링용 이미지에 그래픽 객체를 얻어야 그림을 그릴 수 있다.
		updateScreen(g);
	}
	
	public void updateScreen(Graphics g) {
		screenGraphic.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic.drawImage(Main.background, 0, 0, this);
		screenGraphic.drawImage(level1, level1_PosX, level_posY, levelImageWidth, levelImageHeight, this);
		screenGraphic.drawImage(level2, level1_PosX+400, level_posY, levelImageWidth, levelImageHeight, this);
		screenGraphic.drawImage(level3, level1_PosX+800, level_posY, levelImageWidth, levelImageHeight, this);
		paintComponents(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
		revalidate();
		repaint();
	}
}
