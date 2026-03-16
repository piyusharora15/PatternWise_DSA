/*

A 6*6 energy grid contains both positive and negative charge values. An hourglass is defined as:                                
a b c
  d
e f g                             
There are exactly 16 possible hourglasses. Compute the maximum hourglass sum. 

Example 1:
1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0

Hourglass at (3,2):

2 4 4
  2
1 2 4

Sum = 2 + 4 + 4 + 2 + 1 + 2 + 4 = 19
This is the maximum hourglass sum.

*/

/*

Approach:
If the top-left of the hourglass is (i, j), the sum is:

arr[i][j]     + arr[i][j+1]     + arr[i][j+2]
                arr[i+1][j+1]
arr[i+2][j]   + arr[i+2][j+1]   + arr[i+2][j+2]

Key Observation

A 6×6 grid can contain 16 hourglasses.

Why?

rows: 0 → 3
cols: 0 → 3

Because each hourglass occupies 3 rows and 3 columns.

So possible starting positions:

(0,0) (0,1) (0,2) (0,3)
(1,0) (1,1) (1,2) (1,3)
(2,0) (2,1) (2,2) (2,3)
(3,0) (3,1) (3,2) (3,3)

Total = 4 × 4 = 16 hourglasses

Algorithm:

Read the 6×6 matrix.
For each possible hourglass start (i, j):
Compute the hourglass sum.
Track the maximum sum.

 */
// Code Implementation:
import java.util.Scanner;

public class HourglassSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {

                int sum
                        = arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                        + arr[i + 1][j + 1]
                        + arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];

                maxSum = Math.max(maxSum, sum);
            }
        }

        System.out.println(maxSum);
        sc.close();
    }
}

// Time Complexity: O(1) - The number of hourglasses is fixed (16), so the time complexity is constant.
// Space Complexity: O(1) - We are using a fixed amount of space to store the input and the maximum sum, so the space complexity is constant.