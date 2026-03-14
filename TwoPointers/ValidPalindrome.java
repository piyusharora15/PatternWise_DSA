// Problem Link: https://leetcode.com/problems/valid-palindrome

/*

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

*/

// Approach: Two Pointers.
// We use two pointers, one starting from the beginning and the other from the end of the string.
// We move both pointers towards the center, skipping non-alphanumeric characters. 
// If the characters at both pointers do not match (ignoring case), we return false.
// If we successfully compare all characters, we return true.

// Code:
class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null) return true;
        int i=0,j=s.length()-1;
        while(i < j){
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if(i < j){
                char a = Character.toLowerCase(s.charAt(i));
                char b = Character.toLowerCase(s.charAt(j));
                if(a != b) return false;
                i++;
                j--;
            }
        }
        return true;
    }
}

// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(1).