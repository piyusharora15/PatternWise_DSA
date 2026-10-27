/*

Problem Link: https://leetcode.com/problems/decode-ways?envType=problem-list-v2&envId=wh8gjvyh

You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
Input: s = "12"
Output: 2
Explanation:
"12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: s = "226"
Output: 3
Explanation:
"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:
Input: s = "06"
Output: 0
Explanation:
"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.


Understand the problem:

Letters map to numbers:
A → 1, B → 2, C → 3, ... Z → 26

You're given a string s containing digits. Return the number of valid ways to decode it into letters.

For example, s = "12" has two ways:
- "1" + "2" → A + B → "AB"
- "12" → L
So the answer is 2.

Another example:
s = "226"

Valid decodings:
- 2 | 2 | 6 → BBF
- 22 | 6 → VF
- 2 | 26 → BZ
Answer: 3.

Important rule about zero: 0 cannot be decoded by itself. It can only be part of 10 or 20. For example, "06" has zero valid decodings.

Approach 1: Brute-force recursion.

Intuition:

At every index, we have up to two choices:

1. Decode one digit, if it is between 1 and 9.
2. Decode two digits together, if they form a number between 10 and 26.

Let's define:
solve(i) = number of ways to decode the substring starting at index i.

The recurrence is:

solve(i) = solve(i+1) + solve(i+2)

But we add each term only when its corresponding one-digit or two-digit choice is valid.

Dry Run:

s = "226"

Initially, we call:
solve(0) → substring "226"

At index 0, the digit is 2.
- Take one digit: 2 → call solve(1) for "26".
- Take two digits: 22 → call solve(2) for "6".

Let's evaluate the branches.

solve(0): "226"
Choose 2 or 22

Take 2
solve(1)
"26"
1 + 1 = 2

Take 22
solve(2)

"6"
1 way


For solve(1), the remaining substring is "26":
- Take 2, then decode 6 → BF.
- Take 26 together → Z.
Thus, solve(1) = 2.

For solve(2), the remaining substring is "6", which has one valid decoding.

Therefore: solve(0) = 2 + 1 = 3

Code:

class Solution {
    public int numDecodings(String s) {
        return solve(s, 0);
    }

    private int solve(String s, int i) {
        if (i == s.length()) {
            return 1;
        }

        if (s.charAt(i) == '0') {
            return 0;
        }

        int ways = solve(s, i + 1);  // Decode one digit.

        if (i + 1 < s.length()) {  // Decode two digits if valid.
            int num = Integer.parseInt(s.substring(i, i + 2));

            if (num >= 10 && num <= 26) {
                ways += solve(s, i + 2);
            }
        }

        return ways;
    }
}

Complexity:

- Time: O(2^n) in the worst case, because many combinations are explored repeatedly.

- Auxiliary space: O(n) for the recursion stack.

Approach 2: Recursion + Memoization (Top-Down DP).

Intuition:

In brute-force recursion, we may calculate solve(i) multiple times. 
But the answer for a particular index is always the same.

So we'll store each result in a memo array.
- memo[i] = number of ways to decode s.substring(i).
- If memo[i] is already calculated, return it.
- Otherwise, calculate the one-digit and two-digit choices, save the answer, and return it.

The recurrence stays the same; we simply avoid recomputing states.

Dry Run:

s = "226"

Initial state:
memo = [-1, -1, -1]

Step 1 — solve(0)
The first digit is 2, so we can take one digit or two digits.
ways = solve(1) + solve(2)

We calculate solve(1) first.

Step 2 — solve(1)

The remaining substring is "26".
- Take 2: solve(2) gives 1.
- Take 26: solve(3) gives 1, because reaching the end means one valid completion.
So:
memo[1] = 1 + 1 = 2

Step 3 — Return to solve(0)

Now solve(2) is calculated:
- The substring is "6".
- Take 6: solve(3) = 1.
- There is no second digit to combine with.
So:
memo[2] = 1

Finally:
memo[0] = memo[1] + memo[2]
         = 2 + 1
         = 3

Answer: 3.

Code:

import java.util.Arrays;

class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        return solve(s, 0, memo);
    }

    private int solve(String s, int i, int[] memo) {
        if (i == s.length()) {
            return 1;
        }

        if (s.charAt(i) == '0') {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int ways = solve(s, i + 1, memo);

        if (i + 1 < s.length()) {
            int num = (s.charAt(i) - '0') * 10
                    + (s.charAt(i + 1) - '0');

            if (num >= 10 && num <= 26) {
                ways += solve(s, i + 2, memo);
            }
        }

        memo[i] = ways;
        return ways;
    }
}

Complexity:

- Time: O(n), because each index is computed at most once.

- Auxiliary space: O(n)for the memo array and recursion stack.

Approach 3: Bottom-Up Dynamic Programming (DP Array).

Step 1: Define the DP state.

Let: dp[i] = number of ways to decode the first i digits.

Notice that dp[i] represents the first i characters, not the character at index i.

For a string of length n, our array has length n + 1.

Step 2: Initialize the DP array.

Step 2: Initialize the base cases
dp[0] = 1;


Why is dp[0] = 1? There is exactly one way to decode an empty prefix: choose nothing. 
This acts as the base case when a valid two-digit code starts the string.

For the first digit:
- If it is nonzero, dp[1] = 1.
- If it is zero, dp[1] = 0.

Step 3: Derive the recurrence.

For each position i from 2 through n, consider two possibilities.

Choice A: Decode the current digit alone.

If s.charAt(i - 1) != '0', this digit can be decoded independently.

dp[i] += dp[i-1]

Choice B: Decode the current digit together with the previous digit.

Let the two-digit number be formed from s.charAt(i - 2) and s.charAt(i - 1).

If this number lies between 10 and 26:

dp[i] += dp[i-2]

If neither choice is valid, dp[i] remains 0.

Dry Run:

Input: s = "226"
n = 3

Initialize:
dp[0] = 1
dp[1] = 1

dp = [1, 1, 0, 0]

Iteration 1 — i = 2
The prefix is "22"

Current digit: 2

Since 2 != 0, we can decode it alone:
dp[2] += dp[1]
dp[2] = 1

Now examine the last two digits: "22".

Since 22 is between 10 and 26:
dp[2] += dp[0]
dp[2] = 1 + 1 = 2

DP array:
[1, 1, 2, 0]

Meaning: "22" has two decodings: BB and V.

Iteration 2 — i = 3
The prefix is "226"

Current digit: 6

Since 6 != 0, decode it alone:
dp[3] += dp[2]
dp[3] = 2

Now examine the last two digits: "26".

Since 26 is a valid code:
dp[3] += dp[1]
dp[3] = 2 + 1 = 3

Final DP array:
[1, 1, 2, 3]

So: dp[3] = 3

The three decodings are BBF, VF, and BZ.

Time Complexity: O(n), where n is the length of the string s. We iterate through the string once.

Space Complexity: O(n), for the dp array of size n + 1.

*/

// Code:

class DecodeWays {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;

        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }

        for (int i = 2; i <= n; i++) {

            if (s.charAt(i - 1) != '0') {  // Choice 1: Decode the current digit alone.
                dp[i] += dp[i - 1];
            }

            int twoDigit = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0'); // Choice 2: Decode the last two digits together.

            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}