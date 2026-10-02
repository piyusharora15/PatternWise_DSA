/*

Problem Link: https://leetcode.com/problems/generate-parentheses?envType=daily-question&envId=2026-10-02

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]


Approach: Using Backtracking.

1. First, we will create a helper function that will take the current string, the number of open parentheses, and the number of closed parentheses as parameters.
2. We will check if the current string has reached the maximum length (which is 2 * n). If it has, we will add the current string to the result list.
3. If the number of open parentheses is less than n, we will add an open parenthesis to the current string and call the helper function recursively.
4. If the number of closed parentheses is less than the number of open parentheses, we will add a closed parenthesis to the current string and call the helper function recursively.
5. Finally, we will return the result list containing all the valid combinations of well-formed parentheses.


Dry Run:

Input: n = 3
generateParenthesis(3)
- Helper function called with current = "", open = 0, close = 0
- Since open < n (0 < 3), add "(" and call helper with current = "(", open = 1, close = 0
- Since open < n (1 < 3), add "(" and call helper with current = "((", open = 2, close = 0
- Since open < n (2 < 3), add "(" and call helper with current = "(((", open = 3, close = 0
- Since open == n (3 == 3), check if close < open (0 < 3) and add ")" and call helper with current = "((())", open = 3, close = 1
- Since close < open (1 < 3), add ")" and call helper with current = "((()))", open = 3, close = 2
- Since close < open (2 < 3), add ")" and call helper with current = "((()))", open = 3, close = 3
- Since current length == 2 * n (6 == 6), add to result list.
- Return result list.


Time Complexity: O(4^n / sqrt(n)) - The number of valid parentheses combinations is given by the nth Catalan number, which is approximately 4^n / (n * sqrt(n)).

Space Complexity: O(n) - The maximum depth of the recursion tree is n, which corresponds to the maximum number of open parentheses that can be added to the current string.

*/


// Code:

import java.util.ArrayList;
import java.util.List;

class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}