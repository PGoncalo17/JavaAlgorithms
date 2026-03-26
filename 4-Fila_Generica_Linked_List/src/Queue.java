import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
	private Node first;
	private Node last;
	private int size;

	public class Node {
		public T item;
		public Node previous;
		public Node next;

		public Node(T item, Node previous, Node next) {
			this.item = item;
			this.previous = previous;
			this.next = next;
		}
	}

	public Queue() {
		first = null;
		last = null;
		size = 0;
	}

	public void enqueue(T item) {
		Node oldlast = last;
		last = new Node(item, oldlast, null);
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		size++;
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		T item = first.item;
		first = first.next;
		if (first != null) {
			first.previous = null;
		} else {
			last = null;
		}
		size--;
		return item;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void shift() {
		if (isEmpty() || size == 1) {
			return;
		}
		Node oldlast = last;
		last = last.previous;
		last.next = null;
		oldlast.next = first;
		first.previous = oldlast;
		first = oldlast;
		first.previous = null;
	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {
			Node current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T item = current.item;
				current = current.next;
				return item;
			}

		};
	}
}
