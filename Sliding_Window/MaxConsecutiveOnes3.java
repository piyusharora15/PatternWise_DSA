/*

Problem Link: https://leetcode.com/problems/max-consecutive-ones-iii?envType=problem-list-v2&envId=dtka3dwv

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]

Approach: Using Sliding Window Technique.

1. First, we will initialize two pointers, left and right, both set to 0. These pointers will represent the current window of the array that we are examining.

2. We will also initialize a variable, zeroCount, to keep track of the number of 0's in the current window.

3. We will iterate through the array using the right pointer. 
For each element, we will check if it is a 0. If it is, we will increment the zeroCount.

4. If the zeroCount exceeds k, we will move the left pointer to the right until the zeroCount is less than or equal to k. 
This effectively shrinks the window from the left side.

5. At each step, we will calculate the length of the current window (right - left + 1) and update the maximum length found so far.

6. Finally, we will return the maximum length of consecutive 1's found.


Dry Run:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2

- Initialize left = 0, right = 0, zeroCount = 0, maxLength = 0
- right = 0, nums[right] = 1, zeroCount = 0, currentLength = 1, maxLength = 1
- right = 1, nums[right] = 1, zeroCount = 0, currentLength = 2, maxLength = 2
- right = 2, nums[right] = 1, zeroCount = 0, currentLength = 3, maxLength = 3
- right = 3, nums[right] = 0, zeroCount = 1, currentLength = 4, maxLength = 4
- right = 4, nums[right] = 0, zeroCount = 2, currentLength = 5, maxLength = 5
- right = 5, nums[right] = 0, zeroCount = 3 (exceeds k), move left to 1, zeroCount = 3, move left to 2, zeroCount = 3, move left to 3, zeroCount = 2, left = 4, currentLength = 2, maxLength = 5
- right = 6, nums[right] = 1, zeroCount = 2, currentLength = 3, maxLength = 5
- right = 7, nums[right] = 1, zeroCount = 2, currentLength = 4, maxLength = 5
- right = 8, nums[right] = 1, zeroCount = 2, currentLength = 5, maxLength = 5
- right = 9, nums[right] = 1, zeroCount = 2, currentLength = 6, maxLength = 6
- right = 10, nums[right] = 0, zeroCount = 3 (exceeds k), move left to 5, zeroCount = 2, currentLength = 6, maxLength = 6

Return maxLength = 6


Time Complexity: O(n), where n is the length of the input array. 
We traverse the array once with the right pointer, and the left pointer only moves forward, ensuring that each element is processed at most twice.

Space Complexity: O(1), as we are using a constant amount of extra space for variables like left, right, zeroCount, and maxLength.

*/

// Code:

class MaxConsecutiveOnes3 {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int zeroCount = 0;
        int maxLength = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}