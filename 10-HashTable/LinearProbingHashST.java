import java.util.LinkedList;

public class LinearProbingHashST<Key, Value> {
	Key[] keys;
	Value[] vals;
	int M;
	int size;

	public LinearProbingHashST(int M) {
		this.M = M;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
		size=0;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void put(Key key, Value value) {
		if(key ==null) throw new IllegalArgumentException("Key é nula");
		
		if(value==null) {
			delete(key);
			return;
		}
		
		if(size >= M/2) resize(2*M);
		
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				vals[i]=value;
			return;
			}
		}
				
		keys[i] = key;
		vals[i] = value;
		size++;
	}

	public Value get(Key key) {
		if(key==null) throw new IllegalArgumentException("Key é nula");
		
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key))  
				return vals[i];
		}
		return null;
	}

	public void delete(Key key) {
		if(!contains(key)) return;
		
		int i=hash(key);
		while (!key.equals(keys[i])){
			i = next(i);
		}

		keys[i] = null;
		vals[i] = null;
		i = next(i);

		while (keys[i] != null) {
			Key newKey = keys[i];
			Value newVals = vals[i];
			keys[i] = null;
			vals[i] = null;
			put(newKey, newVals);
			i = next(i);
		}
		size--;

		if (size > 0 && size <= M / 8)
		resize(M / 2);
	}

	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> tmp = new LinearProbingHashST<Key, Value>(capacity);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null)
				tmp.put(keys[i], vals[i]);
		}
		this.keys = tmp.keys;
		this.vals = tmp.vals;
		this.M = tmp.M;
	}

	private int next(int i) {
		return (i + 1) % M;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size;
	}

	public Iterable<Key> keys() {
		LinkedList<Key> key=new LinkedList<Key>();
		for(int i=0; i < M; i++) {
			if(keys[i] != null)
			key.add(keys[i]);	
		}
		return key;
	}
	
}
