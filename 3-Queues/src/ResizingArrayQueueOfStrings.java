
public class ResizingArrayQueueOfStrings {
	private String[] queue;
	private int first;
	private int last;

	public ResizingArrayQueueOfStrings() {
		queue = new String[1];
		first = -1;
		last = -1;
	}

	public int next(int i) {
		return (i + 1) % queue.length;
	}

	public void resize(int newSize) {
		String[] s = queue;
		queue = new String[newSize];

		int currentSize = size();
		int j = 0;
		for (int i = first; j < currentSize; i = next(i), j++) {
			queue[j] = s[i];
		}
		first = 0;
		last = j - 1;
				
	}

	public void enqueue(String item) {
		if (next(last) == first) {
			resize(2 * queue.length);
		}

		last = next(last);
		queue[last] = item;

		if (first == -1) {
			first = 0;
		}
	}

	public String dequeue() {
		if (first == -1)
			throw new IllegalStateException("Error: underflow");
		String Item = queue[first];
		queue[first] = null;
		if (first == last) {
			first = -1;
			last = -1;
		} else {
			first = next(first);
		}
		if (size() > 0 && size() == queue.length / 4 && queue.length > 1) {
			resize(queue.length / 2);
		}
		return Item;
	}

	public boolean isEmpty() {
		return first == -1;
	}

	public int size() {
		if (first == -1)
			return 0;
		else if (first <= last)
			return last - first + 1;
		else
			return queue.length - first + last + 1;
	}

	public int prev(int i) {
		if (i == 0) {
			return queue.length - 1;
		} else {
			return (i - 1) % queue.length;
		}
	}
	
	public void shift() {
		if(isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		if(first != last) {
			first = prev(first);
			queue[first] = queue[last];
			queue[last] = null;
			last = prev(last);
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i<queue.length; i++) {
			if(queue[i] == null) {
				s += "null | ";
			}else {
				s += queue[i] + " | ";
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
		
		System.out.println(queue);
		
		queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.enqueue("G");
        queue.enqueue("H");
        queue.enqueue("I");
        queue.enqueue("Pedro");
        System.out.println(queue);

        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);

        queue.enqueue("Gonçalo");
        System.out.println(queue);

        queue.shift();
        System.out.println(queue);
	}
	

}
