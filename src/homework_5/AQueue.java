package homework_5;

public class AQueue {
	
	private static final int defaultSize = 500;
	
	private int size;
	
	private int font;
	
	private int rear;
	
	private WordLadder.WordNode[] listArray;
	
	public AQueue() {
		// TODO �Զ����ɵĹ��캯�����
		init(defaultSize);
	}
	
	public AQueue(int sz) {
		// TODO �Զ����ɵĹ��캯�����
		init(sz);
	}
	
	private void init(int sz) {
		// TODO �Զ����ɵķ������
		size = sz + 1;
		font = rear = 0;
		listArray = new WordLadder.WordNode[size];
	}
	
	public void enqueue(WordLadder.WordNode it){
		if(((rear+1) % size) != font){
			rear = (rear + 1) % size;
			listArray[rear] = it;
		} 
	}
	
	public WordLadder.WordNode  dequeue(){
		if(rear != font){
			font = (font + 1) % size;
			return listArray[font];
		}
		return new WordLadder.WordNode(null, null);
	}
	
	public WordLadder.WordNode firstValue(){
		if(rear != font){
			return listArray[(font + 1) % size];
		}else
			return new WordLadder.WordNode(null, null);
	}

	public boolean empty(){
		return font == rear;
	}

	public WordLadder.WordNode lastValue() {
		if(rear != font) {
			return listArray[rear];
		}else
			return  new WordLadder.WordNode(null, null);
	}

}
