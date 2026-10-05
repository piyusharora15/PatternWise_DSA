/*

Problem Link: https://leetcode.com/problems/score-of-parentheses?envType=daily-question&envId=2026-10-05

Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.

Example 1:
Input: s = "()"
Output: 1

Example 2:
Input: s = "(())"
Output: 2

Example 3:
Input: s = "()()"
Output: 2

Approach: Using Stack.

1. First, we will create a stack to keep track of the scores of the balanced parentheses strings.
2. We will iterate through each character in the input string s.
3. If the character is '(', we will push 0 onto the stack to represent a new balanced parentheses string.
4. If the character is ')', we will pop the top element from the stack, which represents the score of the balanced parentheses string that just ended.
5. If the popped score is 0, it means we have encountered a "()", so we will add 1 to the score of the previous balanced parentheses string (the new top of the stack).
6. If the popped score is greater than 0, it means we have encountered a balanced parentheses string of the form "(A)", so we will add 2 * popped score to the score of the previous balanced parentheses string (the new top of the stack).
7. Finally, we will return the score of the entire balanced parentheses string, which will be the top element of the stack after processing all characters in the input string s.

Dry Run:
Input: s = "(()(()))"
1. Initialize an empty stack: stack = []
2. Iterate through each character in the input string s:
   - For '(': push 0 onto the stack: stack = [0]
   - For '(': push 0 onto the stack: stack = [0, 0]
   - For ')': pop the top element (0) from the stack, add 1 to the new top of the stack: stack = [1]
   - For '(': push 0 onto the stack: stack = [1, 0]
   - For '(': push 0 onto the stack: stack = [1, 0, 0]
   - For ')': pop the top element (0) from the stack, add 1 to the new top of the stack: stack = [1, 1]
   - For ')': pop the top element (1) from the stack, add 2 * popped score (2) to the new top of the stack: stack = [3]
   - For ')': pop the top element (3) from the stack, add 2 * popped score (6) to the new top of the stack: stack = [6]
3. Return the top element of the stack, which is 6.


Time Complexity: O(n), where n is the length of the input string s. We iterate through each character in the string once.

Space Complexity: O(n), where n is the length of the input string s. In the worst case, we may need to store all characters in the stack.

*/

// Code:

import java.util.Stack;

class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Initialize the stack with a base score of 0

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Start a new balanced parentheses string
            } else {
                int score = stack.pop(); // End of a balanced parentheses string
                int newScore = (score == 0) ? 1 : 2 * score; // Calculate the score
                stack.push(stack.pop() + newScore); // Add the score to the previous balanced parentheses string
            }
        }

        return stack.pop(); // The final score is at the top of the stack
    }
}