/*

Problem Link: https://leetcode.com/problems/substring-with-concatenation-of-all-words?envType=problem-list-v2&envId=wh88bf73

You are given a string s and an array of strings words. All the strings of words are of the same length.

A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.

For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. 
"acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation:
The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Explanation:
There is no concatenated substring.

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
Explanation:
The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].


"The key observation in this problem is that all the words have the same length."
"So instead of checking every possible starting index independently, I can process the string in chunks of wordLength."


Approach: Using Sliding Window and HashMap.

1. First, I'll create a frequency map called required that stores how many times each word is supposed to occur.
2. Then I'll run wordLength different sliding windows, one for each possible alignment. For example, if the word length is 3, I'll process indices 0,3,6..., then 1,4,7..., and finally 2,5,8....
3. For each window, I'll maintain another frequency map called window, which stores the words currently present in the window. 
I'll also maintain left, right, and the number of words currently in the window.
4. Whenever I encounter a word that doesn't exist in the required map, the current window becomes invalid, so I clear the window and start again from the next word.
5. If the word is valid, I add it to the current window. 
If its frequency exceeds the required frequency, I move the left pointer forward word by word until the frequency becomes valid again.
6. Finally, whenever the current window contains exactly wordCount words, it means we have a valid concatenation, so I add left to the answer. 
Then I move the left pointer by one word to continue searching for overlapping answers.
7. This avoids checking every candidate substring independently. 
Each word-sized chunk is added and removed from the sliding window at most once for each alignment, giving us approximately O(N) time with O(K) auxiliary space, where N is the length of the string and K is the number of words.

Dry Run:

Input: s = "barfoofoobarthe" , words = ["bar", "foo", "the"]

Chunks:
bar | foo | foo | bar | the
Required:
bar → 1
foo → 1
the → 1

Initial
left = 0
right = 0
window = {}
count = 0

Read bar
window = {bar=1}
count = 1

Read foo
window = {bar=1, foo=1}
count = 2

Read second foo
window = {bar=1, foo=2}
count = 3
But foo is allowed only once.
So shrink from left:
bar | foo | foo
^
left
Remove bar:
window = {bar=0, foo=2}
left = 3
count = 2
Still too many foo.
Remove first foo:
window = {bar=0, foo=1}
left = 6
count = 1
Now valid.

Read bar
foo | bar
window = {foo=1, bar=1}
count = 2

Read the
foo | bar | the
window = {
    foo=1,
    bar=1,
    the=1
}
Now:
count == wordCount
3 == 3

Therefore:
answer = [6]

And indeed:  s.substring(6, 15) = "foobarthe"
which is: foo + bar + the
So index 6 is correct.

Time Complexity: O(N × L), where N is the length of the string s and L is the length of each word.

Space Complexity: O(M), where M is the number of unique words in the words array (for the HashMap).

*/

// Code:

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        if (s.length() < totalLength) {
            return result;
        }

        // Frequency of words required
        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Try every possible alignment
        for (int offset = 0; offset < wordLength; offset++) {

            int left = offset;
            int right = offset;
            int count = 0;

            Map<String, Integer> window = new HashMap<>();

            while (right + wordLength <= s.length()) {

                String word = s.substring(right, right + wordLength);
                right += wordLength;

                // Word doesn't exist in required list
                if (!required.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                // Add word to current window
                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // Too many occurrences of this word
                while (window.get(word) > required.get(word)) {

                    String leftWord = s.substring(left, left + wordLength);
                    left += wordLength;

                    window.put(leftWord, window.get(leftWord) - 1);
                    count--;
                }

                // Exactly wordCount words
                if (count == wordCount) {
                    result.add(left);

                    // Move window forward by one word
                    String leftWord = s.substring(left, left + wordLength);
                    left += wordLength;

                    window.put(leftWord, window.get(leftWord) - 1);
                    count--;
                }
            }
        }

        return result;
    }
}