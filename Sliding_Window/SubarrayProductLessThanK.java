// Problem Link: https://leetcode.com/problems/subarray-product-less-than-k?envType=problem-list-v2&envId=wh88bf73

/*

Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Example 2:
Input: nums = [1,2,3], k = 0
Output: 0


Intuition:
We can use a sliding window approach to solve this problem efficiently. The idea is to maintain a window of contiguous elements and calculate the product of the elements in that window. If the product is less than k, we can count all the subarrays that end at the current right pointer. If the product exceeds k, we need to move the left pointer to reduce the product until it is less than k again.

Approach:
1. Initialize two pointers, left and right, to the start of the array, and a variable product to 1 to keep track of the product of the elements in the current window.
2. Iterate through the array with the right pointer:
   a. Multiply the current element (nums[right]) to the product.
   b. While the product is greater than or equal to k, divide the product by nums[left] and move the left pointer to the right.
   c. The number of subarrays that end at right and have a product less than k is (right - left + 1). Add this count to the result.
3. Return the total count of subarrays.

*/

// Code Implementation:

public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0;
        int left = 0, right = 0, product = 1, count = 0;
        int n = nums.length;
        while(right < n) {
            product *= nums[right];
            while(product >= k) product /= nums[left++];
            count += 1 + (right - left);
            right++;
        }
        return count;
    }
}

// Time Complexity: O(n) - Each element is visited at most twice (once by the right pointer and once by the left pointer).
// Space Complexity: O(1) - We are using only a constant amount of extra space.