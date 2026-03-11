/*

Inside a quantum communication facility, each signal packet is labeled with a priority code: 0-> Low priority, 1 -> Medium Priority, 2 -> High Priority. 
Due to electromagnetic interference, the packets are scrambled. 
The control system demands: 
Sort the packets in ascending order of priority, 
Must be done in O(n) time and use O(1) space. 
No secondary storage arrays allowed. 
Restore the signal stream to proper priority order. 

Example 1: Input: [2,0,1,2,1,0,0,2] 
Output: [0,0,0,1,1,2,2,2]

Example 2: Input: [1,1,1,1] 
Output: [1,1,1,1] 

Example 3: Input: [0,2,2,1,0,1,2,0,1]
Output: [0,0,0,1,1,1,2,2,2]

Example 4: Input: [2,2,2,2,0] 
Output: [0,2,2,2,2]

Example 5: Input: [0] 
Output: [0]

*/


// Approach: Dutch National Flag Algorithm.
/*

We maintain three regions in the array:
0 ... low-1      → all 0s
low ... mid-1    → all 1s
mid ... high     → unknown
high+1 ... end   → all 2s

Pointers:
low  → next position for 0
mid  → current element
high → next position for 2

Traverse while mid <= high:

If element is 0
swap with low
low++
mid++

If element is 1
just mid++

If element is 2
swap with high
high--
do not increment mid because the swapped element from high needs to be processed


[2,0,1,2,1,0,0,2]
0s → left
2s → right
1s → middle

[0,0,0,1,1,2,2,2]

*/

// Code Implementation:

import java.util.Arrays;
import java.util.Scanner;

public class Sort0s1s2sInArray {
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while(mid <= high) {
            if(nums[mid] == 0) {
                // swap nums[low] and nums[mid]
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if(nums[mid] == 1) {
                mid++;
            } else {
                // swap nums[mid] and nums[high]
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", "");
        String parts[] = input.split(",");
        int[] nums = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            nums[i] = Integer.parseInt(parts[i].trim());
        }
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        sc.close();
    }
}

// Time Complexity: O(n) - We traverse the array once.
// Space Complexity: O(1) - No extra space used, only a few variables for pointers.