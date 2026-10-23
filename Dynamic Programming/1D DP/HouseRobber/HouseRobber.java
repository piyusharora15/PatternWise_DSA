/*

Problem Link: https://leetcode.com/problems/house-robber?envType=problem-list-v2&envId=wh8gjvyh

You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Solution 1: Using Recursion (Brute Force).

Code:

class Solution {
    public int rob(int[] nums) {
        return robFrom(nums, 0);
    }

    private int robFrom(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        int rob = nums[i] + robFrom(nums, i + 2); // Option 1: Rob this house.

        int skip = robFrom(nums, i + 1); // Option 2: Skip this house.

        return Math.max(rob, skip);
    }
}

Solution 2: Using Recursion + Memoization.

Code:

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        return robFrom(nums, 0, memo);
    }

    private int robFrom(int[] nums, int i, int[] memo) {
        if (i >= nums.length) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int rob = nums[i] + robFrom(nums, i + 2, memo);
        int skip = robFrom(nums, i + 1, memo);

        memo[i] = Math.max(rob, skip);

        return memo[i];
    }
}

Solution 3: Using Tabulation (Bottom-Up Dynamic Programming).

Code:

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int rob = nums[i] + dp[i - 2];
            int skip = dp[i - 1];

            dp[i] = Math.max(rob, skip);
        }

        return dp[n - 1];
    }
}


*/

// Optimal Solution: Using Tabulation (Bottom-Up Dynamic Programming) with Space Optimization.

class HouseRobber {
    public int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;

        for (int num : nums) {
            int current = Math.max(prev1, prev2 + num);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}