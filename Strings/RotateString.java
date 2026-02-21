// Problem Link: https://leetcode.com/problems/rotate-string

// Approach: Concatenating and matching.
// The key insight is that when you concatenate a string with itself (s + s), it contains all possible rotations of the original string.

// For example, if s = "abcde", then s + s = "abcdeabcde" contains all rotations: "abcde", "bcdea", "cdeab", "deabc", "eabcd".

// First, check if lengths are equal (if not, rotation is impossible)
// Create a concatenated string s + s which contains all possible rotations
// Check if goal is a substring of s + s
// s = "abcde", goal = "cdeab"
// s + s = "abcdeabcde"
// "cdeab" is present in "abcdeabcde", so return True

// Code:
class Solution {
    public boolean rotateString(String s, String goal) {
        // Check if lengths are equal
        if (s.length() != goal.length()) {
            return false;
        }
        
        // Concatenate s with itself
        String concatenated = s + s;
        
        // Check if goal is a substring of concatenated
        return concatenated.contains(goal);
    }
}

// Time Complexity: O(n) where n is the length of the string s (or goal). The concatenation takes O(n) time, and checking for substring also takes O(n) time in the worst case.
// Space Complexity: O(n) due to the concatenated string which is of length 2n.