/*

An encryption engine stores a sequence of integers. 
To activate a new cipher pattern, the entire sequence must be rotated to the right by k positions. 
Each element shifts right by one index and elements that fall off reappear at the beginning. 
You must rotate the array in-place. 

Example 1: Input: nums=[1,2,3,4,5,6,7], k=3 
Output: [5,6,7,1,2,3,4] 

Example 2: Input: nums=[-1,-100,3,99], k=2
Output: [3,99,-1,-100] 

Example 3: Input: nums=[1,2] , k=5 
Output: [2,1] 

Example 4: Input: nums=[10,20,30], k=0 
Output: [10,20,30] 

Example 5: Input: nums=[4,5,6,7,8], k=7 
Output: [7,8,4,5,6]

*/


// Approach: Reverse the entire array, then reverse the first k elements and finally reverse the remaining n-k elements.
// We can also use the modulus operator to handle cases where k is greater than n.

// Code:
import java.util.Scanner;

public class RotateArrayToRight {
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k is greater than n

        // Reverse the entire array
        reverse(nums, 0, n - 1);
        // Reverse the first k elements
        reverse(nums, 0, k - 1);
        // Reverse the remaining n-k elements
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read array
        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", "");

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        // Read k separately
        int k = sc.nextInt();

        rotate(arr, k);

        // Print output with [] and commas
        System.out.print("[");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if(i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");

        sc.close();
    }
}

// Time Complexity: O(n) where n is the length of the array.
// Space Complexity: O(1) since we are doing the rotation in-place.