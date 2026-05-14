// Problem Link: https://leetcode.com/problems/peak-index-in-a-mountain-array?envType=problem-list-v2&envId=wh88bf73

/*

Companies: Amazon, Microsoft, Google, Facebook.

You are given an integer mountain array arr of length n where the values increase to a peak element and then decrease.

Return the index of the peak element.

Your task is to solve it in O(log(n)) time complexity.

Example 1:
Input: arr = [0,1,0]
Output: 1

Example 2:
Input: arr = [0,2,1,0]
Output: 1

Example 3:
Input: arr = [0,10,5,2]
Output: 1

Approach: Binary Search.

We can use binary search to find the peak index in a mountain array. 
We will compare the middle element with its next element to determine whether we are in the increasing part of the mountain or the decreasing part. 
If the middle element is less than its next element, it means we are in the increasing part, and we need to move to the right half. 
Otherwise, we are in the decreasing part, and we need to move to the left half. 
We will continue this process until we find the peak index.

*/

// Code:
class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // We are in the increasing part of the mountain
                left = mid + 1;
            } else {
                // We are in the decreasing part of the mountain
                right = mid;
            }
        }

        // At the end of the loop, left and right will point to the peak index
        return left;
    }
}

// Time Complexity: O(log n), where n is the length of the input array.
// Space Complexity: O(1), as we are using only constant extra space.