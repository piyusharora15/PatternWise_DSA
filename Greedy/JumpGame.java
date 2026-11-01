/*

Problem Link: https://leetcode.com/problems/jump-game?envType=problem-list-v2&envId=wr2p5de7

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

Approach: Using Greedy Algorithm.

1. First, we initialize a variable `maxReach` to keep track of the farthest index we can reach. We start from the first index and iterate through the array.

2. For each index `i`, we check if it is reachable by comparing it with `maxReach`. If `i` is greater than `maxReach`, it means we cannot reach this index, and we return false.

3. If the index is reachable, we update `maxReach` to be the maximum of its current value and `i + nums[i]`, which represents the farthest index we can reach from the current position.

4. If we finish iterating through the array and `maxReach` is greater than or equal to the last index, we return true, indicating that we can reach the last index.

Dry Run:

Input: nums = [2,3,1,1,4]

- Initialize maxReach = 0
- i = 0: maxReach = max(0, 0 + 2) = 2
- i = 1: maxReach = max(2, 1 + 3) = 4
- i = 2: maxReach = max(4, 2 + 1) = 4
- i = 3: maxReach = max(4, 3 + 1) = 4
- i = 4: maxReach = max(4, 4 + 4) = 8

Since `maxReach` is greater than or equal to the last index (4), we return true.

Time Complexity: O(n), where n is the length of the input array. We iterate through the array once.

Space Complexity: O(1), as we are using a constant amount of extra space.

*/

// Code:

class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false; // If the current index is beyond the maximum reachable index, return false
            }
            maxReach = Math.max(maxReach, i + nums[i]); // Update the maximum reachable index
        }
        return true; // If we can reach the last index, return true
    }
}