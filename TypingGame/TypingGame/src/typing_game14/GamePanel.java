package typing_game14;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class GamePanel extends JPanel {
	private Image screenImage;
	private Graphics screenGraphic;

	private Music gameMusic;
	private String musicPath;
	
	private ImageIcon bgIcon;
	private ImageIcon pauseBgIcon;
	private ImageIcon btnPauseIcon = new ImageIcon(Main.class.getResource("../images/btnPause.png"));
	private ImageIcon btnSoundIcon = new ImageIcon(Main.class.getResource("../images/btnSound.png"));
	private ImageIcon btnSoundMuteIcon = new ImageIcon(Main.class.getResource("../images/btnSoundMute.png"));
	private JButton btnPause = new JButton(btnPauseIcon);
	private JButton btnSound;

	private Image[] birds = new Image[4];
	private ImageIcon bird1 = new ImageIcon(Main.class.getResource("../images/birdBlue.png"));
	private ImageIcon bird2 = new ImageIcon(Main.class.getResource("../images/birdGreen.png"));
	private ImageIcon bird3 = new ImageIcon(Main.class.getResource("../images/birdBrown.png"));
	private ImageIcon bird4 = new ImageIcon(Main.class.getResource("../images/birdPurple.png"));

	private TextSource textSource = new TextSource();
	private JTextField textInput = new JTextField();
	
	private ArrayList<Word> gameWord = new ArrayList<>();
	private ArrayList<String> wordList = textSource.getList();
	private ArrayList<String> storeWordList = new ArrayList<>();

	private int index = 0;
	private int score = 0;
	private int[] startBirdPosition = { 80, 200, 320, 440 };
	private int[] startWordPosition = 
		{ startBirdPosition[0]+20, startBirdPosition[1]+20, 
			startBirdPosition[2]+20, startBirdPosition[3]+20 };
	private Container contentPane;

	private Font font = new Font("Serif", Font.BOLD, 20);
	public WordThread wordThread = new WordThread();
	
	public GamePanel(Container container, Game contents) {
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		contentPane = container;
		
		if(Main.userSetSoundOn)
			btnSound = new JButton(btnSoundIcon);
		else
			btnSound = new JButton(btnSoundMuteIcon);

		setLayout(null);
		setUI(this, contents);
		
		contentPane.setFocusable(true);
		contentPane.requestFocus();

		if(Main.userSetSoundOn == true) {
			gameMusic.start();
		}
		
	}
	
	public void setUI(GamePanel gamePanel, Game contents) {
		textInput.setSize(300, 30);
		textInput.setLocation(450, 600);
		add(textInput);
		textInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField textField = (JTextField) (e.getSource());
				String inWord = textField.getText();
				checkWord(inWord, index);
				textField.setText("");

				System.out.println(score);
			}
		});
		
		wordThread.start();
		setBackground(contents.getDifficulty());
		setBirds();
		setButton(gamePanel, contentPane);
	}

	private void checkWord(String inputWord, int index) {
		if (storeWordList.contains(inputWord)) {
			int wordIdx = 0;
			for (int i = 0; i < gameWord.size(); i++) {
				if (gameWord.get(i).equals(inputWord)) {
					wordIdx = i;
					break;
				}
			}
			System.out.println("wordIdx: " + wordIdx); 
			storeWordList.remove(storeWordList.indexOf(inputWord));
			score += 10;
			gameWord.remove(wordIdx);
			contentPane.revalidate();
			contentPane.repaint();
			return;
		}
	}
	
	public class WordThread extends Thread {
		private boolean pause = false;
		private boolean stop = false;
		private boolean restart = false;
		@Override
		public void run() {
			while(true){
				do{
					try{
						sleep(2000);
						if(stop == true)
							interrupt();
						else if(restart == true) {
							threadRestart();
							index = 0;
							contentPane.repaint();
							restart = false;
						}
					}catch(InterruptedException e) {
						System.out.println("thread interrupt");
						return ;
					}
				}while(pause == true);
				
				System.out.println("index: "+ index);
				
				changeWordPosition();
						
				if(index < 99){
					addWordInLine(index);
					index+=1;
				}else {
					System.out.println("Thread끝났습니다");
				}
				contentPane.invalidate();
				contentPane.revalidate();
				contentPane.repaint();
			}
		}
		public void threadPause(boolean pause) {
			this.pause = pause;
		}
		public void threadRestart() {
			restart = true;
			gameWord.clear();
		}
		public void threadStop(){
			this.stop = true;
		}
		
		public void threadInterrupt() {
			this.interrupt();
		}
	}

	private void addWordInLine(int threadIndex) {

		String text = textSource.getText(threadIndex);
		//setFont(new Font("Serif", Font.BOLD, 20));
		int line = (int) (Math.random() * 4);
		//word[threadIndex].setSize(text.length()*20, 20);
		//word[threadIndex].setLocation(1280 - 100, startWordPosition[line]);
		gameWord.add(new Word(text, 1280-100, startWordPosition[line], 0));
		storeWordList.add(text);
	}

	
	private void changeWordPosition() {
		for(int i=0; i<gameWord.size(); i++) {
			Word word = gameWord.get(i);
			int posY = word.getY();
			if(posY == startWordPosition[0]) {
				word.setPosition(word.getX() - 130, word.getY());
			}else if(posY == startWordPosition[1]) {
				word.setPosition(word.getX() - 100, word.getY());
			}else if(posY == startWordPosition[2]) {
				word.setPosition(word.getX() - 80, word.getY());
			}else {
				word.setPosition(word.getX() - 115, word.getY());
			}
		}
	}
	public void setBackground(int difficulty) {
		switch (difficulty) {
		case 1:
			Main.background = new ImageIcon(Main.class.getResource("../images/springGameBackground.png")).getImage();
			Main.pauseBackground = new ImageIcon(Main.class.getResource("../images/springGamePauseBg.png")).getImage();
			musicPath = "spring.mp3";
			if(Main.userSetSoundOn)
				gameMusic = new Music(musicPath, true);
			break;
		case 2:
			Main.background = new ImageIcon(Main.class.getResource("../images/autumnGameBackground.png")).getImage();
			Main.pauseBackground = new ImageIcon(Main.class.getResource("../images/autumnGamePauseBg.png")).getImage();
			musicPath = "autumn.mp3";
			if(Main.userSetSoundOn)
				gameMusic = new Music(musicPath, true);
			break;
		case 3:
			Main.background = new ImageIcon(Main.class.getResource("../images/winterGameBackground.png")).getImage();
			Main.pauseBackground = new ImageIcon(Main.class.getResource("../images/winterGamePauseBg.png")).getImage();
			musicPath = "winter.mp3";
			if(Main.userSetSoundOn)
				gameMusic = new Music(musicPath, true);
			break;
		}
	}

	public void setBirds() {
		birds[0] = bird1.getImage();
		birds[1] = bird2.getImage();
		birds[2] = bird3.getImage();
		birds[3] = bird4.getImage();
	}

	public void setButton(GamePanel gamePanel, Container contentPane) {
		//contentPane.setFocusable(false);
		btnPause.setSize(80, 80);
		btnPause.setLocation(Main.SCREEN_WIDTH - 150, 0);
		btnPause.setBorderPainted(false); // 버튼 테두리 설정
		btnPause.setContentAreaFilled(false); // 버튼 영역 배경
		btnPause.setFocusPainted(false); // 포커스링 삭제
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wordThread.threadPause(true);
				gamePanel.setVisible(false);
				contentPane.add(new PausePanel(contentPane, gamePanel));
				// contentPane.repaint();
			}
		});
		add(btnPause);

		btnSound.setSize(80, 80);
		btnSound.setLocation(Main.SCREEN_WIDTH - 250, 0);
		btnSound.setBorderPainted(false); // 버튼 테두리 설정
		btnSound.setContentAreaFilled(false); // 버튼 영역 배경
		btnSound.setFocusPainted(false); // 포커스링 삭제
		btnSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnSound.getIcon() == btnSoundIcon) {
					btnSound.setIcon(btnSoundMuteIcon);
					if(gameMusic != null) {
						gameMusic.interrupt();
						gameMusic.close();
					}
				} else {
					if(Main.userSetSoundOn) {
						btnSound.setIcon(btnSoundIcon);
						gameMusic = new Music(musicPath, true);
						gameMusic.start();
					}

				}
			}
		});
		add(btnSound);
	}

	public Music getMusic() {
		return gameMusic;
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		updateScreen(g);
	}

	public void updateScreen(Graphics g) {
		screenGraphic.drawImage(Main.background, 0, 0, this);
		screenGraphic.drawImage(birds[0], 60, startBirdPosition[0], 100, 100, this);
		screenGraphic.drawImage(birds[1], 140, startBirdPosition[1], 100, 100, this);
		screenGraphic.drawImage(birds[2], 220, startBirdPosition[2], 100, 100, this);
		screenGraphic.drawImage(birds[3], 120, startBirdPosition[3], 120, 120, this);
		for(int i=0; i<gameWord.size(); i++) {
			Word word =	gameWord.get(i);
			
			AttributedString atString = new AttributedString(word.getWord());
			atString.addAttribute(TextAttribute.FONT, font);
			screenGraphic.drawString(atString.getIterator(), word.getX(), word.getY());
		}
			
		paintComponents(screenGraphic);
		g.drawImage(screenImage, 0, 0, this);
		
		revalidate();
		repaint();
	}
}
