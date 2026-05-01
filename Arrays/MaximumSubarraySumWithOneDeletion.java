// Problem Link: https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion?envType=problem-list-v2&envId=wh88bf73

/*

Companies -> Google, Facebook, Amazon, Microsoft, Apple.

Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. 
In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.

Example 1:
Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.

Example 2:
Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.

Example 3:
Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 

Constraints:
1 <= arr.length <= 105
-104 <= arr[i] <= 104

 */

/*

Intuition 🧠

The problem is a variation of the classic Kadane’s Algorithm. The twist is that we are allowed to "delete" at most one element to maximize the sum.

Imagine you have a "Deletion Ticket." At any index i, you have two states:

State A (maxNoDelete): You haven't used your ticket yet. You are doing a standard Kadane's sum.
State B (maxOneDelete): You have already used your ticket (either in the past or exactly at this index).
The goal is to transition between these states while keeping the sum as high as possible.

Approach🚀

We iterate through the array once and maintain two variables representing our states:

maxNoDelete: The max sum ending at i without any deletions.

Decision: Either extend the previous sum or start fresh at arr[i].

maxNoDelete = Math.max(maxNoDelete + arr[i], arr[i])

maxOneDelete: The max sum ending at i with exactly one deletion.

Option 1 (Keep current): You already used the deletion ticket earlier. 
To stay in this state, you must add the current arr[i].

Option 2 (Delete current): You use your deletion ticket right now on arr[i]. 
This means your sum is simply whatever the max sum was at i−1 without any deletions (prevMaxNoDelete).

maxOneDelete = Math.max(maxOneDelete + arr[i], prevMaxNoDelete)

Complexity:

Time complexity: O(N) — We traverse the array exactly once.

Space complexity: O(1) — We only use three variables to track state, regardless of array size.

Key Logic Summary:

The Deletion Act: When we set maxOneDelete = prevNoDelete, we are mathematically "skipping" the current element arr[i].

The Continuity: If we add arr[i] to maxOneDelete, we are saying, "I used my one allowed skip/delete somewhere in the past, so I have to take this current value."

Why this works for Negative Numbers? 🛑

By using oneDeleteMaxSum = Math.max(oneDeleteMaxSum + arr[i], prevNoDeleteMaxSum), we effectively "skip" a negative number if adding it would result in a sum lower than the sum we had before it. 

This is why prevNoDeleteMaxSum acts as the perfect "delete" action.

*/

class MaximumSubarraySumWithOneDeletion {

    public int maximumSum(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        // maxNoDelete: Maximum subarray sum ending at index i with ZERO elements deleted.
        // This is the standard Kadane's algorithm state.
        int maxNoDelete = arr[0];

        // maxOneDelete: Maximum subarray sum ending at index i with EXACTLY ONE element deleted.
        // Initialized to arr[0] because at index 1, one option is to delete arr[1] 
        // and keep only arr[0].
        int maxOneDelete = arr[0];
        int result = arr[0];

        for (int i = 1; i < n; i++) {
            // We store the 'no-delete' result from the previous index (i-1).
            int prevNoDelete = maxNoDelete;
            /* Standard Kadane's logic: 
               Either extend the previous subarray or start a new one at the current index.
             */
            maxNoDelete = Math.max(maxNoDelete + arr[i], arr[i]);

            /* Two options for a subarray with one deletion:
               Option 1: (maxOneDelete + arr[i]) 
                         We already used our deletion on an earlier element. 
                         Therefore, we MUST include the current element arr[i].
               
               Option 2: (prevNoDelete) 
                         We choose to delete the current element arr[i] right now. 
                         The sum becomes the max sum ending at the previous index (i-1) 
                         where no elements were deleted.
             */
            maxOneDelete = Math.max(maxOneDelete + arr[i], prevNoDelete);
            // The answer is the best sum found across all indices, 
            // whether we deleted an element or not.
            result = Math.max(result, Math.max(maxNoDelete, maxOneDelete));
        }
        return result;
    }
}
