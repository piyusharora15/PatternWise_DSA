// Problem Link: https://leetcode.com/problems/maximum-product-subarray

/*

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Note that the product of an array with a single element is the value of that element.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

*/

// Approach: Kadane's Algorithm.
// We will keep track of the maximum product and minimum product at each index, because a negative number can turn a minimum product into a maximum product.
// We will also keep track of the global maximum product.
// We start with the first element as the initial maximum product, minimum product and global maximum product.
// Then we iterate through the array, at each index we calculate the maximum product and minimum product by considering the current element, the product of the current element with the previous maximum product and the product of the current element with the previous minimum product.
// Finally, we update the global maximum product if the current maximum product is greater than the global maximum product.

// Code:
class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int globalMaxProduct = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int tempMax = Math.max(current, Math.max(maxProduct * current, minProduct * current));
            minProduct = Math.min(current, Math.min(maxProduct * current, minProduct * current));
            maxProduct = tempMax;
            
            globalMaxProduct = Math.max(globalMaxProduct, maxProduct);
        }
        
        return globalMaxProduct;
    }
}

// Time Complexity: O(n), where n is the length of the input array.
// Space Complexity: O(1), we are using only constant extra space.