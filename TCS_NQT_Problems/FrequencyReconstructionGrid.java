/*

A corrupted database stores integers between 1 and p. 

You are given:
n -> size for counting range 1 to n,
arr[] -> array of integers,
p -> upper possible bound of values.

You must modify arr[] in place such that: 
arr[i] becomes the frequency of integer (i+1). 
Ignore values greater than n. 

Example 1: Input: n=5,arr=[2,3,2,3,5], p=5 
Output: [0,2,2,0,1] 

Example 2: n=4, arr=[3,3,3,3], p=3 
Output: [0,0,4,0] 

Example 3: Input: n=6, arr= [1,2,3,4,5,6], p=6 
Output: [1,1,1,1,1,1] 

Example 4: n=3, arr=[4,5,6],p=6 
Output: [0,0,0] 

Example 5: Input: n=7, arr=[1,1,2,8,2,3,10] ; p=10 ; Output: [2,2,1,0,0,0,0]

*/

/*
Approach:

This is a well-known in-place frequency counting problem.

We must convert the array so that:
arr[i] = frequency of (i + 1)

Constraints:
Values range from 1 → p
But we only care about 1 → n
Ignore values > n
Must be in-place (O(1) extra space)

This pattern is commonly called “Count frequencies in-place”.

Key Idea:

-> Use the array itself to store counts by using modular arithmetic.
-> Since the values are ≤ p, we use (n + 1) as a multiplier.

Steps:

-> Traverse array.
-> For each value val = arr[i] % (n+1)
-> If val is between 1 and n, increment its count:
   arr[val-1] += (n+1)

-> Finally extract frequency:
   arr[i] = arr[i] / (n+1)

Example:
Input:
n = 5
arr = [2,3,2,3,5]
p = 5

Goal:
arr[i] = frequency of (i+1)
So the final array should represent:

Number	Frequency
1	    0
2	    2
3	    2
4	    0
5	    1

Output:
[0,2,2,0,1]

Step 1 — Choose Encoding Value
We use:
mod = n + 1
mod = 6
Why?
Because all original numbers are ≤5, so adding multiples of 6 lets us store frequency information without losing the original value.

We use:
arr[val-1] += mod

Step 2 — Start Traversing Array
Initial array
[2,3,2,3,5]

Iteration 1 (i = 0)
arr[0] = 2
val = arr[0] % 6 = 2
Update index:
arr[val-1] → arr[1]
Add mod:
arr[1] = 3 + 6 = 9
Array becomes
[2,9,2,3,5]

Iteration 2 (i = 1)
arr[1] = 9
val = 9 % 6 = 3
Update:
arr[2] += 6
arr[2] = 2 + 6 = 8
Array
[2,9,8,3,5]

Iteration 3 (i = 2)
arr[2] = 8
val = 8 % 6 = 2
Update:
arr[1] += 6
arr[1] = 9 + 6 = 15
Array
[2,15,8,3,5]

Iteration 4 (i = 3)
arr[3] = 3
val = 3 % 6 = 3
Update
arr[2] += 6
arr[2] = 8 + 6 = 14
Array
[2,15,14,3,5]

Iteration 5 (i = 4)
arr[4] = 5
val = 5 % 6 = 5
Update
arr[4] += 6
arr[4] = 5 + 6 = 11
Array
[2,15,14,3,11]

Step 3 — Extract Frequencies
Now divide every element by 6

Value  ÷6	Frequency
2	    0	    0
15	    2	    2
14	    2	    2
3	    0	    0
11	    1	    1

Final array
[0,2,2,0,1]
Correct.

*/


// Code:

import java.util.Scanner;

public class FrequencyReconstructionGrid {

    public static void countFrequency(int[] arr, int n, int p) {
        int mod = n + 1;

        for(int i = 0; i < n; i++){
            int val = arr[i] % mod;
            if(val >= 1 && val <= n){
                arr[val - 1] += mod;
            }
        }

        for(int i = 0; i < n; i++){
            arr[i] = arr[i] / mod;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String input = sc.nextLine();
        input = input.replace("[","").replace("]","");
        String[] parts = input.split(",");
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        int p = sc.nextInt();

        countFrequency(arr,n,p);

        System.out.print("[");
        for(int i=0;i<n;i++){
            System.out.print(arr[i]);
            if(i != n-1) System.out.print(",");
        }
        System.out.print("]");
        sc.close();
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1) (in-place)