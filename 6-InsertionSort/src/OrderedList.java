import java.util.Iterator;

public class OrderedList<T extends Comparable<T>> implements Iterable<T> {

	private class Node {
		T item;
		Node previous;
		Node next;

		Node(T item) {
			this.item = item;
		}
	}

	int size;
	Node first;
	Node last;

	public OrderedList() {
		first = null;
		last = null;
		size = 0;
	}

	public void add(T item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			first = last = newNode;
		} else {
			last.next = newNode;
			newNode.previous = last;
			last = newNode;
		}
		size++;
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

	public boolean isEmpty() {
		return size == 0;
	}

	private Node get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of range");
		}
		Node atual = first;
		for (int i = 0; i < index; i++) {
			atual = atual.next;
		}
		return atual;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node i = first;

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

	private void exchange(Node i, Node j) {
		T temp = i.item;
		i.item = j.item;
		j.item = temp;
	}

	private boolean less(Node i, Node j) {
		return i.item.compareTo(j.item) < 0;
	}

	public void sort() {
		for (Node i = first; i != null; i = i.next) {
			for (Node j = i; j != first && less(j, j.previous); j = j.previous) {
				exchange(j, j.previous);
			}
		}
	}

	public void shuffle() {
		Node atual = first;
		for (int i = 1; i <= size; i++) {
			int aleatorio = (int) (Math.random() * i) + 1;
			if (i != aleatorio)
				exchange(atual, get(aleatorio));
			atual = atual.next;
		}
	}

	public boolean isSorted() {
		for (Node i = first; i != last; i = i.next) {
			for (Node j = i.next; j != first; j = j.previous) {
				if (less(j, j.previous)) {
					return false;
				}
			}
		}
		return true;
	}
}
