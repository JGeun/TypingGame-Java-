package typing_game15_test;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon soundOnBtnBasic = new ImageIcon(Main.class.getResource("../images/soundOnBtnBasic.png"));
	private ImageIcon soundOnBtnHover = new ImageIcon(Main.class.getResource("../images/soundOnBtnHover.png"));
	private ImageIcon soundOnBtnClick = new ImageIcon(Main.class.getResource("../images/soundOnBtnClick.png"));
	
	private ImageIcon soundOffBtnBasic = new ImageIcon(Main.class.getResource("../images/soundOffBtnBasic.png"));
	private ImageIcon soundOffBtnHover = new ImageIcon(Main.class.getResource("../images/soundOffBtnHover.png"));
	private ImageIcon soundOffBtnClick = new ImageIcon(Main.class.getResource("../images/soundOffBtnClick.png"));
	
	private ImageIcon soundBtnBasic;
	private ImageIcon soundBtnHover;
	private ImageIcon soundBtnClick;
	
	private ImageIcon backBtnBasic = new ImageIcon(Main.class.getResource("../images/backBtnBasic.png"));
	private ImageIcon backBtnHover = new ImageIcon(Main.class.getResource("../images/backBtnHover.png"));
	private ImageIcon backBtnClick = new ImageIcon(Main.class.getResource("../images/backBtnClick.png"));
	
	private boolean isSoundOn;
	private JButton soundBtn;
	private JButton backBtn = new JButton(backBtnBasic);
	
	private BufferedWriter bufferedWriter;
	private JTextField wordInput = new JTextField();
	private Font font = new Font("Serif", Font.BOLD, 30);
	
	public SettingPanel(Container contentPane) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null);
		
		Main.background = new ImageIcon(Main.class.getResource("../images/backgroundTitle.png")).getImage();
		
		setWordInputField();
		setSoundBtn(contentPane, 100, 410);
		setBackBtn(contentPane, 100, 520);
		
		contentPane.setFocusable(true);
		contentPane.requestFocus();
	}
	
	public void setWordInputField() {
		wordInput.setSize(200, 60);
		wordInput.setLocation(270, 315);
		wordInput.setFont(font);
		add(wordInput);
		wordInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField textField = (JTextField) (e.getSource());
				String addWord = textField.getText().trim().toLowerCase();
				addWord = addWord.replace(" ", "");
				if(addWord.length() > 8)
					addWord = addWord.substring(0, 7);
				else
					addWord = addWord.substring(0, addWord.length());
				try {
					bufferedWriter = new BufferedWriter(new FileWriter("vocab.txt", true));
					
					bufferedWriter.write(addWord+"\n");
					bufferedWriter.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
				textField.setText("");
			}
		});
		
		ImageIcon wordAddBtnIcon = new ImageIcon(Main.class.getResource("../images/wordAddBtn.png"));
		JButton btn_Add = new JButton(wordAddBtnIcon);
		btn_Add.setSize(390, 90);
		btn_Add.setLocation(100, 300);
		btn_Add.setBorderPainted(false); //버튼 테두리 설정
		btn_Add.setContentAreaFilled(false); //버튼 영역 배경 
		btn_Add.setFocusPainted(false); //포커스링 삭제
		add(btn_Add);
	}
	
	public void setSoundBtn(Container contentPane, int btnPosX, int btnPosY) {
		if(Main.userSetSoundOn){
			Main.userSetSoundOn = true;
			isSoundOn = true;
			soundBtnBasic = soundOnBtnBasic;
			soundBtnHover = soundOnBtnHover;
			soundBtnClick = soundOnBtnClick;
		}else {
			Main.userSetSoundOn = false;
			isSoundOn = false;
			soundBtnBasic = soundOffBtnBasic;
			soundBtnHover = soundOffBtnHover;
			soundBtnClick = soundOffBtnClick;
		}
		
		soundBtn = new JButton(soundBtnBasic);
		
		soundBtn.setLocation(btnPosX, btnPosY);
		soundBtn.setSize(390, 90);
		soundBtn.setBorderPainted(false); //버튼 테두리 설정
		soundBtn.setContentAreaFilled(false); //버튼 영역 배경 
		soundBtn.setFocusPainted(false); //포커스링 삭제
		
		soundBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				soundBtn.setVisible(true);
				soundBtn.setIcon(soundBtnHover);
				soundBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				soundBtn.setVisible(true);
				soundBtn.setIcon(soundBtnClick);
				soundBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override 
			public void mouseClicked(MouseEvent e) {
				if(isSoundOn) {
					isSoundOn = false;
					Main.userSetSoundOn = false;
					soundBtnBasic = soundOffBtnBasic;
					soundBtnHover = soundOffBtnHover;
					soundBtnClick = soundOffBtnClick;
				}else {
					isSoundOn = true;
					Main.userSetSoundOn = true;
					soundBtnBasic = soundOnBtnBasic;
					soundBtnHover = soundOnBtnHover;
					soundBtnClick = soundOnBtnClick;
				}
				soundBtn.setIcon(soundBtnBasic);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				soundBtn.setIcon(soundBtnBasic);
				soundBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		add(soundBtn);
	}
	
	public void setBackBtn(Container contentPane,  int x, int y) {
		backBtn.setLocation(x, y);
		backBtn.setSize(390, 90);
		backBtn.setBorderPainted(false); //버튼 테두리 설정
		backBtn.setContentAreaFilled(false); //버튼 영역 배경 
		backBtn.setFocusPainted(false); //포커스링 삭제
		
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setIcon(backBtnHover);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backBtn.setIcon(backBtnClick);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override 
			public void mouseClicked(MouseEvent e) {
				contentPane.remove(backBtn.getParent());
				
				backBtn.getParent().setVisible(false);
				
				contentPane.add(new MainPanel(contentPane));
				contentPane.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setIcon(backBtnBasic);
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
		repaint();
	}
}
