/*

Problem Link: https://leetcode.com/problems/remove-outermost-parentheses?envType=daily-question&envId=2026-10-08

A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.

For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.

Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.


Example 1:
Input: s = "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".

Example 2:
Input: s = "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".

Example 3:
Input: s = "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".


Understand the Problem:

A valid parentheses string can be divided into one or more primitive valid parentheses strings.

A primitive string is a valid parentheses string that cannot be split into two non-empty valid parentheses strings.

For example: (()()) is primitive.

But: ()()()
is not one primitive string.

It is:
()
()
()

So:
s = "(()())(())"
can be decomposed as:
"(()())" + "(())"

For each primitive string, we remove its outermost pair.

Therefore:
"(()())" → "()()"
"(())"   → "()"

Final: "()()()"


What Does "Outermost" Actually Mean?

This is the most important observation.

Consider: (())
The structure is:
(
  ()
)

The first ( and final ) are the outermost pair.
Remove them:
()

Similarly:
(()())

has:
(
  ()()
)

Remove the outer pair:
()()

So we're basically removing:
Every ( that changes balance from 0 → 1, and every ) that changes balance from 1 → 0.

That observation leads directly to the optimal solution.

Approach 1: Using Stack.

We can use a stack to track parentheses.

Idea:

Whenever we see:
(
push it onto the stack.

Whenever we see:
)
pop from the stack.

But we don't want to include:
- the ( that starts a primitive
- the ) that ends a primitive

We can detect them based on stack size.

Stack Logic:

For (:
if stack is not empty:
    append '('
push '('

For ):
pop first
if stack is not empty:
    append ')'


The reason is:
Opening parenthesis:
If the stack is empty before pushing: depth = 0
and we encounter: (
this is the outermost opening parenthesis, so don't add it.

Closing parenthesis:
If after popping the stack becomes empty:
depth = 0
then this is the outermost closing parenthesis, so don't add it.


Code:

class Solution {
    public String removeOuterParentheses(String s) {

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (!stack.isEmpty()) {  // If stack is not empty, this '(' is not outermost.
                    result.append(c);
                }
                stack.push(c);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {  // If stack is not empty after popping, this ')' is not outermost.
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
}

Time Complexity: O(n), where n is the length of the string s. We traverse the string once.

Space Complexity: O(n), in the worst case, the stack can hold all the characters of the string.


Optimal Approach: Using Balance Counter.

Instead of: Stack
we maintain: a balance counter.
where:
'(' → balance++
')' → balance--

Now we can identify the outermost parentheses using the balance.

Key Observation:

Consider: (()())
Track the balance:

| Character | Balance before | Action | Balance after |

| `(`       | 0              | `+1`   | 1 |
| `(`       | 1              | `+1`   | 2 |
| `)`       | 2              | `-1`   | 1 |
| `(`       | 1              | `+1`   | 2 |
| `)`       | 2              | `-1`   | 1 |
| `)`       | 1              | `-1`   | 0 |


Now look at the first and last parentheses.

First (
Before processing: balance = 0

After: balance = 1

This is: 0 → 1
Therefore it's the outermost opening parenthesis.
Don't add it.

Last )
Before processing: balance = 1

After: balance = 0

This is: 1 → 0
Therefore it's the outermost closing parenthesis.
Don't add it.

The Trick:
For every character:
If (
First increase balance:
balance++;
Then:
if (balance > 1)
    append '(';

Why?
If balance is: 1
this is the outermost (.
If balance is: 2+
it's an inner (.


If )
First decrease balance:
balance--;
Then:
if (balance > 0)
    append ')';

Why?
If balance becomes: 0
we just closed the outermost pair.
Don't add it.

If balance remains: 1+
it's an inner ).

Dry Run:
s = "(()())(())"

Initial:
balance = 0
result = ""

Character 1: (
balance++
So: balance = 1

Since: balance > 1 is false:
result = ""
We removed the outermost (.

Character 2: (
balance = 2
Now: balance > 1 is true.
Add: result = "("

Character 3: )
Decrease: balance = 1
Since: balance > 0 is true:
add: result = "()"

Character 4: (
balance = 2
Add: result = "()("

Character 5: )
balance = 1
Add: result = "()()"

Character 6: )
balance = 0
Don't add.

First primitive is now:
(()()) → ()()

Second Primitive
Character 7: (
balance = 1
Don't add.

Character 8: (
balance = 2
Add: result = "()()("

Character 9: )
balance = 1
Add: result = "()()()"

Character 10: )
balance = 0
Don't add.

Final: "()()()"

Time Complexity: O(n), where n is the length of the string s. We traverse the string once.

Space Complexity: O(n), in the worst case, the result can hold all the characters of the string.
*/

// Code:

class RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {

        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {

                balance++;

                // Keep only non-outermost '('
                if (balance > 1) {
                    result.append(c);
                }

            } else {

                balance--;

                // Keep only non-outermost ')'
                if (balance > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}