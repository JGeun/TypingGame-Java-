package typing_game9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	private ImageIcon bgIcon ;
	private ImageIcon pauseBgIcon;
	private ImageIcon btnPauseIcon = new ImageIcon(Main.class.getResource("../images/btnPause.png"));
	private ImageIcon btnSoundIcon = new ImageIcon(Main.class.getResource("../images/btnSound.png"));
	private ImageIcon btnSoundMuteIcon = new ImageIcon(Main.class.getResource("../images/btnSoundMute.png"));
	private JButton btnPause = new JButton(btnPauseIcon);
	private JButton btnSound = new JButton(btnSoundIcon);
	
	private Image[] birds = new Image[4];
	private ImageIcon bird1 = new ImageIcon(Main.class.getResource("../images/birdBlue.png"));
	private ImageIcon bird2 = new ImageIcon(Main.class.getResource("../images/birdGreen.png"));
	private ImageIcon bird3 = new ImageIcon(Main.class.getResource("../images/birdBrown.png"));
	private ImageIcon bird4 = new ImageIcon(Main.class.getResource("../images/birdPurple.png"));
	
	private JLabel word = new JLabel("����");
	private TextSource textSource = new TextSource();
	
	public GamePanel(Container c, Game contents) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBackground(contents.getDifficulty());
		
		setLayout(null);
		
		setUI(c, contents);
		
		c.setFocusable(true);
		c.requestFocus();
	}
	public void setUI(Container contentPane, Game contents) {
		setBackground(contents.getDifficulty());
		setBirds();
		setButton(contentPane);
	}
	
	public void setBackground(int difficulty) {
		switch(difficulty) {
		case 1:
			bgIcon = new ImageIcon(Main.class.getResource("../images/springGameBackground.png"));
			pauseBgIcon = new ImageIcon(Main.class.getResource("../images/springGamePauseBg.png"));
			break;
		case 2:
			bgIcon = new ImageIcon(Main.class.getResource("../images/autumnGameBackground.png"));
			pauseBgIcon = new ImageIcon(Main.class.getResource("../images/autumnGamePauseBg.png"));
			break;
		case 3:
			bgIcon = new ImageIcon(Main.class.getResource("../images/winterGameBackground.png"));
			pauseBgIcon = new ImageIcon(Main.class.getResource("../images/winterGamePauseBg.png"));
			break;
		}
		background = bgIcon.getImage();
	}
	
	public void setBirds() {
		birds[0] = bird1.getImage();
		birds[1] = bird2.getImage();
		birds[2] = bird3.getImage();
		birds[3] = bird4.getImage();
	}
	
	public void setButton(Container contentPane) {
		btnPause.setSize(80, 80);
		btnPause.setLocation(Main.SCREEN_WIDTH-150, 0);
		btnPause.setBorderPainted(false); //��ư �׵θ� ����
		btnPause.setContentAreaFilled(false); //��ư ���� ��� 
		btnPause.setFocusPainted(false); //��Ŀ���� ����
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				background = pauseBgIcon.getImage();
				btnPause.getParent().setVisible(false);
				contentPane.add(new PausePanel(contentPane, pauseBgIcon));
			}
		});
		add(btnPause);
		
		btnSound.setSize(80, 80);
		btnSound.setLocation(Main.SCREEN_WIDTH-250, 0);
		btnSound.setBorderPainted(false); //��ư �׵θ� ����
		btnSound.setContentAreaFilled(false); //��ư ���� ��� 
		btnSound.setFocusPainted(false); //��Ŀ���� ����
		btnSound.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				if(btnSound.getIcon() == btnSoundIcon) {
					btnSound.setIcon(btnSoundMuteIcon);
					contentPane.requestFocus();
				}
				else {
					btnSound.setIcon(btnSoundIcon);
					contentPane.requestFocus();
				}	
			}
		});
		add(btnSound);
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(birds[0], 60, 80, 100, 100, null);
		g.drawImage(birds[1], 140, 200, 100, 100, null);
		g.drawImage(birds[2], 220, 320, 100, 100, null);
		g.drawImage(birds[3], 120, 420, 120, 120, null);
		paintComponents(g);
		this.repaint();
	}
}