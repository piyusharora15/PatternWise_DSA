// Problem Link: https://leetcode.com/problems/intersection-of-two-arrays?envType=problem-list-v2&envId=wh88bf73

/*

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.

*/

// Approach: Using HashSet.
// We can use a HashSet to store the unique elements of the first array, and then iterate through the second array to check if any of its elements are present in the HashSet. If they are, we can add them to another HashSet to ensure uniqueness in the result.

// Code:
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }
}

// Time Complexity: O(n + m), where n is the length of nums1 and m is the length of nums2.
// Space Complexity: O(n), where n is the length of nums1, due to the HashSet storing the unique elements of nums1. The resultSet will at most store min(n, m) unique elements, but this is also bounded by O(n).