// Problem Link: https://leetcode.com/problems/string-compression?envType=problem-list-v2&envId=wem0bhs2

// Approach: Two Pointers.
// We will maintain two pointers, one for reading the input array and another for writing the compressed string. We will iterate through the input array and count the occurrences of each character. When we encounter a different character, we will write the current character and its count (if greater than 1) to the output array. Finally, we will return the length of the compressed string.We will also handle the case when the count is greater than 9, as we need to split it into multiple characters.We will use a StringBuilder to build the compressed string and then convert it back to a character array before returning the length.

// Code:
class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 0) return 0;

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                compressed.append(chars[i - 1]);
                if (count > 1) {
                    compressed.append(count);
                }
                count = 1;
            }
        }

        // Handle the last character
        compressed.append(chars[n - 1]);
        if (count > 1) {
            compressed.append(count);
        }

        // Convert the compressed string back to a character array
        char[] result = compressed.toString().toCharArray();
        System.arraycopy(result, 0, chars, 0, result.length);

        return result.length;
    }
}

// Time Complexity: O(n), where n is the length of the input array. We iterate through the array once to count the characters and build the compressed string.

// Space Complexity: O(n) in the worst case, when all characters are different and we need to store the compressed string. However, in practice, the space used will be less than n due to compression.