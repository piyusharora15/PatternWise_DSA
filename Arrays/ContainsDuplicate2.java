// Problem Link: https://leetcode.com/problems/contains-duplicate-ii?envType=problem-list-v2&envId=wh88bf73

/*

Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

*/

// Approach: HashSet.
// We can maintain a sliding window of size k using a HashSet.
// As we iterate through the array, we add elements to the HashSet. 
// If we encounter an element that is already in the HashSet, it means we have found a duplicate within the range of k, and we return true. 
// If the size of the HashSet exceeds k, we remove the element that is sliding out of the window.

// Code:
import java.util.HashSet;
public class ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}

// Time Complexity: O(n), where n is the number of elements in the array. We traverse the array once.
// Space Complexity: O(min(n, k)), where n is the number of elements in the array and k is the size of the sliding window. In the worst case, we may have to store k elements in the HashSet.