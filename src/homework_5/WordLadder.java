package homework_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class WordLadder {

	private static String[] words = getWords("words5.txt");

	private static WordNode[] wordNodes = new WordNode[2416];

	private static WordNode[] searchNodes = new WordNode[2416];

	private static AQueue queue = new AQueue();

	public static class WordNode {
		String value;
		WordNode next;
		int mark = 0;

		WordNode(String value, WordNode next) {
			this.value = value;
			this.next = next;
		}

		private void setMark () {
			this.mark = 1;
		}

		private  boolean hasNext() {
			return (next != null);
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < words.length; i++) {
			if (words[i] == null)
				break;

			wordNodes[i] = new WordNode(words[i], null);
		}

		// 建图
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

		// 初始化searchNodes
		for (int i = 0; i < words.length; i++) {
			if (words[i] == null)
				break;
			searchNodes[i] = new WordNode(words[i], null);
		}

		//Test~
		for (int i = 0; i < words.length; i++) {
		if (words[i] == null)
			break;
		System.out.print(words[i] + " ");
		printSimilar(wordNodes[i]);
		System.out.println();
		}

		build(wordNodes[findWords("acorn")]);
//		System.out.println(searchNodes[findWords("adorn")].next.value);
		getChain(wordNodes[findWords("acorn")], wordNodes[findWords("blade")]);
	}// acorn blade

	private static void build(WordNode start) {

		WordNode temp = start;

		if (start.mark == 0) {
			// 未标记的入队
			while ((temp = temp.next) != null) {
				if (temp.mark == 0) {
					queue.enqueue(wordNodes[findWords(temp.value)]);
					insertNode(start.value, searchNodes[findWords(temp.value)]);
				}
			}

			start.setMark();
		}

		if ( !queue.empty()) {
			// 出队,进入递归
			build(queue.dequeue());
		}
	}

	private static void getChain(WordNode start, WordNode end) {

		ArrayList<String> list = new ArrayList<>();

		AStack stack = new AStack();

		WordNode temp = new WordNode(null, null);

		if (searchNodes[findWords(end.value)] .next != null) {
			stack.push(searchNodes[findWords(end.value)]);
			list.add(searchNodes[findWords(end.value)].value);

			WordNode w = searchNodes[findWords(end.value)];
			while ((w = searchNodes[findWords(w.next.value)] ) != null) {
				stack.push(w);
				list.add(w.value);
				searchNodes[findWords(w.value)].setMark();
				if (w.value.equals(start.value))
					break;
			}

			System.out.println(list);

			while (stack.topValue() != null) {
				temp = (WordNode) stack.pop();
				if (temp == null)
					break;

				System.out.println(temp.value);

				if (list.size() > 0)
					list.remove(list.size() - 1);

				if (temp.hasNext()) {

					WordNode n = searchNodes[findWords(temp.value)];
					while ((n = searchNodes[findWords(n.next.value)]) != null) {
						if (n.mark == 0) {
							stack.push(n);
							list.add(n.value);
							searchNodes[findWords(n.value)].setMark();
							if (n.value.equals(start.value))
								break;
						}
					}

					System.out.println(list);
				}
			}

		} else {
			System.out.println("No such chain.");
		}
	}

	private static int findWords(String value) {
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(value))
				return i;
		}
		return -1;
	}


//private static void wordLadder(WordNode start, WordNode end) {
//    AQueue[] queues = new AQueue[50];
//    int i = 0;
//
//    for (int j = 0; j <= queues.length; j ++)
//    	queues[j].enqueue(start);
//
//    //广搜
//    while (queues[i].lastValue().next != null) {
//    	queues[i].enqueue(queues[i].lastValue().next);
//    	if (queues[i].lastValue().value == end.value) {
//    		while (!queues[i].empty()) {
//    			System.out.print(queues[i].dequeue() + " --> ");
//			}
//		}
//    	i++;
//	}
//
//	//递归
//	for (int j = 0; j < i ; i++) {
//    	wordLadder(queues[j].lastValue(), end);
//	}
//}


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
