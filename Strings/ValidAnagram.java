// Problem Link: https://leetcode.com/problems/valid-anagram

/*

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

*/

// Approach 1: Sorting.
// We can sort both strings and compare them. If they are equal after sorting, they are anagrams.

/* Code:
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a,b);
    }
}

*/

// Time Complexity: O(n log n) due to sorting.
// Space Complexity: O(1) if we ignore the space used for sorting.

// Approach 2: Frequency Count.
// We can count the frequency of each character in both strings and compare the counts.

// Code:
class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] count = new int[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for(int c : count){
            if(c != 0) return false;
        }
        return true;
    }
}

// Time Complexity: O(n) where n is the length of the strings.
// Space Complexity: O(1) since the size of the count array is fixed (26 for lowercase letters).