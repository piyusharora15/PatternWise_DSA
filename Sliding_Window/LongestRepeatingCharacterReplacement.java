/*

Problem Link: https://leetcode.com/problems/longest-repeating-character-replacement?envType=problem-list-v2&envId=auswip1r

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


Approach: Using Sliding Window Technique.

1. Initialize a frequency array of size 26 to keep track of the count of each character in the current window.

2. Use two pointers, left and right, to represent the current window in the string.

3. Expand the right pointer to include new characters in the window and update their counts in the frequency array.

4. Calculate the maximum frequency of any character in the current window.

5. If the size of the current window minus the maximum frequency is greater than k, it means we need to shrink the window from the left to maintain the condition.

6. Keep track of the maximum length of the window that satisfies the condition.

7. Return the maximum length found.

Dry Run:

Input: s = "AABABBA", k = 1

- Initialize frequency array: freq = [0, 0, ..., 0] (size 26)
- Initialize left = 0, maxCount = 0, maxLength = 0  

- Start iterating with right pointer:

  - right = 0, char = 'A', freq['A']++, maxCount = 1, window size = 1, maxLength = 1
  - right = 1, char = 'A', freq['A']++, maxCount = 2, window size = 2, maxLength = 2
  - right = 2, char = 'B', freq['B']++, maxCount = 2, window size = 3, maxLength = 3
  - right = 3, char = 'A', freq['A']++, maxCount = 3, window size = 4, maxLength = 4
  - right = 4, char = 'B', freq['B']++, maxCount = 3, window size = 5, (5 - 3) > k => shrink window
    - left = 0, char = 'A', freq['A']--, left++
    - left = 1, char = 'A', freq['A']--, left++
    - Now window size is 3 (right - left + 1), maxLength remains 4
  - right = 5, char = 'B', freq['B']++, maxCount = 3, window size = 4, (4 - 3) <= k => maxLength remains 4
  - right = 6, char = 'A', freq['A']++, maxCount = 3, window size = 5, (5 - 3) > k => shrink window
    - left = 2, char = 'B', freq['B']--, left++
    - Now window size is 4 (right - left + 1), maxLength remains 4

Return maxLength = 4

Time Complexity: O(n), where n is the length of the string. 
Each character is processed at most twice (once when added to the window and once when removed).

Space Complexity: O(1), since the frequency array has a fixed size of 26 regardless of the input size.

*/

// Code:

class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26]; // Frequency array for characters A-Z
        int left = 0; // Left pointer of the sliding window
        int maxCount = 0; // Maximum frequency of a single character in the current window
        int maxLength = 0; // Maximum length of the valid window

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            freq[currentChar - 'A']++; // Increment frequency of the current character
            maxCount = Math.max(maxCount, freq[currentChar - 'A']); // Update maxCount

            // If the number of characters to change exceeds k, shrink the window from the left
            while ((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--; // Decrement frequency of the left character
                left++; // Move the left pointer to the right
            }

            // Update maxLength with the size of the current valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength; // Return the maximum length found
    }
}