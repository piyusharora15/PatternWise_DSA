/*

Problem Link: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string?envType=daily-question&envId=2026-10-09

Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.

Return the minimum number of insertions needed to make s balanced.

Example 1:
Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.

Example 2:
Input: s = "())"
Output: 0
Explanation: The string is already balanced.

Example 3:
Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.


Understand the problem:

Normally, in a valid parentheses string, every ( matches one ).
But in this problem, every ( must match two consecutive ) characters.

For example: ())
Valid
One ( is matched by the two consecutive )).

(()))
Needs 1 insertion
The first ( needs one additional ).

))())(
Needs 3 insertions
Add one ( at the beginning and two ) at the end.

We can insert either ( or ) anywhere. We need to return the minimum number of insertions needed to make the entire string valid.

The constraints allow n <= 10^5, so we should aim for an O(n) solution.

The key observation:

Think of each ( as creating a requirement for two closing brackets.

For example:
(  → needs ))
(( → needs ))))

Now consider:
s = "(()))"

We can visualize the matching:
(       )
 \     /
  (   ))

The first ( needs two closing brackets, but the first available ) is used as part of the second opening bracket's pair. Ultimately, one closing bracket must be inserted.

The challenge is to track these requirements without repeatedly modifying the string.

Approach 1 — Stack.

The most intuitive approach is to use a stack to track unmatched opening brackets.

Idea:

Every ( requires two consecutive ) characters. When we encounter a closing bracket, we inspect whether it has another ) immediately after it.

- If the next character is also ), we have a complete closing pair )).
- Otherwise, we must insert one ) to complete the pair.
- If no unmatched ( exists, we must insert an opening bracket ( to match the closing pair.
- After processing all characters, every remaining ( requires two additional ).

Dry run: s = "())"

Initially:
stack = []
insertions = 0

Step	Character(s) processed	Stack	Insertions
1	        (	                 [(]	   0
2	        ))	                 []	       0

At step 2, the two consecutive ) form a complete closing pair, so we pop one (.
Final answer: 0.


Code:

import java.util.Stack;

class Solution {
    public int minInsertions(String s) {
        Stack<Character> stack = new Stack<>();
        int insertions = 0;
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') { // Consume a complete pair of closing brackets.
                    i++;
                } else {
                    insertions++;  // Insert the missing second ')'.
                }
                if (stack.isEmpty()) {
                    insertions++;  // No '(' available to match this pair.
                } else {
                    stack.pop();
                }
            }

            i++;
        }

        insertions += 2 * stack.size(); // Every unmatched '(' requires two ')'.
        return insertions;
    }
}

Time complexity: O(n) where n is the length of the string s. We process each character once.

Space complexity: O(n) in the worst case, where all characters are '(' and we push them onto the stack.


Optimal Approach: Greedy with Unmatched Opening Brackets.

Instead of storing each unmatched ( in a stack, maintain a counter:
- open: number of unmatched opening brackets.
- insertions: number of insertions required.

The strategy is the same as the stack solution, but we only need the count of unmatched opening brackets.

Algorithm:

When we see (:
open++;

When we see ):

1. If the next character is ), consume both closing brackets.
2. Otherwise, insert one ) and count it as the missing half of the closing pair.
3. If open == 0, insert a ( because no opening bracket exists to match this pair.
4. Otherwise, match the closing pair with one opening bracket by decrementing open.

At the end, each unmatched opening bracket needs two closing brackets:
insertions += 2 * open;

Dry Run: s = "(()))"

Initially:
insertions = 0
open = 0

1 Read (
Increment open.
open = 1, insertions = 0


2 Read the second (
Increment open again.
open = 2, insertions = 0


3 Read the first )
The next character is also ), so consume both. They form a complete closing pair.
Match that pair with one opening bracket.
open = 1, insertions = 0


4 Read the final )
There is no following ) to complete the pair. Insert one.
Match the resulting pair with the remaining opening bracket.
open = 0, insertions = 1

Final answer: 1
The balanced result can be (()))), which requires one inserted ).

Time Complexity: O(n) where n is the length of the string s. We process each character once.

Space Complexity: O(1) since we only use a few integer variables to track the state.

*/

// Code:

class MinInsertionsToBalanceAParenthesesString {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Consume the second ')'.
                } else {
                    insertions++; // Insert a missing ')'.
                }

                if (open == 0) {
                    insertions++; // Insert a missing '('.
                } else {
                    open--;
                }
            }
        }

        insertions += 2 * open;
        return insertions;
    }
}