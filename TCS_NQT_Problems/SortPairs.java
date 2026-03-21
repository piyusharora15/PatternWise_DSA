/*

You are given n pairs. Sort them : 
1. By first element(ascending).
2. If first elements are equal -> sort by second element ascending.  

Input Format:
3
3 6, 2 4, 7 8
Output Format:
2 4, 3 6, 7 8

*/

// Code:

import java.util.Scanner;

public class SortPairs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of pairs
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input line: "3 6, 2 4, 7 8"
        String input = sc.nextLine();

        // Split pairs
        String[] pairs = input.split(",");

        int[][] arr = new int[n][2];

        // Parse input
        for(int i = 0; i < n; i++) {

            String p = pairs[i].trim();     // remove spaces
            String[] nums = p.split(" ");   // split by space

            arr[i][0] = Integer.parseInt(nums[0]);
            arr[i][1] = Integer.parseInt(nums[1]);
        }

        // 🔥 Selection Sort
        for(int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for(int j = i + 1; j < n; j++) {

                // Compare based on condition
                if(arr[j][0] < arr[minIndex][0] ||
                   (arr[j][0] == arr[minIndex][0] && arr[j][1] < arr[minIndex][1])) {

                    minIndex = j;
                }
            }

            // Swap both elements
            int temp0 = arr[i][0];
            int temp1 = arr[i][1];

            arr[i][0] = arr[minIndex][0];
            arr[i][1] = arr[minIndex][1];

            arr[minIndex][0] = temp0;
            arr[minIndex][1] = temp1;
        }

        // Output in required format
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i][0] + " " + arr[i][1]);
            if(i != n - 1) {
                System.out.print(", ");
            }
        }

        sc.close();
    }
}

// Time Complexity: O(n^2) due to selection sort
// Space Complexity: O(n) for storing pairs

/*

✅ Comparison Logic
arr[j][0] < arr[minIndex][0]
OR
(arr[j][0] == arr[minIndex][0] && arr[j][1] < arr[minIndex][1])


✅ Why 2D Array?
arr[i][0] → first element
arr[i][1] → second element


✅ Selection Sort Pattern
for i → fix position
for j → find minimum
swap

*/