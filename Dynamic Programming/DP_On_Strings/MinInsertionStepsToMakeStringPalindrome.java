// Problem Link: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome?envType=problem-list-v2&envId=wh8gjvyh

/*

Companies: Amazon, Microsoft, Google, Airtel.

Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
A Palindrome String is one that reads the same backward as well as forward.

Example 1:
Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.

Example 2:
Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:
Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".


Most Important Insight:
This problem is directly connected to:
Longest Palindromic Subsequence (LPS)
Because:
characters already forming palindrome do not need insertion
Only missing characters must be inserted.

Core Formula:
If:
n = string length
LPS = longest palindromic subsequence length

Then:
Answer = n−LPS
This is the MOST IMPORTANT concept.

Why?
Suppose:
s = "abcda"

Longest palindromic subsequence: aca
Length: 3

String length: 5

Missing characters to complete palindrome: 5 - 3 = 2
Exactly the answer.

Another Important Insight:

LPS = LCS(original, reverse(original))

This converts palindrome problem into standard LCS problem.


Naive Approach: Using Recursion.

At every substring: s[i...j]

Case 1 — Characters match.
No insertion needed at ends.
Move inward.

Case 2 — Characters do not match.
We must insert:
matching char near left
OR
matching char near right
Take minimum.

Recursive Relation:

If match:
f(i,j)=f(i+1,j−1)

Else:
1+min(f(i+1,j),f(i,j−1))

Base Cases:
If: i >= j
already palindrome.
Return: 0

Dry Run:

Input:
s = "mbadm"

Step 1: m == m
Move inward:
solve(1,3)

Step 2:
Substring: bad
b != d

Try:
Insert near left
1 + solve(2,3)

Insert near right
1 + solve(1,2)

Minimum eventually becomes: 2

Code:

class Solution {

    public int minInsertions(String s) {
        return solve(0, s.length() - 1, s);
    }

    private int solve(int i, int j, String s) {
        if(i >= j) {   // Already palindrome
            return 0;
        }

        if(s.charAt(i) == s.charAt(j)) {  // Characters match
            return solve(i + 1, j - 1, s);
        }

        return 1 + Math.min(solve(i + 1, j, s), solve(i, j - 1, s));  // Insertions needed
    }
}

Time Complexity: O(2^n) - Exponential, due to overlapping subproblems.
Space Complexity: O(n) - Recursive stack space.


Better Approach 1: Memoization.

DP State:
dp[i][j] = minimum insertions needed to make substring s[i...j] a palindrome

We can store results of subproblems in a 2D array to avoid redundant calculations.

Code:

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(0, n - 1, s, dp);
    }
    private int solve(int i, int j, String s, int[][] dp) {
        if(i >= j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if(s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = solve(i + 1, j - 1, s, dp);
        }
        return dp[i][j] = 1 + Math.min(solve(i + 1, j, s, dp), solve(i, j - 1, s, dp));
    }
}

Time Complexity: O(n^2) - Each subproblem is solved once.
Space Complexity: O(n^2) - DP array + O(n) - Recursive stack space.


Better Approach 2: Tabulation.

We can fill the DP table iteratively based on the length of the substring.
We start with substrings of length 1 (which are already palindromes) and build up to the full string.
We can use the same logic as the recursive approach but in a bottom-up manner.

State Transition:
If s[i] == s[j]:
dp[i][j] = dp[i + 1][j - 1]
Else:
dp[i][j] = 1 + min(dp[i + 1][j], dp[i][j - 1])


dp[i][j] = minimum insertions needed for substring s[i...j]


Code:

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int len = 1; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if(i >= j) {
                    dp[i][j] = 0; // Single character or empty substring
                } else if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}

Time Complexity: O(n^2) - Filling the DP table.
Space Complexity: O(n^2) - DP table.


Optimal Approach: Using LPS (Longest Palindromic Subsequence).

Main Observation:

We need: largest palindromic part already present.
That is: Longest Palindromic Subsequence.

Key Formula: 
Answer=n−LPS

How To Find LPS?
Reverse string.
Then compute:
LCS(original, reversed)

Why?

Common subsequence between string and reverse:
must read same forward and backward
Hence palindrome.

Example
s = "mbadm"
reverse = "mdabm"

LCS: mam
Length: 3

Answer: 5 - 3 = 2
Correct.

Time Complexity: O(n^2) - LCS computation.
Space Complexity: O(n^2) - DP table for LCS.
 

*/

// Code:

class MinInsertionStepsToMakeStringPalindrome {

    public int minInsertions(String s) {

        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        // Build LCS table
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lps = dp[n][n];
        return n - lps;
    }
}