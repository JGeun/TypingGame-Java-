package typing_game15_test;

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
	
	private long secondEffectTime;
	private boolean isSecondEffect = false;
	private JLabel timeStopLabel = new JLabel("time stop");
	private JLabel secondEffectLabel = new JLabel("÷2");
	
	private long thirdEffectTime;
	private boolean isThirdEffect = false;
	private JLabel thirdEffectLabel = new JLabel("×2");
	
	private int difficulty;
	private long delay;
	private ArrayList<Word> gameWord = new ArrayList<>();
	private ArrayList<String> wordList = textSource.getList();
	private ArrayList<String> storeWordList = new ArrayList<>();

	private int index = 0;
	private JLabel scoreLabel = new JLabel("Score: ");
	private JLabel scoreNumLabel = new JLabel("0");
	private int score = 0;
	private int point = 10;
	
	private int[] startBirdPosition = { 80, 200, 320, 440 };
	private int[] startWordPosition = 
		{ startBirdPosition[0]+50, startBirdPosition[1]+50, 
			startBirdPosition[2]+55, startBirdPosition[3]+62 };
	private Container contentPane;

	private Font fontPlain = new Font("Serif", Font.PLAIN, 20);
	private Font fontBold = new Font("Serif", Font.BOLD, 20);
	private Color[] effectColor = new Color[4];
	
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
		
		if(Main.userSetSoundOn == true) {
			gameMusic.start();
		}
	}
	
	public void setUI(GamePanel gamePanel, Game contents) {
		setColor();
		
		scoreLabel.setSize(100, 50);
		scoreLabel.setLocation(750, 20);
		scoreLabel.setFont(fontBold);
		add(scoreLabel);
		
		scoreNumLabel.setSize(100, 50);
		scoreNumLabel.setLocation(910, 20);
		scoreNumLabel.setFont(fontBold);
		add(scoreNumLabel);
		
		timeStopLabel.setSize(100, 50);
		timeStopLabel.setLocation(600, 20);
		timeStopLabel.setFont(fontBold);
		timeStopLabel.setForeground(effectColor[2]);
		add(timeStopLabel);
		timeStopLabel.setVisible(false);
		
		secondEffectLabel.setSize(100, 50);
		secondEffectLabel.setLocation(960, 20);
		secondEffectLabel.setFont(new Font("Serif", Font.BOLD, 30));
		secondEffectLabel.setForeground(effectColor[2]);
		add(secondEffectLabel);
		secondEffectLabel.setVisible(false);
		
		thirdEffectLabel.setSize(100, 50);
		thirdEffectLabel.setLocation(990, 20);
		thirdEffectLabel.setFont(new Font("Serif", Font.BOLD, 30));
		thirdEffectLabel.setForeground(effectColor[3]);
		add(thirdEffectLabel);
		thirdEffectLabel.setVisible(false);
		
		difficulty = contents.getDifficulty();
		delay = contents.getDelay();
		
		textInput.setSize(300, 30);
		textInput.setLocation(450, 600);
		textInput.setFont(fontPlain);
		textInput.setFocusable(true);
		textInput.requestFocus();
		add(textInput);
		textInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField textField = (JTextField) (e.getSource());
				String inWord = textField.getText();
				checkWord(inWord);
				textField.setText("");
				System.out.println(score);
			}
		});
		
		wordThread.start();
		setBackground(difficulty);
		setBirds();
		setButton(gamePanel, contentPane);
	}

	void setColor() {
		effectColor = new Color[4];
		if(difficulty != 3) {
			effectColor[0] = new Color(0, 0, 0);
			effectColor[3] = new Color(0, 255, 255);
			
		}
		else {
			effectColor[0] = new Color(255, 255, 255);
			effectColor[3] = new Color(157, 255, 30); //연두색
		}
		effectColor[1] = new Color(255, 0, 0);
		effectColor[2] = new Color(255, 255, 0);
		
	}
	
	private void checkWord(String inputWord) {
		point = 10;
		if(isSecondEffect)
			point /= 2;

		if(isThirdEffect) {
			point *= 2;
			long endTime = System.currentTimeMillis();
			if((endTime - thirdEffectTime)/1000 > 5) {
				isThirdEffect = false;
				thirdEffectLabel.setVisible(false);
			}
		}
		
		if (storeWordList.contains(inputWord)){
			for (int i = 0; i < gameWord.size(); i++) {
				if (gameWord.get(i).getWord().equals(inputWord)) {
					int effect = gameWord.get(i).getEffect();
					int bonusPoint = 100;
					if(isSecondEffect)
						bonusPoint /= 2;
					if(isThirdEffect)
						bonusPoint *= 2;
					
					if(effect != 0) {
						score += bonusPoint;
						if(effect == 1) {
							score =  score + (gameWord.size()-1)*point;
							gameWord.clear();
						}else if(effect == 2){
							secondEffectTime = System.currentTimeMillis();
							wordThread.threadProceed(false);
							secondEffectLabel.setVisible(true);
							timeStopLabel.setVisible(true);
							gameWord.remove(i);//점수 적게 주기
						}else{ //*2배 이벤트
							thirdEffectTime = System.currentTimeMillis();
							isThirdEffect = true;
							thirdEffectLabel.setVisible(true);
							gameWord.remove(i);
						}
					}else {
						score += point;
						gameWord.remove(i);
					}
					scoreNumLabel.setText(Integer.toString(score));
					break;
				}
			}
			contentPane.repaint();
			return;
		}
	}
	
	public class WordThread extends Thread {
		private boolean pause = false;
		private boolean stop = false;
		private boolean restart = false;
		private boolean isProceed = true;
		@Override
		public void run() {
			while(true){
				do{
					try{
						sleep(delay);
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
				
				if(isProceed) {
					changeWordPosition();
					if(index < 99){
						addWordInLine(index);
						index+=1;
					}else {
						System.out.println("Thread끝났습니다");
					}
				}else {
					long endTime = System.currentTimeMillis();
					if((endTime-secondEffectTime)/1000 > 3)
						isProceed = true;
					isSecondEffect = false;
					secondEffectLabel.setVisible(false);
					timeStopLabel.setVisible(false);
				}
				
				contentPane.invalidate();
				contentPane.revalidate();
				contentPane.repaint();
			}
		}
		public void threadProceed(boolean proceed) {
			this.isProceed = proceed;
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
		int line = (int) (Math.random() * 4);
		int effect;
		int num = (int)(Math.random()*100);
		if(num >= 95)
			effect = 3;
		else if(num >= 90)
			effect = 2;
		else if(num >= 85)
			effect = 1;
		else
			effect = 0;
		gameWord.add(new Word(text, 1280-100, startWordPosition[line], effect));
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
	
	public void resetGame() {
		scoreNumLabel.setText("0");
		score = 0;
		if(isSecondEffect) {
			timeStopLabel.setVisible(false);
			secondEffectLabel.setVisible(false);
			isSecondEffect = false;
		}
		if(isThirdEffect) {
			thirdEffectLabel.setVisible(false);
			isThirdEffect = false;
		}
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
		
		Graphics2D g2d = (Graphics2D)g;
		for(int i=0; i<gameWord.size(); i++) {
			Word word =	gameWord.get(i);
			
			screenGraphic.setColor(effectColor[word.getEffect()]);

			AttributedString atString = new AttributedString(word.getWord());
			atString.addAttribute(TextAttribute.FONT, fontBold);
			screenGraphic.drawString(atString.getIterator(), word.getX(), word.getY());
			screenGraphic.setColor(Color.BLACK);
		}
		paintComponents(screenGraphic);
		textInput.setFocusable(true);
		textInput.requestFocus();
		g.drawImage(screenImage, 0, 0, this);
		
		revalidate();
		repaint();
	}
}
