/*

Problem Link: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid?envType=daily-question&envId=2026-10-06

A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

Example 1:
Input: s = "())"
Output: 1

Example 2:
Input: s = "((("
Output: 3


Approach: Using Stack.

1. First, we will create a stack to keep track of the parentheses.
2. We will iterate through the string and for each character:
   - If it is an opening parenthesis '(', we will push it onto the stack.
   - If it is a closing parenthesis ')', we will check if the stack is not empty and the top of the stack is an opening parenthesis. If so, we pop the stack (this means we have found a matching pair). If not, we increment a counter for unmatched closing parentheses.
3. After iterating through the string, the number of elements left in the stack will be the number of unmatched opening parentheses.
4. The total number of moves required will be the sum of the counter for unmatched closing parentheses and the number of elements left in the stack.


Dry Run:
Input: s = "())"
- Character '(': Push onto stack. Stack: ['(']
- Character ')': Check stack. It's not empty and top is '('. Pop the stack. Stack: []
- Character ')': Check stack. It's empty. Increment counter for unmatched closing parentheses. Counter: 1
- Final result: 1

Input: s = "((("
- Character '(': Push onto stack. Stack: ['(']
- Character '(': Push onto stack. Stack: ['(', '(']
- Character '(': Push onto stack. Stack: ['(', '(', '(']
- Final result: 3 (3 unmatched opening parentheses)

Input: s = "(()))("
- Character '(': Push onto stack. Stack: ['(']
- Character '(': Push onto stack. Stack: ['(', '(']
- Character ')': Check stack. It's not empty and top is '('. Pop the stack. Stack: ['(']
- Character ')': Check stack. It's not empty and top is '('. Pop the stack. Stack: []
- Character ')': Check stack. It's empty. Increment counter for unmatched closing parentheses. Counter: 1
- Character '(': Push onto stack. Stack: ['(']
- Final result: 1 (1 unmatched closing parenthesis) + 1 (1 unmatched opening parenthesis) = 2


Time Complexity: O(n), where n is the length of the string. We are iterating through the string once.

Space Complexity: O(n), where n is the length of the string. In the worst case, we may have to store all opening parentheses in the stack.

*/

// Code:

import java.util.Stack;

class MinAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int unmatchedClosing = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    unmatchedClosing++;
                }
            }
        }

        return unmatchedClosing + stack.size();
    }
}