/*

Problem Link: https://leetcode.com/problems/valid-anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false


Approach 1: Sorting.

1. First, we check if the lengths of the two strings are different. 
If they are, we can immediately return false since anagrams must have the same length.

2. Next, we convert both strings to character arrays and sort them. 
If the sorted arrays are equal, then the original strings are anagrams of each other.

3. Finally, we return the result of the comparison.


Code:

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


Time Complexity: O(n log n) due to sorting.

Space Complexity: O(1) if we ignore the space used for sorting.


Approach 2: Counting Characters.    

1. First, we check if the lengths of the two strings are different. 
If they are, we can immediately return false since anagrams must have the same length.

2. Next, we create an array of size 26 to count the occurrences of each character in the strings (assuming only lowercase letters).

3. We iterate through both strings simultaneously. 
For each character in the first string, we increment the corresponding count in the array. 
For each character in the second string, we decrement the corresponding count.

4. After processing both strings, we check if all counts in the array are zero. 
If they are, it means that both strings have the same characters with the same frequency, and we return true. 
Otherwise, we return false.


Time Complexity: O(n) where n is the length of the strings.

Space Complexity: O(1) since the size of the count array is fixed (26 for lowercase letters).

*/


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
