/*

Problem Link: https://leetcode.com/problems/unique-paths?envType=problem-list-v2&envId=wh8gjvyh

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Approach 1: Using Recursion.

1. First, we will create a recursive function that takes the current position of the robot (i, j) and the dimensions of the grid (m, n) as parameters.

2. The base case of the recursion will be when the robot reaches the bottom-right corner (i.e., when i == m - 1 and j == n - 1). In this case, we will return 1, indicating that we have found a unique path.

3. If the robot goes out of bounds (i.e., when i >= m or j >= n), we will return 0, indicating that this path is not valid.

4. In the recursive function, we will explore two possible moves for the robot: moving down (i + 1, j) and moving right (i, j + 1). 
We will recursively call the function for both moves and sum the results to get the total number of unique paths from the current position.

5. Finally, we will call the recursive function starting from the top-left corner (0, 0) and return the result.

Code:

class UniquePaths {
    public int uniquePaths(int m, int n) {
        return countPaths(0, 0, m, n);
    }

    private int countPaths(int i, int j, int m, int n) {

        if (i == m - 1 && j == n - 1) {  // Base case: reached the bottom-right corner
            return 1;
        }
    
        if (i >= m || j >= n) {  // Out of bounds
            return 0;
        }

        return countPaths(i + 1, j, m, n) + countPaths(i, j + 1, m, n); // Move down and right
    }
}

Time Complexity: O(2^(m+n)) - In the worst case, the recursion tree can have 2^(m+n) nodes, leading to exponential time complexity.

Space Complexity: O(m+n) - The maximum depth of the recursion tree is m+n, which corresponds to the maximum number of moves the robot can make.

Approach 2: Using Memoization.

1. First, we will create a 2D array (dp) of size m x n to store the number of unique paths from each cell to the bottom-right corner. 

We will initialize all elements of the dp array to -1, indicating that they have not been computed yet.

2. We will create a recursive function that takes the current position of the robot (i, j), the dimensions of the grid (m, n), and the dp array as parameters.

3. The base case of the recursion will be when the robot reaches the bottom-right corner (i.e., when i == m - 1 and j == n - 1). In this case, we will return 1, indicating that we have found a unique path.

4. Before making the recursive calls, we will check if the number of unique paths from the current position (i, j) to the bottom-right corner is already computed and stored in the dp array. If it is, we will return that value.

5. Otherwise, we will compute the number of unique paths from the current position by recursively calling the function for the two possible moves (down and right) and store the result in the dp array.

6. Finally, we will call the recursive function starting from the top-left corner (0, 0) and return the result.

Dry Run Example:

Input: m = 3, n = 2

The dp array will be initialized as follows:
[[ -1, -1 ],
 [ -1, -1 ],
 [ -1, -1 ]]

- The robot starts at position (0, 0). It can move down to (1, 0) or right to (0, 1).
- When the robot moves down to (1, 0), it can again move down to (2, 0) or right to (1, 1).
- When the robot moves down to (2, 0), it can only move right to (2, 1), which is the bottom-right corner. This path contributes 1 unique path.
- When the robot moves right to (1, 1), it can only move down to (2, 1), which is the bottom-right corner. This path contributes another unique path.
- When the robot moves right to (0, 1), it can only move down to (1, 1), which can then move down to (2, 1). This path contributes another unique path.
- The total number of unique paths from (0, 0) to (2, 1) is 3.

Code:

class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return countPaths(0, 0, m, n, dp);
    }

    private int countPaths(int i, int j, int m, int n, int[][] dp) {
        if (i == m - 1 && j == n - 1) {  // Base case: reached the bottom-right corner
            return 1;
        }
    
        if (i >= m || j >= n) {  // Out of bounds
            return 0;
        }

        if (dp[i][j] != -1) {  // Check if already computed
            return dp[i][j];
        }

        dp[i][j] = countPaths(i + 1, j, m, n, dp) + countPaths(i, j + 1, m, n, dp); // Move down and right
        return dp[i][j];
    }
}

Time Complexity: O(m*n) - Each cell in the dp array is computed only once, leading to a time complexity of O(m*n).

Space Complexity: O(m*n) - The dp array of size m x n is used to store the number of unique paths from each cell to the bottom-right corner.

Approach 3: Using Tabulation (Bottom-Up Dynamic Programming).

1. First, we will create a 2D array (dp) of size m x n to store the number of unique paths from each cell to the bottom-right corner.

2. We will initialize the first row and the first column of the dp array to 1, as there is only one way to reach any cell in the first row (by moving right) and any cell in the first column (by moving down).

3. We will fill the rest of the dp array by summing up the number of unique paths from the cell above and the cell to the left.

4. Finally, we will return the value in the bottom-right corner of the dp array, which represents the total number of unique paths from the top-left corner to the bottom-right corner.

Time Complexity: O(m*n) - We iterate through the entire dp array once, leading to a time complexity of O(m*n).

Space Complexity: O(m*n) - The dp array of size m x n is used to store the number of unique paths from each cell to the bottom-right corner.

*/

// Code:

class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Initialize the first row and first column to 1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the rest of the dp array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // Sum of paths from above and left
            }
        }

        return dp[m - 1][n - 1]; // Return the value in the bottom-right corner
    }
}