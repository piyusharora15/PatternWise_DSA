// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array?envType=study-plan-v2&envId=top-interview-150

/*

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.

The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.

Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).


*/

// Approach: Two Pointers.

// We can use two pointers to keep track of the current position in the array and the position of the last unique element. 
// We iterate through the array and whenever we find a new unique element, we move it to the position of the last unique element and update the pointer for the last unique element.
// We continue this process until we have processed all elements in the array. Finally, we return the number of unique elements.


// Code:
class RemoveDuplicatesFromSortedArray {
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