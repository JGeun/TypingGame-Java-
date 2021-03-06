package typing_game21;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
		
	private Music titleMusic; //메인화면의 음악
	
	//시작버튼의 기본, hover, click 이미지들
	private ImageIcon startBtnBasic = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
	private ImageIcon startBtnHover = new ImageIcon(Main.class.getResource("../images/startBtnHover.png"));
	private ImageIcon startBtnClick = new ImageIcon(Main.class.getResource("../images/startBtnClicked.png"));
	
	//세팅버튼의 기본, hover, click 이미지들
	private ImageIcon settingBtnBasic = new ImageIcon(Main.class.getResource("../images/settingBtnBasic.png"));
	private ImageIcon settingBtnHover = new ImageIcon(Main.class.getResource("../images/settingBtnHover.png"));
	private ImageIcon settingBtnClick = new ImageIcon(Main.class.getResource("../images/settingBtnClick.png"));
	
	//종료버튼의 기본, hover, click 이미지들
	private ImageIcon exitBtnBasic = new ImageIcon(Main.class.getResource("../images/exitBtnBasic.png"));
	private ImageIcon exitBtnHover = new ImageIcon(Main.class.getResource("../images/exitBtnHover.png"));
	private ImageIcon exitBtnClick = new ImageIcon(Main.class.getResource("../images/exitBtnClick.png"));
	
	//각 버튼들 생성
	private JButton startBtn = new JButton(startBtnBasic);
	private JButton settingBtn = new JButton(settingBtnBasic);
	private JButton exitBtn = new JButton(exitBtnBasic);
	
	public MainPanel(Container contentPane) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); 
		setLayout(null);
		
		Main.background = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage(); //배경지정
		
		//버튼들 위치 지정 및 이벤트 생성
		setStartBtn(contentPane, 100, 300, this);
		setSettingBtn(contentPane, 100, 410);
		setExitBtn(contentPane, 100, 520);
		
		//만약 사용자가 음악을 원하지 않는 경우 체크
		if(Main.userSetSoundOn == true){
			titleMusic = new Music("titleMusic.mp3", true);
			titleMusic.start();
		}
	}
	
	//시작버튼 설정 및 이벤트 생성
	public void setStartBtn(Container contentPane, int btnPosx, int btnPosy, MainPanel mainPanel) {
		startBtn.setLocation(btnPosx, btnPosy);
		startBtn.setSize(390, 90);
		startBtn.setBorderPainted(false); //버튼 테두리 설정
		startBtn.setContentAreaFilled(false); //버튼 영역 배경 표시 설정
		startBtn.setFocusPainted(false); //포커스 표시 설정
	
		startBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {  //마우스가 올라갔을 때
				startBtn.setIcon(startBtnHover);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 눌렸을 때
				startBtn.setIcon(startBtnClick);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) { //마우스로 클릭했을 때
				if(titleMusic != null) {
					titleMusic.interrupt();
					titleMusic.close();
				}
				
				contentPane.remove(startBtn.getParent()); //MainPanel 제거
				//다음 패널의 배경 지정
				Main.background = new ImageIcon(Main.class.getResource("../images/selectBackground.png")).getImage(); 
				contentPane.add( new SelectLevelPanel(contentPane));
				contentPane.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때 원상복귀
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
		settingBtn.setContentAreaFilled(false); //버튼 영역 배경 표시 설정
		settingBtn.setFocusPainted(false); //포커스 표시 설정
		
		settingBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				settingBtn.setIcon(settingBtnHover);
				settingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 눌렸을 때
				settingBtn.setIcon(settingBtnClick);
				settingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) { //마우스로 클릭했을 때
				if(titleMusic != null) {
					titleMusic.interrupt();
					titleMusic.close();
				}
				
				
				contentPane.remove(settingBtn.getParent()); //MainPanel삭제
				contentPane.add(new SettingPanel(contentPane)); //SettingPanel 추가
				contentPane.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e){ //마우스가 나갔을 때 원상복귀
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
		exitBtn.setContentAreaFilled(false); //버튼 영역 배경 표시 설정
		exitBtn.setFocusPainted(false); //포커스 표시 설정
		
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				exitBtn.setIcon(exitBtnHover);
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 눌렸을 때
				exitBtn.setIcon(exitBtnClick);
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override 
			public void mouseClicked(MouseEvent e) { //마우스로 클릭했을 때 0.5초 후 종료
				try {
					Thread.sleep(500);
				}catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
				System.exit(1);
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때 원상복귀
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
		revalidate(); //새 구성 요소 목록을 기반으로 재설정
	}
}
