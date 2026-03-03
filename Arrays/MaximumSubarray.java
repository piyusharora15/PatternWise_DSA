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

// Approach 2: Kadane's Algorithm.
// We can use Kadane's algorithm, which runs in O(n) time. 
// The idea is to iterate through the array while keeping track of the current subarray sum and the maximum sum found so far. 
// If the current subarray sum becomes negative, we reset it to zero.We also update the maximum sum whenever we find a new maximum.We can implement this as follows:
// -> We initialize two variables, currentSum and maxSum, to the first element of the array.
// -> We iterate through the array starting from the second element. For each element, we update currentSum by taking the maximum of the current element and the sum of currentSum and the current element. This step decides whether to start a new subarray at the current element or to continue the existing subarray.
// -> We then update maxSum to be the maximum of maxSum and currentSum.
// -> Finally, we return maxSum as the result.

// Code Implementation:

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

// Approach 3: Divide and Conquer.
// We can also solve this problem using a divide and conquer approach, which has a time complexity of O(n log n).
// The idea is to divide the array into two halves, recursively find the maximum subarray sum in each half, and then find the maximum subarray sum that crosses the midpoint. The final result will be the maximum of these three values. However, this approach is less efficient than Kadane's algorithm for this problem.

// Code Implementation for Divide and Conquer:

/*
class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    
    private int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        int leftMax = maxSubArrayHelper(nums, left, mid);
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }
}
*/

// Time Complexity: O(n log n), where n is the length of the input array.
// Space Complexity: O(log n) due to recursive stack space.