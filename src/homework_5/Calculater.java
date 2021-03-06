package homework_5;

public class Calculater {

	public static String[] op = {"+", "-", "*", "/", "(", ")"};
	public static char[] cop = {'+', '-', '*', '/', '(', ')'};

	public static boolean isDigit(char c) {  
        if(c >= '0' && c <= '9'){  
            return true;  
        }  
        return false;  
    }  
	
    public static boolean isOp(String s) {
        for(int i = 0; i < op.length; i++){  
            if(op[i].equals(s)){
                return true;  
            }  
        }  
        return false;  
    }

	public static boolean isOp(char c) {
		for(int i = 0; i < op.length; i++){
			if(cop[i] == c){
				return true;
			}
		}
		return false;
	}

	public static int getPriority(String c) {
    	switch(c) {
    	case "@":
    		return 0;
    	case "+" :
    		return 1;
    	case "-" :
    		return 1;
    	case "*" :
    		return 2;
    	case "/" :
    		return 2;
    	case "(" :
    		return 3;
    	}
    	
    	return 3;
    }
    
    //��׺���ʽת��Ϊ��׺���ʽ
//    public static String InfixToPostfix(String infix) {
//
//    	char[] c = infix.toCharArray();
//
//    	AStack stack = new AStack();
//
//    	StringBuffer postfix = new StringBuffer();
//
//    	stack.push('@');
//
//    	for (int i = 0; i < infix.length(); i++) {
//
//    		if(c[i] == ')') {
//    			while ((char) stack.topValue() != '@') {
//    				postfix.append(stack.pop());
//    			}
//    			stack.pop();
//    		}
//
//    		else if (!isDigit(c[i])) {
//	    		while (getPriority((char) stack.topValue()) >= getPriority(c[i])) {
//	    			postfix.append(stack.pop());
//	    		}
//	    		if ( c[i] == '(')
//	    			stack.push('@');
//	    		else
//	    			stack.push(c[i]);
//    		}
//    		else {
//    			postfix.append(c[i]);
//    		}
//    	}
//
//    	while (getPriority((char) stack.topValue()) > 0) {
//    		postfix.append(stack.pop());
//    	}
//
//    	return postfix.toString();
//    }
    
    //��׺תΪ������
    public static Node InfixToTree(String infix) {

		int count = 0;

		String[] s = new String[1000];

    	char[] c = infix.toCharArray();

    	StringBuffer tmp = new StringBuffer();
    	
    	AStack nodeStack = new AStack();
    	
    	AStack opStack = new AStack();

    	for (int i = 0; i < c.length; i++ ) {
    		if (! isOp(c[i])) {
				tmp.append(c[i]);
			} else {
    			s[count++] = tmp.toString();
    			tmp = new StringBuffer();
    			s[count++] = String.valueOf(c[i]);
			}

			if (i == c.length - 1)
				s[count++] = tmp.toString();
		}

    	opStack.push("@");
    	
    	for (int i = 0; i < count; i++) {
    		if (s[i].equals(")")) {
    			while (! ((String) opStack.topValue()).equals("@")) {
    				nodeStack.push(new Node((String) opStack.pop(), (Node) nodeStack.pop(), (Node) nodeStack.pop()));
    			}
    			opStack.pop();
    		}
    		else if (isOp(s[i])) {
    			while (getPriority((String) opStack.topValue()) >= getPriority(s[i])) {
    				nodeStack.push(new Node((String) opStack.pop(), (Node) nodeStack.pop(), (Node) nodeStack.pop()));
    			}
    			if (s[i].equals("(")) {

					opStack.push("@");
				}
    			else {
					opStack.push(s[i]);
				}
    		}
    		else {
    			nodeStack.push(new Node(s[i]));
    		}

			if (((Node) nodeStack.topValue()).data.equals("3"))
				System.out.println("TTT");

		}
    	
    	while (getPriority((String) opStack.topValue()) > 0) {
    		nodeStack.push(new Node((String) opStack.pop(), (Node) nodeStack.pop(), (Node) nodeStack.pop()));
    	}
    	
    	return (Node) nodeStack.pop();
    }
    
    //����������ó�������
    public static double CalculateByTree(Node node) {
    	if (isOp(node.data)) {
    		switch (node.data) {
    		case "+":
    			return CalculateByTree(node.right) + CalculateByTree(node.left);
    		case "-":
    			return CalculateByTree(node.right) - CalculateByTree(node.left);
    		case "*":
    			return CalculateByTree(node.right) * CalculateByTree(node.left);
    		case "/":
    			return CalculateByTree(node.right) / CalculateByTree(node.left);
    		default:
    			return 0;
    		}
    	}
    	else {
    		return Double.valueOf(node.data);
    	}
    }
    
    public static void main(String[] args) {
//    	System.out.println(InfixToPostfix("3+4*(5+6)-3"));
    	System.out.println(InfixToTree("3+(4-1)").left.data);
    	System.out.print("正确结果为：");
    	System.out.println(3+4*(5+6)-3);
    	System.out.print("实验结果为：");
//    	System.out.println(CalculateByTree(InfixToTree("3+(4-1)")));
    }
}
