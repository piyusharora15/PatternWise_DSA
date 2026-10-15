/*

Problem Link: https://leetcode.com/problems/spiral-matrix?envType=problem-list-v2&envId=dtka3dwv

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Approach: 

1. First, we will create a list to store the result.

2. We will define four variables to keep track of the boundaries of the matrix: top, bottom, left, and right.

3. We will iterate through the matrix in a spiral order, starting from the top-left corner and moving right, then down, then left, then up, and so on.

4. At each step, we will add the current element to the result list and update the boundaries accordingly.

5. We will continue this process until all elements are visited.

Dry Run:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
1. Initialize result = [], top = 0, bottom = 2, left = 0, right = 2
2. While top <= bottom and left <= right:
   - Move right: Add 1, 2, 3 to result, update top = 1
   - Move down: Add 6, 9 to result, update right = 1
   - Move left: Add 8, 7 to result, update bottom = 1
   - Move up: Add 4 to result, update left = 1

   - Move right: Add 5 to result, update top = 2
3. Final result = [1, 2, 3, 6, 9, 8, 7, 4, 5]


Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. 
We visit each element once.

Space Complexity: O(1) if we don't count the output list, otherwise O(m * n) for the output list.

*/

// Code:

import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Move right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // Move down
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                // Move left
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                // Move up
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}