/*

Problem Link: https://leetcode.com/problems/house-robber-ii?envType=problem-list-v2&envId=wh8gjvyh

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. 

All houses at this place are arranged in a circle. 

That means the first house is the neighbor of the last one. 

Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [1,2,3]
Output: 3

Understand the problem:

You're given an array nums, where nums[i] is the money in house i.

You cannot rob two adjacent houses, and because the houses are circular, you cannot rob both nums[0] and nums[n - 1].

Example:
nums = new int[]{2, 3, 2};

The first and last houses both contain 2, but they're neighbors. So you cannot rob both of them.

Options:
- Rob the first house → 2
- Rob the middle house → 3
- Rob the last house → 2

The maximum amount is 3.

Approach 1: Brute-force Recursion.

Intuition:

In House Robber I, for each house, we have two choices:
1. Rob the current house and skip the next one.
2. Skip the current house and move to the next.

For the circular version, we also need to make sure we don't rob both the first and last houses.

The simplest way to reason about this is to split into two cases:

- Case A: Exclude the last house, solve houses 0 through n - 2.
- Case B: Exclude the first house, solve houses 1 through n - 1.

Each case is now a normal linear House Robber problem.

Code:

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    private int robLinear(int[] nums, int start, int end) {
        return solve(nums, start, end);
    }

    private int solve(int[] nums, int i, int end) {
        if (i > end) {
            return 0;
        }

        int rob = nums[i] + solve(nums, i + 2, end);
        int skip = solve(nums, i + 1, end);

        return Math.max(rob, skip);
    }
}

Complexity:

- Time: O(2^n), because each linear subproblem explores two recursive choices per house.

- Auxiliary space: O(n), due to the recursion stack.

Approach 2: Recursion + Memoization (Top-Down DP).

Intuition:

The brute-force recursion repeatedly calculates the best result for the same house index.

We can store previously calculated results in a memo array. 

Since the two cases have different starting and ending boundaries, solve each range independently with its own memo array.

For a linear range, the recurrence is:

rob(i)=max(nums[i]+rob(i+2),rob(i+1))

Dry Run:
Input: nums = [2, 3, 2]

We have two cases.

Case 1: Exclude the last house.
Range = [2, 3]

- At index 1, the best is 3.
- At index 0, robbing gives 2; skipping gives 3.

So the result is:
case1 = max(2, 3) = 3

Case 2: Exclude the first house
Range = [3, 2]

- At index 2, the best is 2.
- At index 1, robbing gives 3; skipping gives 2.
So:
case2 = max(3, 2) = 3

Finally:
answer = max(3, 3) = 3

Code:

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int case1 = robRange(nums, 0, n - 2);
        int case2 = robRange(nums, 1, n - 1);

        return Math.max(case1, case2);
    }

    private int robRange(int[] nums, int start, int end) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return solve(nums, start, end, memo);
    }

    private int solve(int[] nums, int i, int end, int[] memo) {
        if (i > end) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int rob = nums[i] + solve(nums, i + 2, end, memo);
        int skip = solve(nums, i + 1, end, memo);

        memo[i] = Math.max(rob, skip);
        return memo[i];
    }
}

Complexity:

- Time: O(n), because each index is calculated at most once per case.

- Auxiliary space: O(n) for the memo arrays and recursion stack.

Approach 3: Bottom-Up Dynamic Programming (DP Array).

Intuition:

Instead of recursion, build the answer iteratively for each linear range.

For each range, define:

dp[i] = maximum money obtainable up to index i

For every house, compare:

- Skip: dp[i - 1]
- Rob: nums[i] + dp[i - 2]

Therefore:

dp[i] = max(dp[i-1],nums[i]+dp[i-2])

We'll use the same two ranges:

1. nums[0 ... n - 2] — exclude the last house.
2. nums[1 ... n - 1] — exclude the first house.

Dry Run:
Input: nums = [1, 2, 3, 1]

Since the houses are circular, the first and last cannot both be robbed.

Case 1: Exclude the last house.

Consider houses [1, 2, 3]

Initialize:
dp[0] = 1
dp[1] = max(1, 2) = 2

dp = [1, 2, _]

Process the house containing 3:
Rob  = 3 + dp[0] = 3 + 1 = 4
Skip = dp[1] = 2
dp[2] = max(4, 2) = 4

So: case1 = 4

The best choice is houses with 1 and 3.

Case 2: Exclude the first house.

Consider houses [2, 3, 1]

Initialize:
dp[0] = 2
dp[1] = max(2, 3) = 3

dp = [2, 3, _]

Process the house containing 1:
Rob  = 1 + dp[0] = 1 + 2 = 3
Skip = dp[1] = 3
dp[2] = max(3, 3) = 3

So: case2 = 3

Finally:

answer = max(case1, case2)
       = max(4, 3)
       = 4

Output: 4.

Code:

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int len = end - start + 1;
        int[] dp = new int[len];

        dp[0] = nums[start];

        if (len == 1) {
            return dp[0];
        }

        dp[1] = Math.max(nums[start], nums[start + 1]);

        for (int i = 2; i < len; i++) {
            int money = nums[start + i];

            dp[i] = Math.max(dp[i - 1], money + dp[i - 2]);
        }

        return dp[len - 1];
    }
}

Complexity:

- Time: O(n) — two linear passes.

- Auxiliary space: O(n) — each range uses a DP array.

Approach 4: Space-Optimized DP.

Core Idea:

In House Robber I, the recurrence is:

current = max(prev1, prev2 + nums[i])

where:

- prev1 = best amount up to the previous house.
- prev2 = best amount up to the house before that.
- current = best amount if we include the current house or skip it.

For House Robber II, run this same logic twice:

- Case 1: Rob houses 0 through n - 2 — exclude the last house.
- Case 2: Rob houses 1 through n - 1 — exclude the first house.

Return the larger result. This is the standard linear-time, constant-space solution.

Dry Run:
Input: nums = [2, 3, 2]

There are three houses arranged in a circle. 
The first and last are neighbors, so they cannot both be robbed.

Case 1: Exclude the last house.

Range: indices 0 to 1 → [2, 3]

Initial state:
prev2 = 0
prev1 = 0

House 0: money = 2
current = max(0, 0 + 2) = 2

prev2 = 0
prev1 = 2

House 1: money = 3
current = max(2, 0 + 3) = 3

prev2 = 2
prev1 = 3

Return 3.

Case 2: Exclude the first house

Range: indices 1 to 2 → [3, 2]

Reset the variables:
prev2 = 0
prev1 = 0

House 1: money = 3
current = max(0, 0 + 3) = 3

prev2 = 0
prev1 = 3

House 2: money = 2
current = max(3, 0 + 2) = 3

prev2 = 3
prev1 = 3

Return 3.

Finally:
excludeLast  = 3
excludeFirst = 3

answer = max(3, 3) = 3

Output: 3

Complexity analysis:

Time Complexity: O(n). We process at most (n-1) houses in each of two passes, so O(2n) = O(n).

Space Complexity: O(1). We only use a constant amount of space for the variables prev1, prev2, and current.

*/

// Code:

class HouseRobber2 {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int excludeLast = robRange(nums, 0, n - 2);
        int excludeFirst = robRange(nums, 1, n - 1);

        return Math.max(excludeLast, excludeFirst);
    }

    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(
                prev1,          // Skip current house
                prev2 + nums[i] // Rob current house
        );

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}