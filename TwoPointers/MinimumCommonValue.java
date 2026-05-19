// Problem Link: https://leetcode.com/problems/minimum-common-value?envType=daily-question&envId=2026-05-19

/*

Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.

Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.

Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.

Example 2:
Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.


Brute Force Approach:
1. We can use two nested loops to compare each element of nums1 with each element of nums2.
2. If we find a common element, we can keep track of the minimum common value found so far.
3. Finally, we return the minimum common value or -1 if no common element is found.

Code:

class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    return nums1[i];
                }
            }
        }
        return -1;
    }
}

Time Complexity: O(n * m), where n and m are the lengths of nums1 and nums2 respectively.
Space Complexity: O(1), as we are not using any extra space.

Better Approach: HashSet.

1. We can use a HashSet to store the elements of one of the arrays (say nums1).
2. Then, we can iterate through the other array (nums2) and check if any element is present in the HashSet.
3. HashSet provides O(1) average time complexity for lookups, so this approach will be more efficient than the brute force method.

Code:

import java.util.*;

class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                return num;
            }
        }
        return -1;
    }
}

Time Complexity: O(n + m), where n and m are the lengths of nums1 and nums2 respectively. 
O(n) for adding elements of nums1 to the HashSet and O(m) for checking elements of nums2 against the HashSet.

Space Complexity: O(n), where n is the length of nums1, as we are storing all elements of nums1 in the HashSet.


Optimal Approach: Two Pointers.

1. Since both arrays are sorted, we can use two pointers to traverse both arrays simultaneously.
2. We can initialize two pointers, one for each array, and compare the elements at these pointers.
3. If the elements are equal, we have found the minimum common value and can return it.
4. If the element in nums1 is smaller than the element in nums2, we can move the pointer in nums1 forward to find a larger value.
5. If the element in nums2 is smaller than the element in nums1, we can move the pointer in nums2 forward to find a larger value.


Time Complexity: O(n + m), where n and m are the lengths of nums1 and nums2 respectively, as we are traversing both arrays at most once.

Space Complexity: O(1), as we are using only a constant amount of extra space for the pointers.

*/

// Code:

class MinimumCommonValue {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            // Common element found
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            }
            // Move smaller element pointer
            else if (nums1[i] < nums2[j]) {
                i++;
            }
            else {
                j++;
            }
        }
        return -1;
    }
}