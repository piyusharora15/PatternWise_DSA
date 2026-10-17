/*

Problem Link: https://leetcode.com/problems/longest-common-prefix?envType=problem-list-v2&envId=dtka3dwv

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Approach 1: Using Horizontal Scanning.

1. First, we take the first string as the initial prefix.

2. Then, we compare this prefix with the next string in the array. 
If the next string does not start with the current prefix, we reduce the prefix by removing the last character and repeat this process until we find a common prefix or the prefix becomes empty.

3. We continue this process for all strings in the array.

Dry Run:
Input: strs = ["flower","flow","flight"]
Step 1: prefix = "flower"
Step 2: prefix = "flow" (since "flow" starts with "flower", we keep it)
Step 3: prefix = "fl" (since "flight" starts with "flow", we keep it)
Output: "fl"

Code:

class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
}

Time Complexity: O(S), where S is the sum of all characters in all strings. 
In the worst case, we have to compare all characters of all strings.

Space Complexity: O(1), since we are using only a constant amount of extra space.

Approach 2: Using Vertical Scanning.

1. First, we take the first string as the initial prefix.

2. Then, we compare each character of the prefix with the corresponding character of the other strings in the array.

3. If all strings have the same character at a given position, we include that character in the common prefix. Otherwise, we stop and return the current common prefix.

4. We continue this process for all characters of the first string.

Dry Run:
Input: strs = ["flower","flow","flight"]
Step 1: prefix = "flower"
Step 2: Compare 'f' with 'f' and 'f' (all match)
Step 3: Compare 'l' with 'l' and 'l' (all match)
Step 4: Compare 'o' with 'o' and 'i' (mismatch, stop)
Output: "fl"

Time Complexity: O(S), where S is the sum of all characters in all strings.
We may have to compare all characters of all strings in the worst case.

Space Complexity: O(1), since we are using only a constant amount of extra space.

*/

// Code:

class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        
        return strs[0];
    }
}