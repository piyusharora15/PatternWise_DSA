/*

A distributed computing grid contains N processing units. 
Each unit consumes resources represented by array arr[]. 
You must determine whether it is possible to partition the array into two subsets such that the sum of elements in both subsets is equal. 
Return 1 if such a partition exists. 0 otherwise. 

Constraints: 
1 <= N <= 100 
1 <= arr[i] <= 1000 
N * sum(arr[i]) <= 5 * 10 ^6 

Example 1: 
Input: 
N = 4 
arr[] = [1, 5, 11, 5] 
Output: 1 

Example 2: 
Input: 
N = 3 
arr[] = [1, 3, 5] 
Output: 0

*/

import java.util.Scanner;

public class PartitionEqualSubsetSum {

    public static int canPartition(int[] arr, int n) {

        int sum = 0;

        for(int num : arr) {
            sum += num;
        }

        if(sum % 2 != 0) return 0;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for(int num : arr) {
            for(int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target] ? 1 : 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String input = sc.nextLine();

        // 🔥 Clean input (handles [], commas, spaces)
        input = input.replace("[", "")
                     .replace("]", "")
                     .replace(",", " ")
                     .trim();

        String[] parts = input.split("\\s+");

        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(canPartition(arr, n));

        sc.close();
    }
}

// Time Complexity: O(N * target) where target is sum(arr) / 2
// Space Complexity: O(target) due to the dp array