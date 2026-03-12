/*

In a deep-space transmission relay, binary signals are streamed continuously. 
1 represents a stable signal pulse. 
0 represents a disruption caused by cosmic interference. 
The stability of a transmission session is measured by the longest uninterrupted chain of stable pulses. 
Your task is to determine the maximum number of consecutive 1s in the signal log. 

Example 1: Input: [1,1,0,1,1,1,0,1] 
Output: 3              

Example 2: Input: [0,0,0,0] 
Output: 0 

Example 3: Input: [1,1,1,1,1]  
Output: 5

Example 4: Input: [1,0,1,1,0,1,1,1,1,0,1] 
Output: 4 

Example 5: Input: [0,1,1,0,1,1,1,0,1,1] 
Output: 3


*/

/*

Approach: The idea is simple:

-> Traverse the array.
-> Maintain a current streak of 1s.
-> Update the maximum streak whenever we see a 1.
-> Reset the current streak to 0 whenever we encounter a 0.

Initialize : count = 0, maxCount = 0

Traverse the array: 
If element is 1 → count++
Update maxCount = max(maxCount, count)
If element is 0 → reset count = 0
Return maxCount.

*/

// Code:

import java.util.*;

public class MaxConsecutiveOnes {

    public static int maxConsecutiveOnes(int[] arr) {

        int count = 0;
        int maxCount = 0;

        for(int i = 0; i < arr.length; i++) {

            if(arr[i] == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } 
            else {
                count = 0;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        input = input.replace("[", "").replace("]", "");

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        int result = maxConsecutiveOnes(arr);

        System.out.println(result);

        sc.close();
    }
}

// Time Complexity: O(n) where n is the length of the input array.
// Space Complexity: O(1) since we are using only a constant amount of extra space.