// Problem Link: https://leetcode.com/problems/minimum-size-subarray-sum

/*

Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. 
If there is no such subarray, return 0 instead.

Example 1:Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

*/

// Approach 1: Brute Force Approach:
// Try every subarray, compute its sum, track smallest length with sum ≥ target.
// For every i from 0..n-1, run j from i..n-1, accumulate sum, when sum ≥ target update minLen.

/* Code:

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum += nums[j];
                if(sum >= target){
                    ans = Math.min(ans,j-i+1);
                    break;
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }
}

*/
// Time Complexity: O(n^2) in worst case, gives TLE on large inputs.
// Space Complexity: O(1)

/* Approach 2: Optimal Approach: Sliding Window Technique:

Initialize two pointers, i and j, to track the start and end of the current subarray, respectively. Set i and j to 0 initially.
Initialize a variable sum to keep track of the current sum of elements in the subarray.
Initialize a variable minLen to store the minimum length found so far. Set it to the maximum possible integer value (INT_MAX).
Start a while loop that continues until the j pointer reaches the end of the array nums.
Inside the loop, add the element at index j to the sum variable.
Check if the sum is greater than or equal to the target value.
If the condition is true, enter another while loop. This loop will handle the case where the current subarray sum is equal to or greater than the target.
a. Decrement the sum by subtracting the element at index i.
b. Update minLen with the minimum length found so far (j - i + 1).
c. Increment the i pointer to move the window to the right.
d. Repeat steps a-c until the sum is no longer greater than or equal to the target value.
Increment the j pointer to move the window to the right.
Repeat steps 5-8 until the j pointer reaches the end of the array.
After the loop, check if the value of minLen is still INT_MAX, indicating that no subarray was found. In this case, return 0.
Otherwise, return the value of minLen, which represents the minimum length of a subarray whose sum is greater than or equal to the target.
The sliding window technique allows us to efficiently search for the minimum length subarray that satisfies the given condition. By maintaining two pointers and adjusting the window based on the sum of elements, we can avoid unnecessary computations and achieve a time complexity of O(N), where N is the size of the input array nums.

*/ 

// Code:
class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i=0,j=0,sum=0;
        int minLen = n+1;
        while(j < n){
            sum += nums[j];
            while(sum >= target){
                minLen = Math.min(minLen,j-i+1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minLen == n+1 ? 0 : minLen;
    }
}

// Time Complexity: O(n) as each element is visited at most twice.
// Space Complexity: O(1)