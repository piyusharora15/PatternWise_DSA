/*

Problem Link: https://leetcode.com/problems/maximum-average-subarray-i?envType=problem-list-v2&envId=wh88bf73

You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. 
Any answer with a calculation error less than 10-5 will be accepted.

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75

Example 2:
Input: nums = [5], k = 1
Output: 5.00000

Approach: Using Sliding Window Technique.

1. First, we will calculate the sum of the first k elements of the array and store it in a variable called maxSum.

2. Then, we will iterate through the array starting from the k-th element to the end of the array. 
For each element, we will update the current sum by subtracting the element that is leaving the window (i.e., nums[i - k]) and adding the new element that is entering the window (i.e., nums[i]).

3. After updating the current sum, we will check if the current sum is greater than maxSum. 
If it is, we will update maxSum with the current sum.

4. Finally, we will return the maximum average by dividing maxSum by k.

Dry Run:
Input: nums = [1,12,-5,-6,50,3], k = 4

Step 1: Calculate the sum of the first k elements.
maxSum = 1 + 12 + (-5) + (-6) = 2

Step 2: Iterate through the array starting from the k-th element.
i = 4: currentSum = 2 - 1 + 50 = 51
i = 5: currentSum = 51 - 12 + 3 = 42

Step 3: Check if the current sum is greater than maxSum.
maxSum = max(2, 51) = 51

Step 4: Return the maximum average.
Maximum average = maxSum / k = 51 / 4 = 12.75

Time Complexity: O(n), where n is the number of elements in the array. We are iterating through the array once.

Space Complexity: O(1), as we are using a constant amount of space.

*/

// Code:

class MaximumAverageSubarray1 {
    public double findMaxAverage(int[] nums, int k) {
        // Step 1: Calculate the sum of the first k elements
        double maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += nums[i];
        }
        
        double currentSum = maxSum;
        
        // Step 2: Iterate through the array starting from the k-th element
        for (int i = k; i < nums.length; i++) {
            currentSum = currentSum - nums[i - k] + nums[i];
            // Step 3: Update maxSum if currentSum is greater
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        
        // Step 4: Return the maximum average
        return maxSum / k;
    }
}