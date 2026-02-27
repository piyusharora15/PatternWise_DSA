// Problem Link: https://leetcode.com/problems/find-peak-element?envType=problem-list-v2&envId=wh88bf73

// Approach: Binary Search.
// We can use binary search to find the peak element in O(log n) time. We compare the middle element with its neighbors to determine the direction to move. We use the fact that if the middle element is less than its right neighbor, then there must be a peak element on the right side. Otherwise, there must be a peak element on the left side. We continue this process until we find the peak element.

/*

Why it is guaranteed that peak element will definitely exist on the right side of an element, if its next element is greater than it?

If we keep moving in the right side of this element, as long as the elements are increasing, we will eventually reach an element that is either:

The last element of the array, which will be a peak as it is greater than or equal to its previous element.

An element where the sequence is no longer increasing, i.e., arr[i] > arr[i + 1], which would be a peak element.

For the same reasons, if an element is lesser than its previous element, then it is guaranteed that at least one peak element will exist on the left side of that element.

*/

// Code 1:
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n == 1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;
        int left = 1, right = n-2;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]){
                return mid;
            }
            if(nums[mid] < nums[mid+1]){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return 0;
    }
}

// Code 2: (More concise)
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[mid+1]){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}

// Time Complexity: O(log n), where n is the number of elements in the input array.
// Space Complexity: O(1), as we are using only a constant amount of extra space.