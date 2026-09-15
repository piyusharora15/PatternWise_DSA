// Problem Link: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters?envType=problem-list-v2&envId=wh88bf73

/*

Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).

Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:
Input: s = "abc"
Output: 1


Approach: Using Sliding Window.

I will use a sliding window approach.

The main idea is to maintain a window [left, right] and keep track of the frequency of a, b, and c inside that window.

I will expand the window by moving the right pointer and adding the current character to the frequency array.

Whenever the current window contains at least one a, one b, and one c, the window becomes valid.

Now, instead of counting only the current substring, I can count multiple valid substrings at once.

Suppose the current valid window is: [left ... right]

Since this window already contains all three characters, any substring that starts at left and ends at right or any position after right will also contain all three characters.

There are exactly: n - right such possible ending positions.

So I add: answer += n - right to the answer.

After that, I move the left pointer forward and remove s[left] from the frequency array. I continue shrinking the window as long as it still contains all three characters.

This is important because there may be multiple valid starting positions for the same right pointer.

Once the window becomes invalid, I stop shrinking and continue expanding the window by moving right.


Dry Run:

Input: s = "abcabc"

Length: n = 6
Initial: left = 0, right = 0, countA = 0, countB = 0, countC = 0, answer = 0

Right = 0
Character: a
Window: [a]
Counts: a = 1, b = 0, c = 0
Not valid.
answer = 0

Right = 1
Add b.
Window: [a b]
Counts: a = 1, b = 1, c = 0
Still invalid.
answer = 0

Right = 2
Add c.
Window: [a b c]
Counts:

a = 1
b = 1
c = 1
Now valid! 🎯

We use: answer += n - right
Therefore: answer += 6 - 2 += 4
So: answer = 4

These 4 substrings are:  abc, abca, abcab, abcabc

Now shrink the window.
Remove s[left]: remove 'a'
Window becomes: [b c]
Counts:

a = 0
b = 1
c = 1
Invalid.
Move: left = 1

Right = 3
Add: a
Window: [b c a]
Counts:

a = 1
b = 1
c = 1
Valid.
Now: answer += n - right += 6 - 3 += 3
Answer: 4 + 3 = 7
The substrings starting at left = 1 are: bca, bcab, bcabc
All contain all three characters.
Now shrink.
Remove b: [c a]
No b.
Window becomes invalid.
left = 2

Right = 4
Add: b
Window: [c a b]
Valid.
answer += 6 - 4 += 2
Answer: 7 + 2 = 9
Valid substrings:cab, cabc
Shrink: remove c
Window: [a b]
Invalid.
left = 3

Right = 5

Add: c
Window: [a b c]
Valid.
answer += 6 - 5 += 1
Answer: 9 + 1 = 10
Shrink: remove a
Window: [b c]
Invalid.
Done.

Final answer: 10

Time Complexity: O(n), where n is the length of the string s.
Although there is a for loop and a nested while loop, the complexity is still O(n)
The right pointer moves from left to right only once, and the left pointer also moves from left to right only once.
Therefore, each character is added to and removed from the window at most once.
So the overall time complexity is O(n).

Space Complexity: O(1), since we are using a fixed-size array of size 3 to store the counts of characters a, b, and c.

*/

// Code:

class NoOfSubstringsContainingAll3Characters {
    public int numberOfSubstrings(String s) {

        int n = s.length();

        int left = 0;
        int answer = 0;

        int[] count = new int[3];

        for (int right = 0; right < n; right++) {

            // Add current character
            count[s.charAt(right) - 'a']++;

            // While window contains a, b and c
            while (count[0] > 0 &&
                   count[1] > 0 &&
                   count[2] > 0) {

                // All substrings starting at 'left'
                // and ending at right or later are valid
                answer += n - right;

                // Remove left character
                count[s.charAt(left) - 'a']--;

                left++;
            }
        }

        return answer;
    }
}