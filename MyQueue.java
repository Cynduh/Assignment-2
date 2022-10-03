

import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

	public Object[] data;
	public int first;
	public int last;
	public int numOfElements;
	public int capacity;

	public MyQueue() {
		this.capacity = 100;
		this.data = new Object[capacity];

	}
	
	public MyQueue(int capacity) {
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
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
		      throw new QueueUnderflowException();
		    }
		
		T firstInQ = (T) data[first];
		
		if(firstInQ == null)			
			return null;
		data[first] = null;
		first++;
		numOfElements--;
		
		return firstInQ;
		
	}

	@Override
	public int size() {
		return numOfElements;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}
		
		if(isEmpty()) {
			first = 0;
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
	public void fill(ArrayList<T> list) {
		ArrayList<T> List = new ArrayList<>(list);
	    List.forEach(t -> {
	      try {
	        enqueue(t);
	      } catch (QueueOverflowException exception) {
	        exception.getMessage();
	      }
	    });
	  }

}
