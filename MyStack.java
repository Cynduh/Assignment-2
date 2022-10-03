
import java.util.ArrayList;

/**
 * @author Johnathan Duong
 *
 */
public class MyStack<T> implements StackInterface<T>{

	public Object[] data;
	public int first;
	public int last;
	public int numOfElements;
	public int capacity;
	
	public MyStack() {
		this.capacity = 100;
		this.data = new Object[capacity];
	}
	
	public MyStack(int capacity) {
		this.data = new Object[capacity];
		numOfElements = 0;
		this.first = -1;
		this.last = -1;
		this.capacity = capacity;
	}

	@Override
	public boolean isEmpty() {
		if(numOfElements == 0)
			return true;
		
		else
			return false;
	}

	@Override
	public boolean isFull() {
		if(numOfElements >= capacity)
			return true;
		
		else		
			return false;
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		
		T TopStack = (T) data[last];
		
		if(TopStack == null)
			return null;
		data[last] = null;
		last--;
		numOfElements--;
		
		return TopStack;
	}

	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
				
		T TopStack = (T) data[last];
		
		return TopStack;		
	}

	@Override
	public int size() {
		
		return numOfElements;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		}
		if(isEmpty()) {
			first =0;
			last = 0;
		}
		else {
		last++;
		}
		numOfElements++;
	    data[last] = e;
	    return true;
	  
	}
	
	@Override
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i <=last; i++) {
			s.append(data[i]);
		}
		
		return s.toString();
		
		
	}
	
	@Override
	public String toString(String delimiter) {
		
		StringBuilder s = new StringBuilder();

	    for (int i = first; i < last; i++) {
	      s.append(data[i] + delimiter);
	    }
	    
	    s.append(data[last]);
	    
	    return s.toString();
	}

	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException{		
		ArrayList<T> List = new ArrayList<>(list);
	    List.forEach(t-> {
	    	try {
	        push(t);
	      } 
	    	catch (StackOverflowException exception) {
	        exception.getMessage();
	      }
	    });
	}
	
	
	
}
