// Problem Link: https://leetcode.com/problems/add-binary?envType=daily-question&envId=2026-02-15

// Approach: We can use two pointers to traverse both strings from the end, adding corresponding bits along with any carry from the previous addition. We will keep track of the carry and build the result string as we go.We will continue this process until we have traversed both strings and there is no carry left. Finally, we will reverse the result string to get the final answer.

// Code:
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            result.append(sum % 2); // Append the current bit
            carry = sum / 2; // Update the carry
        }

        return result.reverse().toString(); // Reverse the result to get the final answer
    }
}

// Time Complexity: O(max(m, n)), where m and n are the lengths of the input strings a and b respectively. We traverse both strings once.
// Space Complexity: O(max(m, n)), as we are using a StringBuilder to store the result, which in the worst case can be as long as the longer of the two input strings plus one additional character for a possible carry.