// Problem Link: https://www.geeksforgeeks.org/problems/longest-common-substring1452/1

/*

Companies: Amazon, Microsoft, Morgan Stanley.

Given two strings s1 and s2, find the length of the longest common substring. 
A substring is a sequence of characters that appears contiguously in a string.

Example: 

Input: s1 = "GeeksforGeeks", s2 = "GeeksQuiz" 
Output : 5 
Explanation: The longest common substring is "Geeks" and is of length 5.

Input: s1 = "abcdxyz", s2 = "xyzabcd" 
Output : 4
Explanation: The longest common substring is "abcd" and is of length 4.

Input: s1 = "abc", s2 = "" 
Output : 0


Difference between Subsequence and Substring:

Subsequence -> Characters can be skipped.

Example:  abcde → ace  -> Valid.

Substring -> Characters must be continuous.

Example:  abcde → bcd  -> Valid.

But:  "ace" is NOT a substring.

Key Observation:

If characters mismatch:

In LCS: take max of two possibilities

But in Longest Common Substring: substring breaks immediately

So mismatch means:  dp[i][j]=0

This is the entire difference between LCS and Longest Common Substring.


Naive Approach: 

The idea is to consider every pair of indexes (i, j) and find the longest common substring ending at i in s1 and j in s2. 
In other words, we find the longest common suffix ending at every pair(i, j). 
At the end, we return length of the max of all longest common suffixes. 
To find length of the common suffix ending at i and j, we recursively use the lengths of the longest common suffixes ending at (i-1) and (j-1).

Length of the Longest Common Substring = Max of lengths of all Longest Common Suffixes  

Length of the Longest Common Suffix Ending at (i, j)  = Length of Longest Common Suffix Ending at (i-1, j-1) + 1 if s1[i] == s2[j], else 0   

Suffix -> substring MUST end exactly at i and j

Why Suffix?

Suppose:

s1 = "ABCD"
s2 = "XBCD"

At: i = 3, j = 3

Characters:  D == D
Now the substring can continue backward.

So: f(3,3) = 1 + f(2,2)

Recursive Formula:

If characters match: f(i,j)=1+f(i−1,j−1)
Else: f(i,j)=0

Why 0 on Mismatch?
Because substring continuity breaks immediately.

Example:
ABCD ; ABXD

At: C != X

Continuous substring cannot continue anymore.
So: return 0

The recursion only gives: length of substring ending at (i,j)

But the answer can end anywhere. So we must check ALL pairs.

Important Observation:

We compute: f(i,j) for every i,j

Then take maximum.

Example Dry Run:

Input: s1 = "ABC" ; s2 = "XBC"

Step 1: Check: f(2,2)
Characters: C == C
So: 1 + f(1,1)

Step 2: Check: f(1,1)
Characters: B == B
So: 1 + f(0,0)

Step 3: Check: f(0,0)
Characters: A != X
Return: 0

Backtracking:
Now:
f(1,1) = 1
f(2,2) = 2

Answer: 2
Substring: BC

Time Complexity: O(m * n * min(m, n))  where m and n are lengths of s1 and s2 respectively.
We compute recursively for every pair: (i,j)
Total pairs: m×n
For each pair, recursion can go diagonally up to: min(m,n)
Hence: O(m×n×min(m,n))

Space Complexity:
Maximum recursive depth:
O(min(m,n))
because recursion moves diagonally only.

Code:

class Solution {
    public int longestCommonSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {      // Check all pairs
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, solve(s1, s2, i, j));
            }
        }
        return ans;
    }

    private int solve(String s1, String s2, int i, int j) {
        if (i < 0 || j < 0) {    // Out of bounds
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {  // Characters match
            return 1 + solve(s1, s2, i - 1, j - 1);
        }
        return 0;  // Characters mismatch, substring breaks
    }
}


Better Approach: Dynamic Programming (Tabulation)

In the recursive approach, the same (i,j) states are recomputed multiple times, creating overlapping subproblems.

So we use dynamic programming where dp[i][j] represents the length of the longest common substring ending at   s1[i-1] and s2[j-1].

If the characters match, we extend the current substring chain using the diagonal value:

dp[i][j]=1+dp[i−1][j−1]

If they don't match, continuity breaks, so we reset the value to 0.

Since the longest substring can end anywhere in the table, we maintain a global maximum while filling the DP table.

Dry Run:

s1="ABCD" ; s2="ACDG"

DP Table:

      A  C  D  G
   0  0  0  0  0
A  0  1  0  0  0
B  0  0  0  0  0
C  0  0  1  0  0
D  0  0  0  2  0

Max Value: 2 (Substring: "CD")

Code:

class Solution {
    public int longestCommonSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int ans = 0;
        int[][] dp = new int[n + 1][m + 1]; // DP table

        for (int i = 1; i <= n; i++) {      // Fill DP table
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // Characters match
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]); // Update max length
                } else {
                    dp[i][j] = 0; // Characters mismatch, reset to 0
                }
            }
        }
        return ans;
    }
}

Time Complexity: O(m * n) where m and n are lengths of s1 and s2 respectively. We fill a DP table of size (m+1) x (n+1).

Space Complexity: O(m * n) for the DP table.


Optimal Approach: Dynamic Programming (Space Optimized)

Since each state depends only on the diagonal value from the previous row, we don't need the full DP table.

We can keep two 1D arrays:

prev[] → previous row
curr[] → current row

If characters match, we extend the substring using prev[j-1]; otherwise, we reset the value to 0.

After processing a row, curr becomes the new prev, reducing space complexity from O(m×n) to O(n) while keeping the same transition logic.

Time Complexity: O(m * n) where m and n are lengths of s1 and s2 respectively. We still fill a DP table, but now it's just two rows.

Space Complexity: O(n) for the two 1D arrays.


*/

// Code:

class LongestCommonSubstring {
    public int longestCommonSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int ans = 0;
        int[] prev = new int[m + 1]; // Previous row
        for (int i = 1; i <= n; i++) {      // Fill DP table
            int[] curr = new int[m + 1]; // Current row
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // Characters match
                    curr[j] = 1 + prev[j - 1];
                    ans = Math.max(ans, curr[j]); // Update max length
                } else {
                    curr[j] = 0; // Characters mismatch, reset to 0
                }
            }
            prev = curr; // Update previous row
        }
        return ans;
    }
}