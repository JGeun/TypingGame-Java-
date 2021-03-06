package typing_game8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	
	private ImageIcon btnPauseIcon = new ImageIcon(Main.class.getResource("../images/btnPause.png"));
	private ImageIcon btnSoundIcon = new ImageIcon(Main.class.getResource("../images/btnSound.png"));
	private ImageIcon btnSoundMuteIcon = new ImageIcon(Main.class.getResource("../images/btnSoundMute.png"));
	private JButton btnPause = new JButton(btnPauseIcon);
	private JButton btnSound = new JButton(btnSoundIcon);
	
	private JLabel word = new JLabel("ㄱㄱ");
	private TextSource textSource = new TextSource();
	
	public GamePanel(Container c, Game contents) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		background = contents.getBackground();
		
		setLayout(null);
		
		btnPause.setSize(80, 80);
		btnPause.setLocation(Main.SCREEN_WIDTH-150, 0);
		btnPause.setBorderPainted(false); //버튼 테두리 설정
		btnPause.setContentAreaFilled(false); //버튼 영역 배경 
		btnPause.setFocusPainted(false); //포커스링 삭제
		add(btnPause);
		
		btnSound.setSize(80, 80);
		btnSound.setLocation(Main.SCREEN_WIDTH-250, 0);
		btnSound.setBorderPainted(false); //버튼 테두리 설정
		btnSound.setContentAreaFilled(false); //버튼 영역 배경 
		btnSound.setFocusPainted(false); //포커스링 삭제
		add(btnSound);
		
		//test
		word.setSize(100, 100);
		word.setLocation(100, 100);
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_LEFT) {
					System.out.println("a");
					int index = (int)(Math.random()*textSource.getSize());
					word.setText(textSource.getText(index));
				}
			}
		});
		add(word);
		
		c.setFocusable(true);
		c.requestFocus();
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
