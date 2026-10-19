/*

Problem Link: https://leetcode.com/problems/permutation-in-string

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Approach: Using Sliding Window Technique.

1. First, we will create a frequency array for the characters in s1. This will help us keep track of how many times each character appears in s1.

2. Next, we will create a sliding window of size equal to the length of s1 and slide it over s2. 
For each window, we will create a frequency array for the characters in the current window of s2.

3. We will then compare the frequency array of the current window with the frequency array of s1.

4. If the two frequency arrays are equal, it means that the current window is a permutation of s1, and we return true.

5. If we slide the window over the entire length of s2 and do not find any matching frequency arrays, we return false.

Dry Run:
Input: s1 = "ab", s2 = "eidbaooo"
1. Create frequency array for s1: [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
2. Create a sliding window of size 2 (length of s1) and slide it over s2:
   - Window 1: "ei" -> Frequency array: [0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    - Window 2: "id" -> Frequency array: [0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    - Window 3: "db" -> Frequency array: [0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    - Window 4: "ba" -> Frequency array: [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    - Window 5: "ao" -> Frequency array: [1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    - Window 6: "oo" -> Frequency array: [0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
3. Compare the frequency arrays of each window with the frequency array of s1. 
   - Window 4 matches the frequency array of s1, so we return true.

Time Complexity: O(n), where n is the length of s2. We are sliding the window over s2 once.

Space Complexity: O(1), since the frequency arrays have a fixed size of 26 (for each letter in the alphabet).

*/

// Code:

class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Freq = new int[26];
        int[] windowFreq = new int[26];

        // Create frequency array for s1
        for (char c : s1.toCharArray()) {
            s1Freq[c - 'a']++;
        }

        // Create initial window frequency array for the first window in s2
        for (int i = 0; i < s1.length(); i++) {
            windowFreq[s2.charAt(i) - 'a']++;
        }

        // Check if the initial window matches the frequency of s1
        if (matches(s1Freq, windowFreq)) {
            return true;
        }

        // Slide the window over s2
        for (int i = s1.length(); i < s2.length(); i++) {
            // Add the next character to the window
            windowFreq[s2.charAt(i) - 'a']++;
            // Remove the character that is no longer in the window
            windowFreq[s2.charAt(i - s1.length()) - 'a']--;

            // Check if the current window matches the frequency of s1
            if (matches(s1Freq, windowFreq)) {
                return true;
            }
        }

        return false;
    }

    private boolean matches(int[] s1Freq, int[] windowFreq) {
        for (int i = 0; i < 26; i++) {
            if (s1Freq[i] != windowFreq[i]) {
                return false;
            }
        }
        return true;
    }
}