/*

A security vault issues encrypted identification codes ranging from 0 to n inclusive. Exactly n codes are active in the system, but due to a cyber breach, one code has disappeared. You are given an array containing n distinct numbers within the range [0,n]. Identify the missing identification code. Return the missing number.

Example 1: Input: [0,1,3,4,5]; Output: 2 
Example 2: Input: [1]; Output: 0 
Example 3: [0]; Output: 1 
Example 4: Input: [4,2,1,0]; Output: 3
Example 5: Input: [8,6,4,2,3,5,7,0,1]; Output: 9

*/


// Approach: Using Sum Formula
// We can calculate the sum of first n natural numbers using the formula n*(n+1)/2. Then we can subtract the sum of the given array from this calculated sum to find the missing number.

// Code:

import java.util.*;

public class MissingNumber {

    public static int findMissing(int[] arr) {

        int n = arr.length;

        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for(int num : arr){
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for(int i = 0; i < parts.length; i++){
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        int result = findMissing(arr);

        System.out.println(result);

        sc.close();
    }
}


// Time Complexity: O(n) - We need to iterate through the array once to calculate the actual sum.
// Space Complexity: O(1) - We are using only a constant amount of extra space to store the expected sum and actual sum.