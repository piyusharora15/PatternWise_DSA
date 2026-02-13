// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array?envType=study-plan-v2&envId=top-interview-150

// Approach: Two Pointers.
// We can use two pointers to keep track of the current position in the array and the position of the last unique element. We iterate through the array and whenever we find a new unique element, we move it to the position of the last unique element and update the pointer for the last unique element.We continue this process until we have processed all elements in the array. Finally, we return the number of unique elements.

// Code:
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int uniqueCount = 1; // Start with the first element as unique
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueCount - 1]) {
                nums[uniqueCount] = nums[i]; // Move the unique element to the correct position
                uniqueCount++; // Increment the count of unique elements
            }
        }
        
        return uniqueCount; // Return the number of unique elements
    }
}

// Time Complexity: O(n), where n is the length of the input array. We traverse the array once.
// Space Complexity: O(1), as we are modifying the input array in place and using only a constant amount of extra space for the pointers.