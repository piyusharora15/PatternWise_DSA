/*

Problem Link: 
https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range

You are given a 0-indexed array of string words and two integers left and right.
A string is called a vowel string if it starts with a vowel character and ends with a vowel character where vowel characters are 'a', 'e', 'i', 'o', and 'u'.
Return the number of vowel strings words[i] where i belongs to the inclusive range [left, right].

Example 1:

Input: words = ["are","amy","u"], left = 0, right = 2
Output: 2

Example 2:

Input: words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
Output: 3

*/

/*

Approach:
1. Initialize a count variable to keep track of the number of vowel strings.
2. Iterate through the words array from index left to right.
3. For each word, check if the first and last characters are vowels.
4. If both characters are vowels, increment the count variable.
5. Return the count variable as the result.

*/

// Code:

class CountVowelStrings {
    public int vowelStrings(String[] words, int left, int right) {
        int c = 0;
        for(int i=left;i<=right;i++) {
            String word = words[i];
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);
            if(isVowel(first) && isVowel(last)) {
                c++;
            }
        }
        return c;
    }
    public boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
}

// Time Complexity: O(n) where n is the number of words in the range [left, right].
// Space Complexity: O(1) since we are using only a constant amount of extra space.