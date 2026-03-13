// Problem Link: https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side

/*

Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.

Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
Explanation: 
- index 0 --> the greatest element to the right of index 0 is index 1 (18).
- index 1 --> the greatest element to the right of index 1 is index 4 (6).
- index 2 --> the greatest element to the right of index 2 is index 4 (6).
- index 3 --> the greatest element to the right of index 3 is index 4 (6).
- index 4 --> the greatest element to the right of index 4 is index 5 (1).
- index 5 --> there are no elements to the right of index 5, so we put -1.
Example 2:

Input: arr = [400]
Output: [-1]
Explanation: There are no elements to the right of index 0.

*/

// Approach: We can iterate the array from the end and keep track of the maximum element seen so far. For each element, we replace it with the maximum element seen to its right. Finally, we set the last element to -1.

// Code:

public class LeaderInArray {
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int maxRight = -1; // Initialize maxRight to -1 for the last element
        for(int i=n-1;i>=0;i--) {
            int current = arr[i]; // Store the current element
            arr[i] = maxRight; // Replace current element with maxRight
            if(current > maxRight) { // Update maxRight if current element is greater
                maxRight = current;}
        }
        return arr; // Return the modified array
    }
}

// Time Complexity: O(n), where n is the length of the input array. We traverse the array once.
// Space Complexity: O(1), as we are modifying the input array in place and using only a constant amount of extra space for the maxRight variable.