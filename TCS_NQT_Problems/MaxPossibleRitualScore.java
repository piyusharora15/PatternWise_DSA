/*

In the Empire of Velmora, a ceremonial corridor is embedded with enchanted energy tiles. 
Each tile stores a positive integer energy value in array arr[], indexed from 0. 
The High Council mandates the following ritual: 
You may choose any two indices i and j such that i < j. 
From the contiguous segment arr[i..j] , determine: 
The smallest energy value, 
The second smallest distinct energy value. 

The ritual score of that segment is defined as the sum of those two values. 
Your task is to compute the maximum possible ritual score obtainable from any valid segment. 

Constraints: 

2<=arr.size<=10^5 
1<=arr[i]<=10^6 
Expected Time Complexity: O(n) 
Expected Auxiliary Space: O(1).                                         

Example 1: Input: arr=[4,3,1,5,6] 
Output: 11 

Example 2: arr=[5,4,3,1,6] 
Output: 9

*/

/*

Approach:
For any subarray, the smallest and second smallest elements will always come from two adjacent elements after sorting that subarray.

But instead of sorting every subarray (too slow), we observe something powerful:

The maximum possible sum of (smallest + second smallest) will always come from two adjacent elements in the original array.

This is because if we take any two adjacent elements, they will be the smallest and second smallest in that subarray of size 2.

So, we can simply iterate through the array and calculate the sum of every pair of adjacent elements, and keep track of the maximum sum.

We just need to check:
arr[i] + arr[i+1]
for all i.

Take the maximum sum among all adjacent pairs.

Why This Works
Because:
If you take a larger subarray, the smallest values tend to decrease, lowering the sum.

The best chance to get a large minimum + second minimum is when both values are already large and close together.
So the answer always lies in adjacent pairs.

Algorithm:

Initialize maxSum = 0
Loop from i = 0 → n-2
Compute:
sum = arr[i] + arr[i+1]
Update max
Return result

*/

// Code Implementation:

import java.util.*;

public class MaxPossibleRitualScore {
    public static int maxRitualScore(int[] arr) {
        int maxSum = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            maxSum = Math.max(maxSum, arr[i] + arr[i+1]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("[","").replace("]","");
        String[] parts = input.split(",");
        int[] arr = new int[parts.length];
        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        System.out.println(maxRitualScore(arr));
        sc.close();
    }
}

// Time Complexity: O(n) - We traverse the array once.
// Space Complexity: O(1) - We use only a constant amount of extra space.