// Problem link: https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1

/*

Companies: Amazon, Microsoft, Adobe, Google, Facebook.

Given an array of positive integers arr[] and a value sum, determine if there is a subset of arr[] with sum equal to given sum. 

Examples:

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
Output: true 
Explanation: Here there exists a subset with target sum = 9, 4+3+2 = 9.

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 30
Output: false
Explanation: There is no subset with target sum 30.

Input: arr[] = [1, 2, 3], sum = 6
Output: true
Explanation: The entire array can be taken as a subset, giving 1 + 2 + 3 = 6.

Constraints:
1 <= arr.size() <= 200
1<= arr[i] <= 200
1<= sum <= 104

*/

/*

Naive Approach: Recursive Solution.

For the recursive approach, there will be two cases (In both cases, the number of available elements decreases by 1)

Consider the 'last' element to be a part of the subset. Now the new required sum = required sum - value of 'last' element.

Don't include the 'last' element in the subset. Then the new required sum = old required sum.

Mathematically the recurrence relation will look like the following:

isSubsetSum(arr, n, sum) = isSubsetSum(arr, n-1, sum) OR isSubsetSum(arr, n-1, sum - arr[n-1])

Base Cases:

isSubsetSum(arr, n, sum) = false, if sum > 0 and n = 0
isSubsetSum(arr, n, sum) = true, if sum = 0

Code:

class Solution
{
    public Boolean isSubsetSum(int arr[], int n, int sum)
    {
        Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
        
        If last element is greater than sum, then ignore it
        if (arr[n - 1] > sum)
            return isSubsetSum(arr, n - 1, sum);

        else, check if sum can be obtained by any of the following
           (a) including the last element
           (b) excluding the last element
        return isSubsetSum(arr, n - 1, sum) || isSubsetSum(arr, n - 1, sum - arr[n - 1]);
    }
}

Time Complexity: O(2^n) where n is the number of elements in the given array.
Space Complexity: O(n) due to the recursive stack space.


Better Approach 1: Memoization.

The recursive approach involves two properties of Dynamic Programming: Overlapping Subproblems and Optimal Substructure. Hence, we can optimize the recursive approach using Dynamic Programming.

1. Optimal Substructure: 

The solution to the subset sum problem can be derived from the optimal solutions of smaller subproblems. Specifically, for any given n (the number of elements considered) and a target sum, we can express the recursive relation as follows:

If the last element (arr[n-1]) is greater than sum, we cannot include it in our subset isSubsetSum(arr,n,sum) = isSubsetSum(arr,n-1,sum)

If the last element is less than or equal to sum, we have two choices: 

Include the last element in the subset, isSubsetSum(arr,n,sum) = isSubsetSum(arr,n-1,sum-arr[n−1]) 
Exclude the last element, isSubsetSum(arr,n,sum) = isSubsetSum(arr,n-1,sum)

2. Overlapping Subproblems:

When implementing a recursive approach to solve the subset sum problem, we observe that many subproblems are computed multiple times. For instance, when computing isSubsetSum(arr, sum), where arr[] = {2,3,1,1} and sum = 4 we might need to compute isSubsetSum(1,3) multiple times.

-> The recursive solution involves changing two parameters: the current index in the array (n) and the current target sum (sum). 
-> We need to track both parameters, so we create a 2D array of size (n+1) x (sum+1) because the value of n will be in the range [0, n] and sum will be in the range [0, sum].
-> We initialize the 2D array with -1 to indicate that no subproblems have been computed yet.
-> We check if the value at dp[n][sum] is -1. If it is, we proceed to compute the result. otherwise, we return the stored result.

Code:

class Solution
{
    public Boolean isSubsetSumUtil(int arr[], int n, int sum, boolean[][] dp)
    {
        Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
        
        if (dp[n][sum] != false)
            return dp[n][sum];
        
        If last element is greater than sum, then ignore it
        if (arr[n - 1] > sum)
            return dp[n][sum] = isSubsetSumUtil(arr, n - 1, sum, dp);

        else, check if sum can be obtained by any of the following
           (a) excluding the last element
           (b) including the last element
        return dp[n][sum] = isSubsetSumUtil(arr, n - 1, sum, dp) || isSubsetSumUtil(arr, n - 1, sum - arr[n - 1], dp);
    }
        public Boolean isSubsetSum(int arr[], int sum)
        {
            int n = arr.length;
            boolean[][] dp = new boolean[n + 1][sum + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {
                    dp[i][j] = false;
                }
            }
            return isSubsetSumUtil(arr, n, sum, dp);
        }
}

Time Complexity: O(n*sum) where n is the number of elements in the given array and sum is the required sum.
Space Complexity: O(n*sum) due to the dp array + O(n) due to the recursive stack space, which can be optimized to O(1) by using tabulation.


Better Approach 2: Tabulation.

We will create a 2D boolean array dp of size (n+1) x (sum+1) where dp[i][j] will be true if there is a subset of the first i elements of arr[] with sum equal to j, otherwise false.
We will initialize the first column of the dp array to true because a sum of 0 can always be achieved with an empty subset. We will initialize the first row of the dp array to false (except for dp[0][0]) because a non-zero sum cannot be achieved with an empty subset.

Code:

class Solution
{
    public Boolean isSubsetSum(int arr[], int sum)
    {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // A sum of 0 can always be achieved with an empty subset
        }
        
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = false; // A non-zero sum cannot be achieved with an empty subset
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j) { // If the last element is greater than the current sum, we cannot include it
                    dp[i][j] = dp[i - 1][j]; // Ignore the last element
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]]; // Include or exclude the last element
                }
            }
        }
        
        return dp[n][sum];
    }
}

Time Complexity: O(n*sum) where n is the number of elements in the given array and sum is the required sum.
Space Complexity: O(n*sum) due to the dp array.


Optimal Approach: Space Optimization.

Create two 1D boolean arrays prev and curr of size (sum+1) where prev[j] will represent the result for the previous row (i-1) and curr[j] will represent the result for the current row (i).
We will initialize the prev array such that prev[0] = true (because a sum of 0 can always be achieved with an empty subset) and the rest of the elements will be false.
We will iterate through the elements of the array and update the curr array based on the values in the prev array. 
After processing each element, we will copy the curr array to prev for the next iteration.
We will return the value in prev[sum] at the end, which will indicate whether a subset with the given sum exists.

Code:

class Solution
{
    public Boolean isSubsetSum(int arr[], int sum)
    {
        int n = arr.length;
        boolean[] prev = new boolean[sum + 1];
        boolean[] curr = new boolean[sum + 1];
        
        prev[0] = true; // A sum of 0 can always be achieved with an empty subset
        
        for (int j = 1; j <= sum; j++) {
            prev[j] = false; // A non-zero sum cannot be achieved with an empty subset
        }
        
        for (int i = 1; i <= n; i++) {
            curr[0] = true; // A sum of 0 can always be achieved with an empty subset
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j) { // If the last element is greater than the current sum, we cannot include it
                    curr[j] = prev[j]; // Ignore the last element
                } else {
                    curr[j] = prev[j] || prev[j - arr[i - 1]]; // Include or exclude the last element
                }
            }
            System.arraycopy(curr, 0, prev, 0, sum + 1); // Copy curr to prev for the next iteration
        }
        
        return prev[sum];
    }
}

Time Complexity: O(n*sum) where n is the number of elements in the given array and sum is the required sum.
Space Complexity: O(sum) due to the prev and curr arrays.

*/