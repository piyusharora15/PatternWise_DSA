/*

Problem Link: https://leetcode.com/problems/minimum-path-sum?envType=problem-list-v2&envId=wh8gjvyh

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Approach 1: Using Recursion.

1. First, we will create a recursive function that takes the current position (i, j) in the grid and returns the minimum path sum from that position to the bottom-right corner of the grid.

2. The base case will be when we reach the bottom-right corner of the grid, in which case we return the value at that position.

3. For any other position, we will recursively call the function for the positions below and to the right, and return the minimum of the two values plus the value at the current position.

4. We will also need to handle the cases when we are at the last row or last column, where we can only move in one direction.

Code:

class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        return helper(grid, 0, 0);
    }

    private int helper(int[][] grid, int i, int j) {
    
        if (i == grid.length - 1 && j == grid[0].length - 1) { // Base case: if we reach the bottom-right corner
            return grid[i][j];
        }

        if (i == grid.length - 1) {  // If we are at the last row, we can only move right
            return grid[i][j] + helper(grid, i, j + 1);
        }

        if (j == grid[0].length - 1) { // If we are at the last column, we can only move down
            return grid[i][j] + helper(grid, i + 1, j);
        }

        int down = helper(grid, i + 1, j);  // Move down and right and take the minimum path sum
        int right = helper(grid, i, j + 1);

        return grid[i][j] + Math.min(down, right);
    }
}

Time Complexity: O(2^(m+n)) where m is the number of rows and n is the number of columns in the grid. 
This is because at each cell, we have two choices: move down or move right.

Space Complexity: O(m+n) for the recursion stack, where m is the number of rows and n is the number of columns in the grid.

Approach 2: Using Memoization.

1. First, we will create a 2D array to store the minimum path sum for each position in the grid.

2. We will modify the recursive function to check if the minimum path sum for the current position has already been calculated and stored in the 2D array. 
If it has, we will return that value instead of recalculating it.

3. If the minimum path sum for the current position has not been calculated, we will calculate it using the same logic as in the recursive approach and store it in the 2D array before returning it.

Time Complexity: O(m*n) where m is the number of rows and n is the number of columns in the grid. 
This is because we calculate the minimum path sum for each cell only once and store it in the 2D array.

Space Complexity: O(m*n) for the 2D array used to store the minimum path sum for each position in the grid.

Code:

class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return helper(grid, 0, 0, memo);
    }

    private int helper(int[][] grid, int i, int j, int[][] memo) {
        if (i == grid.length - 1 && j == grid[0].length - 1) { // Base case: if we reach the bottom-right corner
            return grid[i][j];
        }

        if (memo[i][j] != -1) { // If the minimum path sum for this position has already been calculated
            return memo[i][j];
        }

        if (i == grid.length - 1) {  // If we are at the last row, we can only move right
            memo[i][j] = grid[i][j] + helper(grid, i, j + 1, memo);
            return memo[i][j];
        }

        if (j == grid[0].length - 1) { // If we are at the last column, we can only move down
            memo[i][j] = grid[i][j] + helper(grid, i + 1, j, memo);
            return memo[i][j];
        }

        int down = helper(grid, i + 1, j, memo);  // Move down and right and take the minimum path sum
        int right = helper(grid, i, j + 1, memo);

        memo[i][j] = grid[i][j] + Math.min(down, right);
        return memo[i][j];
    }
}

Approach 3: Using Tabulation.

1. First, we will create a 2D array to store the minimum path sum for each position in the grid.

2. We will initialize the first cell of the 2D array with the value of the first cell of the grid.

3. We will then fill in the first row and first column of the 2D array with the cumulative sums from the grid, as we can only move right or down.

4. We will then iterate through the rest of the 2D array and fill in each cell with the minimum path sum from the top or left cell plus the value of the current cell in the grid.

5. Finally, we will return the value in the bottom-right cell of the 2D array, which will contain the minimum path sum from the top-left to the bottom-right corner of the grid.

Dry Run:

Input: grid = [[1,3,1],[1,5,1],[4,2,1]]

- We create a 2D array dp of the same size as grid to store the minimum path sums.
- We initialize dp[0][0] = grid[0][0] = 1.
- We fill in the first row of dp:
  - dp[0][1] = dp[0][0] + grid[0][1] = 1 + 3 = 4
  - dp[0][2] = dp[0][1] + grid[0][2] = 4 + 1 = 5
- We fill in the first column of dp:
  - dp[1][0] = dp[0][0] + grid[1][0] = 1 + 1 = 2
  - dp[2][0] = dp[1][0] + grid[2][0] = 2 + 4 = 6
- We fill in the rest of the cells in dp:
  - dp[1][1] = Math.min(dp[0][1], dp[1][0]) + grid[1][1] = Math.min(4, 2) + 5 = 7
  - dp[1][2] = Math.min(dp[0][2], dp[1][1]) + grid[1][2] = Math.min(5, 7) + 1 = 6
  - dp[2][1] = Math.min(dp[1][1], dp[2][0]) + grid[2][1] = Math.min(7, 6) + 2 = 8
  - dp[2][2] = Math.min(dp[1][2], dp[2][1]) + grid[2][2] = Math.min(6, 8) + 1 = 7

- Finally, we return dp[2][2] = 7, which is the minimum path sum from the top-left to the bottom-right corner of the grid.

Time Complexity: O(m*n) where m is the number of rows and n is the number of columns in the grid.

Space Complexity: O(m*n) for the 2D array used to store the minimum path sum for each position in the grid.

*/

// Code:

class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0]; // Initialize the first cell
        
        // Fill in the first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        // Fill in the first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        // Fill in the rest of the cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1]; // Return the minimum path sum to the bottom-right corner
    }
}