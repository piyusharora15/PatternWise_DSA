/*

A real time monitoring system records a sequence of integer values Arr of size N.
An element is considered "dominant" if it is strictly greater than every element that appeared before it. 
The first element is always considered dominant. 
Determine how many elements in the sequence satisfy this dominance condition. 

Constraints:
1 <= N <= 20
1 <= Arr[i] <= 10000

Input Format:
N = 5
Arr = [7, 4, 8, 2, 9]
Output Format:
3

Input:
N = 5
Arr = [3, 4, 5, 8, 9]
Output:
5

*/

/*

Approach:
An element is dominant if:
Arr[i] > all previous elements
So we just need to track:
maximum value seen so far.

Algorithm:
First element is always dominant → count = 1
Set:
maxSoFar = Arr[0]
Traverse from index 1 → n-1
If:
Arr[i] > maxSoFar

👉 It's dominant
👉 Increment count
👉 Update max

🧪 Dry Run:
Example 1
[7, 4, 8, 2, 9]

Element	MaxSoFar	Dominant?
7	     7	        ✅
4	     7	        ❌
8	     8	        ✅
2	     8	        ❌
9	     9	        ✅

Output:
3
Example 2

[3, 4, 5, 8, 9]

Every element is increasing → all dominant

Output:
5

*/

// Code:

import java.util.Scanner;

public class FindDominantElements {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String input = sc.nextLine();

        // Remove brackets if present
        input = input.replace("[", "").replace("]", "");

        String[] parts = input.split(",");

        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        int count = 1; // first element always dominant
        int maxSoFar = arr[0];

        for(int i = 1; i < n; i++) {

            if(arr[i] > maxSoFar) {
                count++;
                maxSoFar = arr[i];
            }
        }

        System.out.println(count);
        sc.close();
    }
}

// Time Complexity: O(N)
// Space Complexity: O(N) for input array, O(1) for counting and max