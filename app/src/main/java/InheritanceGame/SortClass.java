package InheritanceGame;

public class SortClass {
		int search (int[] a, int searchValue) {
			int left = 0;
			int right = a.length - 1;
			while (left <= right) {
				int midpoint = (left + right) / 2;
				if (a[midpoint] == searchValue)
					return midpoint;
				else if (a[midpoint] < searchValue)
					left = midpoint + 1;
				else 
					right = midpoint -1;
			}
			return -1;
		}
		
		void selectionSort(int[] a) {
			for (int i = 0; i < a.length -1; i++) {
				int minIndex = findMinimum(a, i);
				if (minIndex != i)
					swap(a, i, minIndex);
			}
		}
		
		void selectionSort(Object[] a) {
			for (int i = 0; i < a.length -1; i++) {
				int minIndex = findMinimum(a, i);
				if (minIndex != i)
					swap(a, i, minIndex);
			}
		}
		
		
		void bubbleSort(int[] a) {
			int k = 0;
			boolean exchangeMade = true;
			while((k < a.length - 1) && exchangeMade) {
				exchangeMade = false;
				k++;
				for(int j = 0; j < a.length - k; j++)
					if(a[j] > a[j + 1]) {
						swap(a, j, j + 1);
						exchangeMade = true;
					}
			}
		}
		
		void bubbleSort(Object[] a) {
			int k = 0;
			boolean exchangeMade = true;
			while((k < a.length - 1) && exchangeMade) {
				exchangeMade = false;
				k++;
				for(int j = 0; j < a.length - k; j++)
					if(((Comparable)a[j]).compareTo(a[j + 1]) > 0 ) {
						swap(a, j, j + 1);
						exchangeMade = true;
					}
				}
			}
		
		
		void insertionSort(int[] a) {
			int itemToInsert, j;
			boolean stillLooking;
			
			for(int k = 1; k < a.length; k++) {
				itemToInsert = a[k];
				j = k - 1;
				stillLooking = true;
				
				while((j >= 0) && stillLooking)
					if(itemToInsert < a[j]) {
						a[j+1] = a[j];
						j--;
					}else
						stillLooking = false;
				
					a[j + 1] = itemToInsert;
			}
		} 
		
		void insertionSort(Object[] a) {
			int itemToInsert, j;
			boolean stillLooking;
			
			for(int k = 1; k < a.length; k++) {
				itemToInsert = (int) a[k];
				j = k - 1;
				stillLooking = true;
				
				while((j >= 0) && stillLooking)
					if(itemToInsert < (int) a[j]) {
						a[j+1] = a[j];
						j--;
					}else
						stillLooking = false;
				
					a[j + 1] = itemToInsert;
			}
		} 
		


		int findMinimum(int[] a, int first) {
			int minIndex = first;
			for (int i = first + 1; i < a.length; i++)
				if (a[i] < a[minIndex])
					minIndex = i;
			
			return minIndex;
		}
		
		int findMinimum(Object[] a, int first) {
			int minIndex = first;
			for (int i = first + 1; i < a.length; i++)
				if (((Comparable)a[i]).compareTo(a[minIndex]) < 0)
					minIndex = i;
			
			return minIndex;
		}
		
		void swap(int[] a, int x, int y) {
			int temp = a[x];
			a[x] = a[y];
			a[y] = temp;
		}
		
		void swap(Object[] a, int x, int y) {
			Object temp = a[x];
			a[x] = a[y];
			a[y] = temp;
		}
		
		
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
		
		
		
		
		

	



///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////
///////////////////////////


	

}
