package typing_game16;

import java.io.*;
import java.util.*;

public class TextSource {
	private ArrayList<String> wordList = new ArrayList<>();
	public TextSource() {
		try {
			FileReader fileReader = new FileReader("vocab.txt");
			int c;
			StringBuffer sb = new StringBuffer();
			while((c = fileReader.read()) != -1) {
				if(c == '\n') {
					String word = sb.toString();
					if(word.length() <= 7)
						wordList.add(sb.toString());
					sb.setLength(0);
				}else {
					char ch = (char)c;
					if((ch>='a' && ch<='z') || ch == '\n')
						if(ch != ' ')
							sb.append(ch);
				}
			}
			if(sb.length() != 0)
				wordList.add(sb.toString());
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}
		Collections.shuffle(wordList);
	}
	
	public ArrayList<String> getList(){
		return wordList;
	}
	public int getSize() {
		return wordList.size();
	}
	
	public String getText(int index) {
		return wordList.get(index);
	}
}
