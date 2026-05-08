// Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array?envType=problem-list-v2&envId=binary-search

/*

There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1

*/

/*

Brute Force Approach: Linear Search.

Iterate through the array and check for each element, if it matches the target then return the index, otherwise return -1.

Time Complexity: O(N)
Space Complexity: O(1)

Code: 

public int search(int[] nums, int target) {
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] == target) {
            return i;
        }
    }
    return -1;
}

Better Approach: Two Pass Binary Search.

First we will find the pivot index using binary search. 
Pivot Index is the index of the smallest element in the array, which is also the number of rotations applied to the sorted array. 
Once the pivot is known, the array is split into two sorted subarrays. 

If the target is equal to the pivot element, its index is returned. 
If the pivot is at index 0, the entire array is already sorted, so a standard binary search is applied to the whole array. 
Otherwise, the target is compared with the first element: if it's greater than or equal, binary search is performed on the left half; if not, on the right half.

Time Complexity: O(logN)
Space Complexity: O(1)

Code:
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int pivot = findPivot(nums);
        int l,r;
        if(target >= nums[pivot] && target <= nums[n-1]){
            l = pivot;
            r = n-1;
        }
        else{
            l = 0;
            r = pivot - 1;
        }
        while(l <= r){
            int mid = l + (r-l) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) l = mid+1;
            else r = mid-1;
        }
        return -1;
    }
    private int findPivot(int[] nums){
        int l = 0, r = nums.length-1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] > nums[r]) l = mid + 1; // If the middle element is greater than the rightmost element, it means the pivot is in the right half of the array, so we move the left pointer to mid + 1.
            else r = mid;
        }
        return l;
    }
}


Optimal Approach: One Pass Binary Search.

This approach applies a modified version of binary search directly to the entire rotated array. 
At every iteration, the middle element is checked against the key. 
If it’s not the key, we determine whether the left half or right half is sorted by comparing values at arr[lo] and arr[mid]. 
If the left half is sorted and the key lies within its range, we adjust hi = mid - 1; otherwise, we shift lo = mid + 1. 
If the right half is sorted and the key lies within its range, we move lo = mid + 1; else, hi = mid - 1.

Dry Run:

nums[] = [4,5,6,7,0,1,2], target = 0
lo = 0, hi = 6
mid = 3, nums[mid] = 7
Since nums[lo] <= nums[mid], the left half is sorted.
Now, we check if the target (0) lies within the left half (4 to 7). 
It does not, so we move to the right half by setting lo = mid + 1 = 4.

Now, lo = 4, hi = 6
mid = 5, nums[mid] = 1
Since nums[lo] <= nums[mid], the left half is sorted.
Now, we check if the target (0) lies within the left half (0 to 1). 
It does, so we move to the left half by setting hi = mid - 1 = 4.

Next, lo = 4, hi = 4
mid = 4, nums[mid] = 0
Since nums[mid] == target, we return mid, which is 4.


Time Complexity: O(logN)
Space Complexity: O(1)

*/

// Code:
class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) return mid;
            if(nums[l] <= nums[mid]){
                if(nums[l] <= target && target <= nums[mid]){
                    r = mid - 1;
                }
                else{
                    l = mid + 1;
                }
            }
            else{
                if(nums[mid] < target && target <= nums[r]){
                    l = mid + 1;
                }
                else{
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}