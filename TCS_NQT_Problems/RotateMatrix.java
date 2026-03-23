/*

An advanced image processing engine stores pixel intensities in an n * n matrix.
You are required to rotate the image by 90 degrees clockwise. 
The rotation must be performed in-place. 
No additional 2D matrix may be allocated. 
Constraints: 
1 <= n <= 1000 

Example 1: 
Input:                                                           
matrix = [ 
[1, 2, 3],
[4, 5, 6], 
[7, 8, 9] 
] 

Output: [ 
[7, 4, 1],
[8, 5, 2], 
[9, 6, 3] 
]

*/

/*

Approach:
To rotate 90° clockwise in-place, do:

1. Transpose the matrix
2. Reverse each row

🔥 Why This Works
Step 1: Transpose
Convert:
matrix[i][j] → matrix[j][i]

Example:

1 2 3        1 4 7
4 5 6  →     2 5 8
7 8 9        3 6 9

Step 2: Reverse each row
1 4 7        7 4 1
2 5 8  →     8 5 2
3 6 9        9 6 3

🧪 Final Output
7 4 1
8 5 2
9 6 3

*/

import java.util.Scanner;

public class RotateMatrix {
    public static void rotate(int[][] matrix, int n) {
        // Step 1: Transpose the matrix
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Step 2: Reverse each row
        for(int i=0;i<n;i++) {
            int left = 0, right = n-1;
            while(left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline
        String input = sc.nextLine();
        // Clean input(handles [], commas, spaces)
        input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
        String[] parts = input.split("\\s+"); // Split by whitespace
        int[][] matrix = new int[n][n];
        int index = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                matrix[i][j] = Integer.parseInt(parts[index++]);
            }
        }
        rotate(matrix, n);
        // Print the rotated matrix
        System.out.println("[");
        for(int i=0;i<n;i++) {
            System.out.print(" [");
            for(int j=0;j<n;j++) {
                System.out.print(matrix[i][j]);
                if(j < n-1) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println("]");
        sc.close();
    }
}

// Time Complexity: O(n^2) due to the two nested loops for transposition and reversal.
// Space Complexity: O(1) since we are performing the rotation in-place without using any additional data structures.