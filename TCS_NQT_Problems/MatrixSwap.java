/*

A data processing team stores numerical information in a 2D matrix of size N*M. 
Due to certain transformation rules, you must perform multiple swap operations:
Each swap operation provides two positions: (r1,c1) and (r2,c2).
You must swap the values at these positions. 
After performing all swap operations, print the final matrix.                                                                         

Example:
Input:
2 3
"1 2 3 4 5 6"
2
0 0 1 2
0 1 1 1

Initial Matrix:
1 2 3
4 5 6

Output:
6 5 3
4 2 1

*/

/*

Approach:
Step 1 — Build Matrix
Input:
2 3
1 2 3 4 5 6

Matrix becomes:

1 2 3
4 5 6

Step 2 — Perform Swaps
Swap 1:
(0,0) ↔ (1,2)
1 ↔ 6

Matrix:
6 2 3
4 5 1

Swap 2:
(0,1) ↔ (1,1)
2 ↔ 5

Final Matrix:

6 5 3
4 2 1

Use nextInt() for numbers like n, m, q
Use nextLine() for the quoted array
Remove quotes:
input = input.replace("\"", "");
Then split normally.

*/

// Code Implementation:
import java.util.Scanner;
public class MatrixSwap {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read n and m (same line)
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        // Read the quoted array line
        String input = sc.nextLine();

        // Remove double quotes ONLY
        input = input.replace("\"", "");

        // Split by space (since numbers are space-separated)
        String[] parts = input.split(" ");

        int[][] matrix = new int[n][m];

        int index = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(parts[index++]);
            }
        }

        // Number of swap operations
        int q = sc.nextInt();

        // Perform swaps
        for(int i = 0; i < q; i++){

            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();

            int temp = matrix[r1][c1];
            matrix[r1][c1] = matrix[r2][c2];
            matrix[r2][c2] = temp;
        }

        // Print final matrix
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}

// Time Complexity: O(n*m + q) where n*m is for building the matrix and q is for performing swaps.
// Space Complexity: O(n*m) for storing the matrix.