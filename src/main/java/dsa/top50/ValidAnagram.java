package dsa.top50;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        /* To be a valid anagram following should be true:
            1. both strings should be same length
            2. each character should occur same number of times
         */

        if(s.length() != t.length())
            return false;

        Map<Character, Integer> freq = new HashMap<>();
        for(char c: s.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for(char c: t.toCharArray()){
            int count = freq.getOrDefault(c, 0);
            if(count == 0)
                return false;
            freq.put(c, count - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        assertTrue(validAnagram.isAnagram("anagram", "nagaram"));
        assertFalse(validAnagram.isAnagram("rat", "cat"));
        assertFalse(validAnagram.isAnagram("rat", "ct"));
        assertFalse(validAnagram.isAnagram("ct", "cat"));
        assertTrue(validAnagram.isAnagram("act", "cat"));
    }
}
