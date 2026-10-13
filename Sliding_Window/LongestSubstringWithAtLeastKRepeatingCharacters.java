/*

Problem Link: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters?envType=problem-list-v2&envId=wh88bf73

Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

If no such substring exists, return 0.

Example 1:
Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


Approach 1: Using Sliding Window Technique.

Core intuition:

A substring is valid if every distinct character in it appears at least k times.

For example:
s = "aababb"
k = 3

The whole string isn't valid because a occurs 3 times and b occurs 3 times? Let's count carefully: a occurs 3 times and b occurs 3 times, so the whole string is valid. Its length is 6.

The challenge is that when we expand or shrink a sliding window, we cannot simply decide whether to move the left or right pointer based on a single character. 
The condition depends on all distinct characters and their frequencies.

The trick: Fix the number of distinct characters.

Suppose we try windows containing:
- Exactly 1 distinct character
- Exactly 2 distinct characters
- Exactly 3 distinct characters
- … up to 26 distinct characters

For each target distinct-character count, use sliding window to find the longest window where:
1. The window contains at most that many distinct characters.
2. Every character in the window appears at least k times.

To check condition 2 efficiently, track:
- unique: number of distinct characters currently in the window.
- countAtLeastK: number of distinct characters whose frequency is at least k.
- freq[26]: frequency of each character.

When:
unique == targetUnique
&& countAtLeastK == targetUnique
the current window is valid.

Why do we iterate over the number of distinct characters?

A normal sliding window cannot easily decide whether a window is invalid because of a low-frequency character or because it contains too many distinct characters. 
By fixing the target number of distinct characters, we gain a clear shrinking condition:

while (unique > targetUnique)

Then we check whether every distinct character appears at least k times:

unique == targetUnique
&& countAtLeastK == targetUnique


Dry Run:
Input:
s = "ababbc"
k = 2
The correct answer is 5, because "ababb" contains a twice and b three times.
We'll focus on targetUnique = 2, because the answer substring contains exactly two distinct characters.

Initial state:
left = 0
unique = 0
countAtLeastK = 0
freq = all zeroes

Step 1: right = 0, character = 'a'
Window: "a"
- a frequency changes from 0 to 1.
- unique = 1
- countAtLeastK = 0
Not valid yet: a occurs fewer than twice.

Step 2: right = 1, character = 'b'
Window: "ab"
- b frequency changes from 0 to 1.
- unique = 2
- countAtLeastK = 0
Not valid yet.

Step 3: right = 2, character = 'a'
Window: "aba"
- a frequency changes from 1 to 2.
- countAtLeastK = 1, because a now meets the requirement.
- unique = 2
Not valid yet: b occurs only once.

Step 4: right = 3, character = 'b'
Window: "abab"
- b frequency changes from 1 to 2.
- countAtLeastK = 2
- unique = 2
Now:
unique == targetUnique       → true
countAtLeastK == targetUnique → true

Window length = 3 - 0 + 1 = 4.  
answer = 4

Step 5: right = 4, character = 'b'
Window: "ababb"
- b frequency changes from 2 to 3.
- countAtLeastK stays at 2.
- unique stays at 2.
The window remains valid.
Window length = 4 - 0 + 1 = 5.
answer = 5

Step 6: right = 5, character = 'c'
Temporary window: "ababbc"
- c frequency changes from 0 to 1.
- unique = 3, which exceeds targetUnique = 2.
Shrink from the left until there are at most two distinct characters:
- Remove a: its frequency goes from 2 to 1, so countAtLeastK decreases from 2 to 1.
- left becomes 1.
- The remaining window is "babbc", which still has three distinct characters.
- Remove the first b: its frequency goes from 3 to 2. b still meets the requirement.
- left becomes 2.
- The remaining window is "abbc", with three distinct characters.
- Remove the next a: its frequency goes from 1 to 0, so unique decreases to 2.
- left becomes 3.
The window is now "bbc".
It has two distinct characters, but c occurs only once, so it isn't valid. The best answer remains 5.

Final answer: 5

Code:

class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        int answer = 0;
        for (int targetUnique = 1; targetUnique <= 26; targetUnique++) { // Try every possible number of distinct characters.
            int[] freq = new int[26];
            int left = 0;
            int unique = 0;
            int countAtLeastK = 0;
            for (int right = 0; right < n; right++) {
                int r = s.charAt(right) - 'a';
                if (freq[r] == 0) {  // Add the new character.
                    unique++;
                }
                freq[r]++;

                if (freq[r] == k) {
                    countAtLeastK++;
                }

                while (unique > targetUnique) { // Shrink until the window has at most targetUnique distinct characters.
                    int l = s.charAt(left) - 'a';
                    if (freq[l] == k) {
                        countAtLeastK--;
                    }
                    freq[l]--;
                    if (freq[l] == 0) {
                        unique--;
                    }
                    left++;
                }
                
                if (unique == targetUnique
                        && countAtLeastK == targetUnique) { // Every distinct character meets the k requirement.
                    answer = Math.max(answer, right - left + 1);
                }
            }
        }

        return answer;
    }
}

Complexity analysis
Let n be the length of s and assume lowercase English letters.

- Time: O(26n), which simplifies to O(n) for a fixed 26-character alphabet. Each target distinct-character count processes the string with a sliding window, and there are 26 targets.

- Auxiliary space: O(1), because the frequency array has a fixed size of 26.

Optimal Approach: Divide and Conquer.

Understand the problem.

Given a string s and an integer k, return the length of the longest substring in which every distinct character appears at least k times.

Example 1:
s = "aaabb"
k = 3
Output = 3

"aaa" is valid because a occurs three times. The full string is invalid because b occurs only twice.

Example 2:
s = "ababbc"
k = 2
Output = 5

The substring "ababb" is valid:
- a occurs 2 times.
- b occurs 3 times.
Therefore, the answer is 5.

Core intuition:

Imagine: s = "aaabbbcddeee" , k = 3
Character frequencies in the entire string:

Character	Frequency	Valid?
   a	        3	     Yes
   b	        3	     Yes
   c	        1	     No
   d	        2	     No
   e	        3	     Yes

Neither c nor d can belong to a valid substring of this entire string, because their total frequencies are already below k.

We can split at these characters:
"aaabbb" | c | "dd" | "eee"


Now solve the independent segments:
- "aaabbb" → valid, length 6
- "dd" → invalid, length 0
- "eee" → valid, length 3
The maximum is 6.

Important: This split is valid because a character whose frequency in the current segment is less than k cannot belong to any valid substring contained in that segment.

Step-by-step algorithm:

1. If the current substring's length is less than k, return 0.
2. Count the frequency of every character in the current substring.
3. Find a character whose frequency is less than k.
4. Split the substring at that character and recursively solve the left and right parts.
5. Return the maximum answer from both parts.
6. If every character appears at least k times, the entire substring is valid, so return its length.

Dry Run:
Input: s = "aaabb", k = 3

Initial substring: "aaabb".

Step 1: Count frequencies

Character	Frequency
  a	           3
  b	           2

Since b occurs fewer than 3 times, no valid substring can include b within this segment.

Split at the first b:
"aaa" | "bb"

Step 2: Solve "aaa"
Frequency: a = 3
Every character meets the requirement. Return the substring length:
solve("aaa") = 3

Step 3: Solve "bb"
Its length is 2, which is less than k = 3.
solve("bb") = 0

Step 4: Combine results
max(3, 0) = 3

Recursion tree
"aaabb" → split at b
"aaa" -> 3
"bb" -> 0

Final answer: 3


Input: s = "ababbc", k = 2

Step 1: Count frequencies in the entire string
a = 2
b = 3
c = 1

c occurs only once, so it cannot be part of any valid substring. Split at c:
"ababb" | "c"

Step 2: Solve "ababb"
Count the frequencies again:
a = 2
b = 3

Both characters occur at least twice. No split is necessary.
solve("ababb") = 5

Step 3: Solve "c"
Its length is 1, which is less than k = 2.
solve("c") = 0

Step 4: Combine results
max(5, 0) = 5

Final answer: 5, corresponding to the substring "ababb".

Complexity analysis:

Let n be the length of the string.

- Time complexity: O(26n), which simplifies to O(n) for this problem because the alphabet contains only 26 lowercase English letters. 
Each recursive level processes disjoint segments, and the recursion depth is bounded by the number of distinct letters that can be used as split points.

- Auxiliary space: O(1), because the frequency array has a fixed size of 26.


*/

// Code:

class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        return solve(s, 0, s.length() - 1, k);
    }

    private int solve(String s, int left, int right, int k) {
        // Empty or too-short substring cannot be valid.
        if (right - left + 1 < k) {
            return 0;
        }

        int[] freq = new int[26];

        // Count character frequencies in the current substring.
        for (int i = left; i <= right; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Split at a character that occurs fewer than k times.
        for (int i = left; i <= right; i++) {
            if (freq[s.charAt(i) - 'a'] < k) {
                int leftPart = solve(s, left, i - 1, k);
                int rightPart = solve(s, i + 1, right, k);

                return Math.max(leftPart, rightPart);
            }
        }

        // Every character satisfies the frequency requirement.
        return right - left + 1;
    }
}