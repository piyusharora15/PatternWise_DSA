// Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii

/*

Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:

[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.

You must decrease the overall operation steps as much as possible.

Example 1:

Input: nums = [1,3,5]
Output: 1

Example 2:

Input: nums = [2,2,2,0,1]
Output: 0

Optimal Approach: Modified Binary Search.

At each step compare nums[mid] with nums[right]:
If nums[mid] > nums[right] → minimum is in (mid+1 .. right] → set left = mid + 1.
Else if nums[mid] < nums[right] → minimum is in [left .. mid] → set right = mid.
Else (nums[mid] == nums[right]) → ambiguous because duplicates hide which side is sorted → do right-- to shrink the window by 1 (safe but can degrade to linear time).
Stop when left == right; return nums[left].

Why this is correct: If nums[mid] > nums[right] the rotation point is to the right; if nums[mid] < nums[right] the min is at mid or to the left. When equal, you cannot tell which side contains the pivot, but decrementing right preserves the minimum in the remaining window.

*/

// Code:
class FindMinimumInRotatedSortedArray2 {
    public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while(l < r){
            while(l < r && nums[l] == nums[l+1]) l++;
            while(r < l && nums[r] == nums[r-1]) r--;
            int mid = l + (r-l) / 2;
            if(nums[mid] < nums[r]){
                r = mid;
            }
            else if(nums[mid] > nums[r]){
                l = mid+1;
            }
            else{
                r--;
            }
        }
        return nums[l];
    }
}

// T.C: O(log n) in average case, O(n) in worst case due to duplicates.
// S.C: O(1)