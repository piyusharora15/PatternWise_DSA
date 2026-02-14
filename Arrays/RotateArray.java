// Problem Link: https://leetcode.com/problems/rotate-array?envType=study-plan-v2&envId=top-interview-150

// Approach 1: Brute Force
// We can rotate the array by one position to the right, k times. This approach has a time complexity of O(n*k) and a space complexity of O(1).

// Code:
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k is greater than n
        
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1]; // Store the last element
            // Shift all elements to the right
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last; // Place the last element at the front
        }
    }
}

// Approach 2: Using Extra Array.
// We can create a new array to store the rotated elements and then copy it back to the original array. This approach has a time complexity of O(n) and a space complexity of O(n).

// Code:
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k is greater than n
        int[] rotated = new int[n];
        
        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i]; // Place elements in the rotated position
        }
        
        // Copy the rotated array back to the original array
        for (int i = 0; i < n; i++) {
            nums[i] = rotated[i];
        }
    }
}

// Approach 3: Reverse the Array.
// We can reverse the entire array, then reverse the first k elements and the remaining n-k elements separately. This approach has a time complexity of O(n) and a space complexity of O(1).

// Code:
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k is greater than n
        
        // Reverse the entire array
        reverse(nums, 0, n - 1);
        // Reverse the first k elements
        reverse(nums, 0, k - 1);
        // Reverse the remaining n-k elements
        reverse(nums, k, n - 1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}