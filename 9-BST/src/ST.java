import java.util.LinkedList;

public class ST<Key extends Comparable<Key>, Value> {
	Node root;

	private class Node {
		public Node(Key key, Value val) {
			this.key = key;
			this.value = value;
		}
		public Key key;
		public Value value;
		public Node left;
		public Node right;
		int size;
	}
	
	public ST() {
		root = null;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.value = val;
		x.size=1+size(x.left)+ size(x.right); 
		return x;
	}
	
	
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.value;
	}
	
	public void delete(Key key) {
	    if (key == null) throw new IllegalArgumentException("A chave não pode estar vazia");
	    root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (key == null) throw new IllegalArgumentException("Key não pode ser nula");
	    if (x == null) return null;
	    int cmp = key.compareTo(x.key);
	    if (cmp < 0) {
	        x.left = delete(x.left, key);
	    } else if (cmp > 0) {
	        x.right = delete(x.right, key);
	    } else {
	        if (x.right == null) return x.left;
	        if (x.left == null) return x.right;
	        Node t = x;
	        x = t.right;
	        while (x.left != null) {
	            x = x.left;
	        }
	        x.right = deleteMin(t.right);
	        x.left = t.left;
	    }
	    x.size = 1 + size(x.left) + size(x.right);
	    return x;
	}
	
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	
	public boolean isEmpty() {
		return size()==0;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if(x==null) return 0;
		else
			return 1 + size(x.left)+ size(x.right); 
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(Node x) {
		if(x==null) return 0;
		return 1 + Math.max(height(x.left), height(x.right));
	}
	
	public Key min() {
		return min(root);
	}
	
	private Key min(Node x) {
		if(x==null) return null;
		if(x.left==null) return x.key;
		return min(x.left);
		}
	
	
	public Key max() {
		return max(root);
	}
	
	private Key max(Node x) {
		if(x==null) return null;
		if(x.right==null) return x.key;
		return max(x.right);
	}
	
	
	public Key floor(Key key) {
		return floor(root, key);
	}
	
	private Key floor(Node x, Key key) {
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0) return floor(x.left, key);
		if(cmp>0) {
			Key T = floor(x.right, key);
			if(T!=null) return T;
			else return x.key;
		}
		else return x.key;
	}
	
	
	public Key ceiling(Key key) {
		return ceiling(root, key);
		}
	
	private Key ceiling(Node x, Key key) {
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp>0) return ceiling(x.right, key);
		if(cmp<0) {
			Key T=ceiling(x.left, key);
			if(T!=null) return T;
			else return x.key;
		}
		return x.key;
	}
	
	
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node x, Key key) {
		if(x==null) return 0;
		int cmp=key.compareTo(x.key);
		if(cmp<0) return rank(x.left, key);
		if(cmp>0) return 1 + size(x.left) + rank(x.right, key);
		else return size(x.left);
	}
	
	public Key select(int k) {
		Node x=select(root, k);
		return x.key;
	}
	
	private Node select(Node x, int k) {
		if(k < 0 || k >= size()) throw new IllegalStateException("Index out of bounds");
		int i=size(x.left);
		if(i > k) return select(x.left, k);
		else if(i < k) return select(x.right, k - i - 1);
		else return x;
	}
	
	public void deleteMin() {
		root=deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if(x==null) return null;
		if(x.left==null) return x.right;
		x.left=deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right); 
		return x;
	}
	
	public void deleteMax() {
		root=deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if(x==null) return null;
		if(x.right==null) return x.left;
		x.right=deleteMax(x.right);
		x.size = 1 + size(x.left)+ size(x.right); 
		return x;
	}
	
	public int size(Key lo, Key hi) {
		if(lo.compareTo(hi) > 0) return 0;
		if(contains(hi)) return rank(hi) - rank(lo) +1;
		else return rank(hi) - rank(lo);
	}
	
	
	public Iterable<Key> keys(Key lo, Key hi){
		LinkedList<Key> q=new LinkedList<>();
		keys(root, q, lo, hi);
		return q;
	}
	
	private void keys(Node n, LinkedList<Key> q, Key lo, Key hi) {
		if (n == null) return;
		int cmpLo = lo.compareTo(n.key);
		int cmpHi = hi.compareTo(n.key);
		if (cmpLo < 0)
			keys(n.left, q, lo, hi);
		if (cmpLo <= 0 && cmpHi >= 0)
			q.add(n.key);
		if (cmpHi > 0)
			keys(n.right, q, lo, hi);
	}
	
	public Iterable<Key> keys(){
		LinkedList<Key> q=new LinkedList<Key>();
		inorder(root, q);
		return q;
	}
	
	
	private void inorder(Node x, LinkedList<Key> q) {
		if(x==null) return;
		inorder(x.left, q);
		q.add(x.key);
		inorder(x.right, q);
	}
}
