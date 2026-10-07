/*

Problem Link: https://leetcode.com/problems/remove-invalid-parentheses?envType=daily-question&envId=2026-10-07

Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]


Problem Understanding:

Given: s = "()())()"

We need to remove the minimum number of parentheses so that the resulting string is valid.

Possible answers: "(())()" and "()()()"

Both require removing exactly one ).

So: Output = ["(())()", "()()()"]

Another example: s = "(a)())()"

Output: ["(a())()", "(a)()()"]

And: s = ")("

We have to remove both parentheses: ""

So: [""]

The important requirement is:
Return every unique valid string requiring the minimum number of removals.


First: What Makes a Parentheses String Valid?

For a string containing parentheses:
(
)

we maintain a balance:
'(' → balance++
')' → balance--

A valid string must satisfy two conditions:

Condition 1: At no point can: balance < 0
because that means we've encountered a ) without a matching (.

Condition 2: At the end: balance == 0

Example:
( ) ( )

Balance:
0
1
0
1
0
Valid.

But:
) ( )

Balance:
0
-1
Already invalid.

This validation logic is fundamental to every approach.


Approach 1 — Pure Brute Force.

The most straightforward idea is: For every character, either keep it or remove it.

That creates a binary decision: keep, remove

For n characters: 2^n possible subsequences.

For each generated string:
1. Check whether it's valid.
2. Count its length.
3. Keep the valid strings with maximum length.

Why maximum length?

Because: minimum removals = maximum remaining length

Example
s = "()())()"

We generate strings such as:
()())()
()())(
()()()
...

Then validate all of them.

Eventually:
(())()
()()()
are the longest valid ones.

Complexity:

There are: 2^n subsequences.
Checking each one takes: O(n)

Therefore roughly: Time = O(2^n * n)
and storing all generated strings can also be exponential.
This is correct, but wasteful.
With: n <= 25
it's conceptually useful but not the approach that should be presented as the final solution.


Optimal Approach: Backtracking with Minimum Removal Count.

Instead of blindly exploring all possible removals, first calculate:
Exactly how many ( and ) do we need to remove?

Then DFS only explores possibilities that remove exactly those parentheses.
This dramatically reduces unnecessary search.

Step 1 — Calculate Minimum Removals.

We scan the original string.
Maintain:
left = unmatched '('
right = unmatched ')'

For every character:
If '('
left++

If ')'
If there is an unmatched (:
left--

Otherwise:
right++

At the end:
left = number of '(' that must be removed
right = number of ')' that must be removed


Example — ()())()
Let's calculate.
s = ( ) ( ) ) ( )

Start:
left = 0
right = 0

(
left = 1
right = 0

)
Matches previous (:
left = 0
right = 0

(
left = 1
right = 0

)
Matches:
left = 0
right = 0

)
No unmatched ( exists.
Therefore:
right = 1

(
left = 1
right = 1

)
Matches:
left = 0
right = 1

Final:
left = 0
right = 1

Therefore:
We must remove exactly one ).

That immediately tells our DFS:
delete exactly:
0 '('
1 ')'

This is a huge improvement.


DFS State:

Our DFS needs to track:

index
leftRem
rightRem
leftCount
rightCount
currentString


Meaning:

index: Which character are we processing?
leftRem: How many ( still need to be deleted?
rightRem: How many ) still need to be deleted?
leftCount: How many ( have we kept?
rightCount: How many ) have we kept?


The Two Decisions:

At every parenthesis we have two possibilities:

Decision 1 — Remove it.
Only allowed if we still need to remove that type.

For '(':
leftRem > 0

For ')':
rightRem > 0

Decision 2 — Keep it.
But when keeping ):
rightCount <= leftCount
must remain true.
Otherwise we have an invalid prefix.


Example
Consider:
s = "()())()"

We calculated:
leftRem = 0
rightRem = 1

Therefore, whenever we encounter a ):
we can either:

remove it
if rightRem > 0, or:

keep it
if doing so doesn't make:
rightCount > leftCount

The DFS eventually finds:
(())()
()()()


Very Important Pruning Rule:

Suppose: leftCount < rightCount
Then the current prefix is already invalid.

Example:
")"

We have:
leftCount = 0
rightCount = 1

There is no way to fix the past prefix by adding characters later.
Therefore:
if (leftCount < rightCount) {
    return;
}
This is an extremely important pruning condition.


Another Pruning Rule:

Suppose: remainingCharacters < leftRem + rightRem
Then there aren't enough characters left to perform all required removals.
So we can immediately stop.

if (n - index < leftRem + rightRem) {
    return;
}
This is another useful optimization.


Duplicate Parentheses Problem:

Consider: s = "((("

Suppose we need to remove two parentheses.

Removing:
index 0

or:
index 1

or:
index 2

can produce the same result: "("

So our HashSet<String> handles duplicate final answers.
But we can also avoid generating some duplicates during DFS by skipping equivalent consecutive parentheses in deletion choices.
This becomes especially useful in a more traditional "delete characters from the string" DFS.


Dry Run:
Input: s = "()())()"

First calculate:
leftRem = 0
rightRem = 1

So exactly one ) must be removed.

Our DFS starts:
index = 0
leftRem = 0
rightRem = 1
leftCount = 0
rightCount = 0
current = ""

Character 0: (
We cannot remove it because:
leftRem = 0

So we keep it:
current = "("
leftCount = 1
rightCount = 0

Character 1: )
Now we have two possibilities.

Option A — Remove )
Since:
rightRem = 1
we can remove it.

State:
current = "("
rightRem = 0

Continue.
Eventually this produces:
(())()

Option B — Keep )
Then:
current = "()"
leftCount = 1
rightCount = 1

Continue.
Eventually this produces:
()()()

The DFS explores all valid choices while:
leftCount >= rightCount

and:
leftRem + rightRem

eventually becomes zero.
Thus we get:
(())()
()()()

and nothing requiring more removals.


Dry Run — ")("

Calculate removals.

First character )
No unmatched (:
rightRem = 1

Second character (
leftRem = 1

So:
leftRem = 1
rightRem = 1

We must remove both.
DFS:
")("

Remove ):
"("

Remove (:
""

Final: [""]
Correct.


Time Complexity: O(2^n * n), where n is the length of the string. 
The 2^n comes from the fact that we are exploring all combinations of removals, and the n factor comes from the time taken to validate each combination.

Space Complexity: O(n), where n is the length of the string. We use space for the recursion stack and the HashSet to store unique valid strings.

*/

// Code:

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RemoveInvalidParentheses {

    private Set<String> result = new HashSet<>();
    private String s;
    private int n;

    public List<String> removeInvalidParentheses(String s) {

        this.s = s;
        this.n = s.length();

        // Calculate minimum removals
        int leftRem = 0;
        int rightRem = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRem++;
            }
            else if (c == ')') {

                if (leftRem > 0) {
                    leftRem--;
                }
                else {
                    rightRem++;
                }
            }
        }

        dfs(0, leftRem, rightRem, 0, 0, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void dfs(
        int index,
        int leftRem,
        int rightRem,
        int leftCount,
        int rightCount,
        StringBuilder current
    ) {

        // Invalid prefix
        if (leftCount < rightCount) {
            return;
        }

        // Not enough characters left to remove
        if (n - index < leftRem + rightRem) {
            return;
        }

        // Reached end
        if (index == n) {

            if (leftRem == 0 &&
                rightRem == 0 &&
                leftCount == rightCount) {

                result.add(current.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // Option 1: Remove current character
        if (c == '(' && leftRem > 0) {

            dfs(
                index + 1,
                leftRem - 1,
                rightRem,
                leftCount,
                rightCount,
                current
            );
        }

        if (c == ')' && rightRem > 0) {

            dfs(
                index + 1,
                leftRem,
                rightRem - 1,
                leftCount,
                rightCount,
                current
            );
        }

        // Option 2: Keep current character
        current.append(c);

        if (c == '(') {

            dfs(
                index + 1,
                leftRem,
                rightRem,
                leftCount + 1,
                rightCount,
                current
            );

        }
        else if (c == ')') {

            if (leftCount > rightCount) {

                dfs(
                    index + 1,
                    leftRem,
                    rightRem,
                    leftCount,
                    rightCount + 1,
                    current
                );
            }

        }
        else {

            // Letter
            dfs(
                index + 1,
                leftRem,
                rightRem,
                leftCount,
                rightCount,
                current
            );
        }

        current.deleteCharAt(current.length() - 1);
    }
}