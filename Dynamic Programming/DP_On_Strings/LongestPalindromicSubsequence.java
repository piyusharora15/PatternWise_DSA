// Problem Link: https://leetcode.com/problems/longest-palindromic-subsequence?envType=problem-list-v2&envId=dynamic-programming


/*

Companies: Amazon, Microsoft, Google, Facebook, Adobe, Apple, Netflix, Uber, LinkedIn, Twitter.

Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Naive Approach: Using Recursion.

The idea is to check for every character from the start and end of the string.
If they are equal, we add 2 to the result and move both pointers towards the center.
If they are not equal, we move either the start pointer or the end pointer and take the maximum result.


Code:

class Solution {
    public int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int left, int right) {
        
        if (left > right) {  // Base case: if the pointers cross each other
            return 0;
        }
        
        if (left == right) {  // Base case: if the pointers are at the same character, it's a palindrome of length 1
            return 1;
        }
        
        if (s.charAt(left) == s.charAt(right)) {   // If the characters at both pointers are equal
            return 2 + helper(s, left + 1, right - 1);
        } else {
            return Math.max(helper(s, left + 1, right), helper(s, left, right - 1)); // If the characters are not equal, move either pointer and take the maximum result
        }
    }
}

Time Complexity: O(2^n) where n is the length of the string, as we are exploring all possible subsequences.
Space Complexity: O(n) for the recursion stack in the worst case when the string is a palindrome.


Better Approach : Using Dynamic Programming (Memoization).
The idea is to store the results of subproblems in a 2D array (memoization) to avoid redundant calculations and improve the time complexity.

Code:

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return helper(s, 0, n - 1, memo);
    }

    public int helper(String s, int left, int right, int[][] memo) {
        if (left > right) return 0;
        if (left == right) return 1;
        if (memo[left][right] != -1) return memo[left][right];
        if (s.charAt(left) == s.charAt(right)) {
            return memo[left][right] = 2 + helper(s, left + 1, right - 1, memo);
        }
        return memo[left][right] = Math.max(helper(s, left, right - 1, memo), helper(s, left + 1, right, memo));
    }
}

Time Complexity: O(n^2) where n is the length of the string, as we are storing results for all possible pairs of indices.
Space Complexity: O(n^2) for the memoization array and O(n) for the recursion stack in the worst case when the string is a palindrome.


Optimal Approach: Using Dynamic Programming (Tabulation) (LCS Approach).

The problem asks for the length of the longest subsequence in a string s that reads the same forwards and backwards.

The brilliant insight here is that the Longest Palindromic Subsequence (LPS) of a string s is exactly the same as the Longest Common Subsequence (LCS) of string s and its reverse. Since a palindrome is identical when reversed, any palindromic subsequence found in s will also appear in its reverse, making it a "common" subsequence between the two.

Approach:

1. Reverse the String: Create a second string rev which is the reverse of the input string s.

2. LCS Calculation: Use the standard 2D Dynamic Programming approach to find the LCS between s and rev.

3. DP Table Setup: Initialize dp[n+1][n+1] where n is the length of the string.

4. Transitions:
   - If s[i-1] == rev[j-1], we found a matching character: dp[i][j] = 1 + dp[i-1][j-1].
   
   - If they don't match, we take the maximum from the previous states: dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]).

5. Result: The value in dp[n][n] is our answer.


Time Complexity: O(n^2) where n is the length of the string, due to the nested loops for filling the DP table.

Space Complexity: O(n^2) for the DP table.


*/



// Code:

class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        // Reverse the original string
        String rev = new StringBuilder(s).reverse().toString();
        
        char[] str1 = s.toCharArray();
        char[] str2 = rev.toCharArray();
        int n = str1.length;

        // DP table for LCS
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    // Characters match
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Mismatch: take max of excluding one char from either string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }
}
