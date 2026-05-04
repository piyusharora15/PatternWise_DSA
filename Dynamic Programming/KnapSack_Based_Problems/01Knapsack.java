// Problem Link: https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1

/*

Companies: Flipkart, Amazon, Microsoft, Adobe, Oracle, Morgan Stanley, Walmart, Paytm, OYO, Zomato, Swiggy, PhonePe, Google, Facebook.

Given two arrays, val[] and wt[], where each element represents the value and weight of an item respectively, and an integer W representing the maximum capacity of the knapsack (the total weight it can hold).

The task is to put the items into the knapsack such that the total value obtained is maximum without exceeding the capacity W.

Note: You can either include an item completely or exclude it entirely — fractional selection of items is not allowed. Each item is available only once.

Examples :

Input: W = 4, val[] = [1, 2, 3], wt[] = [4, 5, 1]
Output: 3
Explanation: Choose the last item, which weighs 1 unit and has a value of 3.

Input: W = 3, val[] = [1, 2, 3], wt[] = [4, 5, 6] 
Output: 0
Explanation: Every item has a weight exceeding the knapsack's capacity (3).

Input: W = 5, val[] = [10, 40, 30, 50], wt[] = [5, 4, 2, 3] 
Output: 80
Explanation: Choose the third item (value 30, weight 2) and the last item (value 50, weight 3) for a total value of 80.

Constraints:
1 ≤ val.size() = wt.size() ≤ 10^3
1 ≤ W ≤ 10^3
1 ≤ val[i] ≤ 10^3
1 ≤ wt[i] ≤ 10^3

*/

/*

Why Greedy Approach Doesn't Work?

The greedy approach, which involves selecting items based on their value-to-weight ratio, does not guarantee an optimal solution for the 0/1 Knapsack problem. 
This is because it may lead to suboptimal choices when items have varying weights and values.

For example, consider we have a knapsack with a capacity of 6 and the following items:
Item 1: value = 30, weight = 3 (value-to-weight ratio = 10)
Item 2: value = 40, weight = 2 (value-to-weight ratio = 20)
Item 3: value = 60, weight = 5 (value-to-weight ratio = 12)

If we apply the greedy approach, we would first select Item 2 (value 40, weight 2) because it has the highest value-to-weight ratio. After selecting Item 2, we have a remaining capacity of 4 (6 - 2).
Next, we would select Item 1 (value 30, weight 3) because it has the next highest value-to-weight ratio. After selecting Item 1, we have a remaining capacity of 1 (4 - 3).
At this point, we cannot select Item 3 because it exceeds the remaining capacity. The total value obtained from the greedy approach would be 40 (from Item 2) + 30 (from Item 1) = 70.

However, the optimal solution for this problem is to select Item 3 (value 60, weight 5) and Item 2 (value 40, weight 2), which gives a total value of 100 (60 + 40) without exceeding the capacity of the knapsack.

*/

/*

Naive Recursive Approach:

The idea is to use recursion to explore all possible combinations of items. 
For each item, there are two choices: either include the item in the knapsack or skip it, depending on whether its weight allows it to fit within the remaining capacity.

-> Pick the item: If the weight of the current item is less than or equal to the remaining capacity of the knapsack, include it and reduce the remaining capacity accordingly.

-> Do not pick the item: Skip the current item and move to the next one while keeping the remaining capacity unchanged.

For every item, compute the maximum value obtained from these two choices and return the larger one as the result.

Code:

class Solution {
Function to return max value that can be put in knapsack of capacity W.
    public int knapSack(int W, int wt[], int val[], int n) {
        Base case: If there are no items or capacity is 0, return 0
        if (n == 0 || W == 0) {
            return 0;
        }
            int pick = 0;
        If the weight of the nth item is less than or equal to the remaining capacity
        if (wt[n - 1] <= W) {
            Return the maximum of including the item and excluding the item.
            pick = val[n-1] + knapSack(W-wt[n-1], wt, val, n-1);}
            int notPick = knapSack(W, wt, val, n - 1);
            return Math.max(pick, notPick);
    }
    public int knap(int W, int[] wt, int[] val) {
        int n = val.length;
        return knapSack(W, wt, val, n);
    }
}

Time Complexity: O(2^n) - In the worst case, we explore all possible combinations of items, which leads to an exponential time complexity.
Space Complexity: O(n) - The maximum depth of the recursion is n, which occurs when we explore all items.


Better Approach 1: Memoization (Top-Down Dynamic Programming).

We can optimize the naive recursive approach by using memoization to store the results of previously computed subproblems.
We can create a 2D array to store the maximum value that can be obtained for a given capacity and number of items.
We check if the result for a specific capacity and number of items has already been computed before performing the recursive calls.
We can use a 2D array dp[n+1][W+1] where dp[i][j] represents the maximum value that can be obtained with the first i items and a knapsack capacity of j.

class Solution {
    public int knapSack(int W, int wt[], int val[], int n, int[][] dp) {
        Base case: If there are no items or capacity is 0, return 0
        if (n == 0 || W == 0) {
            return 0;
        }
        If the result for this subproblem has already been computed, return it
        if (dp[n][W] != -1) {
            return dp[n][W];
        }
        int pick = 0;
        If the weight of the nth item is less than or equal to the remaining capacity
        if (wt[n - 1] <= W) {
            Return the maximum of including the item and excluding the item.
            pick = val[n-1] + knapSack(W-wt[n-1], wt, val, n-1, dp);
        }
        int notPick = knapSack(W, wt, val, n - 1, dp);
        Store the result in the dp array before returning
        dp[n][W] = Math.max(pick, notPick);
        return dp[n][W];
    }
    public int knap(int W, int[] wt, int[] val) {
        int n = val.length;
        Create a dp array initialized with -1 to indicate uncomputed subproblems
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return knapSack(W, wt, val, n, dp);
    }
}

Time Complexity: O(n*W) - Each subproblem is solved only once, and there are n*W possible subproblems.
Space Complexity: O(n*W) - The dp array takes up O(n*W) space to store the results of subproblems, and the recursion stack can go up to O(n) in the worst case.

Better Approach 2: Tabulation (Bottom-Up Dynamic Programming).

In the tabulation approach, we build a 2D dp array iteratively, where dp[i][j] represents the maximum value that can be obtained with the first i items and a knapsack capacity of j.

class Solution {
    public int knap(int W, int[] wt, int[] val) {
        int n = val.length;
        Create a dp array where dp[i][j] represents the maximum value for the first i items and capacity j
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                Base case: If there are no items or capacity is 0, the maximum value is 0
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (wt[i - 1] <= j) {
                    If the weight of the current item is less than or equal to the current capacity,
                    we can choose to include it or exclude it
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    If the weight of the current item exceeds the current capacity,
                    we cannot include it, so we take the value from the previous item
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        The answer will be in the bottom-right cell of the dp array
        return dp[n][W];
    }
}

Time Complexity: O(n*W) - We fill a dp array of size (n+1) x (W+1), which takes O(n*W) time.
Space Complexity: O(n*W) - The dp array takes up O(n*W) space to store the results of subproblems.


Space Optimization:
Since the value of dp[i][j] only depends on the previous row (i-1), we can optimize the space complexity to O(W) by using a single array to store the results for the current row.

We can iterate through the items and update the dp array in reverse order to ensure that we are using the results from the previous iteration.

We can use a 1D array dp[W+1] where dp[j] represents the maximum value that can be obtained with a knapsack capacity of j.

Code:

class Solution {
    public int knap(int W, int[] wt, int[] val) {
        int n = val.length;
        Create a dp array where dp[j] represents the maximum value for capacity j
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {
            for (int j = W; j >= wt[i]; j--) {
                Update the dp array in reverse order to use results from the previous iteration
                dp[j] = Math.max(dp[j], val[i] + dp[j - wt[i]]);
            }
        }
        The answer will be in the last cell of the dp array
        return dp[W];
    }
}


Time Complexity: O(n*W) - We still need to fill the dp array for each item and capacity, which takes O(n*W) time.
Space Complexity: O(W) - We only use a single array of size W+1 to store the results for the current row, which reduces the space complexity to O(W).


*/