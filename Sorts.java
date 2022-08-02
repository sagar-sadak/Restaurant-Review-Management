import java.util.ArrayList;
import java.util.Comparator;

public class Sorts
{
	// method that will be called by other classes to sort the arraylist
	public static void sort(ArrayList<Restaurant> reviewList, Comparator<Restaurant> xComparator)
	{
		// call the quicksort method
		quicksort(reviewList, 0, reviewList.size() - 1, xComparator);
	}
	
	// method that actually handles the quicksort
	private static void quicksort(ArrayList<Restaurant> reviewList, int startIndex, int endIndex, Comparator<Restaurant> xComparator)
	{
		// check end condition
		if (startIndex >= endIndex)
			return;
		
		//choose the last element as the pivot
		Restaurant pivot = reviewList.get(endIndex);
		// choose the left and right pointers
		int leftPointer = startIndex;
		int rightPointer = endIndex;
		
		// find the smaller and larger objects compared to the pivot in the arraylist
		while (leftPointer < rightPointer)
		{
			while (xComparator.compare(reviewList.get(leftPointer), pivot) <= 0 && leftPointer < rightPointer)
				leftPointer++;
			
			while (xComparator.compare(reviewList.get(rightPointer), pivot) >= 0 && leftPointer < rightPointer)
				rightPointer--;
			
			swap(reviewList, leftPointer, rightPointer);
		}
		
		// swap the left pointer with the pivot
		swap(reviewList, leftPointer, endIndex);
		
		// recursively call the method for the smaller and larger partitions of the arraylist
		quicksort(reviewList, startIndex, leftPointer - 1, xComparator);
		quicksort(reviewList, leftPointer + 1, endIndex, xComparator);
	}
	
	// method to swap two elements in the arraylist
	private static void swap(ArrayList<Restaurant> reviewList, int index1, int index2)
	{
		// create temp variable and swap
		Restaurant temp = reviewList.get(index1);
		reviewList.set(index1, reviewList.get(index2));
		reviewList.set(index2, temp);
	}
}