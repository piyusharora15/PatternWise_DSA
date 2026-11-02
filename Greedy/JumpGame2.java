/*

Problem Link: https://leetcode.com/problems/jump-game-ii?envType=problem-list-v2&envId=wr2p5de7

You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2

Approach: Using Greedy Algorithm.

1. First, we will initialize the variables jumps, currentEnd, and farthest to 0. 
The variable jumps will keep track of the number of jumps needed to reach the end of the array. 
The variable currentEnd will represent the farthest index that can be reached with the current number of jumps. 
The variable farthest will represent the farthest index that can be reached with the next jump.

2. We will iterate through the array from index 0 to n - 2 (inclusive).

3. For each index i, we will update the farthest variable to be the maximum of its current value and i + nums[i], which represents the farthest index that can be reached from the current position.

4. If the current index i is equal to the currentEnd, it means we have reached the end of the current jump range. In this case, we increment the jumps variable and update the currentEnd to the value of the farthest variable.

5. Finally, we return the value of the jumps variable, which represents the minimum number of jumps needed to reach the last index.

Dry Run:

Input: nums = [2,3,1,1,4]

- Initialize jumps = 0, currentEnd = 0, farthest = 0
- i = 0: farthest = max(0, 0 + 2) = 2
- i = 1: farthest = max(2, 1 + 3) = 4
- i = 2: farthest = max(4, 2 + 1) = 4
- i = 3: farthest = max(4, 3 + 1) = 4
- i = 4: farthest = max(4, 4 + 4) = 8

- i = 0: currentEnd = 0, jumps = 1, currentEnd = 2
- i = 1: currentEnd = 2, jumps = 2, currentEnd = 4
- i = 2: currentEnd = 4, jumps = 2, currentEnd = 4
- i = 3: currentEnd = 4, jumps = 2, currentEnd = 4

Since we have reached the last index, we return jumps = 2.

Time Complexity: O(n), where n is the length of the input array. We iterate through the array once.

Space Complexity: O(1), as we are using a constant amount of extra space.

*/

// Code:

class JumpGame2 {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]); // Update the farthest index that can be reached
            if (i == currentEnd) { // If we have reached the end of the current jump range
                jumps++; // Increment the number of jumps
                currentEnd = farthest; // Update the current end to the farthest index
            }
        }
        return jumps; // Return the minimum number of jumps needed to reach the last index
    }
}