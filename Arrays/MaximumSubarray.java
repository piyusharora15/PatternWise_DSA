// Problem Link: https://leetcode.com/problems/maximum-subarray?envType=study-plan-v2&envId=top-interview-150

/*
 
Companies: Flipkart, Amazon, Microsoft, Google, Walmart.

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

/* 

Approach 1: Brute Force -> Try out all subarrays.

We can use two nested loops to generate all possible subarrays and calculate their sums, keeping track of the maximum sum found. This approach has a time complexity of O(n^2).

Code:
class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int res = nums[0];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                res = Math.max(res, sum);
            }
        }
        return res;
    }
}

*/

/* Approach 2: Optimal Kadane's Algorithm (Way 1).

The idea of Kadane's algorithm is to traverse over the array from left to right and for each element, find the maximum sum among all subarrays ending at that element. 
The result will be the maximum of all these values. 

To calculate the maximum sum of subarray ending at current element, say maxEnding, we can use the maximum sum ending at the previous element.

So for any element, we have two choices:

Choice 1: Extend the maximum sum subarray ending at the previous element by adding the current element to it. If the maximum subarray sum ending at the previous index is positive, then it is always better to extend the subarray.

Choice 2: Start a new subarray starting from the current element. If the maximum subarray sum ending at the previous index is negative, it is always better to start a new subarray from the current element

This means that maxEnding at index i = max(maxEnding at index (i - 1) + arr[i], arr[i]) and the maximum value of maxEnding at any index will be our answer.

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

class MaximumSubarray {
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
}

/* Kadane's Algorithm (Way 2).

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

*/

// Time Complexity: O(n), where n is the length of the input array.
// Space Complexity: O(1), as we are using only a constant amount of extra space.


/*

Follow-Up Question: Print the subarray with the largest sum.

Take two variables, start and end, to keep track of the starting and ending indices of the maximum subarray. 
When we update maxSum, we also update start and end to the current indices.

class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        int ansStart = 0, ansEnd = 0, start = 0;
        for (int i = 0; i < nums.length; i++) {
            if(sum == 0) start = i;
            sum += nums[i];
            if(sum > res) {
                res = sum;
                ansStart = start;
                ansEnd = i;
            }
            if(sum < 0) sum = 0;
        }
        System.out.println("Maximum subarray is from index " + ansStart + " to " + ansEnd);
        return res;
    }
}

*/
