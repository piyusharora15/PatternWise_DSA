// Problem Link: https://leetcode.com/problems/reverse-words-in-a-string-iii?envType=problem-list-v2&envId=wem0bhs2

// Approach: Two Pointers.
// We will use two pointers to reverse each word in the string. We will iterate through the string and whenever we encounter a space, we will reverse the word between the two pointers. Finally, we will reverse the last word after the loop.

// Code:
class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == ' ') {
                reverse(arr, left, right - 1);
                left = right + 1;
            }
        }
        reverse(arr, left, arr.length - 1);
        return new String(arr);
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}

// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(n), since we are using a character array to store the string.

// https://www.geeksforgeeks.org/problems/reverse-each-word-in-a-given-string1001/1

// You are given a string s. You need to reverse each word in it where the words are separated by spaces and return the modified string.

// Note: The string may contain leading or trailing spaces, or multiple spaces between two words. The returned string should only have a single space separating the words, and no extra spaces should be included.

// Code:
class Solution {
    public String reverseWords(String s) {
        // remove leading/trailing + multiple spaces
        String[] words = s.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(new StringBuilder(word).reverse());
            result.append(" ");
        }

        // remove last extra space
        return result.toString().trim();

    }
}
// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(n), since we are using a StringBuilder to store the result.