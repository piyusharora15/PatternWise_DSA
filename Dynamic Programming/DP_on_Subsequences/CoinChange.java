/*

Problem Link: https://leetcode.com/problems/coin-change

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

Example 3:

Input: coins = [1], amount = 0
Output: 0

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4


Why Greedy Approach will not work?

Imagine you're a cashier and need to give someone 11 cents in change, and you only have 1, 2, and 5 cent coins. 
How would you do it using the fewest coins?

Your first thought might be to use the biggest coin possible. You'd grab a 5 cent coin, leaving you with 6 cents. 
Then you'd grab another 5 cent coin, leaving 1 cent. Finally, you'd grab a 1 cent coin. 
That's 5+5+1, a total of 3 coins. This is a greedy approach, and it works for this example.

But what if the coins were [1, 3, 4] and the amount was 6? 
A greedy approach would pick 4, leaving 2. Now you're stuck! 
You can't make 2 with 3s so you have to choose two 1s and hence getting a total 3 coins. 
But the optimal solution is 3+3, which is only 2 coins.

This tells us that we need to explore all possibilities. 
For a given amount, we could try using one of our coins (say, a 1-cent coin), and then the problem becomes "what's the minimum number of coins for amount - 1?". 
We could do this for every coin we have and then take the best result.

This idea of breaking the main problem down into smaller, identical subproblems is a perfect signal for using recursion or dynamic programming.


Naive Approach: Recursion.

For every coin:

Either take it (stay at same index because unlimited supply)
Or skip it and move to next coin

We try all possibilities and return the minimum coins needed.

Recursive Relation:

If we take current coin:  1+f(i,amount−coins[i])

If we skip:  f(i+1,amount)

Take minimum of both.

Dry Run:

coins = [1,2,5], amount = 11

f(0,11) = min(1+f(0,10), f(1,11))
f(0,10) = min(1+f(0,9), f(1,10))
f(0,9) = min(1+f(0,8), f(1,9))
f(0,8) = min(1+f(0,7), f(1,8))
f(0,7) = min(1+f(0,6), f(1,7))
f(0,6) = min(1+f(0,5), f(1,6))
f(0,5) = min(1+f(0,4), f(1,5))
f(0,4) = min(1+f(0,3), f(1,4))
f(0,3) = min(1+f(0,2), f(1,3))
f(0,2) = min(1+f(0,1), f(1,2))
f(0,1) = min(1+f(0,0), f(1,1))
f(0,0) = 0 (base case)


Code:

class Solution {

    private static final int INF = (int)1e9;

    public int coinChange(int[] coins, int amount) {
        int ans = solve(0, coins, amount);
        return ans >= INF ? -1 : ans;
    }

    private int solve(int idx, int[] coins, int amount) {
        if (amount == 0)  // Base case: If amount is 0, we need 0 coins
            return 0;

        if (idx == coins.length)
            return INF;

        int take = INF;

        if (coins[idx] <= amount) {  // If we can take the current coin, we add 1 to the result of solving for the remaining amount
            take = 1 + solve(idx, coins, amount - coins[idx]);
        }

        int notTake = solve(idx + 1, coins, amount); // If we skip the current coin, we move to the next index

        return Math.min(take, notTake);
    }
}

Time Complexity: O(2^n) where n is the number of coins, because in the worst case we explore all combinations of coins.
Space Complexity: O(amount) for the recursion stack in the worst case when we keep taking coins until we reach 0.

Better Approach 1: Memoization.

We can optimize the recursive solution by storing the results of subproblems in a 2D array to avoid redundant calculations.

State: dp[idx][amount] -> Minimum coins needed to make up the amount using coins from index idx to end.

Code:

class Solution {

    private static final int INF = (int)1e9;

    public int coinChange(int[] coins, int amount) {

        int n = coins.length;

        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = solve(0, coins, amount, dp);

        return ans >= INF ? -1 : ans;
    }

    private int solve(int idx, int[] coins, int amount, int[][] dp) {

        if (amount == 0)
            return 0;

        if (idx == coins.length)
            return INF;

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int take = INF;

        if (coins[idx] <= amount) {
            take = 1 + solve(idx, coins, amount - coins[idx], dp);
        }

        int notTake = solve(idx + 1, coins, amount, dp);

        return dp[idx][amount] = Math.min(take, notTake);
    }
}

Time Complexity: O(n * amount) where n is the number of coins, because we compute each state once.
Space Complexity: O(n * amount) for the dp array and O(amount) for the recursion stack.

Better Approach 2: Tabulation.

DP State:
dp[i][target]

Meaning: Minimum coins needed using first i coins to make target

If taking current coin:  1 + dp[i][target - coin]
We stay at same row because unlimited supply.

If skipping current coin: dp[i-1][target]

Base Case:
Amount 0 requires 0 coins.

dp[i][0] = 0 for all i.

Code:

class Solution {

    public int coinChange(int[] coins, int amount) {

        int n = coins.length;

        int INF = (int)1e9;

        int[][] dp = new int[n][amount + 1];

        for (int target = 0; target <= amount; target++) {  // Initialize first row

            if (target % coins[0] == 0)
                dp[0][target] = target / coins[0];
            else
                dp[0][target] = INF;
        }

        for (int i = 1; i < n; i++) {

            for (int target = 0; target <= amount; target++) {

                int notTake = dp[i - 1][target];

                int take = INF;

                if (coins[i] <= target) {
                    take = 1 + dp[i][target - coins[i]];
                }

                dp[i][target] = Math.min(take, notTake);
            }
        }

        int ans = dp[n - 1][amount];

        return ans >= INF ? -1 : ans;
    }
}

Time Complexity: O(n * amount) where n is the number of coins, because we fill a dp table of size n x amount.
Space Complexity: O(n * amount) for the dp table.


*/