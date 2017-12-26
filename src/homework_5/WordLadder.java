package homework_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class WordLadder {

	public static class WordNode {
		String value;
		WordNode next;

		WordNode(String value, WordNode next) {
			this.value = value;
			this.next = next;
		}

		private void setNext (WordNode next) {
			this.next = next;
		}

		private  boolean hasNext() {
			return (next != null);
		}
	}

	public static void main(String[] args) {
		String[] words = getWords("words5.txt");
		WordNode[] wordNodes = new WordNode[2416];

		for (int i = 0; i < words.length; i++) {
			if (words[i] == null)
				break;

			wordNodes[i] = new WordNode(words[i], null);
		}

		for (int i = 0; i < words.length; i++) {
			if (words[i] == null)
				break;
			for (int j = 0; j < words.length; j++) {
				if (words[j] == null)
					break;
				if (isSimilar(words[i], words[j])) {
					wordNodes[i] = insertNode(words[j], wordNodes[i]);
//					wordNodes[i].setNext(new WordNode(words[j], null));
				}
			}
		}

		//Test~
		for (int i = 0; i < words.length; i++) {
		if (words[i] == null)
			break;
		System.out.print(words[i] + " ");
		printSimilar(wordNodes[i]);
		System.out.println();
		}

}

private static void wordLadder(String start, String end) {

}


	private static WordNode insertNode(String word, WordNode wordNode) {
		if (wordNode == null)
			return new WordNode(word, null);

		wordNode.next = insertNode(word, wordNode.next);

		return  wordNode;
	}

	private static void printSimilar(WordNode wordNode) {
		if (wordNode.hasNext()) {
			System.out.print(wordNode.next.value + " ");
			printSimilar(wordNode.next);
		}
	}

	private static String[] getWords(String fileName) {
		
		String[] words = new String[5000];
		int count = 0;
		File file = new File(fileName);
		BufferedReader reader = null;
		String temp = new String();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while ((temp = reader.readLine()) != null) {
				words[count++] = temp;
			}
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
		return words;
	}

	private static  boolean isSimilar(String s1, String s2) {
		int  diff = 0;

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i))
				if (++diff > 1)
					return false;
		}

		return diff == 1;
	}
}
