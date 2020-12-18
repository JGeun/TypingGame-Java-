package typing_game19;

import java.io.*;
import java.util.*;

public class RankUser {
	private ArrayList<UserData> userList = new ArrayList<>();
	private String txtFileName = "";
	public RankUser(int difficulty) {
		switch(difficulty) {
		case 1:
			txtFileName = "rank1.txt";
			break;
		case 2:
			txtFileName = "rank2.txt";
			break;
		case 3:
			txtFileName = "rank3.txt";
			break;
		}
			
		try {
			System.out.println("Àß µé¾î¿È");
			FileReader fileReader = new FileReader(txtFileName);
			int c;
			StringBuffer sb = new StringBuffer();
			while((c = fileReader.read()) != -1) {
				if(c == '\n') {
					String[] data = sb.toString().split(" ");
					userList.add(new UserData(data[0], Integer.parseInt(data[1].trim())));
					sb.setLength(0);
				}else {
					char ch = (char)c;
					sb.append(ch);
				}
			}
			if(sb.length() != 0) {
				String[] data = sb.toString().split(" ");
				userList.add(new UserData(data[0], Integer.parseInt(data[1])));
			}
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}
		listSort();
	}
	
	public void listSort() {
		Collections.sort(userList, new Comparator<UserData>() {
			@Override
			public int compare(UserData data1, UserData data2) {
				int score1 = data1.getScore();
				int score2 = data2.getScore();
				if(score1 < score2)
					return 1;
				else if(score1 > score2)
					return -1;
				else 
					return 0;
			}
		});
	}
	public ArrayList<UserData> getList(){
		return userList;
	}
	public void addUserRecord(UserData data) {
		userList.add(data);
		listSort();
	}
	public int getSize() {
		return userList.size();
	}
	
	public UserData getUser(int index) {
		return userList.get(index);
	}
	
	public String getRankFileName() {
		return txtFileName;
	}
}
