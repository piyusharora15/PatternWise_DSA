/*

Problem Link: https://leetcode.com/problems/set-matrix-zeroes?envType=problem-list-v2&envId=dtka3dwv

Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Approach: 

1. First, we will check if the first row and first column have any zeros. 
We will use two boolean variables, `firstRowHasZero` and `firstColHasZero`, to keep track of this.

2. Next, we will iterate through the rest of the matrix (excluding the first row and first column) and mark the corresponding first row and first column elements as zero if we encounter a zero in the matrix.

3. After marking, we will iterate through the matrix again (excluding the first row and first column) and set the elements to zero based on the markers in the first row and first column.

4. Finally, we will check the boolean variables `firstRowHasZero` and `firstColHasZero` to determine if we need to set the entire first row or first column to zero.

Dry Run:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]

Step 1: Check first row and first column
- `firstRowHasZero` = false
- `firstColHasZero` = false

Step 2: Iterate through the rest of the matrix
- At (1,1): 0, so mark (1,0) and (0,1) as 0

Step 3: Set elements to zero based on markers
- Set (1,1) to 0

Step 4: Check boolean variables
- `firstRowHasZero` = false
- `firstColHasZero` = false

Output: [[1,0,1],[0,0,0],[1,0,1]]

Time Complexity: O(m*n), where m is the number of rows and n is the number of columns in the matrix. 
We traverse the matrix multiple times, but each traversal is linear with respect to the number of elements.

Space Complexity: O(1), since we are using only a constant amount of extra space for the boolean variables and modifying the matrix in place.

*/

// Code:

class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        
        // Check if the first row has any zeros
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        
        // Check if the first column has any zeros
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        
        // Use the first row and column to mark zeros
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark the row
                    matrix[0][j] = 0; // Mark the column
                }
            }
        }
        
        // Set elements to zero based on markers in the first row and column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set the first row to zero if needed
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Set the first column to zero if needed
        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}