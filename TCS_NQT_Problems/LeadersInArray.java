/*

A kingdom has watchtowers built along a mountain ridge. 
Each tower has a defensive strength value. 
A tower is considered a Guardian Tower if no tower to its right has greater strength. 
The rightmost tower is always a Guardian Tower. 
Identify all Guardian Towers in the given sequence. 

Example 1: 
Input: [16,17,4,3,5,2] 
Output: [17,5,2] 

Example 2: 
Input: [10,4,2,4,1] 
Output: [10,4,4,1] 

Example 3: 
Input: [5,10,20,40] 
Output: [40] 

Example 4: 
Input: [30,20,20,10] 
Output: [30,20,20,10] 

Example 5: 
Input: [7,7,7,7] 
Output: [7,7,7,7]


*/

/*
Approach:
A tower is a Guardian Tower (Leader) if no element to its right is greater than it.
Important observations:
The rightmost element is always a leader.
Traverse the array from right to left.
Keep track of the maximum seen so far.
If the current element is greater than or equal to the max so far, it is a Guardian Tower.

Algorithm:
Start from the last element.
Maintain maxRight.
If arr[i] >= maxRight
it is a leader.
update maxRight.

Dry Run: 
[16,17,4,3,5,2]

Traverse from right:

2 → leader
5 → leader
3 → no
4 → no
17 → leader
16 → no

Leaders found (reverse order):

2 5 17

Correct order:

17 5 2

*/


// Code:

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LeadersInArray {
     public static void findLeaders(int[] arr) {

        int n = arr.length;
        ArrayList<Integer> leaders = new ArrayList<>();

        int maxRight = arr[n-1];
        leaders.add(maxRight);

        for(int i = n-2; i >= 0; i--){
            if(arr[i] >= maxRight){
                maxRight = arr[i];
                leaders.add(arr[i]);
            }
        }

        Collections.reverse(leaders);

        System.out.println(leaders);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        input = input.replace("[","").replace("]","");

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for(int i = 0; i < parts.length; i++){
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        findLeaders(arr);

        sc.close();
    }
}

// Time Complexity: O(n) - We traverse the array once.
// Space Complexity: O(k) - Where k is the number of leaders found. In the worst case (strictly decreasing array), k can be O(n).