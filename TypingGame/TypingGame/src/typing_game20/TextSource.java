package typing_game20;

import java.io.*;
import java.util.*;

public class TextSource {
	private ArrayList<String> wordList = new ArrayList<>();
	public TextSource() {
		try {
			FileReader fileReader = new FileReader("vocab.txt"); //단어파일 읽기
			int c;
			StringBuffer sb = new StringBuffer();
			while((c = fileReader.read()) != -1) {
				if(c == '\n') {
					String word = sb.toString();
					if(word.length() <= 7) //단어의 길이가 7이하면 list에 추가
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
		Collections.shuffle(wordList); //호출할 때마다 섞어줍니다
	}
	
	public ArrayList<String> getList(){ return wordList;}
	public int getSize() { return wordList.size(); }
	public String getText(int index) { return wordList.get(index); }
}
