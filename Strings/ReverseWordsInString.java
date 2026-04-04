// Problem Link: https://leetcode.com/problems/reverse-words-in-a-string?envType=problem-list-v2&envId=two-pointers

/*

Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

*/

// Brute Force Approach: 
// Manually parse the string 's' character by character to identify words, store them in a list, and then construct the result by reversing the order of words while ensuring single spaces between them.

/* Code:

class Solution {
    public String reverseWords(String s) {
       List<String> words = new ArrayList<>();
       int i = 0, n = s.length();
       while(i < n){
        while(i < n && s.charAt(i) == ' ') i++;
        if(i >= n) break;
        int j = i;
        while(j < n && s.charAt(j) != ' ') j++;
        words.add(s.substring(i,j));
        i = j;
       }
       Collections.reverse(words);
       return String.join(" ",words);
    }
}

*/

// Time Complexity: O(n) where n is the length of the string s.
// Space Complexity: O(m) where m is the number of words in the string s.

// Better Approach: Split the string 's' using built-in functions to get the words, reverse the list of words, and then join them back together with single spaces.

/*  Code: 
class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+"); // Split the string by one or more spaces to handle multiple spaces between words.
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
*/

// Time Complexity: O(n) where n is the length of the string s.
// Space Complexity: O(m) where m is the number of words in the string s.

// Optimal Approach: Two Pointer Technique to reverse the entire string and then reverse each word individually in place, while also handling spaces appropriately.
// Convert the string to a character array for in-place manipulation. Clean up spaces, reverse the entire array, and then reverse each word.

/*  Code:
class ReverseWordsInString {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        reverse(chars, 0, n - 1);
        int i = 0;
        for (int l = 0; l < n; ++l) {
            if (chars[l] != ' ') {
                if (i != 0) chars[i++] = ' ';
                int r = l;
                while (r < n && chars[r] != ' ') chars[i++] = chars[r++];
                reverse(chars, i - (r - l), i-1);
                l = r;
            }
        }
        return new String(chars, 0, i);
    }
    
    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
}

*/

// Time Complexity: O(n) where n is the length of the string s.
// Space Complexity: O(1) since we are manipulating the string in place.

/*

Optimal Approach 2:

I’ll iterate through the string, extract words, store them, reverse the list, and join them with a single space.

*/

// Code:
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ReverseWordsInString {
    public String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        int i = 0;
        int n = s.length();
        while(i < n) {
            // skip spaces
            while(i < n && s.charAt(i) == ' ') i++;
            if(i < n) {
                int j = i;
                // find word end
                while(j < n && s.charAt(j) != ' ') j++;
                words.add(s.substring(i, j));
                i = j;
            }
        }
        Collections.reverse(words);
        return String.join(" ", words);
    }
}

// Time Complexity: O(n) where n is the length of the string s.
// Space Complexity: O(m) where m is the number of words in the string s.