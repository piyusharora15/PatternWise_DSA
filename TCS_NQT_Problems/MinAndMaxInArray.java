// Problem Statement: In the valley of Titans, the warriors are ranked by their strength values in array arr[]. The King requires immediate identification of the weakest warrior and the strongest warrior. Return both values in the format: min max . The scan must be done in a single traversal.

// Examples: 1. Input: arr = [3,2,1,56,10000, 167]
//   Output: 1 10000

// 2. Input: arr = [1, 345, 234, 21,56789]
//   Output: 1 56789

// 3. Input: arr = [56789]
//   Output: 56789 56789

// 4. Input: arr = [999999999999,1,500000000000]
//   Output: 1 999999999999

// Code:

import java.util.*;
public class MinAndMaxInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] parts = s.split(",");
        int n = parts.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(parts[i]);
        }
        findMinMax(arr, n);
        sc.close();
    }
    public static void findMinMax(long[] arr, int n) {
        long min = arr[0];
        long max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println(min + " " + max);
    }
}

// Time Complexity: O(n) where n is the number of elements in the array, as we need to traverse the array once to find both minimum and maximum values.
// Space Complexity: O(1) as we are using only a constant amount of extra space to store the minimum and maximum values.