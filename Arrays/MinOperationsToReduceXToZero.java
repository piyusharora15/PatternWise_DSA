/*

Problem Link: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero?envType=daily-question&envId=2026-09-23

You are given an integer array nums and an integer x. 
In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. 
Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

Approach: Using Sliding Window.

1. First, we calculate the total sum of the array nums. Let's call this totalSum.
2. We need to find a subarray whose sum is equal to totalSum - x.
3. If we can find such a subarray, then the minimum number of operations required to reduce x to zero will be equal to the length of the array minus the length of this subarray.
4. We can use a sliding window approach to find the longest subarray with the sum equal to totalSum - x.
5. If we find such a subarray, we return the length of the array minus the length of this subarray. 
If we cannot find such a subarray, we return -1.


Dry Run: 
Input: nums = [3,2,20,1,1,3], x = 10
totalSum = 3 + 2 + 20 + 1 + 1 + 3 = 30
target = totalSum - x = 30 - 10 = 20

We need to find the longest subarray with sum equal to 20.

Using sliding window:
- Start with index 0, end with index 0: sum = 3
- Move end to index 1: sum = 5
- Move end to index 2: sum = 25 (exceeds target)
- Move start to index 1: sum = 25 - 3 = 22 (exceeds target)
- Move start to index 2: sum = 25 - 3 - 2 = 20 (matches target)

The longest subarray with sum equal to 20 is from index 2 to index 2 (length = 1).

So, the minimum number of operations is: 6 - 1 = 5.

Time Complexity: O(n), where n is the length of the array nums. 
We traverse the array once to calculate the total sum and then use a sliding window approach to find the longest subarray with the required sum.

Space Complexity: O(1), as we are using a constant amount of extra space.

*/

// Code:

class MinOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int target = totalSum - x;
        if (target < 0) return -1; // If target is negative, it's impossible to reduce x to zero
        
        int maxLength = -1;
        int currentSum = 0;
        int start = 0;
        
        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end];
            
            while (currentSum > target && start <= end) {
                currentSum -= nums[start];
                start++;
            }
            
            if (currentSum == target) {
                maxLength = Math.max(maxLength, end - start + 1);
            }
        }
        
        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}