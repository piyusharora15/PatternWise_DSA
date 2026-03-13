/*

A sequence of power nodes is arranged randomly. 
You must find the first node such that: 
Every node to its left has strictly smaller value. 
Every node to its right has strictly greater value. 
If no such node exists, return -1. 

Example 1: Input: [4,2,5,7] 
Output: 5 

Example 2: Input: [11,9,12] 
Output: -1 

Example 3: Input: [1,2,3,4,5] 
Output: 2 

Example 4: Input: [5,1,4,4] 
Output: -1 

Example 5: Input: [2,3,8,9,10,5] 
Output: -1

*/

/* Approach:

This is a classic array problem often called “Find the element where all left elements are smaller and all right elements are greater.”

Condition for a valid node arr[i] :

Every element on the left must be strictly smaller: max(left side) < arr[i]

Every element on the right must be strictly greater: arr[i] < min(right side)

Brute force checking both sides for every element would be O(n²), which is inefficient.

Instead we solve it in O(n) using two helper arrays.

Optimal Idea:

Create two arrays:
1️⃣ leftMax[i] : Stores the maximum element from index 0 to i-1

2️⃣ rightMin[i] : Stores the minimum element from index i+1 to n-1

Then check for each element:

leftMax[i] < arr[i] < rightMin[i]

The first element satisfying this condition is the answer.

Example: Input : [4,2,5,7]

leftMax: [-∞,4,4,5]

rightMin: [2,5,7,∞]

Check:
4 < 5 < 7  → valid

Output: 5

*/

// Code Implementation:

import java.util.Scanner;
public class equilibriumSentinel {
    public static int findEquilibriumNode(int[] arr) {
        int n = arr.length;
        if(n < 3) return -1; // Edge case: array too small
        int[] leftMax = new int[n];
        leftMax[0] = arr[0];
        for(int i=1;i<n;i++){
            leftMax[i] = Math.max(arr[i-1], leftMax[i-1]);
        }
        int[] rightMin = new int[n];
        rightMin[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--){
            rightMin[i] = Math.min(arr[i+1], rightMin[i+1]);
        }
        for(int i=1;i<n-1;i++) {
            if(leftMax[i] < arr[i] && arr[i] < rightMin[i]) {
                return arr[i];
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", "");
        String[] parts = input.split(",");
        int[] arr = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        int result = findEquilibriumNode(arr);
        System.out.println(result);
        sc.close();
    }
}

// Time Complexity: O(n) due to single pass for leftMax, rightMin and final check.
// Space Complexity: O(n) for the leftMax and rightMin arrays.