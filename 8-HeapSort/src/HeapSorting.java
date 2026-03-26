import java.util.Arrays;

public class HeapSorting {
	 
	public static void sort(Comparable[] a) {
	        int N = a.length;
	        heapify(a, N);
	        sortdown(a, N);
	    }

	
	    public static void heapify(Comparable[] a, int N) {
	        for (int i = parent(N - 1); i >= 0; i--) {
	            sink(a, i, N);
	        }
	    }

	    
	    public static void sortdown(Comparable[] a, int N) {
	        for (int i = N - 1; i > 0; i--) {
	            exchange(a, 0, i);       
	            sink(a, 0, i);       
	        }
	    }

	    
	    public static void sink(Comparable[] a, int k, int N) {
	        while (left(k) < N) { 
	            int child = left(k); 
	            if (right(k) < N && less(a, child, right(k))) {
	                child = right(k); 
	            }
	            if (!less(a, k, child)) {
	                break; 
	            }
	            exchange(a, k, child);
	            k = child; 
	        }
	    }
	    
	    
	    public static int left(int k) {
	        return ((2 * k) + 1);
	   }
	    
	    
	     public static int right(int k) {
	        return ((2 * k) + 2);
	    }
	     
	     
	     public static int parent(int k) {
	        return ((k - 1) / 2);
	    }

	     
	    private static boolean less(Comparable[] a, int i, int j) {
	        return a[i].compareTo(a[j]) < 0;
	    }

	    
	    private static void exchange(Comparable[] a, int i, int j) {
	        Comparable temp = a[i];
	        a[i] = a[j];
	        a[j] = temp;
	    }
	    
	   public static void main(String[] args) {
		   Integer[] arr1 = {5, 3, 8, 4, 1, 9, 2};
		    System.out.println("Original: " + Arrays.toString(arr1));
		    sort(arr1);
		    System.out.println("Ordenado: " + Arrays.toString(arr1));
		    System.out.println("------------");

		    Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7};
		    System.out.println("Original (já ordenado): " + Arrays.toString(arr2));
		    sort(arr2);
		    System.out.println("Ordenado: " + Arrays.toString(arr2));
		    System.out.println("------------");

		    Integer[] arr3 = {9, 8, 7, 6, 5, 4, 3};
		    System.out.println("Original (inverso): " + Arrays.toString(arr3));
		    sort(arr3);
		    System.out.println("Ordenado: " + Arrays.toString(arr3));
		    System.out.println("------------");

		    Integer[] arr4 = {4, 1, 3, 4, 2, 4, 1};
		    System.out.println("Original (repetidos): " + Arrays.toString(arr4));
		    sort(arr4);
		    System.out.println("Ordenado: " + Arrays.toString(arr4));
		    System.out.println("------------");

		    Integer[] arr5 = {42};
		    System.out.println("Original (um elemento): " + Arrays.toString(arr5));
		    sort(arr5);
		    System.out.println("Ordenado: " + Arrays.toString(arr5));
		    System.out.println("------------");

		    Integer[] arr6 = {};
		    System.out.println("Original (vazio): " + Arrays.toString(arr6));
		    sort(arr6);
		    System.out.println("Ordenado: " + Arrays.toString(arr6));
		    System.out.println("------------");

		    String[] arr7 = {"banana", "apple", "orange", "grape"};
		    System.out.println("Original (Strings): " + Arrays.toString(arr7));
		    sort(arr7);
		    System.out.println("Ordenado: " + Arrays.toString(arr7));
		    System.out.println("------------");
	   }
	    
	}
