/*

Problem Link: https://leetcode.com/problems/min-cost-climbing-stairs?envType=problem-list-v2&envId=wh8gjvyh

You are given an integer array cost where cost[i] is the cost of ith step on a staircase.

Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the staircase, which is the position just past the last step (index cost.length).

Example 1:
Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.

Example 2:
Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.

Approach: Using Dynamic Programming.

1. First, we will check if the length of the cost array is less than or equal to 1. If it is, we will return 0 as there are no steps to climb.

2. We will create a dp array of size cost.length + 1 to store the minimum cost to reach each step.

3. We will initialize the first two elements of the dp array to 0, as we can start from either step 0 or step 1 without any cost.

4. We will iterate through the cost array starting from index 2 to cost.length, and for each step, we will calculate the minimum cost to reach that step by taking the minimum of the cost to reach the previous step plus the cost of that step, or the cost to reach two steps back plus the cost of that step.

5. Finally, we will return the value at dp[cost.length], which represents the minimum cost to reach the top of the staircase.

Dry Run:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]

dp = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Iteration 1: i = 2
dp[2] = Math.min(dp[1] + cost[1], dp[0] + cost[0]) = Math.min(0 + 100, 0 + 1) = 1
Iteration 2: i = 3
dp[3] = Math.min(dp[2] + cost[2], dp[1] + cost[1]) = Math.min(1 + 1, 0 + 100) = 2
Iteration 3: i = 4
dp[4] = Math.min(dp[3] + cost[3], dp[2] + cost[2]) = Math.min(2 + 1, 1 + 1) = 2
Iteration 4: i = 5
dp[5] = Math.min(dp[4] + cost[4], dp[3] + cost[3]) = Math.min(2 + 1, 2 + 1) = 3
Iteration 5: i = 6
dp[6] = Math.min(dp[5] + cost[5], dp[4] + cost[4]) = Math.min(3 + 100, 2 + 1) = 3
Iteration 6: i = 7
dp[7] = Math.min(dp[6] + cost[6], dp[5] + cost[5]) = Math.min(3 + 1, 3 + 100) = 4
Iteration 7: i = 8
dp[8] = Math.min(dp[7] + cost[7], dp[6] + cost[6]) = Math.min(4 + 1, 3 + 1) = 4
Iteration 8: i = 9
dp[9] = Math.min(dp[8] + cost[8], dp[7 + cost[7]) = Math.min(4 + 100, 4 + 1) = 5
Iteration 9: i = 10
dp[10] = Math.min(dp[9] + cost[9], dp[8] + cost[8]) = Math.min(5 + 1, 4 + 100) = 6

Return dp[10] = 6


Time Complexity: O(n), where n is the length of the cost array. We are iterating through the cost array once.

Space Complexity: O(n), where n is the length of the cost array. We are using an extra array dp of size n + 1.


*/

// Code:

class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 1) {
            return 0;
        }
        
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        
        return dp[cost.length];
    }
}
