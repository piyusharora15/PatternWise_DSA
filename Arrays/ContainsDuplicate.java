// Problem Link: https://leetcode.com/problems/contains-duplicate?envType=problem-list-v2&envId=wh88bf73

/*

Companies: Amazon, Apple, Facebook, Google, Microsoft, Uber.

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:

Input: nums = [1,2,3,1]
Output: true
Explanation: The element 1 occurs at the indices 0 and 3.

Example 2:

Input: nums = [1,2,3,4]
Output: false
Explanation: All elements are distinct.

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true


Approach 1: Using HashSet.

We can use a HashSet to keep track of the elements we have seen as we iterate through the array. 

If we encounter an element that is already in the HashSet, it means we have found a duplicate and we can return true. 

If we finish iterating through the array without finding any duplicates, we return false.

Dry Run:
Input: nums = [1,2,3,1]

1. Initialize an empty HashSet called seen.
2. Iterate through the array:
    - For the first element (1), add it to the HashSet. seen = {1}
    - For the second element (2), add it to the HashSet. seen = {1, 2}
    - For the third element (3), add it to the HashSet. seen = {1, 2, 3}
    - For the fourth element (1), we check if it is already in the HashSet. It is, so we return true.
3. The output is true, which matches the expected result.


Time Complexity: O(n), where n is the length of the input array. We need to iterate through the array once.

Space Complexity: O(n) in the worst case, if all elements in the array are distinct, we will store all of them in the HashSet.


*/


// Code:

import java.util.HashSet;
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}


/* Approach 2: Sorting the array.

We can sort the array and then check for adjacent elements. 

If we find any two adjacent elements that are the same, it means we have found a duplicate and we can return true. 

If we finish checking all adjacent elements without finding any duplicates, we return false.


Code:
 
import java.util.Arrays;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}


Time Complexity: O(n log n) due to the sorting step.

Space Complexity: O(1) if we ignore the space used by the sorting algorithm, otherwise O(n) if the sorting algorithm uses additional space.


*/