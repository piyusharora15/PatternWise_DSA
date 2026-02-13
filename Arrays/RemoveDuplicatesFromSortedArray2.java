// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii?envType=study-plan-v2&envId=top-interview-150

// Approach: Two Pointers.
// We will maintain two pointers, one for iterating through the array and another for keeping track of the position to place the next unique element. We will allow at most two occurrences of each element. If we encounter an element that is the same as the previous two elements, we will skip it.We will continue this process until we have processed all elements in the array. Finally, we will return the length of the modified array.

// Code:
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        
        int j = 2; // Pointer for the position to place the next unique element
        
        for (int i = 2; i < nums.length; i++) {
            // Check if the current element is the same as the previous two elements
            if (nums[i] != nums[j - 2]) {
                nums[j] = nums[i]; // Place the current element at position j
                j++; // Move the pointer for the next unique element
            }
        }
        
        return j; // Return the length of the modified array
    }
}

// Time Complexity: O(n), where n is the length of the input array.
// Space Complexity: O(1), as we are modifying the array in place and using only a constant amount of extra space.