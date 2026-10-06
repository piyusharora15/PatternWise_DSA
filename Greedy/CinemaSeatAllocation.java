/*

Problem Link: https://leetcode.com/problems/cinema-seat-allocation?envType=problem-list-v2&envId=wr2p5de7

A cinema has n rows of seats, numbered from 1 to n. Each row has 10 seats, numbered from 1 to 10.

You are given a 2D integer array reservedSeats, where reservedSeats[i] = [rowi, seati] means that seat seati in row rowi is already reserved.

A four-person group must be assigned to four seats in the same row. The group can be seated in one of the following seat blocks:

seats 2, 3, 4, 5
seats 4, 5, 6, 7
seats 6, 7, 8, 9
A block can be used only if none of its seats are reserved. Each seat can be assigned to at most one group.

Return an integer denoting the maximum number of four-person groups that can be assigned.

Example 1:
Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already reserved, and each set of four contiguous seats marked in orange is assigned to one group.

Example 2:
Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2

Example 3:
Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4


Problem Understanding:
We have: n rows
10 seats per row

Seats: 1 2 3 4 5 6 7 8 9 10

A family of 4 can occupy only these configurations:
2 3 4 5
or
4 5 6 7
or
6 7 8 9

Notice something important:

- Seats 1 and 10 never matter.
- An empty row can always accommodate 2 families:
  - seats 2–5
  - seats 6–9

The challenge is handling rows with reservations.
And the constraint is the real clue:
n <= 10^9
reservedSeats.length <= 10^4

So we cannot afford to process all n rows.

Let: m = reservedSeats.length
We'll aim for: O(m)
rather than: O(n)


Approach 1 — Brute Force
Let's start with the most straightforward approach.
For every row: 1 → n
we check whether the three possible family blocks are available.
For example:
2-5
4-7
6-9

We can store reservations in a Set.

Then for each row:
if seats 2-5 are free
    place family

if seats 6-9 are free
    place family

if neither left nor right works,
   check 4-7

Why this works?

Each row can have at most: 2 families
because the only way to get two non-overlapping groups is essentially:
[2 3 4 5] [6 7 8 9]

The middle configuration: [4 5 6 7] overlaps both.

So for every row we can determine whether it contributes: 0, 1, or 2 families.

Complexity:

If we iterate through every row:
Time: O(n)
Space: O(m)

where m = reservedSeats.length.

But:
n <= 10^9

So this is unacceptable.


Optimal Approach: HashMap + Greedy Logical Checks.

Now let's exploit the fact that only rows containing reservations can be problematic.

Suppose: n = 1,000,000,000
and reservations only occur in:
row 15
row 729
row 100000

There is absolutely no reason to inspect the other 999,999,997 rows.

Every completely empty row contributes: 2 families

So we can start with: answer = (n - numberOfReservedRows) * 2

Then only process rows appearing in reservedSeats.

Store reservations by row:

We can create: Map<Integer, Set<Integer>>

For example:
reservedSeats =
[
    [1,2],
    [1,3],
    [1,8],
    [2,6]
]

becomes:

row 1 → {2, 3, 8}
row 2 → {6}

Then we process only these rows.

How to Evaluate One Row?

There are three candidate blocks:

LEFT   = 2 3 4 5
MIDDLE = 4 5 6 7
RIGHT  = 6 7 8 9

We want the maximum number of families.

The important observation is:

LEFT and RIGHT don't overlap.

LEFT:
2 3 4 5

RIGHT:
6 7 8 9

Therefore:

if LEFT is free AND RIGHT is free
    answer += 2

Otherwise, we can potentially place one family.

We then check:

if LEFT is free
    answer += 1

else if RIGHT is free
    answer += 1

else if MIDDLE is free
    answer += 1

This works because the middle block overlaps both left and right.

Example of the Greedy Logic:

Suppose: reserved = {3}

Row:
1 2 3 4 5 6 7 8 9 10
    R

LEFT:
2 3 4 5
blocked because seat 3 is reserved.

RIGHT:
6 7 8 9
free.

Therefore: 1 family

Another example:
reserved = {5}

LEFT is blocked.
RIGHT is free.
Therefore: 1 family

Another:
reserved = {1, 10}

Neither reservation affects the three blocks.
Therefore: 2 families

This is why seats 1 and 10 can essentially be ignored.


Time Complexity: O(m), where m = reservedSeats.length is the number of reserved seats. 
We only process rows that have reservations, and for each row, we check a constant number of seat blocks.

Space Complexity: O(m), where m = reservedSeats.length is the number of reserved seats.
We store the reserved seats in a HashMap, which can contain at most m entries.

*/

// Code:

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CinemaSeatAllocation {

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Store reservations grouped by row
        for (int[] reservation : reservedSeats) {

            int row = reservation[0];
            int seat = reservation[1];

            map.computeIfAbsent(row, k -> new HashSet<>())
               .add(seat);
        }

        // Every row without reservations contributes 2 families
        int answer = (n - map.size()) * 2;

        // Process only rows having reservations
        for (Set<Integer> reserved : map.values()) {

            boolean leftFree = true;
            boolean middleFree = true;
            boolean rightFree = true;

            // 2-5
            for (int seat = 2; seat <= 5; seat++) {
                if (reserved.contains(seat)) {
                    leftFree = false;
                    break;
                }
            }

            // 4-7
            for (int seat = 4; seat <= 7; seat++) {
                if (reserved.contains(seat)) {
                    middleFree = false;
                    break;
                }
            }

            // 6-9
            for (int seat = 6; seat <= 9; seat++) {
                if (reserved.contains(seat)) {
                    rightFree = false;
                    break;
                }
            }

            if (leftFree && rightFree) {
                answer += 2;
            } else if (leftFree || middleFree || rightFree) {
                answer += 1;
            }
        }

        return answer;
    }
}