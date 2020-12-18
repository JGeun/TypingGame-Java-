package typing_game21;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.io.*;
import java.text.*;

import javax.swing.*;

public class RankPanel extends JPanel{
	private Image screenImage;
	private Graphics screenGraphic;
	private Color textColor = Color.BLACK; //폰트색깔
	
	private RankUser rankUser;
	private int[][] rankBorderPos = {{400,50},{900,600}};
	private JLabel rankTitle = new JLabel("Best Player");
	
	private JTextField nameInput = new JTextField();
	private boolean isFirstInput = true;
	
	private JLabel userNameLabel = new JLabel();
	private String userName;
	private int userScore;
	private int userRank;
	private boolean isUserInTopRank = false;
	
	private int titleWidth = 400;
	private int titleHeight = 200;
	
	public RankPanel(Container contentPane, int difficulty, int score){
		setColor(difficulty);
		rankUser = new RankUser(difficulty);
		userScore = score;
		setSize(480, 560);
		setLayout(null);
		
		rankTitle.setLocation(95, -50);
		rankTitle.setSize(titleWidth, titleHeight);
		rankTitle.setFont(new Font("Serif", Font.BOLD, 60));
		rankTitle.setForeground(textColor);
		add(rankTitle);
		
		nameInput.setSize(150, 60);
		nameInput.setLocation(150, 460);
		nameInput.setFont(new Font("Serif", Font.BOLD, 60));
		nameInput.setFocusable(true);
		nameInput.requestFocus();
		nameInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField textField = (JTextField) (e.getSource());
				if(textField.getText().toString().length() >= 3) {
					String name = textField.getText().toString().substring(0, 3);
					textField.setText("");
					userName = name;
					userNameLabel.setText(userName);
					isFirstInput = false;
					textField.setVisible(false);
					userNameLabel.setVisible(true);
					
					writeUserRecord();
					findUserRankInTop();
					repaint();
				}
			}
		});
		nameInput.addKeyListener(new KeyAdapter() {
		       @Override
		       public void keyReleased(KeyEvent e) {
		           int pos = nameInput.getCaretPosition();
		           nameInput.setText(nameInput.getText().toUpperCase());
		           nameInput.setCaretPosition(pos);
		       }
		});
		add(nameInput);
		
		userNameLabel.setSize(150, 60);
		userNameLabel.setLocation(150, 460);
		userNameLabel.setFont(new Font("Serif", Font.BOLD, 60));
		userNameLabel.setForeground(textColor);
		add(userNameLabel);
		userNameLabel.setVisible(false);
	}
	
	public void findUserRankInTop(){
		for(int i=0; i<rankUser.getSize(); i++) {
			if(userScore > rankUser.getUser(i).getScore()) {
				userRank = i-1;
				if(i < 5)
					isUserInTopRank = true;
				break;
			}
		}
	}
	public void writeUserRecord() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rankUser.getRankFileName(), true));
			bufferedWriter.write(userName+" " + userScore+ "\n");
			bufferedWriter.flush();
			rankUser.addUserRecord(new UserData(userName, userScore));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	
	public void setColor(int difficulty) {
		if(difficulty == 3)
			textColor = Color.WHITE;
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		updateScreen(g);
	}
	
	public void updateScreen(Graphics g) {
		screenGraphic.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic.drawImage(Main.background, 0, 0, null);
		
		screenGraphic.setColor(textColor);
		AttributedString atString;
		
		for(int i=0; i<5; i++) {
			atString = new AttributedString(Integer.toString(i+1));
			atString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 50));
			screenGraphic.drawString(atString.getIterator(), 50, 150+70*i);
			
			UserData userData = rankUser.getUser(i);
			atString = new AttributedString(userData.getName());
			atString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 50));
			screenGraphic.drawString(atString.getIterator(), 150, 150+70*i);
			
			atString = new AttributedString(Integer.toString(userData.getScore()));
			atString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 50));
			screenGraphic.drawString(atString.getIterator(), 350, 150+70*i);
		}
		
		atString = new AttributedString("You: ");
		atString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 50));
	    screenGraphic.drawString(atString.getIterator(), 20, 505);
			
		atString = new AttributedString(Integer.toString(userScore));
		atString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 50));
		screenGraphic.drawString(atString.getIterator(), 350, 505);
		
		if(isUserInTopRank){
			screenGraphic.setColor(Color.RED);
			screenGraphic.fillRect(30, 165+70*userRank, 430, 10);
		}
		
		paintComponents(screenGraphic);
		nameInput.setFocusable(true);
		nameInput.requestFocus();
		
		g.drawImage(screenImage, 0, 0, this);
		
		repaint();
	}
}

