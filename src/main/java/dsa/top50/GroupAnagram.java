package dsa.top50;

import java.util.*;

public class GroupAnagram {

    public List<List<String>> groupAnagrams(String[] strs) {

        //Thinking if I can make a hashmap with key as length of string
        // and value as arraylist of all strings that are of same length and are anagrams
        //but this wont work as there can be string that are of same length but not anagrams
        //we cannot sort each string as that will be very inefficient and also need time of m*nlogn to sort each string
        //thinking if trie data structure can be used but I dont think so as how will I get the original string and groups of anagram in that
        //cannot use sliding window as there can be possibility that s[0] and s[1] are not anagrams but s[0] and s[n] are

        //one brute force way of doing things can be compare each string with every other string that are of same length

        /*Map<String, List<String>> map = new HashMap<>();
        List<String> arrList = new ArrayList<>();
        arrList.add(strs[0]);
        map.put(strs[0], arrList);

        for(int i = 1; i < strs.length; i++) {
            boolean addedString = false;
            for(Map.Entry<String, List<String>> entry: map.entrySet()){
                if(entry.getKey().length() == strs[i].length() && validAnagram.isAnagram(entry.getKey(), strs[i])){
                    arrList = entry.getValue();
                    arrList.add(strs[i]);
                    map.put(entry.getKey(), arrList);
                    addedString = true;
                }
                if(addedString)
                    break;
            }
            if(!addedString){
                arrList = new ArrayList<>();
                arrList.add(strs[i]);
                map.put(strs[i], arrList);
            }
        }

        // I have the map now but I dont know how to sort it by value length and add to list in that order

        return null;*/

        //watched a youtube video where the guy explained the problem again and the property that when we sort anagrams
        // they become same string and that can now become the key of the hashmap

        Map<String, List<String>> map = new HashMap<>();

        for(String s: strs){
            char[] stringAsArray = s.toCharArray();
            Arrays.sort(stringAsArray);
            String sortedString = String.valueOf(stringAsArray);
            List<String> listOfStrings;

            if(map.containsKey(sortedString)){
                listOfStrings = map.get(sortedString);
            } else {
                listOfStrings = new ArrayList<>();
            }
            listOfStrings.add(s);
            map.put(sortedString, listOfStrings);
        }

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            List<String> groupedAnagram = entry.getValue();
            result.add(groupedAnagram);
        }

        return result;

        //Complexity:
        // n = total number of strings
        // k = length of the string with max length
        //space: O (n * k)
        //time: O (n * klogk)
    }

    public static void main(String[] args) {
        GroupAnagram groupAnagram = new GroupAnagram();
        List<List<String>> result = groupAnagram.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat", "good"});
        System.out.println(result);
    }
}
