/*

https://www.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1

Given an array arr[]. Rotate the array to the left (counter-clockwise direction) by d steps, where d is a positive integer. Do the mentioned change in the array in place.

Note: Consider the array as circular.

Examples :

Input: arr[] = [1, 2, 3, 4, 5], d = 2
Output: [3, 4, 5, 1, 2]
Explanation: when rotated by 2 elements, it becomes 3 4 5 1 2.

Input: arr[] = [2, 4, 6, 8, 10, 12, 14, 16, 18, 20], d = 3
Output: [8, 10, 12, 14, 16, 18, 20, 2, 4, 6]
Explanation: when rotated by 3 elements, it becomes 8 10 12 14 16 18 20 2 4 6.

Input: arr[] = [7, 3, 9, 1], d = 9
Output: [3, 9, 1, 7]
Explanation: when we rotate 9 times, we'll get 3 9 1 7 as resultant array.

*/

/*

Approach 1: Brute Force.
Shift one element at a time and repeat the process d times.
Time Complexity: O(n*d)
Space Complexity: O(1)

void rotateArr(int[] arr, int d) {
    int n = arr.length;

    for (int i = 0; i < d; i++) {
        int first = arr[0];

        for (int j = 0; j < n - 1; j++) {
            arr[j] = arr[j + 1];
        }

        arr[n - 1] = first;
    }
}

Approach 2: Using Temporary Array.
Create a temporary array of size d and copy the first d elements of the original array into it. Then, shift the remaining elements of the original array to the left by d positions. Finally, copy the elements from the temporary array back to the end of the original array.

void rotateArr(int[] arr, int d) {
    int n = arr.length;
    d = d % n;

    int[] temp = new int[n];

    for (int i = 0; i < n - d; i++) {
        temp[i] = arr[d + i];
    }

    for (int i = 0; i < d; i++) {
        temp[n - d + i] = arr[i];
    }

    for (int i = 0; i < n; i++) {
        arr[i] = temp[i];
    }
}

Time Complexity: O(n)
Space Complexity: O(n)

Approach 3: Using Reversal Algorithm.(Optimal Solution)
The idea is to reverse the first d elements, then reverse the remaining n-d elements, and finally reverse the entire array.

Dry Run:

[1,2,3,4,5], d = 2

Step1: [2,1,3,4,5]
Step2: [2,1,5,4,3]
Step3: [3,4,5,1,2]

*/

public class RotateArrayToLeft {
    public static void rotateArr(int[] arr, int d) {
        int n = arr.length;
        d = d % n;

        reverse(arr, 0, d - 1);
        reverse(arr, d, n-1);
        reverse(arr, 0, n-1);
    }
    public static void reverse(int[] arr, int start, int end) {
        while(start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)