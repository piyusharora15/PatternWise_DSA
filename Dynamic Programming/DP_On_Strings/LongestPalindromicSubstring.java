// Problem Link: https://leetcode.com/problems/longest-palindromic-substring?envType=problem-list-v2&envId=dynamic-programming

/*

Companies: Amazon, Microsoft, Google, Facebook, Adobe, Apple, Netflix, Uber, Airbnb, LinkedIn, Twitter, Salesforce, Oracle, Intel, IBM, Cisco, PayPal, eBay, Dropbox, Square.

Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"


Naive Approach: Recursion.

Generate all possible substrings of the given string and check if they are palindromes or not. Keep track of the longest palindrome found.

Palindrome Check:
Two pointers: left, right
Move inward.

Dry Run: 
s = "babad"
Substrings: "b", "a", "b", "a", "d", "ba", "ab", "ba", "ad", "bab", "aba", "bad", "baba", "abad", "babad" 
Check for palindrome: "b", "a", "b", "a", "d", "ba", "ab", "ba", "ad", "bab", "aba", "bad", "baba", "abad", "babad"
Result: "bab" or "aba"


Time Complexity: O(n^3) (O(n^2) for generating substrings and O(n) for checking palindrome).
Space Complexity: O(1) (for the longest palindrome string).


Code:

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        String ans = "";
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                if(isPalindrome(sub) && sub.length() > ans.length()) {
                    ans = sub;
                }
            }
        }
        return ans;
    }
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}


Better Approach 1: Dynamic Programming (Memoization).

Create a 2D table to store the results of subproblems.
Define a recursive function that takes the starting and ending indices of the substring.
Check if the substring is a palindrome or not.
If it is a palindrome, then check for the maximum length of the substring.
We can use memoization to store the results of the subproblems and avoid redundant calculations.

DP State: dp[i][j] = whether substring s[i...j] is palindrome or not.

Recursive Relation:
If: s[i] == s[j]
Then: isPal(i,j)=isPal(i+1,j−1)
Else: false

Base Cases:
Single Character: i == j
Always palindrome.

Empty / Crossed: i > j
Also palindrome.


Code:

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        Boolean[][] dp = new Boolean[n][n];
        int start = 0;
        int maxLen = 1;
        
        for(int i = 0; i < n; i++) {    // Check every substring
            for(int j = i; j < n; j++) {
                if(isPalindrome(i, j, s, dp)) {
                    int len = j - i + 1;
                    if(len > maxLen) {
                        maxLen = len;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
    private boolean isPalindrome(int i, int j, String s, Boolean[][] dp) {
        
        if(i >= j) {    // Base cases
            return true;
        }

        if(dp[i][j] != null) {    // Already computed
            return dp[i][j];
        }
    
        if(s.charAt(i) != s.charAt(j)) {    // Characters don't match
            return dp[i][j] = false;
        }

        return dp[i][j] = isPalindrome(i + 1, j - 1, s, dp);
    }
}

Time Complexity: O(n^2) (for checking all substrings and for checking palindrome).
Space Complexity: O(n^2) (for the dp table and for the recursion stack).


Better Approach 2: Dynamic Programming (Tabulation).

Define:
dp[i][j] = true if substring s[i...j] is palindrome

If characters match: s[i] == s[j]
then inside substring must also be palindrome.

Formula:
If: s[i] == s[j]
Then: dp[i][j]=dp[i+1][j−1]

Special Cases:
Length: 1
Always palindrome.

Length: 2
Palindrome if both chars equal.

Since we need:
dp[i+1][j-1]
fill table:
bottom-up diagonally.

Dry Run:
s = "babad"
Table:
    b   a   b   a   d   
b   T   F   T   F   F
a   F   T   F   T   F
b   F   F   T   F   F
a   F   F   F   T   F
d   F   F   F   F   T
Result: "bab" or "aba"

Length: 1 -> "b", "a", "b", "a", "d" ; All are palindrome.
Length: 2 -> "ba", "ab", "ba", "ad" ; None are palindrome.
Length: >=3 -> "bab", "aba", "bad", "baba", "abad", "babad" ; "bab" and "aba" are palindrome.


Code:

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;
    
        for(int i = 0; i < n; i++) {   // Length 1
            dp[i][i] = true;
        }

        for(int i = 0; i < n - 1; i++) {  // Length 2
            if(s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        for(int len = 3; len <= n; len++) {  // Length >= 3
            for(int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLen = len;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }
}

Time Complexity: O(n^2) (for filling the dp table).
Space Complexity: O(n^2) (for the dp table).


Optimal Approach: Expand Around Center.

Core Observation:
Every palindrome expands around a center.

Possible Centers:
For string: babad
Centers can be:
single char
or
between two chars

Because palindrome can be:
odd length
even length

Examples:

Odd Length: aba
Center: b
Even Length: abba
Center: between b and b

Algorithm:

For every index:
Expand:
left, right
while characters equal.
Track maximum length.

Why This Works:
A palindrome grows symmetrically.

Detailed Dry Run:
Input:
s = "babad"
Center at index 1 ('a')
Expand:
left = 1
right = 1
a == a
Expand outward:
b == b
Palindrome: bab

Expand again:
Out of bounds.

Longest = "bab"


Time Complexity:
Each expansion: O(n)
Total centers: 2n
Overall: O(n^2)

Space Complexity: O(1) (for pointers and variables).


*/


// Code:
class LongestPalindromicSubstring {

    private int start = 0;
    private int maxLen = 0;

    public String longestPalindrome(String s) {

        for(int i = 0; i < s.length(); i++) {

            // Odd length
            expand(s, i, i);

            // Even length
            expand(s, i, i + 1);
        }

        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right) {

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        int len = right - left - 1;

        if(len > maxLen) {

            maxLen = len;

            start = left + 1;
        }
    }
}