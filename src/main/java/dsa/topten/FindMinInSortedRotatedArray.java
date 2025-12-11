package dsa.topten;

import static org.junit.Assert.assertEquals;

/*
 Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.
 */
public class FindMinInSortedRotatedArray {

    //I am trying to find the pivot element which is also the min element around which rotation has happened
    //To find pivot element I am trying to see if to search left half or right half based on value of mid
    public static int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high){

            int mid = low + (high - low)/2;

            if(nums[mid] > nums[high]) //Always compare with high to decide which way to go next
                low = mid + 1; //As mid is already greater than high, to find min, we need to do mid + 1
            else
                high = mid; // we dont want to skip the step where mid is the lowest element in the left half
        }

        return nums[low];
    }

    public static void main(String[] args) {
        assertEquals(1, findMin(new int[]{3,4,5,1,2}));
        assertEquals(0, findMin(new int[]{4,5,6,7,0,1,2}));
        assertEquals(11, findMin(new int[]{11,13,15,17}));

    }
}
