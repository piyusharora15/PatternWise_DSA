/* Problem Statement: 

The Chrono-Locked Conveyer Belt: In an ancient mechanical city, a circular conveyer belt transports crystalline energy cores in a strictly non-decreasing sequence of stability levels. 
The belt was originally calibrated in sorted order to prevent resonance collapse. 
However during a maintenance cycle, the system was restarted from an arbitrary position on the belt. 
As a result, the visible sequence now appears rotated, though it must still preserve the integrity of the original sorted structure. 
The engineers suspect that: The belt was originally arranged in non-decreasing order; It may have been rotated any number of positions(including zero); Duplicate stability levels are allowed; 
If the belt does not conform to such a rotated sorted configuration, the stabilization chamber will fail. 
Your task is to determine whether the currently observed sequence could represent a vaild rotation of a non-decreasing sorted array. 
Return true if valid, otherwise return false. 

Test Cases: 
Input: [5,6,7,1,2,3,4] 
Output: true

Input: [2,2,2,3,1,2] 
Output: true

Input: [1,3,2,4]  
Output: false 

Input: [10,10,10]  
Output: true 

Input: [4,5,1,2,6]  
Output: false

*/

// Approach: We can solve this problem by counting the number of times the sequence decreases. 
// In a valid rotated sorted array, there should be at most one decrease. 
// If there are more than one decreases, it means that the array is not a valid rotation of a non-decreasing sorted array.
// We can iterate through the array and compare each element with the next one. 
// If we find a decrease, we increment our count. 
// Finally, we check if the count is less than or equal to 1, we return true, otherwise we return false.

// Code:

import java.util.*;

public class ValidArrayRotation {

    public static boolean isValidRotation(int[] arr) {
        int n = arr.length;
        int breakCount = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] > arr[(i + 1) % n]) {
                breakCount++;
            }
            if (breakCount > 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        boolean result = isValidRotation(arr);

        System.out.println(result);

        sc.close();
    }
}

// Time Complexity: O(n) where n is the number of elements in the array.
// Space Complexity: O(1) as we are using only a constant amount of extra space.