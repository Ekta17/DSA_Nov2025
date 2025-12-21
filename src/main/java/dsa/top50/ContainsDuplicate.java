package dsa.top50;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> unique = new HashSet<>();

        for(int i: nums){
            if(unique.contains(i))
                return true;
            else
                unique.add(i);
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        assertTrue(containsDuplicate.containsDuplicate(new int[]{1,2,3,1}));
        assertTrue(containsDuplicate.containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
        assertFalse(containsDuplicate.containsDuplicate(new int[]{1,2,3,4}));
    }
}
