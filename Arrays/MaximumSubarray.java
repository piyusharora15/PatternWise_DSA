// Problem Link: https://leetcode.com/problems/maximum-subarray?envType=study-plan-v2&envId=top-interview-150

/*

Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

 */

// Approach 1: Brute Force.
// We can use two nested loops to generate all possible subarrays and calculate their sums, keeping track of the maximum sum found. This approach has a time complexity of O(n^2).

/* Approach 2: Kadane's Algorithm (Way 1).

-> We start by initializing two variables: maxSum and currentSum.
   
   maxSum represents the maximum sum encountered so far and is initially set to the minimum possible integer value to ensure that any valid subarray sum will be greater than it.
   
   currentSum represents the current sum of the subarray being considered and is initially set to 0.

We iterate through the nums array using a for loop, starting from the first element and going up to the last element.

For each element in the array, we add it to the current sum 'currentSum'. This calculates the sum of the subarray ending at the current element.

Next, we check if the 'currentSum' is greater than the current maximum sum 'maxSum'.

If it is, we update 'maxSum' with the new value of 'currentSum'. This means we have found a new maximum subarray sum.

If the current sum 'currentSum' becomes negative, it indicates that including the current element in the subarray would reduce the overall sum. 
In such cases, we reset 'currentSum' to 0. This effectively discards the current subarray and allows us to start a fresh subarray from the next element.

We repeat steps 3 to 5 for each element in the array.

After iterating through the entire array, the variable 'maxSum' will contain the maximum subarray sum encountered.

Finally, we return the value of 'maxSum' as the result, representing the maximum sum of a contiguous subarray within the given array nums.

*/

// Code:


/*

public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        
        return maxSum;
    }

 */

// Kadane's Algorithm (Way 2).

class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}

// Time Complexity: O(n), where n is the length of the input array.
// Space Complexity: O(1), as we are using only a constant amount of extra space.