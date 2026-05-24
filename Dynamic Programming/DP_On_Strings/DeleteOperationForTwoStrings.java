// Problem Link: https://leetcode.com/problems/delete-operation-for-two-strings?envType=problem-list-v2&envId=wh8gjvyh

/*

Companies: Amazon, Microsoft.

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4



Most Important Insight:

This problem is directly based on: Longest Common Subsequence (LCS)

Because characters common to both strings should NOT be deleted.

Only non-LCS characters must be deleted.

Core Formula:
Suppose:
n = word1.length(), m = word2.length(), L = LCS length

Then:
Delete from word1: n - L

Delete from word2: m - L

Total deletions: (n−L)+(m−L)
Simplified: n+m−2×LCS
This is the MOST IMPORTANT concept.

Why LCS?
Because LCS is:
largest common part that can be kept.
Everything else must be deleted.

Naive Approach: Recursion.

Recursive Thinking:
At every (i,j):
Case 1 — Characters match
Keep them.
Move both pointers.

Case 2 — Characters do not match.
Try deleting:
from word1
from word2
Take minimum.

Recursive Relation:

If match: f(i,j)=f(i+1,j+1)

Else: f(i,j)=1+min(f(i+1,j),f(i,j+1))

Base Cases:

If one string finishes:
Delete all remaining chars of other string.

Code:

class Solution {

    public int minDistance(String word1, String word2) {
        return solve(0, 0, word1, word2);
    }

    private int solve(int i, int j, String s1, String s2) {

        if(i == s1.length()) {      // word1 finished
            return s2.length() - j;
        }

        if(j == s2.length()) {     // word2 finished
            return s1.length() - i;
        }

        if(s1.charAt(i) == s2.charAt(j)) {  // Characters match

            return solve(i + 1, j + 1, s1, s2);
        }

        return 1 + Math.min(solve(i + 1, j, s1, s2), solve(i, j + 1, s1, s2)); // Delete from either string
    }
}

Time Complexity: O(2^(n+m)) in worst case, where n and m are the lengths of word1 and word2 respectively.
This is because in the worst case, we explore all combinations of deletions.

Space Complexity: O(n+m) due to the recursion stack in the worst case when one string is completely different from the other.

Better Approach 1: Memoization (Top-Down DP).

DP State:

dp[i][j] = minimum deletions needed for strings starting at i and j.

Code:

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(0, 0,word1, word2, dp);
    }

    private int solve(int i, int j, String s1, String s2, int[][] dp) {

        if(i == s1.length()) {
            return s2.length() - j;
        }

        if(j == s2.length()) {
            return s1.length() - i;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        if(s1.charAt(i) == s2.charAt(j)) {

            return dp[i][j] = solve(i + 1, j + 1, s1, s2, dp);
        }

        return dp[i][j] = 1 + Math.min(solve(i + 1, j, s1, s2, dp), solve(i, j + 1, s1, s2, dp));
    }
}

Time Complexity: O(n*m) due to memoization, where n and m are the lengths of word1 and word2 respectively.
This is because we compute the result for each pair of indices (i, j) at most once.

Space Complexity: O(n*m) for the dp array + O(n+m) for the recursion stack, resulting in O(n*m) overall.

Better Approach 2: Tabulation (Bottom-Up DP).

DP State : dp[i][j] = minimum deletions needed for first i chars of word1 and first j chars of word2.

Transition:

If match:
dp[i][j]=dp[i−1][j−1]

Else:
1+min(dp[i−1][j],dp[i][j−1])

Base Initialization:

If one string empty:
delete all chars of other string.

Example Dry Run:
word1 = "sea"
word2 = "eat"

DP Table:

        ""  e  a  t
     ""  0  1  2  3
     s   1  2  3  4
     e   2  1  2  3
     a   3  2  1  2

Answer: dp[3][3] = 2.

Code:

class Solution {
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for(int i = 0; i <= n; i++) {  // Base cases
            dp[i][0] = i;
        }

        for(int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for(int i = 1; i <= n; i++) {   // Fill DP table
            for(int j = 1; j <= m; j++) {
            
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {  // Characters match
                    dp[i][j] = dp[i - 1][j - 1];
                }

                else {

                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}

Time Complexity: O(n*m) due to the nested loops, where n and m are the lengths of word1 and word2 respectively.
This is because we fill a dp table of size (n+1) x (m+1).

Space Complexity: O(n*m) for the dp table.


Optimal Approach: LCS Based Solution.

Main Observation:
Instead of directly calculating deletions:
Find: LCS, because LCS characters can stay.
Everything else deleted.

Formula: n+m−2×LCS.

Time Complexity: O(n*m) for computing LCS, where n and m are the lengths of word1 and word2 respectively.
This is because we use a dynamic programming approach to compute the LCS, which involves filling a dp table of size (n+1) x (m+1).

Space Complexity: O(n*m) for the dp table used in LCS computation.

*/


// Code:

class DeleteOperationForTwoStrings {

    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Build LCS table
        for(int i = 1; i <= n; i++) {

            for(int j = 1; j <= m; j++) {

                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                else {

                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcs = dp[n][m];

        return n + m - 2 * lcs;
    }
}