// Problem Link: https://leetcode.com/problems/longest-substring-without-repeating-characters?envType=study-plan-v2&envId=top-interview-150

// Approach 1: Brute Force.
// We can generate all possible substrings and check if they have repeating characters. This approach has a time complexity of O(n^3) in the worst case, where n is the length of the string.

// Code:
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = s.substring(i, j);
                if (hasAllUniqueCharacters(substring)) {
                    maxLength = Math.max(maxLength, substring.length());
                }
            }
        }

        return maxLength;
    }

    private boolean hasAllUniqueCharacters(String str) {
        Set<Character> charSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (charSet.contains(c)) {
                return false;
            }
            charSet.add(c);
        }
        return true;
    }
}

// Time Complexity: O(n^3) in the worst case, where n is the length of the string.
// Space Complexity: O(min(m, n)), where m is the size of the character set and n is the length of the string. In the worst case, we may need to store all characters in the substring.

// Approach 2: HashSet and Sliding Window.
// We can use a sliding window approach with a HashSet to keep track of the characters in the current window. We can expand the window by moving the right pointer and shrink it by moving the left pointer when we encounter a repeating character. We can keep track of the maximum length of the substring without repeating characters during this process.

// Code:
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            while (charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

// Time Complexity: O(n), where n is the length of the string. Each character is visited at most twice (once by the right pointer and once by the left pointer).
// Space Complexity: O(min(m, n)), where m is the size of the character set and n is the length of the string. In the worst case, we may need to store all characters in the substring.

// Approach 3: HashMap and Sliding Window.
// We can use a HashMap to store the last index of each character. When we encounter a repeating character, we can move the left pointer to the right of the last index of that character. We can keep track of the maximum length of the substring without repeating characters during this process.

// Code:
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }
            charIndexMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

// Time Complexity: O(n), where n is the length of the string. Each character is visited at most once.
// Space Complexity: O(min(m, n)), where m is the size of the character set and n is the length of the string. In the worst case, we may need to store all characters in the substring.