package dsa.top50;

import static dsa.Utils.printArray;
import static org.junit.Assert.assertArrayEquals;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        //I know I have solved this question before that we need to use prefix and suffix products
        // then multiply prefix and suffix to get the result at i
        //I am thinking to first calculate suffix by iterating the array from right to left
        // then using the loop left to right, keep calculating prefix and then multiply with suffic array

        /*
        int[] suffix = new int[nums.length];
        suffix[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i --){
            suffix[i] = suffix[i+1] * nums [i+1];
        }
        //printArray(suffix);

        int[] prefix = new int[nums.length];
        prefix[0] = 1;
        for(int i = 1; i< nums.length; i++){
            prefix[i] = prefix[i-1] * nums [i-1];
        }
        //printArray(prefix);

        for(int i = 0; i< nums.length; i++){
            nums[i] = prefix[i] * suffix[i];
        }

        return nums;*/

        //Follow-up question to solve in O(1) space
        //I am not sure how to solve this , I know that if we calculate result into suffix array first
        //and then while iterating we calculate prefix and actual result in the same loop that should solve it
        //but I cant remember the solution for it
        int[] result = new int[nums.length];
        //calculate and save suffix
        result[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i --){
            result[i] = result[i+1] * nums [i+1];
        }

        //result at this point is [24, 12, 4, 1]
        //result[0] = result[0]; //just the suffix at this point
        int prefix = 1;
        for(int i = 0; i< nums.length;i++){
            result[i] = prefix * result[i]; //prefix * suffix
            prefix = prefix * nums[i]; //prep for next iteration
        }

        return result;
        //Complexities:
        //time: length of array nums
        //space: O(1)
    }



    public static void main(String[] args) {
        ProductOfArrayExceptSelf p = new ProductOfArrayExceptSelf();
        assertArrayEquals(new int[] {24,12,8,6}, p.productExceptSelf(new int[]{1,2,3,4}));
        assertArrayEquals(new int[] {0,0,9,0,0}, p.productExceptSelf(new int[]{-1,1,0,-3,3}));
    }
}
