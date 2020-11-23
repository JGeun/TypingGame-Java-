package typing_game9;

import java.io.*;
import java.util.*;
public class TextSource {
	private ArrayList<String> wordList = new ArrayList<>();
	private String textPath = "word.txt";
	public TextSource() {
		try {
			FileReader fileReader = new FileReader("Vocabulary.txt");
			int c;
			StringBuffer sb = new StringBuffer();
			while((c = fileReader.read()) != -1) {
				if(c == '\n') {
					wordList.add(sb.toString());
					sb.setLength(0);
				}else {
					char ch = (char)c;
					if((ch>='a' && ch<='z') || ch == '\n')
					sb.append(ch);
				}
				
			}
			if(sb.length() != 0)
				wordList.add(sb.toString());
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public int getSize() {
		return wordList.size();
	}
	public String getText(int index) {
		return wordList.get(index);
	}
}
