package purpleAmerica;

import java.util.ArrayList;

import acm.util.ErrorException;

public class Utility {
	
	
	public static ArrayList<ElectionData> mergeSort(ArrayList<ElectionData> whole) {
        ArrayList<ElectionData> left = new ArrayList<ElectionData>();
        ArrayList<ElectionData> right = new ArrayList<ElectionData>();
        int center;
 
        if (whole.size() <= 1) {    
            return whole;
        } else {
            center = whole.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                    left.add(whole.get(i));
            }
 
            //copy the right half of whole into the new arraylist.
            for (int i=center; i<whole.size(); i++) {
                    right.add(whole.get(i));
            }
 
            // Sort the left and right halves of the arraylist.
            
            left  = mergeSort(left);
            right = mergeSort(right);
            
 
            // Merge the results back together.
            merge(left, right, whole);
        }
        return whole;
    }
 
    private static void merge(ArrayList<ElectionData> left, ArrayList<ElectionData> right, ArrayList<ElectionData> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
 
        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).getName().compareTo(right.get(rightIndex).getName())) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
 
        ArrayList<ElectionData> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }
 
        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

}
