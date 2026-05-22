// Problem Link: https://leetcode.com/problems/shortest-common-supersequence?envType=problem-list-v2&envId=wh8gjvyh

/*

Companies: Microsoft, Amazon, Google.

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"


Naive Approach: Recursion.

Core Formula:

Length of shortest common supersequence: ∣SCS∣=n+m−LCS

Why?
Because LCS characters are shared by both strings.
Without subtracting, they get counted twice.

At every step:

Case 1 — Characters match.
Include once and move both pointers.

Case 2 — Characters don't match
Try:
taking character from str1
taking character from str2

Return shorter result.

Recursive Logic:

If match:  SCS(i,j)=str1[i]+SCS(i+1,j+1)

Else:
Choose minimum length:  min(str1[i]+SCS(i+1,j), str2[j]+SCS(i,j+1))

Base Cases:

If one string finishes:
append remaining other string.

Dry Run:

str1: "abac"
str2: "cab"

SCS(0,0): 'a' vs 'c' => no match
Try:
1. Take 'a' from str1: 'a' + SCS(1,0)
2. Take 'c' from str2: 'c' + SCS(0,1)
SCS(1,0): 'b' vs 'c' => no match
Try:
1. Take 'b' from str1: 'b' + SCS(2,0)
2. Take 'c' from str2: 'c' + SCS(1,1)
SCS(2,0): 'a' vs 'c' => no match
Try:
1. Take 'a' from str1: 'a' + SCS(3,0)
2. Take 'c' from str2: 'c' + SCS(2,1)
SCS(3,0): 'c' vs 'c' => match
SCS(3,0) = 'c' + SCS(4,1)
SCS(4,1): str1 finished, append remaining str2: 'ab'
SCS(3,0) = 'c' + 'ab' = 'cab'
SCS(2,0) = 'a' + 'cab' = 'acab'
SCS(1,0) = 'b' + 'acab' = 'bacab'
SCS(0,0) = 'a' + 'bacab' = 'abacab'
SCS(0,1): 'a' vs 'a' => match
SCS(0,1) = 'a' + SCS(1,2)
SCS(1,2): 'b' vs 'b' => match
SCS(1,2) = 'b' + SCS(2,3)
SCS(2,3): 'a' vs ' ' => no match
Try:
1. Take 'a' from str1: 'a' + SCS(3,3)
2. Take ' ' from str2: ' ' + SCS(2,4)
SCS(3,3): 'c' vs ' ' => no match
Try:
1. Take 'c' from str1: 'c' + SCS(4,3)
2. Take ' ' from str2: ' ' + SCS(3,4)
SCS(4,3): str1 finished, append remaining str2: 'b'
SCS(3,3) = 'c' + 'b' = 'cb'
SCS(2,3) = 'a' + 'cb' = 'acb'
SCS(1,2) = 'b' + 'acb' = 'bacb'
SCS(0,1) = 'a' + 'bacb' = 'abacb'
SCS(0,0) = min('abacab', 'abacb') = 'abacb' (length 5)

Time Complexity: O(2^(n+m)) in worst case due to branching at each step.
Space Complexity: O(n+m) for recursion stack in worst case.

Code:

class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        return solve(0, 0, str1, str2);
    }

    private String solve(int i, int j, String s1, String s2) {

        if (i == s1.length()) {
            return s2.substring(j);
        }

        if (j == s2.length()) {
            return s1.substring(i);
        }

        if (s1.charAt(i) == s2.charAt(j)) {

            return s1.charAt(i) + solve(i + 1, j + 1, s1, s2);
        }

        String takeS1 = s1.charAt(i) + solve(i + 1, j, s1, s2);

        String takeS2 = s2.charAt(j) + solve(i, j + 1, s1, s2);

        return takeS1.length() <= takeS2.length() ? takeS1 : takeS2;
    }
}

Better Approach 1: Dynamic Programming (Memoization)

We can optimize the recursive approach by storing results of subproblems in a 2D array (memoization). 
This way, we avoid redundant calculations and significantly reduce the time complexity.

Code:

class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        String[][] memo = new String[str1.length()][str2.length()];
        return solve(0, 0, str1, str2, memo);
    }

    private String solve(int i, int j, String s1, String s2, String[][] memo) {

        if (i == s1.length()) {
            return s2.substring(j);
        }

        if (j == s2.length()) {
            return s1.substring(i);
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = s1.charAt(i) + solve(i + 1, j + 1, s1, s2, memo);
            return memo[i][j];
        }

        String takeS1 = s1.charAt(i) + solve(i + 1, j, s1, s2, memo);
        String takeS2 = s2.charAt(j) + solve(i, j + 1, s1, s2, memo);

        memo[i][j] = takeS1.length() <= takeS2.length() ? takeS1 : takeS2;
        return memo[i][j];
    }
}

Time Complexity: O(n*m) due to memoization.
Space Complexity: O(n*m) for the memoization table and O(n+m) for the recursion stack in worst case.

Better Approach 2: Dynamic Programming (Tabulation)

We can also solve this problem using a bottom-up approach (tabulation).
We can create a 2D array where dp[i][j] represents the shortest common supersequence of the substrings str1[0...i-1] and str2[0...j-1].
We can fill this table iteratively based on the same logic as the recursive approach.
We can then return the value at dp[str1.length()][str2.length()] as the final answer.

Code:

class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        String[][] dp = new String[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = str2.substring(0, j);
                } else if (j == 0) {
                    dp[i][j] = str1.substring(0, i);
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    String takeS1 = dp[i - 1][j] + str1.charAt(i - 1);
                    String takeS2 = dp[i][j - 1] + str2.charAt(j - 1);
                    dp[i][j] = takeS1.length() <= takeS2.length() ? takeS1 : takeS2;
                }
            }
        }

        return dp[n][m];
    }
}

If we have to return the length of the shortest common supersequence instead of the string itself, we can modify the code to store lengths in the dp table instead of strings.

static int minSuperSeq(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)   // If s2 is empty, take all of s1
            dp[i][0] = i;

        for (int j = 0; j <= n; j++)   // If s1 is empty, take all of s2
            dp[0][j] = j;

        for (int i = 1; i <= m; i++) {       // Fill the dp table iteratively
            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))   // If last chars match, include once
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);  // If not match, take min of both options
            }
        }

        return dp[m][n];
    }



Time Complexity: O(n*m) due to filling the dp table.
Space Complexity: O(n*m) for the dp table.


Optimal Approach: Using LCS (Longest Common Subsequence).

Instead of directly building SCS: First find LCS.

Why?
Because: Common characters should appear only once. Then merge both strings around LCS.

Step 1 — Build LCS DP Table

We use standard LCS DP.
DP State:
dp[i][j] = length of LCS of first i chars of str1 and first j chars of str2

Transition:

If characters match: dp[i][j]=1+dp[i−1][j−1]

Else: dp[i][j]=max(dp[i−1][j],dp[i][j−1])

Step 2 — Backtrack to Build SCS.

If characters match. Add once. Move diagonally.

Else:
Move toward larger DP value.
If moving up: Add char from str1.
If moving left: Add char from str2.

Dry Run:
str1: "abac"
str2: "cab"

LCS DP Table:
   '' c a b
'' 0 0 0 0
a  0 0 1 1
b  0 0 1 2
a  0 0 1 2
c  0 1 1 2

LCS is "ab" (length 2).

Backtracking:

Start from: i=4, j=3
Step 1: 'c' vs 'b' => no match
Compare:
dp[3][3]=2
dp[4][2]=1
Take from str1: add 'c'
Move up.

Step 2: 'a' != 'b'

Compare:
dp[2][3]=2
dp[3][2]=1

Take from str1: add 'a'

Step 3: b == b
Add once: 'b'
Move diagonal.

Step 4: a == a
Add once.

Step 5:
Remaining char: 'c'

Reverse Result:
Constructed backwards:
cabac -> Answer.


Time Complexity: O(n*m) for LCS DP + O(n+m) for backtracking.
Space Complexity: O(n*m) for LCS DP table + O(n+m) for result string.


*/

// Code:

class ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Build LCS table
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        int i = n;
        int j = m;

        // Backtracking
        while (i > 0 && j > 0) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                ans.append(str1.charAt(i - 1));
                i--;
                j--;
            }
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans.append(str1.charAt(i - 1));
                i--;
            }
            else {
                ans.append(str2.charAt(j - 1));
                j--;
            }
        }

        // Remaining chars
        while (i > 0) {
            ans.append(str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            ans.append(str2.charAt(j - 1));
            j--;
        }

        return ans.reverse().toString();
    }
}