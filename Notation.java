
public class Notation {

	public static MyQueue<String> aQueue;
	public static MyStack<String> aStack;
	public static String operators = "+-*/";
	
	public Notation() {		
	}
	
	
	public static int precedence(char c) {
	    if (c == '*' || c == '/') {
	      return 1;
	    } 
	    else if (c == '+' || c == '-') {
	      return 0;
	    }
	    return -1;
	}
	
	public static String Top() {
		try {
		return aStack.top();
		}
		catch(StackUnderflowException e) {
			e.getMessage();
		}
		return null;
	}
	
	public static String Pop() {
		try {
		      return aStack.pop();
		      
		} 
		catch (StackUnderflowException e) {
			e.getMessage();
			}
		return null;
			}
	
	public static boolean Push(String element) {
		try {
			return aStack.push(element);
		} 
		catch (StackOverflowException e) {
			e.getMessage();
		}
		return false;
	}
	
	public static boolean enqueue(String element) {
		try {
			return aQueue.enqueue(element);
		} 
		catch (QueueOverflowException e) {
			e.getMessage();
		}
		return false;
		}
	
	public static String dequeue() {
		try {
			return aQueue.dequeue();
		} 
		catch (QueueUnderflowException e) {
			e.getMessage();
		}
		return null;
		}
	
	public static String applyOps(String first, String second, char op) throws InvalidNotationFormatException{
		
		double a = Double.parseDouble(first);
		double b = Double.parseDouble(second);
		
		switch(op) {
			case '+':
				return Double.toString(a + b);
			case '-':
				return Double.toString(a - b);
			case '*':
				return Double.toString(a * b);
			case '/':
				if(b==0)
					throw new InvalidNotationFormatException();
				return Double.toString(a / b);				
		}
		return null;
	}
	
	
	public static String convertInfixToPostfix​(String infix) throws InvalidNotationFormatException{
		
		aQueue = new MyQueue<String>();
		aStack = new MyStack<String>();
		
		for(int i = 0; i < infix.length(); i ++	) {
			
			char cur = infix.charAt(i);
			
			if(cur== ' ') {
				continue;
			}
			else if(Character.isDigit(cur)) {
				enqueue(Character.toString(cur));
			}
			else if (cur=='(') {
				Push(Character.toString(cur));
			}
			else if(operators.indexOf(cur) >= 0) {
				while(!aStack.isEmpty() && precedence(Top().charAt(0))>= precedence(cur)) {
					enqueue(Pop());
				}
				Push(Character.toString(cur));
			}
			else if(cur == ')') {
				char top = Pop().charAt(0);	
				while(top !='(') {
					enqueue(Character.toString(top));
					if(aStack.isEmpty()) {
						throw new InvalidNotationFormatException();
					}
					else {
						top = Pop().charAt(0);	
					}
	
				}
			}
			
		}
		while(aStack.isEmpty()) {
			enqueue(Pop());
			
		}
		return aQueue.toString();
	}
	
	public static String convertPostfixToInfix​(String postfix) throws InvalidNotationFormatException{
		
		aStack = new MyStack<String>();
		
		for(int i = 0; i < postfix.length(); i++) {
			char cur = postfix.charAt(i);
			if(cur == ' ') {
				continue;
			}
			else if(Character.isDigit(cur)) {
				Push(Character.toString(cur));
				
			}
			else if(operators.indexOf(cur) >=0) {
				String a = Pop().toString(), b, temp;
			if(aStack.isEmpty()) {
				throw new InvalidNotationFormatException();
				
			}
			else {
				b= Pop().toString();
				temp='(' + b + cur + a + ')';
				Push(temp);
			}
			}
		}
		if(aStack.size() != 1) {
			throw new InvalidNotationFormatException();
			
		}
		return Pop();
	}
	
	public static double evaluatePostfixExpression​(String postfixExpr) throws InvalidNotationFormatException{
		
		aStack = new MyStack<String>();
		
		for (int i = 0; i < postfixExpr.length(); i++) {
		      char cur = postfixExpr.charAt(i);
		      if (cur == ' ') {
		    	  continue;
		      } 
		      else if (Character.isDigit(cur) || cur == '(') {
		    	  Push(Character.toString(cur));
		    	  
		      } 
		      else if (operators.indexOf(cur) >= 0) {
		    	  String a = Pop().toString(), b;
		    	  String result;
		    	  if (aStack.isEmpty()) {
		    		  throw new InvalidNotationFormatException();
		    	  } 
		    	  else {
		    		  b = Pop().toString();
		    		  result = applyOps(b, a, cur);
		    		  Push(result);
		    	  }
		      }
		}
			if (aStack.size() != 1) {
				throw new InvalidNotationFormatException();
			}
			return Double.parseDouble(Pop());
	}


	

}
