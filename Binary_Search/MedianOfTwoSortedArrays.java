/*

Problem Link: https://leetcode.com/problems/median-of-two-sorted-arrays

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Understand the problem:
The median is the middle value after all elements are sorted.
- If the total number of elements is odd, the median is the single middle element.
- If the total number is even, the median is the average of the two middle elements.

Example 1 — Odd total
nums1 = [1, 3]
nums2 = [2]
Combined sorted order: [1, 2, 3]
Median = 2.0

Example 2 — Even total
nums1 = [1, 2]
nums2 = [3, 4]
Combined sorted order: [1, 2, 3, 4]
Median = (2+3)/2 = 2.5

We'll use:
- m = nums1.length
- n = nums2.length
- N = m+n
The arrays are already sorted, and they cannot both be empty.


Approach 1: Combine both arrays and sort.

Idea:

Put all elements from both arrays into a new array, sort that array, and calculate the median from the middle position(s).

Dry Run:
Input:
nums1 = [1, 3]
nums2 = [2, 4]

Step 1: Copy both arrays into one.
merged = [1, 3, 2, 4]

Step 2: Sort the combined array.
merged = [1, 2, 3, 4]

Step 3: Calculate the median.
There are four elements, so the middle indices are:
- total / 2 - 1 = 1 → value 2
- total / 2 = 2 → value 3
Therefore:
median=2+3/2=2.5

Code:

import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n];

        System.arraycopy(nums1, 0, merged, 0, m);
        System.arraycopy(nums2, 0, merged, m, n);

        Arrays.sort(merged);

        int total = merged.length;

        if (total % 2 == 1) {
            return merged[total / 2];
        }

        return ((double) merged[total / 2 - 1]
                + merged[total / 2]) / 2.0;
    }
}

Complexity:
Time Complexity: O((m+n) log(m+n)) — Sorting the merged array takes O((m+n) log(m+n)) time.
Space Complexity: O(m+n) — We are using an extra array to store the merged elements.

Limitation:
This works, but it ignores the fact that both input arrays are already sorted. We can do better by merging them in linear time.


Approach 2: Merge both sorted arrays using two pointers.

Idea: Because each input array is already sorted, we don't need to sort again. We can merge them in the same way as the merge step of Merge Sort.

Maintain two pointers:
- i points to the current element in nums1.
- j points to the current element in nums2.

At each step, take the smaller of the two current elements and append it to the merged array. If one array is exhausted, append the remaining elements from the other.

Dry Run:
Input: nums1 = [1, 3, 8]
nums2 = [2, 7, 10]

Initially:
i = 0, j = 0, k = 0
merged = [_, _, _, _, _, _]

Step 1:
nums1[i] = 1, nums2[j] = 2
Since 1 <= 2, take 1.
merged = [1, _, _, _, _, _]
i = 1, j = 0, k = 1

Step 2:
Since 2 < 3, take 2.
merged = [1, 2, _, _, _, _]
i = 1, j = 1, k = 2

Step 3:
Since 3 < 7, take 3.
merged = [1, 2, 3, _, _, _]
i = 2, j = 1, k = 3

Step 4:
Since 7 < 8, take 7.
merged = [1, 2, 3, 7, _, _]
i = 2, j = 2, k = 4

Step 5:
Since 8 < 10, take 8.
merged = [1, 2, 3, 7, 8, _]
i = 3, j = 2, k = 5

Step 6:
Since nums1 is exhausted, take 10 from nums2.
merged = [1, 2, 3, 7, 8, 10]

There are six elements. The middle two are 3 and 7.
Median = (3 + 7) / 2 = 5.0

Code:

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        while (i < m) {
            merged[k++] = nums1[i++];
        }

        while (j < n) {
            merged[k++] = nums2[j++];
        }

        int total = m + n;

        if (total % 2 == 1) {
            return merged[total / 2];
        }

        return ((double) merged[total / 2 - 1]
                + merged[total / 2]) / 2.0;
    }
}

Time Complexity: O(m+n) — We traverse both arrays once.
Space Complexity: O(m+n) — We are using an extra array to store the merged elements.

*/