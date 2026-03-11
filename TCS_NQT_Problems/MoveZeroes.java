/*

A spacecraft cargo deck records mass distribution in an integer array. 
Due to anti-gravity calibration errors, all cargo units with mass zero must be relocated to the end of the deck. 
Relative order of non-zero cargo must remain unchanged. 
Operation must be performed in place. 
No additional cargo may be allocated. 
Rearrange the array so that all zeros appear at the end. 

Example 1: Input: [0,0,1,0,3,12]
Output: [1,3,12,0,0,0]

Example 2: Input: [4,2,4,0,0,3,0,5,1,0]
Output: [4,2,4,3,5,1,0,0,0,0] 

Example 3: Input: [0,0,0] 
Output: [0,0,0]

Example 4: Input: [1,2,3] 
Output: [1,2,3]

Example 5: Input: [0,1,0,0,2,0,3,4] 
Output: [1,2,3,4,0,0,0,0]

*/

// Approach: Two Pointers.
// We can use two pointers to keep track of the position of the last non-zero element and the current element.
// We can iterate through the array and whenever we encounter a non-zero element, we can swap it with the element at the last non-zero position and move the last non-zero pointer to the right.
// This way, we can ensure that all non-zero elements are moved to the front of the array and all zeros are moved to the end.


// Code:
import java.util.Arrays;
import java.util.Scanner;

public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int lastNonZero = 0; // Pointer to track the position of the last non-zero element

        for(int i=0;i<nums.length;i++) {
            if(nums[i] != 0) {
                // Swap the current non-zero element with the element at the last non-zero position
                int temp = nums[lastNonZero];
                nums[lastNonZero] = nums[i];
                nums[i] = temp;
                lastNonZero++; // Move the last non-zero pointer to the right
            }
        }
        while(lastNonZero < nums.length) {
            nums[lastNonZero] = 0; // Fill the remaining elements with zero
            lastNonZero++;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", ""); // Remove brackets if present
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            nums[i] = Integer.parseInt(parts[i].trim());
        }
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        sc.close();
    }
}

// Time Complexity: O(n), where n is the length of the input array. We traverse the array once to move non-zero elements and once to fill zeros.
// Space Complexity: O(1), as we are performing the operation in place and not using any additional data structures.