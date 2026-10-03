/*

Problem Link: https://leetcode.com/problems/longest-valid-parentheses?envType=daily-question&envId=2026-10-03

Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Example 3:
Input: s = ""
Output: 0


Approach: Using Stack.

1. First, we will create a stack to keep track of the indices of the characters in the string.
2. We will initialize the stack with -1 to handle the case where the valid parentheses start from the beginning of the string.
3. We will iterate through the string and for each character:
   - If it's an opening parenthesis '(', we will push its index onto the stack.
   - If it's a closing parenthesis ')', we will pop an element from the stack.
     - If the stack becomes empty after popping, we will push the current index onto the stack.
     - Otherwise, we will calculate the length of the valid parentheses substring by subtracting the current index from the top of the stack and update the maximum length if necessary.
4. Finally, we will return the maximum length of the valid parentheses substring found during the iteration.


Dry Run:
Input: s = ")()())"
- Initialize stack with -1: stack = [-1]
- Iterate through the string:
  - i = 0, char = ')':
    - Pop from stack: stack becomes empty.
    - Push current index onto stack: stack = [0]
  - i = 1, char = '(':
    - Push index onto stack: stack = [0, 1]
  - i = 2, char = ')':
    - Pop from stack: stack = [0]
    - Calculate length: current length = 2 - 0 = 2
    - Update max length: max_length = 2
  - i = 3, char = '(':
    - Push index onto stack: stack = [0, 3]
  - i = 4, char = ')':
    - Pop from stack: stack = [0]
    - Calculate length: current length = 4 - 0 = 4
    - Update max length: max_length = 4
  - i = 5, char = ')':
    - Pop from stack: stack becomes empty.
    - Push current index onto stack: stack = [5]
Return max_length = 4


Time Complexity: O(n), where n is the length of the input string. 
We traverse the string once, and each character is pushed and popped from the stack at most once.

Space Complexity: O(n), where n is the length of the input string.
The space used by the stack is proportional to the length of the input string.

*/

// Code:

import java.util.Stack;

class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Initialize stack with -1 to handle edge cases
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i); // Push index of '(' onto the stack
            } else {
                stack.pop(); // Pop the last index
                if (stack.isEmpty()) {
                    stack.push(i); // If stack is empty, push current index
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek()); // Calculate valid length
                }
            }
        }

        return maxLength; // Return the maximum length found
    }
}