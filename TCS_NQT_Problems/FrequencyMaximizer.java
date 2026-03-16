/*

You are given resource values in an array. 
In one operation, you may increment any element by 1. 
You may perform at most k operations. 
Determine the maximum possible frequency of any single value after operations. 

Example 1: Input: nums=[1,2,4], k=5
Output: 3 

Example 2: Input: nums=[1,4,8,13], k=5 
Output: 2 

Example 3: Input: nums=[3,9,6], k=2 
Output: 1 

Example 4: Input: nums=[1,1,1,2,2,4], k=3 
Output: 5 

Example 5: Input: nums=[5,5,5,5], k=10 
Output: 4

*/

/*

Approach:
The key insight is:
You are allowed to increase elements, not decrease them. So the best strategy is to raise smaller numbers to match a larger number.
To do this efficiently, we use:

Sorting
Sliding Window
Prefix sum logic

This gives O(n log n) time.

Core Idea:
If we want several numbers to become equal to nums[right], we need operations:
operations = nums[right] * window_size - sum_of_window
If operations ≤ k, the window is valid.
If operations > k, shrink the window from the left.
If operations ≤ k, the window is valid (we can make all elements equal to nums[right]).

Algorithm

Sort the array.
Use two pointers left and right.
Maintain running sum of window.
Check if the required increments exceed k.
Track the largest window size.

Example Walkthrough: Input: nums=[1,1,1,2,2,4], k=3 

Step 1 — Sort the Array
The algorithm first sorts the array.
[1,1,1,2,2,4]
(It is already sorted.)

Step 2 — Initialize Variables
left = 0
sum = 0
maxFreq = 0

We now expand the window using right.

Iteration 1 — right = 0, left = 0
Add element to window:
sum = 1
window = [1]
Window size: 1  (right - left + 1)

Operations needed: 1*1 − 1 = 0
Valid.
maxFreq = 1

Iteration 2 — right = 1, left = 0
Add element:
sum = 2
window = [1,1]
Window size: 2  (right - left + 1)

Operations needed: 1*2 − 2 = 0
Valid.
maxFreq = 2

Iteration 3 — right = 2
Add element:
sum = 3
window = [1,1,1]
Window size: 3  (right - left + 1)

Operations needed: 1*3 − 3 = 0
Valid.
maxFreq = 3

Iteration 4 — right = 3
Add element:
sum = 5
window = [1,1,1,2]
Window size: 4  (right - left + 1)
Target = 2

Operations needed:
2*4 − 5
= 8 − 5
= 3
Allowed because: 3 ≤ k
So we can transform: [1,1,1,2] → [2,2,2,2]
(using 3 operations)
maxFreq = 4

Iteration 5 — right = 4
Add element:
sum = 9
window = [1,1,1,2,2,4]
Window size: 6  (right - left + 1)
Target = 4

Operations needed:
4*6 − 9
= 24 − 9
= 15
But
15 > k
So we shrink the window.
Shrink Window
Remove nums[left]
left = 1
sum = 8
window = [1,1,2,4]
Recalculate:
4*4 − 8 = 16 − 8 = 8
Still
8 > 3
Shrink again.
Remove next element
left = 2
sum = 7
window = [1,2,4]
Recalculate:
4*3 − 7 = 12 − 7 = 5
Still too large.
Remove next element
left = 3
sum = 6
window = [2,4]
Recalculate:
4*2 − 6 = 8 − 6 = 2
Now valid.
Window size:
2
But
maxFreq = 4
so unchanged.

Final Answer
maxFreq = 4

Meaning we can make 4 elements equal using ≤3 operations.

Example transformation:
[1,1,1,2] → [2,2,2,2]
Important Insight

The algorithm always tries to make all numbers equal to the largest element in the window (nums[right]).

Because we are only allowed to increase numbers, never decrease them.

Final Result
Output = 4

*/

// Code Implementation:

import java.util.Arrays;
import java.util.Scanner;
public class FrequencyMaximizer {

    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int left = 0;
        int maxFreq = 0;
        for(int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while((long)nums[right] * (right - left + 1) - sum > k) {
                sum -= nums[left];
                left++;
            }
            maxFreq = Math.max(maxFreq, right - left + 1);
        }
        return maxFreq;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("[","").replace("]","");
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for(int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i].trim());
        }
        int k = sc.nextInt();
        System.out.println(maxFrequency(nums, k));
        sc.close();
    }
}

// Time Complexity: O(n log n) due to sorting.
// Space Complexity: O(1) if we ignore the input array, otherwise O(n).