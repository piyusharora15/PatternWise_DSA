/*

You are given an n x m matrix with values g[i][j].

Find a path from cell (1, 1) to cell (n, m).

Movement rules depend on row parity. In an odd row, you may move right or diagonally down-right to (i + 1, j + 1). In an even row, you may move right or diagonally down-left to (i + 1, j - 1).

You may also move straight down to (i + 1, j) from any row.

Collect g[i][j] at every visited cell.

Find the maximum total value collectible along a valid path from (1, 1) to (n, m).

It is given that the arrays are 1-indexed.

Input Format:

The first line contains an integer n, denoting the number of rows.

The second line contains an integer m, denoting the number of columns.

Each of the next n lines contains m space-separated integers, representing row i of g.

Output Format:

Print a single integer denoting the maximum total value collectible along a valid path from (1, 1) to (n, m).

Example 1:
Input:
2
2
1 2
3 4

Output: 8

Explanation: Start at (1,1) with value 1. One optimal path is (1,1) -> (2,1) -> (2,2), collecting 1 + 3 + 4 = 8. Other valid paths collect less, so the maximum value is 8.

Example 2:
Input:
3
3
0 0 0
0 100 0
0 0 0

Output: 100

Explanation: An optimal path is (1,1) -> (2,2) -> (3,2) -> (3,3). The collected value is 0 + 100 + 0 + 0 = 100.

Example 3:
Input:
2
3
-10 -10 100
-10 -10 -10

Output: 70

Explanation: Moving down early collects more negative values. The best path is (1,1) -> (1,2) -> (1,3) -> (2,3), collecting -10 + (-10) + 100 + (-10) = 70.

Constraints:
1 <= n <= 10^5
1 <= m <= 10^5
-10^9 <= g[i][j] <= 10^9

Understand the Problem:

We are given an N × M matrix.

We start at: (1,1)
And must reach: (N,M)

We collect the value of every visited cell.

Our objective is to maximize the sum of collected values.

Movement rules:

There are three possible movements:

1. Move Right (Always Allowed)
2. Move Straight Down (Always Allowed)
3. Move Diagonally Down (Depends on source row parity)
Odd row: Down-Right
Even row: Down-Left

Remember: the diagonal rule depends on the row from which we are moving, not the destination row.

Identify the DP State:

Let's define: dp[i][j]
As: The maximum sum we can collect when reaching cell (i,j) from the starting cell (1,1).
Our final answer will be: dp[N][M]

Now, how do we calculate dp[i][j]?

Form the DP Transitions:

To reach cell (i,j), we can come from at most three directions.

Case 1: Come from the left.
We move right.
Previous cell: (i,j-1)
Maximum sum: dp[i][j-1]

Case 2: Come from above.
We move straight down.
Previous cell: (i-1,j)
Maximum sum: dp[i-1][j]

Case 3: Come diagonally.
This is where row parity matters.
If the previous row is odd:
We can move down-right.
Previous cell: (i-1,j-1)
If the previous row is even:
We can move down-left.
Previous cell: (i-1,j+1)

Therefore, our complete recurrence is:

dp[i][j] = g[i][j] + max(dp[i][j-1], dp[i-1][j], dp[i-1][j-1] if i-1 is odd, dp[i-1][j+1] if i-1 is even)

We only consider valid cells within the matrix.

Base condition:

Our starting position is (1,1).
dp[1][1]=g[1][1]

All other cells are calculated using the recurrence.

One important detail: Since matrix values can be negative, we must not initialize unreachable DP states with zero. We should use a very small value such as Long.MIN_VALUE / 4.

Example 1:
Input:
2
2
1 2
3 4

Step 1: Initialize. dp[1][1]=1
First row:
- dp[1][1] = 1
- dp[1][2] = 1 + 2 = 3 (move right)
So:
DP = [1, 3]

Step 2: Process Row 2.
Row 1 is odd.
Therefore, diagonal down-right movement is allowed.
Cell (2,1):
Possible predecessor:
- Above: (1,1) → 1
dp[2][1]=3+1=4
Cell (2,2):
Possible predecessors:
- Left: (2,1) → 4
- Above: (1,2) → 3
- Diagonal: (1,1) → 1
Maximum: max(4,3,1)=4
Add current cell value: dp[2][2]=4+4=8

Final DP table:
Row 1	1	3
Row 2	4	8

Answer: 8
Optimal path:
(1,1) → (2,1) → (2,2)

1 + 3 + 4 = 8

Input:
2
3
-10 -10 100
-10 -10 -10

This example is particularly important because it contains negative values.

We cannot simply initialize every DP cell with zero because that might incorrectly allow us to skip negative cells.

Step 1: Process Row 1
Starting at (1,1):
dp[1][1]=-10

Move right to (1,2):
dp[1][2]=-10-10=-20

Move right to (1,3):
dp[1][3]=-20+100=80

First row:
-10   -20    80

Step 2: Process Row 2
Previous row = Row 1 (odd).

Therefore, down-right diagonal movement is allowed.

Cell (2,1):

Only valid predecessor is above:
dp[2][1]=-10+(-10)=-20

Cell (2,2):

Possible predecessors:
- Left = -20
- Above = -20
- Diagonal = -10
Maximum: max(-20,-20,-10)=-10

Add current value:
dp[2][2]=-10-10=-20

Cell (2,3):

Possible predecessors:
- Left = -20
- Above = 80
- Diagonal = -20
Maximum:
max(-20,80,-20)=80

Add current value:
dp[2][3]=80-10=70

Final DP table:
Row 1	-10	-20	80
Row 2	-20	-20	70

Answer: 70
Optimal path:
(1,1) → (1,2) → (1,3) → (2,3)

-10 + (-10) + 100 + (-10) = 70

Space Optimization:

So far, we have discussed a 2D DP array:
dp[N][M]

But notice something!

To calculate the current row, we only need:
1. The previous row.
2. The current row's previous column.
We don't need the entire DP matrix.

Therefore, we can maintain just two arrays:
- prev[] → Stores the previous row's DP values.
- curr[] → Stores the current row's DP values.

How the space optimization works?

Previous row:

4 8 12 15

Calculate the next row using previous values and current left neighbor.

Current row:

7 11 18 20

After finishing the row, swap the arrays. The current row becomes the previous row.

This reduces auxiliary space from: O(N * M) to: O(M)

Code:

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        long NEG = Long.MIN_VALUE / 4;

        long[] prev = new long[m + 2];  // Previous row and current row
        long[] curr = new long[m + 2];

        Arrays.fill(prev, NEG);

        for (int i = 1; i <= n; i++) {  // Process every row

            Arrays.fill(curr, NEG);

            for (int j = 1; j <= m; j++) {

                long val = sc.nextLong();

                if (i == 1 && j == 1) {  // Starting cell

                    curr[j] = val;
                    continue;
                }

                long best = NEG;

                best = Math.max(best, curr[j - 1]);  // Case 1: Come from left

                best = Math.max(best, prev[j]);  // Case 2: Come from above

                if (i > 1) {  // Case 3: Diagonal movement

                    if ((i - 1) % 2 == 1) {

                        best = Math.max(best, prev[j - 1]); // Previous row is odd, diagonal down-right

                    } else {

                        best = Math.max(best, prev[j + 1]);
                    }
                }

                curr[j] = best + val;  // Add current cell value
            }

            long[] temp = prev;  // Current row becomes previous row
            prev = curr;
            curr = temp;
        }

        System.out.println(prev[m]); // Maximum sum at destination

        sc.close();
    }
}

Time Complexity: O(N * M), since we process each cell once.

Space Complexity: O(M), since we only maintain two rows at a time.


*/