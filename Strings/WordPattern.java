// Problem Link: https://leetcode.com/problems/word-pattern?envType=study-plan-v2&envId=top-interview-150

// Approach: We can use two hash maps to store the mapping of characters in the pattern and the corresponding words in the string. We will iterate through both the pattern and the string simultaneously, checking if the current character in the pattern has a corresponding word in the string. If it does, we will check if it matches the current word in the string. If it doesn't, we will return false. If it doesn't have a corresponding word, we will add the mapping to both hash maps. Finally, we will return true if we successfully iterate through both the pattern and the string.

// Code:
import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                charToWord.put(c, word);
            }

            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != c) {
                    return false;
                }
            } else {
                wordToChar.put(word, c);
            }
        }

        return true;
    }
}

// Time Complexity: O(n), where n is the length of the pattern (or the number of words in the string).
// Space Complexity: O(n), where n is the number of unique characters in the pattern (or the number of unique words in the string).