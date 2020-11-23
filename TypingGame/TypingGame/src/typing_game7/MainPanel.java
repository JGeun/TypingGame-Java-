package typing_game7;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Music titleMusic;
	
	private Image background = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
	
	private ImageIcon startBtnBasic = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
	private ImageIcon startBtnHover = new ImageIcon(Main.class.getResource("../images/startBtnHover.png"));
	private ImageIcon startBtnClick = new ImageIcon(Main.class.getResource("../images/startBtnClicked.png"));
	
	private ImageIcon settingBtnBasic = new ImageIcon(Main.class.getResource("../images/settingBtnBasic.png"));
	private ImageIcon settingBtnHover = new ImageIcon(Main.class.getResource("../images/settingBtnHover.png"));
	private ImageIcon settingBtnClick = new ImageIcon(Main.class.getResource("../images/settingBtnClick.png"));
	
	private ImageIcon exitBtnBasic = new ImageIcon(Main.class.getResource("../images/exitBtnBasic.png"));
	private ImageIcon exitBtnHover = new ImageIcon(Main.class.getResource("../images/exitBtnHover.png"));
	private ImageIcon exitBtnClick = new ImageIcon(Main.class.getResource("../images/exitBtnClick.png"));
	
	private JButton startBtn = new JButton(startBtnBasic);
	private JButton settingBtn = new JButton(settingBtnBasic);
	private JButton exitBtn = new JButton(exitBtnBasic);
	
	public MainPanel(Container contentPane) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		setStartBtn(contentPane, 100, 300);
		setSettingBtn(contentPane, 100, 410);
		setExitBtn(contentPane, 100, 520);
		
		titleMusic = new Music("titleMusic.mp3", true);
		titleMusic.start();
	}
	
	public void setStartBtn(Container contentPane, int x, int y) {
		startBtn.setLocation(x, y);
		startBtn.setSize(390, 90);
		startBtn.setBorderPainted(false); //��ư �׵θ� ����
		startBtn.setContentAreaFilled(false); //��ư ���� ��� 
		startBtn.setFocusPainted(false); //��Ŀ���� ����
		
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
				
				//startBtn.setEnabled(false);
				//startBtn.setVisible(false);
				
				//settingBtn.setEnabled(false);
				//settingBtn.setVisible(false);
				
				//exitBtn.setEnabled(false);
				//exitBtn.setVisible(false);
				
				contentPane.remove(startBtn.getParent());
				
				titleMusic.interrupt();
				titleMusic.close();
				
				SelectLevelPanel selectLevelPanel = new SelectLevelPanel(contentPane);
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
	
	public void setSettingBtn(Container c, int x, int y) {
		settingBtn.setLocation(x, y);
		settingBtn.setSize(390, 90);
		settingBtn.setBorderPainted(false); //��ư �׵θ� ����
		settingBtn.setContentAreaFilled(false); //��ư ���� ��� 
		settingBtn.setFocusPainted(false); //��Ŀ���� ����
		
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
			public void mouseExited(MouseEvent e) {
				settingBtn.setIcon(settingBtnBasic);
				settingBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		
		add(settingBtn);
	}
	
	public void setExitBtn(Container c,  int x, int y) {
		exitBtn.setLocation(x, y);
		exitBtn.setSize(390, 90);
		exitBtn.setBorderPainted(false); //��ư �׵θ� ����
		exitBtn.setContentAreaFilled(false); //��ư ���� ��� 
		exitBtn.setFocusPainted(false); //��Ŀ���� ����
		
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
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
}