/*

A strip contains N cells. Each cell must be painted using one of three colors: R, G, or B.

Painting cell i with a color c has a cost cost[i][c].

A streak is a maximal continuous segment of cells painted with the same color. If a streak has length L, then it gives a bonus of L * Q, where Q is a given integer.

Your task is to minimize the final cost.

Final cost = Total painting cost - Total streak bonus.

Return the minimum possible final cost.

Input Format:

The first line contains an integer N, denoting the number of cells.

The second line contains an integer Q, denoting the bonus per cell in any same-color streak.

Each of the next N lines contains three space-separated integers representing the cost of painting the current cell with colors R, G, and B respectively.

Output Format:

Print a single integer representing the minimum total cost after subtracting all streak bonuses.

Example 1:
Input:
3
10
5 20 30
50 5 40
100 100 5

Output:
-15

Explanation: One optimal way is to paint the cells with colors R, G, and B respectively. The painting cost is 5 + 5 + 5 = 15. Each cell forms a separate streak of length 1. Since Q = 10, the total streak bonus is 10 + 10 + 10 = 30. Final cost = 15 - 30 = -15.

Example 2:
Input:
2
0
10 20 30
30 20 10

Output:
20

Explanation: Here Q = 0, so there is no streak bonus. We only need to minimize the painting cost. Paint the first cell with R for cost 10 and the second cell with B for cost 10. Total cost = 10 + 10 = 20.

Example 3:
Input:
4
3
8 15 6
10 4 20
7 12 9
3 18 11

Output:
8

Explanation: We need to choose colors in a way that minimizes painting cost after subtracting streak bonuses. An optimal arrangement gives a final minimum cost of 8.

Constraints:
1 <= N <= 10^5
0 <= Q <= 10^4
1 <= cost[i][j] <= 10^4

Understand the Problem:

We have:
- N cells.
- Three possible colors: R, G, B.
- cost[i][c]: Cost of painting cell i with color c.
- Q: Bonus per cell belonging to a streak.

Our objective is:
Final Cost = Total Painting Cost - Total Streak Bonus

A streak is a continuous sequence of cells having the same color.

For a streak of length L: Bonus = L * Q

2. The Most Important Observation:

Think carefully about this:

Every cell belongs to exactly one streak, regardless of which color we choose.

Suppose we have 5 cells:
R  R  G  B  B

There are three streaks:
- RR → Length 2 → Bonus = 2Q
- G → Length 1 → Bonus = Q
- BB → Length 2 → Bonus = 2Q
Total bonus: 2Q+Q+2Q=5Q

Now consider another arrangement:
R  G  B  R  G

There are five streaks, each of length 1.

Total bonus: Q+Q+Q+Q+Q=5Q

Notice something?

The total bonus is always N × Q, irrespective of how we paint the cells!

Why?

Every cell belongs to exactly one maximal streak.

Therefore: sum(L) = N

And:
Total Bonus = sum(L * Q)

Taking Q outside: = Q * sum (L)

Hence: Total Bonus = N * Q

This is the trick that simplifies the entire problem!

Mathematical Transformation:

Our original objective is: Minimize (Painting Cost - Streak Bonus)

Since streak bonus is always N × Q:
Minimize(Painting Cost-NQ)

Since N × Q is constant, we only need to minimize the total painting cost.

Now another observation:

Each cell can independently be painted using R, G, or B.

There are no restrictions on:
- Consecutive cells having the same color.
- Using the same color multiple times.
- Changing colors between adjacent cells.
Therefore, for every cell, simply choose the cheapest of its three colors.

For cell i:
minCost[i] = min(cost[i][R],cost[i][G],cost[i][B])

Total minimum painting cost: sum_{i=0}^{N-1} minCost[i]

Our final formula becomes:
sum_{i=0}^{N-1} min(cost[i][R],cost[i][G],cost[i][B]) -NQ

Algorithm:

We can solve this using a simple greedy approach.

1. Read N and Q.
2. Initialize totalCost = 0.
3. For every cell:
   - Read its R, G and B painting costs.
   - Find the minimum of the three.
   - Add that minimum to totalCost.
4. Calculate the total bonus: N * Q.
5. Subtract the bonus from the minimum painting cost.
6. Print the answer.

Example 1:

Input:

3
10
5 20 30
50 5 40
100 100 5

Given:
N=3, Q=10

Initially: totalCost = 0

Iteration 1: Cell 0
R = 5
G = 20
B = 30

Minimum: min(5,20,30)=5

totalCost = 0 + 5 = 5

Iteration 2: Cell 1
R = 50
G = 5
B = 40

Minimum:
min(50,5,40)=5

totalCost = 5 + 5 = 10

Iteration 3: Cell 2
R = 100
G = 100
B = 5

Minimum:
min(100,100,5)=5

totalCost = 10 + 5 = 15

Now calculate the total bonus:
Bonus = N * Q = 3 * 10 = 30

Final answer = 15-30
Answer: -15

Example 2:
Input:
2
0
10 20 30
30 20 10

Given:
N=2, Q=0

Cell	R	G	 B	  Minimum
0	   10   20	 30	  10
1	   30	20	 10	  10

Total painting cost: 10+10=20

Total bonus: 2 * 0 = 0

Final answer: 20-0 = 20

Code:

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Read input and write your solution here

        int n = Integer.parseInt(sc.nextLine().trim());      // Read N and Q
        int q = Integer.parseInt(sc.nextLine().trim());

        long totalCost = 0;

        for (int i = 0; i < n; i++) {  // Process each cell

            String[] parts = sc.nextLine().trim().split("\\s+");

            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);

            int minCost = Math.min(r, Math.min(g, b));  // Choose the cheapest color

            totalCost += minCost;
        }

        long totalBonus = (long) n * q;  // Total bonus is always N * Q

        long answer = totalCost - totalBonus;  // Calculate final minimum cost

        System.out.println(answer);
    }
}


Complexity Analysis:

Let N be the number of cells.

Time Complexity: O(N).

We iterate through the array exactly once.

For each cell, we perform:
- Three integer reads.
- Two comparisons using Math.min.
- One addition.

Therefore: O(N)

Auxiliary Space Complexity: O(1).

We only maintain:
- totalCost
- minCost
- totalBonus
- A few input variables.

We don't need to store the entire cost matrix.

*/