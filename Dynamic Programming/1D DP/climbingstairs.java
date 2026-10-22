/*

Problem Link: https://leetcode.com/problems/climbing-stairs?envType=problem-list-v2&envId=wh8gjvyh

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Approach: Using Dynamic Programming.

1. First we will check if n is less than or equal to 1, if yes then return n.

2. Then we will create an array dp of size n+1 and initialize dp[0] = 1 and dp[1] = 1.

3. Then we will iterate from 2 to n and for each i, we will set dp[i] = dp[i-1] + dp[i-2].

4. Finally, we will return dp[n].

Dry Run:
Input: n = 5
dp[0] = 1   
dp[1] = 1
i = 2: dp[2] = dp[1] + dp[0] = 1 + 1 = 2
i = 3: dp[3] = dp[2] + dp[1] = 2 + 1 = 3
i = 4: dp[4] = dp[3] + dp[2] = 3 + 2 = 5
i = 5: dp[5] = dp[4] + dp[3] = 5 + 3 = 8

Return dp[5] = 8

Time Complexity: O(n), since we are iterating from 2 to n.

Space Complexity: O(n), since we are using an array of size n+1.

*/

// Code:

class ClimbingStairs {
    public int climbStairs(int n) {
        if(n <= 1) return n;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}