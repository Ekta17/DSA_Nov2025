package dsa.topten;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

//Given a string s, find the length of the longest substring without duplicate characters.
public class LongestSubstringWoRepeatingChar {

    public static int lengthOfLongestSubstring(String s) {
        //Use Set and while loop
        // Add to the right, shrink from left till all unique char
        /*int uLen = 0;
        Set<Character> uChar = new HashSet<>();
        int left = 0;
        int right = left;
        while(left < s.length()-1 && right < s.length()){
           while(right < s.length() && uChar.contains(s.charAt(right)) ){
               uChar.remove(s.charAt(left));
               left ++;
           }
           uChar.add(s.charAt(right));
           right++;
           uLen = Math.max(uLen, uChar.size());
        }

        return uLen;*/
        if(s == null || s.isEmpty())
            return 0;
        if(s.length()==1)
            return 1;

        Set<Character> uniqueElementWindow = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        for(int right = 0; right < s.length(); right ++){
            while(uniqueElementWindow.contains(s.charAt(right))){
                uniqueElementWindow.remove(s.charAt(left));
                left++;
            }
            uniqueElementWindow.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }


        return maxLength;
    }

    public static void main(String[] args) {
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }
}
