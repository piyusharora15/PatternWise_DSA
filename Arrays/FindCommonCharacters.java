// Problem Link: https://leetcode.com/problems/find-common-characters?envType=problem-list-v2&envId=wh88bf73

/*

Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]

*/

// Approach: We can use a frequency array to count the occurrences of each character in the first string. Then, for each subsequent string, we can update the frequency array by taking the minimum count of each character. Finally, we can construct the result based on the frequency array.

// Code:
import java.util.ArrayList;
import java.util.List;

public class FindCommonCharacters {
    public List<String> commonChars(String[] words) {
        int[] freq = new int[26];
        // Initialize frequency array with the first word
        for (char c : words[0].toCharArray()) {
            freq[c - 'a']++;
        }
        // Update frequency array with minimum counts from subsequent words
        for (int i = 1; i < words.length; i++) {
            int[] temp = new int[26];
            for (char c : words[i].toCharArray()) {
                temp[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                freq[j] = Math.min(freq[j], temp[j]);
            }
        }
        // Construct result based on frequency array
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i]; j++) {
                result.add(String.valueOf((char) ('a' + i)));
            }
        }
        return result;
    }
}

// Time Complexity: O(N * M) where N is the number of words and M is the average length of the words.
// Space Complexity: O(1) since the frequency array has a fixed size of 26.