/*

https://www.geeksforgeeks.org/problems/non-repeating-element3958/1

Find the first non-repeating element in a given array arr of integers and if there is not present any non-repeating element then return 0

Note: The array consists of only positive and negative integers and not zero.

Examples:

Input: arr[] = [-1, 2, -1, 3, 2]
Output: 3
Explanation: -1 and 2 are repeating whereas 3 is the only number occuring once. Hence, the output is 3. 

Input: arr[] = [1, 1, 1]
Output: 0
Explanation: There is not present any non-repeating element so answer should be 0.

*/

/*

Approach: Hashing.
1. Create a hash map to store the frequency of each element in the array.
2. Traverse the array and update the frequency of each element in the hash map.
3. Traverse the array again and return the first element with a frequency of 1.
4. If no such element is found, return 0.

*/

import java.util.HashMap;

public class FindFirstNonRepeatingElement {
    public static int firstNonRepeating(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // Step 1: Count the frequency of each element
        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Find the first non-repeating element
        for(int num : arr) {
            if(map.get(num) == 1) {
                return num;
            }
        }
        return 0; // No non-repeating element found
    }
}

// Time Complexity: O(n), where n is the number of elements in the array.
// Space Complexity: O(n), in the worst case when all elements are unique.