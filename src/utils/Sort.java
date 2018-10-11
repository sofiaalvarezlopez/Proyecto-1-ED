//ESAI
package utils;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unchecked")
public class Sort<T>
{	
	public void mergeSort(T [ ] a, Comparator<T> comp)
	{
		T[] tmp = (T[]) new Object[a.length];
		mergeSort(a, tmp,  0,  a.length - 1, comp);
	}


	private void mergeSort(T [ ] a, T[] tmp, int lo, int hi, Comparator<T> comp)
	{
		if( lo < hi )
		{
			int mid = (lo + hi) / 2;
			mergeSort(a, tmp, lo, mid, comp);
			mergeSort(a, tmp, mid + 1, hi, comp);
			merge(a, tmp, lo, mid + 1, hi, comp);
		}
	}

	public void merge(T[ ] a, T[ ] tmp, int lo, int mid, int hi, Comparator<T> comp)
	{
		int leftEnd = mid - 1;
		int k = lo;
		int num = hi - lo + 1;

		while(lo <= leftEnd && mid <= hi)
			if(comp.compare(a[lo], a[mid]) <= 0)
				tmp[k++] = a[lo++];
			else
				tmp[k++] = a[mid++];

		while(lo <= leftEnd)    // Copy rest of first half
			tmp[k++] = a[lo++];

		while(mid <= hi)  // Copy rest of right half
			tmp[k++] = a[mid++];

		// Copy tmp back
		for(int i = 0; i < num; i++, hi--)
			a[hi] = tmp[hi];

	}


	//Quicksort 

	public void quickSort(T [] a, Comparator<T> comp) 
	{
		shuffleArray(a); // Eliminate dependence on input.
		quicksort(a, 0, a.length-1, comp);
	}

	private void quicksort(T [] a, int lo, int hi, Comparator<T> comp) {
		if(lo >= hi) return;
		int pi = partition(a, lo, hi, comp);
		quicksort(a, lo, pi-1, comp);
		quicksort(a, pi+1, hi, comp);
	}

	void shuffleArray(T[] objects)
	{

		Random rnd = ThreadLocalRandom.current();
		for (int i = objects.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			T a = objects[index];
			objects[index] = objects[i];
			objects[i] = a;
		}
	}

	private int partition(T [] a, int lo, int hi, Comparator<T> comp) {
		int i = lo + 1;
		int j = hi;

		while(i <= j) {
			if(comp.compare(a[i], a[lo]) <= 0) { 
				i++; 
			}
			else if(comp.compare(a[j], a[lo])> 0) { 
				j--;
			}
			else if(j < i) {
				break;
			}
			else
				exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}

	private void exchange(T [] a, int i, int j) {
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
