/*

The Genetic Archive Compression Protocol. 

In a bioinformatics vault, DNA fragments are stored in a sequence sorted by genetic marker intensity(non-decreasing). 
Due to replication errors, several identical fragments exist consecutively. 
The archive has limited storage capacity. The system requires: 

-> Each distinct fragment must appear only once. 
-> The relative order must remain unchanged. 
-> All modifications must be performed in-place. 
-> Only the first k-positions must contain the unique fragments. The rest of the array beyond k-1 is irrelevant. 

Return the number k, representing the count of unique fragments remaining. 

Example 1: Input: [1,1,1,2,2,3]; Output: 3 
Example 2: [-3,-3,-2,-1,-1,0,0,0,1]; Output: 5  
Example 3: [5,5,5,5,5]; Output: 1  
Example 4: [0,1,2,3,4,5]; Output: 6  
Example 5: Input: [-10,-10,-5,-5,-5,-2,-1,-1] Output: 4

*/


/*

Two Pointer Approach:
We maintain:
i → index of the last unique element
j → scans the array

When we find a new unique element, we move it to position i+1.
Steps:

-> Start i = 0
-> Traverse with j from 1 → n-1
-> If arr[j] != arr[i], increment i
-> place element at arr[i] = arr[j]
-> Return i + 1

Why i + 1?
Because i stores the last unique index.


Dry Run:
[1,1,1,2,2,3]

i = 0, j = 1 → arr[j] = 1, arr[i] = 1 → duplicate, j++
i = 0, j = 2 → arr[j] = 1, arr[i] = 1 → duplicate, j++
i = 0, j = 3 → arr[j] = 2, arr[i] = 1 → unique, i = 1, arr[i] = 2
i = 1, j = 4 → arr[j] = 2, arr[i] = 2 → duplicate, j++
i = 1, j = 5 → arr[j] = 3, arr[i] = 2 → unique, i = 2, arr[i] = 3

Return i + 1 = 3.

*/

// Code:

import java.util.*;

public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] arr) {

        if (arr.length == 0) return 0;

        int i = 0;

        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        int k = removeDuplicates(arr);

        System.out.println(k);

        sc.close();
    }
}

// Time Complexity: O(n) - We traverse the array once.
// Space Complexity: O(1) - We use constant extra space for pointers.