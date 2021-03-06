package typing_game13;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SettingPanel extends JFrame{
	private Image screenImage;
	private Graphics screenGraphic;
	
	//private ImageIcon wordBtnBasic = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
	//private ImageIcon startBtnHover = new ImageIcon(Main.class.getResource("../images/startBtnHover.png"));
	//private ImageIcon startBtnClick = new ImageIcon(Main.class.getResource("../images/startBtnClicked.png"));
	
	private ImageIcon soundOnBtnBasic = new ImageIcon(Main.class.getResource("../images/soundOnBtnBasic.png"));
	//private ImageIcon settingBtnHover = new ImageIcon(Main.class.getResource("../images/settingBtnHover.png"));
	//private ImageIcon settingBtnClick = new ImageIcon(Main.class.getResource("../images/settingBtnClick.png"));
	
	private ImageIcon backBtnBasic = new ImageIcon(Main.class.getResource("../images/backBtnBasic.png"));
	//private ImageIcon exitBtnHover = new ImageIcon(Main.class.getResource("../images/exitBtnHover.png"));
	//private ImageIcon exitBtnClick = new ImageIcon(Main.class.getResource("../images/exitBtnClick.png"));
	
	//private JLabel wordLabel = new JLabel(wordBtnBasic);
	//private JButton wordBtnBtn = new JButton(wordBtnBasic);
	private JButton soundOnBtn = new JButton(soundOnBtnBasic);
	private JButton backBtn = new JButton(backBtnBasic);
	
	public SettingPanel(Container contentPane) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		Main.background = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
		//setStartBtn(contentPane, 100, 300);
		setSoundBtn(contentPane, 100, 410);
		setBackBtn(contentPane, 100, 520);
		
	}
	/*
	public void setStartBtn(Container contentPane, int x, int y) {
		wordLabel.setLocation(x, y);
		wordLabel.setSize(390, 90);
		wordLabel.setBorderPainted(false); //버튼 테두리 설정
		wordLabel.setContentAreaFilled(false); //버튼 영역 배경 
		wordLabel.setFocusPainted(false); //포커스링 삭제
		
		contentPane.setFocusable(false);
		
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
	}*/
	
	public void setSoundBtn(Container c, int x, int y) {
		soundOnBtn.setLocation(x, y);
		soundOnBtn.setSize(390, 90);
		soundOnBtn.setBorderPainted(false); //버튼 테두리 설정
		soundOnBtn.setContentAreaFilled(false); //버튼 영역 배경 
		soundOnBtn.setFocusPainted(false); //포커스링 삭제
		
		soundOnBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				//soundOnBtn.setIcon(settingBtnHover);
				soundOnBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//soundOnBtn.setIcon(settingBtnClick);
				soundOnBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				//settingBtn.setIcon(settingBtnBasic);
				soundOnBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		
		add(soundOnBtn);
	}
	
	public void setBackBtn(Container c,  int x, int y) {
		backBtn.setLocation(x, y);
		backBtn.setSize(390, 90);
		backBtn.setBorderPainted(false); //버튼 테두리 설정
		backBtn.setContentAreaFilled(false); //버튼 영역 배경 
		backBtn.setFocusPainted(false); //포커스링 삭제
		
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//backBtn.setIcon(exitBtnHover);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//backBtn.setIcon(exitBtnClick);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
				//backBtn.setIcon(exitBtnBasic);
				backBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(backBtn);
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
