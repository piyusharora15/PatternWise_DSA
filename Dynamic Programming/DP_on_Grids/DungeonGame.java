// Problem Link: https://leetcode.com/problems/dungeon-game?envType=problem-list-v2&envId=wh8gjvyh

// Approach 1: Recursion.
// We start from the destination cell and move backwards to the start cell.At each cell, we calculate the minimum health required to reach the destination cell from that cell.The minimum health required at each cell is determined by the minimum health required at the next cell (either right or down) minus the value of the current cell.If the value of the current cell is negative, we need to add that value to the minimum health required at the next cell to ensure that we have enough health to survive.We also need to ensure that the minimum health required at each cell is at least 1, as the knight cannot survive with 0 or negative health.We continue this process until we reach the start cell, at which point we return the minimum health required at that cell.

// Code:
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        return dfs(dungeon, m - 1, n - 1);
    }

    private int dfs(int[][] dungeon, int i, int j) {
        // Base case: if we reach the top-left cell
        if (i == 0 && j == 0) {
            return Math.max(1, 1 - dungeon[i][j]);
        }

        // If we are out of bounds
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // Calculate the minimum health required from the right and down cells
        int right = dfs(dungeon, i, j - 1);
        int down = dfs(dungeon, i - 1, j);

        // Find the minimum health required to move to the next cell
        int minHealthNextCell = Math.min(right, down);

        // Calculate the minimum health required at the current cell
        int minHealthCurrentCell = minHealthNextCell - dungeon[i][j];

        // Ensure that the minimum health is at least 1
        return Math.max(1, minHealthCurrentCell);
    }
}

// Approach 2: Memoization.
// We can optimize the recursive approach by storing the results of previously computed cells in a memoization table.This way, we avoid redundant calculations and improve the time complexity of the solution.We create a 2D array to store the minimum health required at each cell and check this array before performing any calculations.We only compute the minimum health required for a cell if it has not been computed before.We continue this process until we reach the start cell, at which point we return the minimum health required at that cell.We ensure that the minimum health required at each cell is at least 1, as the knight cannot survive with 0 or negative health.

// Code:
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfs(dungeon, m - 1, n - 1, memo);
    }

    private int dfs(int[][] dungeon, int i, int j, Integer[][] memo) {
        // Base case: if we reach the top-left cell
        if (i == 0 && j == 0) {
            return Math.max(1, 1 - dungeon[i][j]);
        }

        // If we are out of bounds
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // Check if the result is already computed
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // Calculate the minimum health required from the right and down cells
        int right = dfs(dungeon, i, j - 1, memo);
        int down = dfs(dungeon, i - 1, j, memo);

        // Find the minimum health required to move to the next cell
        int minHealthNextCell = Math.min(right, down);

        // Calculate the minimum health required at the current cell
        int minHealthCurrentCell = minHealthNextCell - dungeon[i][j];

        // Ensure that the minimum health is at least 1
        memo[i][j] = Math.max(1, minHealthCurrentCell);
        return memo[i][j];
    }
}

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the dungeon grid.
// Space Complexity: O(m * n) for the memoization table and O(m + n) for the recursion stack in the worst case.

// Approach 3: Tabulation.
// We can further optimize the solution using a bottom-up dynamic programming approach.We create a 2D array to store the minimum health required at each cell, starting from the destination cell and moving backwards to the start cell.At each cell, we calculate the minimum health required to reach the destination cell from that cell using the values in the DP table for the right and down cells.We ensure that the minimum health required at each cell is at least 1, as the knight cannot survive with 0 or negative health.We continue this process until we fill the entire DP table, at which point we return the minimum health required at the start cell.

// Code:
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];

        // Fill the DP table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = Math.max(1, 1 - dungeon[i][j]);
                } else if (i == m - 1) {
                    dp[i][j] = Math.max(1, dp[i][j + 1] - dungeon[i][j]);
                } else if (j == n - 1) {
                    dp[i][j] = Math.max(1, dp[i + 1][j] - dungeon[i][j]);
                } else {
                    int minHealthNextCell = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(1, minHealthNextCell - dungeon[i][j]);
                }
            }
        }

        return dp[0][0];
    }
}

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the dungeon grid.
// Space Complexity: O(m * n) for the DP table.