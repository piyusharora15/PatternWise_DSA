// Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array?envType=problem-list-v2&envId=binary-search

/*

Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:

Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:

Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 

*/

/*

Brute Force Approach: Linear Search.
We can simply traverse the array and find the minimum element. This approach is straightforward but does not meet the O(log n) time requirement.

Time Complexity: O(N)
Space Complexity: O(1)

Code:

class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }
}

Optimal Approach: Binary Search.

At any step, check nums[mid] vs nums[right].
If nums[mid] > nums[right] → minimum is in (mid+1 .. right].
Else (nums[mid] <= nums[right]) → minimum is in [left .. mid] (including mid).
Loop until left == right — that index holds the minimum.

*/

// Code:
class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n-1;
        while(l < r){
            int mid = l + (r-l) / 2;
            if(nums[mid] > nums[r]) l = mid+1;
            else r = mid;
        }
        return nums[l];
    }
}

// Time Complexity: O(logN)
// Space Complexity: O(1)