// Problem Link: https://leetcode.com/problems/merge-sorted-array?envType=study-plan-v2&envId=top-interview-150

// Approach: Two Pointers
// We can use two pointers to keep track of the current index of both arrays. We can compare the elements at these pointers and place the larger one at the end of the first array (which has enough space to hold all elements). We then move the pointer of the array from which we took the element and also move the pointer for the merged array.We repeat this process until we have merged all elements from both arrays.

// Code:
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for nums1
        int j = n - 1; // Pointer for nums2
        int k = m + n - 1; // Pointer for merged array

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If there are remaining elements in nums2, copy them
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}

// Time Complexity: O(m + n) where m and n are the lengths of the two arrays.
// Space Complexity: O(1) since we are merging in place without using extra space.