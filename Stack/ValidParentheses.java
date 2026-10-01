/*

Problem Link: https://leetcode.com/problems/valid-parentheses?envType=problem-list-v2&envId=wra3kcft

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Example 5:
Input: s = "([)]"
Output: false


Approach: Using Stack.

1. First, we will create a stack to keep track of the opening brackets.

2. We will iterate through each character in the string.

3. If the character is an opening bracket ('(', '{', '['), we will push it onto the stack.

4. If the character is a closing bracket (')', '}', ']'), we will check if the stack is empty. If it is empty, it means there is no corresponding opening bracket, so we return false.

5. If the stack is not empty, we will pop the top element from the stack and check if it matches the corresponding opening bracket for the current closing bracket. If it does not match, we return false.

6. After iterating through all characters, we will check if the stack is empty. 

If it is empty, it means all opening brackets have been matched with closing brackets, so we return true. 

If the stack is not empty, it means there are unmatched opening brackets, so we return false.


Dry Run:

Input: s = "([])"

1. Initialize an empty stack.

2. Iterate through each character in the string:

    - Character '(': Push onto stack. Stack: ['(']
    - Character '[': Push onto stack. Stack: ['(', '[']
    - Character ']': Pop from stack and check if it matches '['. It matches, so continue. Stack: ['(']
    - Character ')': Pop from stack and check if it matches '('. It matches, so continue. Stack: []

3. After iterating through all characters, check if the stack is empty. It is empty, so return true.


Time Complexity: O(n), where n is the length of the input string. 
We iterate through each character in the string once.

Space Complexity: O(n), where n is the length of the input string. 
In the worst case, we may have to store all opening brackets in the stack.


*/


// Code:


import java.util.Stack;

class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}