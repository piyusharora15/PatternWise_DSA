// Problem Link: https://leetcode.com/problems/group-anagrams?envType=problem-list-v2&envId=string

/*

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.

*/

// Approach : Sorting each string and using it as a key in a hashmap to group anagrams together.
// We sort each string and store it in a hashmap where the key is the sorted string and the value is a list of anagrams.

// Code:
import java.util.*;
class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> mpp = new HashMap<>();
        for(String s : strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String str = new String(arr);
            if(!mpp.containsKey(str)){
                mpp.put(str,new ArrayList<>());
            }
            mpp.get(str).add(s);
        }
        return new ArrayList<>(mpp.values());
    }
}

// Time Complexity: O(N * K log K) where N is the number of strings and K is the maximum length of a string.
// Space Complexity: O(N * K) for storing the grouped anagrams in the hashmap.