// Problem Link: https://leetcode.com/problems/remove-element?envType=study-plan-v2&envId=top-interview-150

// Approach: Two Pointers.
// We will maintain two pointers, one for iterating through the array and another for keeping track of the position to place the next non-val element. Whenever we encounter an element that is not equal to val, we will place it at the position indicated by the second pointer and increment that pointer. Finally, the second pointer will indicate the new length of the array after removing all occurrences of val.

// Code:
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // Pointer for the position to place the next non-val element
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; // Place the non-val element at position k
                k++; // Increment the pointer for the next position
            }
        }
        return k; // Return the new length of the array
    }
}

// Time Complexity: O(n), where n is the length of the input array.
// Space Complexity: O(1), as we are modifying the array in place and using only a constant amount of extra space.