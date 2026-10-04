/*

Problem Link: https://leetcode.com/problems/valid-parenthesis-string?envType=daily-question&envId=2026-10-04

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "(*)"
Output: true

Example 3:
Input: s = "(*))"
Output: true

Example 4:
Input: s = "("
Output: false


Approach: Using Greedy Algorithm.

The first thought is:
Whenever I see *, try all 3 possibilities.

For example: (*)

* could be:
(( )
() ) 
( )

With many *, the number of possibilities grows exponentially.
For k stars, we could have roughly: 3^k possibilities.
So brute force is not what we want.

The key observation:
Instead of asking: "What exactly is every *?"
we ask:
"How many unmatched ( could we possibly have at this point?"

This is the important greedy insight.

We maintain two values: minOpen, maxOpen

They represent a range.

minOpen: The minimum possible number of unmatched '('.
maxOpen: The maximum possible number of unmatched '('.

So if:
minOpen = 1
maxOpen = 3
that means the current prefix could have: 1, 2, or 3 unmatched opening parentheses.
We don't care exactly which one yet.
This range is enough because the possible counts form a continuous range.

How each character affects the range?

Case 1: '('
A real opening bracket must increase the number of unmatched opens.
So:
minOpen++
maxOpen++

Example:
minOpen = 2
maxOpen = 4

After '(':
minOpen = 3
maxOpen = 5

Case 2: ')'
A closing bracket reduces the number of unmatched opening brackets.
Therefore:
minOpen--
maxOpen--

But there's an important issue.
Suppose:
maxOpen = 0
and we see:
')'

Then:
maxOpen = -1

That means even in the most optimistic interpretation, we have more closing brackets than opening brackets.
Therefore the string is impossible.
So:
if (maxOpen < 0)
    return false;

This is one of the most important checks in the solution.

Case 3: '*'
This is where the magic happens.
* has three possibilities:
'*' → '('
'*' → ')'
'*' → ''

For the minimum number of opens, we want * to behave as:
')'

So: minOpen--

For the maximum number of opens, we want * to behave as:
'('

So: maxOpen++

Thus:
minOpen--
maxOpen++

But minOpen can never logically be negative.
So: minOpen = Math.max(0, minOpen);

The complete algorithm:

Initialize:
minOpen = 0, maxOpen = 0
Then scan the string once.
For every character:
'('
minOpen++
maxOpen++

')'
minOpen--
maxOpen--

'*'
minOpen--
maxOpen++

Then:
if maxOpen < 0
    return false

minOpen = max(minOpen, 0)

Finally:
return minOpen == 0

Why?
Because if the minimum possible number of unmatched opens is 0, then there exists some interpretation of the * characters that leaves us with exactly zero unmatched '('.
Therefore, the string can be valid.

Dry Run:
Example 1: s = "()"
Initially:
minOpen = 0
maxOpen = 0

Character 1: '('
Both minimum and maximum increase.
minOpen = 1
maxOpen = 1

So:
Possible open count = [1, 1]

Character 2: ')'
Both decrease.
minOpen = 0
maxOpen = 0

Final:
minOpen = 0
maxOpen = 0

Therefore:
return true

Example 2: s = "(*)"
Initially:
minOpen = 0
maxOpen = 0

Step 1: '('
minOpen = 1
maxOpen = 1

Step 2: '*'
* could be:
')'   → open count decreases
'('   → open count increases
''    → open count stays same

So:
minOpen = 1 - 1 = 0
maxOpen = 1 + 1 = 2

Now:
[minOpen, maxOpen] = [0, 2]

Meaning we could have:
0, 1, or 2

unmatched opens.

Step 3: ')'
Decrease both:
minOpen = 0 - 1 = -1
maxOpen = 2 - 1 = 1

But:
minOpen = -1

is impossible, so clamp it:
minOpen = 0

Now:
minOpen = 0
maxOpen = 1

Final:
minOpen == 0

Therefore:
true

And indeed:
(*)

can become:
()

by treating * as empty.

Example 3: s = "())"

Start
minOpen = 0
maxOpen = 0

'('
minOpen = 1
maxOpen = 1

')'
minOpen = 0
maxOpen = 0

')'
minOpen = -1
maxOpen = -1

Now:
maxOpen < 0

Therefore:
return false

Why?
There isn't any * available to save us.


Time Complexity: O(n), where n is the length of the string s. 
We only need to scan the string once, and each character is processed in constant time.

Space Complexity: O(1), since we are using only a fixed amount of extra space for the minOpen and maxOpen variables, regardless of the input size.

*/

// Code:

class ValidParenthesesString {
    public boolean checkValidString(String s) {

        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // '(' definitely increases open parentheses
                minOpen++;
                maxOpen++;
            }

            else if (c == ')') {
                // ')' decreases open parentheses
                minOpen--;
                maxOpen--;
            }

            else { // c == '*'
                // '*' can be ')' -> min decreases
                minOpen--;

                // '*' can be '(' -> max increases
                maxOpen++;
            }

            // Even the maximum possible number of opens is negative
            if (maxOpen < 0) {
                return false;
            }

            // We cannot actually have negative open parentheses
            minOpen = Math.max(minOpen, 0);
        }

        // If we can finish with exactly 0 unmatched '(',
        // some interpretation of '*' makes the string valid.
        return minOpen == 0;
    }
}