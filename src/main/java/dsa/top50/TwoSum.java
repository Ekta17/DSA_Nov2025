package dsa.top50;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i<nums.length; i++) {
            int num2 = target - nums[i];
            if(map.containsKey(num2)){
                result[0] = map.get(num2);
                result[1] = i;
                break;
            }else{
                map.put(nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        assertArrayEquals(new int[]{0,1}, twoSum.twoSum(new int[]{2,7,11,15}, 9));
        assertArrayEquals(new int[]{1,2}, twoSum.twoSum(new int[]{3,2,4}, 6));
        assertArrayEquals(new int[]{0,1}, twoSum.twoSum(new int[]{3,3}, 6));
    }
}
