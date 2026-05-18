// Problem Link: https://leetcode.com/problems/longest-common-subsequence?envType=problem-list-v2&envId=dynamic-programming

/*

Companies: Amazon, Microsoft, MakeMyTrip, Paytm, Google, Flipkart, Uber.

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.

Naive Approach (Recursion):

The idea is to compare the last characters of s1 and s2. While comparing the strings s1 and s2 two cases arise:

-> Match : Make the recursion call for the remaining strings (strings of lengths m-1 and n-1) and add 1 to result.
-> Do not Match : Make two recursive calls. First for lengths m-1 and n, and second for m and n-1. Take the maximum of two results.
-> Base case : If any of the strings become empty, we return 0.

Dry Run:

For example, consider the input strings s1 = "ABX" and  s2 = "ACX".

LCS("ABX", "ACX") = 1 + LCS("AB", "AC") [Last Characters Match]

LCS("AB", "AC") = max( LCS("A", "AC") , LCS("AB", "A") ) [Last Characters Do Not Match] 

LCS("A", "AC") = max( LCS("", "AC") , LCS("A", "A") ) = max(0, 1 + LCS("", "")) = 1

LCS("AB", "A") = max( LCS("A", "A") , LCS("AB", "") ) = max( 1 + LCS("", ""), 0) = 1

So overall result is 1 + 1 = 2


Code:

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        return lcs(text1, text2, m, n);
    }

    public int lcs(String s1, String s2, int m, int n) {

        if (m == 0 || n == 0) return 0; // Base case: If either string is empty, the length of LCS is 0

        if (s1.charAt(m - 1) == s2.charAt(n - 1))  // If the last characters of both substrings match
            return 1 + lcs(s1, s2, m - 1, n - 1);
        else    // If the last characters do not match; Recur for two cases: 1. Exclude the last character of S1 ; 2. Exclude the last character of S2  ; Take the maximum of these two recursive calls
            return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
    }
}

Time Complexity: O(2^(m+n)) in the worst case, where m and n are the lengths of the two strings. 
This is because in the worst case, we are making two recursive calls for each character of both strings.

Space Complexity: O(m+n) in the worst case, where m and n are the lengths of the two strings. 
This is because in the worst case, the recursion stack can go as deep as the sum of the lengths of both strings.


Better Approach 1: Memoization.

If we use the above recursive approach for strings "AXYT" and "AYZX", we will get a partial recursion tree as shown below. 
Here we can see that the subproblem L("AXY", "AYZ") is being calculated more than once. 
If the total tree is considered there will be several such overlapping subproblems. 
Hence we can optimize it either using memoization or tabulation.

There are two parameters that change in the recursive solution and these parameters go from 0 to m and 0 to n. 
So we create a 2D array of size (m+1) x (n+1).
We initialize this array as -1 to indicate nothing is computed initially.
Now we modify our recursive solution to first do a lookup in this table and if the value is -1, then only make recursive calls. 
This way we avoid re-computations of the same subproblems.

Recursive Tree for "AXYT" and "AYZX":

L("AXYT", "AYZX") -> Skip last character of S1 : L("AXY", "AYZX") ; Skip last character of S2 : L("AXYT", "AYZ")
L("AXY", "AYZX") -> Skip last character of S1 : L("AX", "AYZX") ; Skip last character of S2 : L("AXY", "AYZ")
L("AXYT", "AYZ") -> Skip last character of S1 : L("AXY", "AYZ") ; Skip last character of S2 : L("AXYT", "AY")

We can see that the subproblem L("AXY", "AYZ") is being calculated more than once.
If we complete the tree, we will find that there are several such overlapping subproblems.
We use a 2D array to store the results of these subproblems and avoid redundant calculations.


Code:
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            Arrays.fill(memo[i],-1);
        }
        return lcs(text1,text2,m,n,memo);
    }
    private int lcs(String s1,String s2,int m,int n,int[][] memo){
        if(m == 0 || n == 0) return 0;   // Base case: If either string is empty, the length of LCS is 0
        if(memo[m][n] != -1) return memo[m][n];  // Already exists in the memoization table, return it
        if(s1.charAt(m-1) == s2.charAt(n-1)){
            return memo[m][n] = 1 + lcs(s1,s2,m-1,n-1,memo);
        }
        return memo[m][n] = Math.max(lcs(s1,s2,m,n-1,memo),lcs(s1,s2,m-1,n,memo));
    }
}

Time Complexity: O(m*n) in the worst case, where m and n are the lengths of the two strings.
This is because we are storing the results of subproblems in a 2D array and each subproblem is solved only once.

Space Complexity: O(m*n) in the worst case, where m and n are the lengths of the two strings.
This is because we are using a 2D array to store the results of subproblems and the recursion stack can go as deep as the sum of the lengths of both strings in the worst case.



Better Approach 2: Tabulation (Bottom-Up DP).

There are two parameters that change in the recursive solution and these parameters go from 0 to m and 0 to n.
So we create a 2D dp array of size (m+1) x (n+1).  

We first fill the known entries when m is 0 or n is 0.
Then we fill the remaining entries using the recursive formula.

Recursive formula:

if(s1.charAt(m-1) == s2.charAt(n-1)){
    dp[m][n] = 1 + dp[m-1][n-1];
}
else{
    dp[m][n] = Math.max(dp[m][n-1],dp[m-1][n]);
}

Dry Run:

For example, consider the input strings s1 = "ABX" and  s2 = "ACX".

We create a dp table of size 4 x 4 (since the lengths of the strings are 3 and we add 1 for the base case).
    ""  A  C  X
""  0  0  0  0
A   0  1  1  1  
B   0  1  1  1
X   0  1  1  2


Code:

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n]; // dp[m][n] contains length of LCS for text1[0..m-1] and text2[0..n-1]
    }
}

Time Complexity: O(m*n) in the worst case, where m and n are the lengths of the two strings.
This is because we are filling a 2D dp array of size (m+1) x (n+1) and each entry takes O(1) time to compute.

Space Complexity: O(m*n) in the worst case, where m and n are the lengths of the two strings.
This is because we are using a 2D dp array to store the results of subproblems.


Optimal Approach: Space Optimization.

We are using a 2D dp array to store the results of subproblems, but we can optimize the space by using only two 1D arrays.
We can use two 1D arrays, one for the current row and one for the previous row.
We can fill the current row using the values from the previous row and then update the previous row to be the current row for the next iteration.

Time Complexity: O(m*n) in the worst case, where m and n are the lengths of the two strings.
This is because we are filling a 2D dp array of size (m+1) x (n+1) and each entry takes O(1) time to compute.

Space Complexity: O(n) in the worst case, where n is the length of the shorter string.
This is because we are using two 1D arrays of size (n+1) to store the results of subproblems, where n is the length of the shorter string.


*/

// Code:

class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[] prev = new int[n+1];
        for(int i=1;i<=m;i++){
            int[] curr = new int[n+1];
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    curr[j] = prev[j-1] + 1;
                }
                else{
                    curr[j] = Math.max(prev[j],curr[j-1]);
                }
            }
            prev = curr; // Update previous row to be the current row for the next iteration
        }
        return prev[n]; // prev[n] contains length of LCS for text1[0..m-1] and text2[0..n-1]
    }
}