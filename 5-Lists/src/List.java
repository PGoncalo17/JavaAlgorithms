import java.util.Iterator;

public class List<T> implements Iterable<T> {
	private Node first;
	private Node last;
	private int size;

	private class Node {
		 T item;
		 Node next;
		 Node previous;

		 Node(T item) {
			this.item = item;
		}
	}

	public List() {
		first = null;
		last = null;
		size = 0;
	}

	public void add(T item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			first = last = newNode;
		}else {
			last.next = newNode;
		newNode.previous = last;
		last=newNode;
		}
		size++;
	}

	public T get(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("index inválido");
		Node current = first;
		for (int i=0; i < index; i++) {
			current = current.next;
		}
		return current.item;
	}

	private void deleteNode(Node node) {
		if(node==null) return;
		if(node==first && node==last) {
			first=last=null;
		}
		else if (node == first) {
			first = first.next;
			if(first != null) first.previous=null;
		}else if (node == last) {
			last = last.previous;
			if (last != null) last.next = null;
		} else {
			node.previous.next=node.next;
			node.next.previous=node.previous;
		}
		size--;
		if(size == 0) first = last = null;
	}

	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index inválido");
		}
		Node current = first;
		for (int i=0; i<index; i++) {
			current = current.next;
		}
		T item=current.item;
		deleteNode(current);
		return item;
	}

	public boolean removeFirst(T item) {
		for (Node i = first; i != null; i = i.next) {
			if (i.item.equals(item)) {
				deleteNode(i);
				return true;
			}
		}
		return false;
	}

	public boolean removeLast(T item) {
		for (Node i = last; i != null; i = i.previous) {
			if (i.item.equals(item)) {
				deleteNode(i);
				return true;
			}
		}
		return false;
	}

	public boolean removeAll(T item) {
		boolean removed=false;
		Node i=first;
		while (i != null) {
			Node next=i.next;
			if (i.item.equals(item)) {
				deleteNode(i);
				removed=true;
			}
			i=next;
		}
		return removed;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(T item) {
		for (Node i = first; i != null; i = i.next) {
			if (i.item.equals(item)) {
				return true;
	}
}
return false;
	}

	public int size() {
		return size;
	}

	public boolean isPalindrome() {
		Node i=first;
		Node j = last;
		while (i != null && j != null && i != j && i.previous != j) {
			if (!i.item.equals(j.item)) {
				return false;
			}
			i = i.next;
			j = j.previous;
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node i = first;

			@Override
			public boolean hasNext() {
				return i != null;
			}

			@Override
			public T next() {
				T item = i.item;
				i = i.next;
				return item;
			}
		};
	}

}
