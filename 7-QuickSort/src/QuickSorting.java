import java.util.Arrays;

public class QuickSorting {

	public static void sort(Comparable[] a) {
		shuffle(a);
		sortSubarray(a, 0, a.length - 1);
	}

	public static void sortSubarray(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		if (hi -lo <= 9) {
			insertionSort(a, lo, hi);
			return;
		} 
			int m = medianOfThree(a, lo, hi);
			exchange(a, lo, m);

			int j = partition(a, lo, hi);
				sortSubarray(a, lo, j - 1);
				sortSubarray(a, j + 1, hi);
		}

	public static void insertionSort(Comparable[] a, int lo, int hi) {
		if (lo < 0 || hi >= a.length) {
			throw new IllegalArgumentException("index out of bounds");
		}
		for (int i = lo + 1; i <= hi; i++) {
			for (int j = i; j > lo && lessOrEqual(a[j], a[j - 1]); j--) {
				exchange(a, j, j - 1);
			}
		}
	}

	public static int partition(Comparable[] a, int lo, int hi) {
		int i= lo;
		int j= hi;
		while (true){
              while(lessOrEqual(a[++i], a[lo]))			
		if(i == hi)
			break;
              while(lessOrEqual(a[lo], a[--j]))
            	  if(j==lo)
            		  break;
            	  if(i>=j)
				break;
            	  exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}

	public static int medianOfThree(Comparable[] a, int lo, int hi) {
		int totalnumbers=hi - lo;
		if(lo < 0 || hi >= a.length|| lo > hi || totalnumbers + 1 < 3) {
			throw new IllegalArgumentException("Índices inválidos");
		}
		int mid=lo + (hi - lo) / 2;
		
		if(hi-lo == 1) {
			return lessOrEqual(a[lo], a[hi]) ? lo : hi;
		}
		
		if(lessOrEqual(a[mid], a[lo]) && lessOrEqual(a[lo], a[hi]))
		return lo;
		if(lessOrEqual(a[hi], a[lo]) && lessOrEqual(a[lo], a[mid]))
		return lo;
		if(lessOrEqual(a[lo], a[mid]) && lessOrEqual(a[mid], a[hi]))
		return mid;
		if(lessOrEqual(a[hi], a[mid]) && lessOrEqual(a[mid], a[lo]))
		return mid;
		
		return hi;
	}

	public static boolean lessOrEqual(Comparable a, Comparable b) {
		return a.compareTo(b) <= 0;
	}

	public static void exchange(Comparable[] a, int i, int j) {
		Comparable troca = a[i];
		a[i] = a[j];
		a[j] = troca;
	}

	public static void shuffle(Comparable[] a) {
		for (int i = a.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			exchange(a, i, j);
		}
	}

	
	public static void main(String args[]) {
	    Integer[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	    Integer[] reverseSorted = {9, 8, 7, 6, 5, 4, 3, 2, 1};
	    Integer[] repeated = {5, 1, 5, 3, 5, 2, 5, 4, 5};
	    Integer[] single = {42};
	    Integer[] empty = {};
	    Integer[] mixed = {-10, 100, 0, -50, 25, 75, -5, 10};
	    Integer[] ultimate = {55435, 3455, 35435, 534534, 6767, 6978, 45, 789, 3, 6, 542, 88, 987, 765, 8, 22, 7, 3, 11, 53, 667, 8999}; 
	    Integer[] shuffle= {1, 4, 7, 8, 9, 5, 6};
	    sort(sorted);
	    sort(reverseSorted);
	    sort(repeated);
	    sort(single);
	    sort(empty);
	    sort(mixed);
	    sort(ultimate);
	    System.out.println("Sorted: " + Arrays.toString(sorted));
	    System.out.println("Reverse Sorted: " + Arrays.toString(reverseSorted));
	    System.out.println("Repeated: " + Arrays.toString(repeated));
	    System.out.println("Single: " + Arrays.toString(single));
	    System.out.println("Empty: " + Arrays.toString(empty));
	    System.out.println("Mixed: " + Arrays.toString(mixed));
	    System.out.println("Ultimate: " + Arrays.toString(ultimate));
	    
	    shuffle(shuffle);
	    System.out.println("\nShuffle: " + Arrays.toString(shuffle));
	    
	
	    Integer[] testMedian = {10, 50, 30, 20, 40};
	    int lo = 0, hi = testMedian.length - 1;
	    int medianIndex = medianOfThree(testMedian, lo, hi);
	    System.out.println("\nTestando medianOfThree:");
	    System.out.println("Array: " + Arrays.toString(testMedian));
	    System.out.println("Índice da mediana: " + medianIndex);
	    System.out.println("Valor da mediana: " + testMedian[medianIndex]);
	    
	    Integer[] testPartition = {4, 3, 4, 6, 7, 7, 9, 8, 5};
	    int partitionIndex = partition(testPartition, 0, testPartition.length - 1);
	    System.out.println("\nTestando partition:");
	    System.out.println("Array após partição: " + Arrays.toString(testPartition));
	    System.out.println("Índice do pivô final: " + partitionIndex);
	    
	    Integer[] testInsertion = {9, 7, 5, 3, 1, 2, 4, 6, 8};
	    insertionSort(testInsertion, 0, testInsertion.length - 1);
	    System.out.println("\nTestando insertionSort:");
	    System.out.println("Array ordenado: " + Arrays.toString(testInsertion));
	   
	    Integer[] testSortSubarray = {10, 3, 5, 8, 2, 6, 9, 1, 7, 4};
	    sortSubarray(testSortSubarray, 2, 7);
	    System.out.println("\nTestando sortSubarray:");
	    System.out.println("Array após ordenar subarray (índices 2 a 7): " + Arrays.toString(testSortSubarray));
	    
	    Integer[] testExchange = {10, 20, 30};
	    exchange(testExchange, 0, 2);
	    System.out.println("\nTestando exchange:");
	    System.out.println("Array após troca: " + Arrays.toString(testExchange));
	    
	    System.out.println("\nTestando lessOrEqual:");
	    System.out.println("lessOrEqual(5, 10): " + lessOrEqual(5, 10));
	    System.out.println("lessOrEqual(10, 5): " + lessOrEqual(10, 5));
	    System.out.println("lessOrEqual(7, 7): " + lessOrEqual(7, 7));
	}
}
