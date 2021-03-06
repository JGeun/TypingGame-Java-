package typing_game15;

import java.awt.*;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
		
	private Music titleMusic;
	
	private ImageIcon startBtnBasic = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
	private ImageIcon startBtnHover = new ImageIcon(Main.class.getResource("../images/startBtnHover.png"));
	private ImageIcon startBtnClick = new ImageIcon(Main.class.getResource("../images/startBtnClicked.png"));
	
	private ImageIcon settingBtnBasic = new ImageIcon(Main.class.getResource("../images/settingBtnBasic.png"));
	private ImageIcon settingBtnHover = new ImageIcon(Main.class.getResource("../images/settingBtnHover.png"));
	private ImageIcon settingBtnClick = new ImageIcon(Main.class.getResource("../images/settingBtnClick.png"));
	
	private ImageIcon exitBtnBasic = new ImageIcon(Main.class.getResource("../images/exitBtnBasic.png"));
	private ImageIcon exitBtnHover = new ImageIcon(Main.class.getResource("../images/exitBtnHover.png"));
	private ImageIcon exitBtnClick = new ImageIcon(Main.class.getResource("../images/exitBtnClick.png"));
	
	private ImageIcon soundOnBasic = new ImageIcon(Main.class.getResource("../images/soundOnBtnBasic.png"));
	private ImageIcon soundOffBasic = new ImageIcon(Main.class.getResource("../images/soundOffBtnBasic.png"));
	private ImageIcon backBtnBasic = new ImageIcon(Main.class.getResource("../images/backBtnBasic.png"));
	
	private JButton startBtn = new JButton(startBtnBasic);
	private JButton settingBtn = new JButton(settingBtnBasic);
	private JButton exitBtn = new JButton(exitBtnBasic);
	
	public MainPanel(Container contentPane) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		Main.background = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
		setStartBtn(contentPane, 100, 300);
		setSettingBtn(contentPane, 100, 410);
		setExitBtn(contentPane, 100, 520);
		
		if(Main.userSetSoundOn == true){
			titleMusic = new Music("titleMusic.mp3", true);
			titleMusic.start();
		}
	}
	
	public void setStartBtn(Container contentPane, int btnPosx, int btnPosy) {
		startBtn.setLocation(btnPosx, btnPosy);
		startBtn.setSize(390, 90);
		startBtn.setBorderPainted(false); //버튼 테두리 설정
		startBtn.setContentAreaFilled(false); //버튼 영역 배경 
		startBtn.setFocusPainted(false); //포커스링 삭제
	
		
		startBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startBtn.setIcon(startBtnHover);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				startBtn.setIcon(startBtnClick);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.remove(startBtn.getParent());
				
				if(titleMusic != null) {
					titleMusic.interrupt();
					titleMusic.close();
				}
				
				SelectLevelPanel selectLevelPanel = new SelectLevelPanel(contentPane);
				startBtn.getParent().setVisible(false);
				
				Main.background = new ImageIcon(Main.class.getResource("../images/selectBackground.png")).getImage();
				contentPane.add(selectLevelPanel);
				contentPane.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				startBtn.setIcon(startBtnBasic);
				startBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		
		add(startBtn);
	}
	
	
	public void setSettingBtn(Container contentPane, int btnPosx, int btnPosy) {
		settingBtn.setLocation(btnPosx, btnPosy);
		settingBtn.setSize(390, 90);
		settingBtn.setBorderPainted(false); //버튼 테두리 설정
		settingBtn.setContentAreaFilled(false); //버튼 영역 배경 
		settingBtn.setFocusPainted(false); //포커스링 삭제
		
		settingBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				settingBtn.setIcon(settingBtnHover);
				settingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				settingBtn.setIcon(settingBtnClick);
				settingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(titleMusic != null) {
					titleMusic.interrupt();
					titleMusic.close();
				}
				
				contentPane.remove(settingBtn.getParent());
				
				contentPane.add(new SettingPanel(contentPane));
				contentPane.invalidate();
				contentPane.revalidate();
				contentPane.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				settingBtn.setIcon(settingBtnBasic);
				settingBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		add(settingBtn);
	}
	
	public void setExitBtn(Container contentPane,  int btnPosx, int btnPosy) {
		exitBtn.setLocation(btnPosx, btnPosy);
		exitBtn.setSize(390, 90);
		exitBtn.setBorderPainted(false); //버튼 테두리 설정
		exitBtn.setContentAreaFilled(false); //버튼 영역 배경 
		exitBtn.setFocusPainted(false); //포커스링 삭제
		
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtn.setIcon(exitBtnHover);
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				exitBtn.setIcon(exitBtnClick);
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override 
			public void mouseClicked(MouseEvent e) {
				try {
					Thread.sleep(500);
				}catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
				System.exit(1);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtn.setIcon(exitBtnBasic);
				exitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(exitBtn);
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		updateScreen(g);
	}
	
	public void updateScreen(Graphics g) {
		screenGraphic.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic.drawImage(Main.background, 0, 0, this);
		paintComponents(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
		revalidate();
		//repaint();
	}
}
