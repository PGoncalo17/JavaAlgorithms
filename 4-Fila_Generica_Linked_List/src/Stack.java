import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

	private Node first;
	private int size;
	
	public Stack() {
	size=0;
	}
	
	
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	
	public void push(T item) {
		Node newNode=new Node(item, first);
		first=newNode;	
		size++;
	}
	
	public T pop() {
		if(isEmpty())
			throw new NoSuchElementException ();
		T item=first.item;
		first=first.next;
		size--;
		return item;
	}
	
	
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}
	
	public class StackIterator implements Iterator<T>{

		private Node i;
		
		public StackIterator() {
			i=first;
		}
		
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public T next() {
			return null;
		}
		
	}
	
	
	public class Node{
		private T item;
		private Node next;
		public Node(T item, Node next) {
			this.item=item;
			this.next=next;
		}
	}
	
	
}
