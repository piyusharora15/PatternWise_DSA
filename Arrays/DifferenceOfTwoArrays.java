/*  

Problem Link: https://leetcode.com/problems/find-the-difference-of-two-arrays?envType=problem-list-v2&envId=wh88bf73

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


Approach: Using HashSet.

1. First, we will create two hash sets to store the distinct integers from nums1 and nums2.
2. Then, we will iterate through nums1 and add each integer to the first hash set.
3. Next, we will iterate through nums2 and add each integer to the second hash set.
4. After populating the hash sets, we will create two lists to store the distinct integers that are not present in the other array.
5. We will iterate through the first hash set and check if each integer is present in the second hash set. If it is not present, we will add it to the first list.
6. We will repeat the same process for the second hash set and add the distinct integers to the second list.
7. Finally, we will create a list of lists to store the two lists and return it as the answer.

Dry Run:
Input: nums1 = [1,2,3], nums2 = [2,4,6]

1. Create two hash sets: set1 = {}, set2 = {}
2. Iterate through nums1 and add each integer to set1: set1 = {1, 2, 3}
3. Iterate through nums2 and add each integer to set2: set2 = {2, 4, 6}
4. Create two lists: diff1 = [], diff2 = []
5. Iterate through set1 and check if each integer is present in set2:
   - 1 is not present in set2, add it to diff1: diff1 = [1]
   - 2 is present in set2, do nothing
   - 3 is not present in set2, add it to diff1: diff1 = [1, 3]
6. Iterate through set2 and check if each integer is present in set1:
   - 2 is present in set1, do nothing
    - 4 is not present in set1, add it to diff2: diff2 = [4]
    - 6 is not present in set1, add it to diff2: diff2 = [4, 6]
7. Create a list of lists to store the two lists: answer = [[1, 3], [4, 6]]


Time Complexity: O(n + m), where n is the length of nums1 and m is the length of nums2. 
We iterate through both arrays to populate the hash sets and then iterate through the sets to find the distinct integers.

Space Complexity: O(n + m), where n is the number of distinct integers in nums1 and m is the number of distinct integers in nums2. 
We use hash sets to store the distinct integers, and the answer list can also contain up to n + m distinct integers in the worst case.


*/


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
