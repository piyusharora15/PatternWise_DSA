// Problem Link: https://leetcode.com/problems/remove-element?envType=study-plan-v2&envId=top-interview-150

/*

Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.


Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).

*/

// Approach: Two Pointers.
// We will maintain two pointers, one for iterating through the array and another for keeping track of the position to place the next non-val element. 
// Whenever we encounter an element that is not equal to val, we will place it at the position indicated by the second pointer and increment that pointer. 
// Finally, the second pointer will indicate the new length of the array after removing all occurrences of val.

// Code:
class RemoveElement {
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