// Problem Link: https://leetcode.com/problems/partition-equal-subset-sum

/*

Companies: Amazon, Microsoft, Google.

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100

*/

/*

Naive Approach: Recursion.

The idea of this approach is to try all possible ways of dividing the array into two subsets using recursion. 
For each element, we have two choices: either include it in the current subset or exclude it. 
The goal is to check whether there exists a subset whose sum is equal to half of the total array sum. 
If such a subset exists, the remaining elements will automatically form the second subset with equal sum.

Code:

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
        }
            if (sum % 2 != 0) {
            return false; // If the total sum is odd, we cannot partition it into two equal subsets
        }
        return canPartitionHelper(nums.length, nums, sum / 2);
    }    
    
    private boolean canPartitionHelper(int n, int[] nums, int sum) {
        Base case: if sum is 0, we found a valid subset
        if (sum == 0) {
            return true;
        }
        if(n == 0) return false; // If we have no elements left and sum is not 0, return false
        
        if(nums[n-1] > sum) {
            If the current element is greater than the remaining sum, we cannot include it
            return canPartitionHelper(n-1, nums, sum);
        }
        return canPartitionHelper(n-1, nums, sum) || canPartitionHelper(n-1, nums, sum - nums[n-1]);
    }
}

Time Complexity: O(2^n) - In the worst case, we explore all subsets of the array.
Space Complexity: O(n) - The maximum depth of the recursion stack can be n in the worst case.

Better Approach 1: Memoization.

We can optimize the recursive approach by using memoization to store the results of previously computed subproblems.
We can use a 2D integer array to store the results of subproblems defined by the current index and the remaining sum.
We can use -1 to indicate that the subproblem has not been computed, 0 to indicate that it cannot be partitioned, and 1 to indicate that it can be partitioned.

Code:

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
        }
            if (sum % 2 != 0) {
            return false; // If the total sum is odd, we cannot partition it into two equal subsets
        }
        int[][] memo = new int[nums.length + 1][sum/2 + 1];
        for (int i=0; i<=nums.length; i++) {
            Arrays.fill(memo[i], -1); // Initialize memoization array with -1
        }
        return canPartitionHelper(nums.length, nums, sum / 2, memo);
    }    
    
    private boolean canPartitionHelper(int n, int[] nums, int sum, int[][] memo) {
        Base case: if sum is 0, we found a valid subset
        if (sum == 0) {
            return true;
        }
        if(n == 0) return false; // If we have no elements left and sum is not 0, return false
        
        if(memo[n][sum] != -1) {
            return memo[n][sum] == 1; // Return the stored result if it has been computed
        }
        
        if(nums[n-1] > sum) {
            If the current element is greater than the remaining sum, we cannot include it
            memo[n][sum] = canPartitionHelper(n-1, nums, sum, memo) ? 1 : 0;
            return memo[n][sum] == 1;
        }
        
        memo[n][sum] = (canPartitionHelper(n-1, nums, sum, memo) || canPartitionHelper(n-1, nums, sum - nums[n-1], memo)) ? 1 : 0;
        return memo[n][sum] == 1;
    }
}

Time Complexity: O(n * sum) - We compute each subproblem at most once, where n is the number of elements and sum is the target sum.
Space Complexity: O(n * sum) - The space used by the memoization array and the recursion stack. 


Better Approach 2: Tabulation.

We can further optimize the solution using tabulation, which is a bottom-up dynamic programming approach.
We can create a 2D boolean array where dp[i][j] indicates whether it is possible to achieve a sum of j using the first i elements of the array.
We can fill this table iteratively based on whether we include or exclude the current element.
We can initialize the first column (sum = 0) to true, since we can always achieve a sum of 0 by excluding all elements.
We can then fill the table based on the choices we have for each element.

Code:

class Solution {
    public boolean canPartition(int[] nums) {
     Calculate sum of the elements in array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length;

        If sum is odd, there cannot be two subsets with equal sum
        if (sum % 2 != 0) 
            return false;
        sum = sum / 2;

        Create a 2D array for storing results of subproblems
        boolean[][] dp = new boolean[n + 1][sum + 1];

        If sum is 0, then answer is true (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        Fill the dp table in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < nums[i - 1]) {
                Exclude the current element
                    dp[i][j] = dp[i - 1][j];
                } else {
                  Include or exclude
                    dp[i][j] = dp[i - 1][j] || 
                    dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }
} 

Time Complexity: O(n * sum) - We fill a table of size n * sum.
Space Complexity: O(n * sum) - The space used by the dp table.


Optimal Approach: Space Optimization.

We can optimize the space complexity of the tabulation approach by using a 1D boolean array instead of a 2D array.
Since each row of the dp table only depends on the previous row, we can use a single array to store the results for the current row and update it iteratively.

Code:

class Solution {
    public boolean canPartition(int[] nums) {
    Calculate sum of the elements in array 
        int sum = 0; 
        for (int num : nums) {
            sum += num;
        }
        
        If sum is odd, there cannot be two subsets with equal sum 
        if (sum % 2 != 0)
            return false;
        sum = sum / 2;
        int n = nums.length;
        boolean[] prev = new boolean[sum + 1];
        boolean[] curr = new boolean[sum + 1];

        Mark prev[0] = true as it is true to make sum = 0 using 0 elements
        prev[0] = true;

        Fill the subset table in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j < nums[i - 1])
                    curr[j] = prev[j];
                else
                    curr[j] = (prev[j] || prev[j - nums[i - 1]]);
            }
            copy curr into prev 
            for (int j=0; j<=sum; j++) {
                prev[j] = curr[j];
            }
        }
        return prev[sum];
    }
} 

Time Complexity: O(n * sum) - We fill a table of size n * sum.
Space Complexity: O(sum) - The space used by the two boolean arrays.


*/