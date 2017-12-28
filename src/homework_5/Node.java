package homework_5;

public class Node {
	String data;
	
	Node left;
	
	Node right;
	
	Node(String data){
		this(data, null ,null);
	}
	
	Node(String data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
