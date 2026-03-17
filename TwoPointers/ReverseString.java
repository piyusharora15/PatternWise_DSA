// Problem Link: https://leetcode.com/problems/reverse-string?envType=problem-list-v2&envId=string

/*

Write a function that reverses a string. The input string is given as an array of characters s.
You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

*/

// Approach : Two Pointer In-Place Swap.
// Place left at 0 and right at n-1. Swap s[left] and s[right], then left++, right--, repeat until left >= right. In-place, constant extra memory.

// Code:
class ReverseString {
    public void reverseString(char[] s) {
        int left=0,right=s.length-1;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)