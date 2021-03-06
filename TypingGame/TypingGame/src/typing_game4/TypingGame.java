package typing_game4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingGame extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image backgroundTitle = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
	//private ImageIcon ButtonBasicImage = new ImageIcon(Main.class.getResource("../images/button.png"));
	private JButton btn = new JButton("start");
	
	public TypingGame() {
		//기본 설정
		setTitle("Typing Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setContentPane(new MainPanel());
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
	}
	
	class MainPanel extends JPanel{
		private Image backgroundTitle = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
		
		private ImageIcon startBtnBasic = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
		private ImageIcon startBtnHover = new ImageIcon(Main.class.getResource("../images/startBtnHover.png"));
		private ImageIcon startBtnClick = new ImageIcon(Main.class.getResource("../images/startBtnClicked.png"));
		
		private ImageIcon settingBtnBasic = new ImageIcon(Main.class.getResource("../images/settingBtnBasic.png"));
		private ImageIcon settingBtnHover = new ImageIcon(Main.class.getResource("../images/settingBtnHover.png"));
		private ImageIcon settingBtnClick = new ImageIcon(Main.class.getResource("../images/settingBtnClick.png"));
		
		private ImageIcon exitBtnBasic = new ImageIcon(Main.class.getResource("../images/exitBtnBasic.png"));
		private ImageIcon exitBtnHover = new ImageIcon(Main.class.getResource("../images/exitBtnHover.png"));
		private ImageIcon exitBtnClick = new ImageIcon(Main.class.getResource("../images/exitBtnClick.png"));
		
		private JButton btnStart = new JButton(startBtnBasic);
		private JButton btnSetting = new JButton(settingBtnBasic);
		private JButton btnExit = new JButton(exitBtnBasic);
		
		public MainPanel() {
			setLayout(null);
			
			setStartBtn(getContentPane(), 100, 300);
			setSettingBtn(getContentPane(), 100, 410);
			setExitBtn(getContentPane(), 100, 520);
			
			Music titleMusic = new Music("titleMusic.mp3", true);
			titleMusic.start();
			
			
		}
		public void setStartBtn(Container c, int x, int y) {
			JButton btn = new JButton(startBtnBasic);
			btn.setLocation(x, y);
			btn.setSize(390, 90);
			btn.setBorderPainted(false); //버튼 테두리 설정
			btn.setContentAreaFilled(false); //버튼 영역 배경 
			btn.setFocusPainted(false); //포커스링 삭제
			
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btn.setIcon(startBtnHover);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					btn.setIcon(startBtnClick);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btn.setIcon(startBtnBasic);
					btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			});
			
			add(btn);
		}
		
		public void setSettingBtn(Container c, int x, int y) {
			JButton btn = new JButton(settingBtnBasic);
			btn.setLocation(x, y);
			btn.setSize(390, 90);
			btn.setBorderPainted(false); //버튼 테두리 설정
			btn.setContentAreaFilled(false); //버튼 영역 배경 
			btn.setFocusPainted(false); //포커스링 삭제
			
			btn.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseEntered(MouseEvent e) {
					btn.setIcon(settingBtnHover);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					btn.setIcon(settingBtnClick);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btn.setIcon(settingBtnBasic);
					btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			});
			
			add(btn);
		}
		
		public void setExitBtn(Container c,  int x, int y) {
			JButton btn = new JButton(exitBtnBasic);
			btn.setLocation(x, y);
			btn.setSize(390, 90);
			btn.setBorderPainted(false); //버튼 테두리 설정
			btn.setContentAreaFilled(false); //버튼 영역 배경 
			btn.setFocusPainted(false); //포커스링 삭제
			
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btn.setIcon(exitBtnHover);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					btn.setIcon(exitBtnClick);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btn.setIcon(exitBtnBasic);
					btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			});
			
			add(btn);
		}
		public void paint(Graphics g) {
			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			screenGraphic = screenImage.getGraphics();
			screenDraw(screenGraphic);
			g.drawImage(screenImage, 0, 0, null);
		}
		
		public void screenDraw(Graphics g) {
			g.drawImage(backgroundTitle, 0, 0, null);
			paintComponents(g);
			this.repaint();
		}
	}
}
