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