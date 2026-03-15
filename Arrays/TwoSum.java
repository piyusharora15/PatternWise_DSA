/*

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]

*/

// Solution 1: Brute Force
// We can use two nested loops to check every pair of numbers in the array and see if they add up to the target. This approach has a time complexity of O(n^2).

// Solution 2: Hash Map
// We can use a hash map to store the values we have seen so far and their indices. 
// For each element in the array, we check if the complement (target - current element) exists in the hash map. 
// If it does, we have found our pair. This approach has a time complexity of O(n).

// Code:
import java.util.HashMap;
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
}

// Time Complexity: O(n) - We traverse the array once.
// Space Complexity: O(n) - In the worst case, we could be storing all n elements in the hash map.