// Problem Link: https://leetcode.com/problems/find-the-difference-of-two-arrays?envType=problem-list-v2&envId=wh88bf73

/*  
Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where: 
answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.

Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums1. Therefore, answer[1] = [4,6].

Example 2:
Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
Output: [[3],[]]
Explanation:
For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
*/

// Approach: Using HashSet.
// We can use two hash sets to store the distinct integers of nums1 and nums2. 
// Then, we can iterate through each set and check if the elements are present in the other set. 
// If not, we add them to the respective answer lists.We can then return the answer list containing both lists of distinct integers.

// Code:
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DifferenceOfTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        for (int num : nums1) {
            set1.add(num);
        }
        
        for (int num : nums2) {
            set2.add(num);
        }
        
        List<Integer> diff1 = new ArrayList<>();
        List<Integer> diff2 = new ArrayList<>();
        
        for (int num : set1) {
            if (!set2.contains(num)) {
                diff1.add(num);
            }
        }
        
        for (int num : set2) {
            if (!set1.contains(num)) {
                diff2.add(num);
            }
        }
        
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(diff1);
        answer.add(diff2);
        
        return answer;
    }
}

// Time Complexity: O(n + m), where n is the length of nums1 and m is the length of nums2. We iterate through both arrays to populate the hash sets and then iterate through the sets to find the distinct integers.
// Space Complexity: O(n + m), where n is the number of distinct integers in nums1 and m is the number of distinct integers in nums2. We use hash sets to store the distinct integers, and the answer list can also contain up to n + m distinct integers in the worst case.