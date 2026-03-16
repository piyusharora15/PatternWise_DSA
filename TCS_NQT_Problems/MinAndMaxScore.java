/*

In a competitive tournament, participants scores are recorded in an array. 
You must determine the Minimum score and Maximum Score. 
Using the minimum possible comparisons possible. 

Example 1: Input: [3,5,4,1,9] 
Output: Min=1, Max=9 

Example 2: Input: [22,14,8,17,35,3] 
Output: Min=3, Max=35 

Example 3: Input: [100] 
Output: Min=100, Max=100 

Example 4: Input: [-5,-1,-9,-3] 
Output: Min=-9, Max=-1 

Example 5: Input: [7,7,7,7] 
Output: Min=7, Max=7

 */

 /*

Approach:
Idea (Pair Comparison Method)

Process elements in pairs.
For each pair:
First compare the two numbers with each other.
Compare the smaller with current min.
Compare the larger with current max.
This saves comparisons.

Comparison Count
Method	Comparisons
Naive	 2(n-1)
Pair method	≈ 3n/2

Algorithm

If array size is 1
min = max = arr[0]

If size > 1
Initialize min and max using first two elements.

Traverse remaining elements two at a time.

 */
// Code Implementation:
import java.util.Scanner;

public class MinAndMaxScore {

    public static void findMinMax(int[] arr) {

        int n = arr.length;
        int min, max;
        int i;

        if (n == 1) {
            min = max = arr[0];
        } else {

            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }

            i = 2;

            while (i < n - 1) {

                int localMin, localMax;

                if (arr[i] < arr[i + 1]) {
                    localMin = arr[i];
                    localMax = arr[i + 1];
                } else {
                    localMin = arr[i + 1];
                    localMax = arr[i];
                }

                if (localMin < min) {
                    min = localMin;
                }

                if (localMax > max) {
                    max = localMax;
                }

                i += 2;
            }

            if (n % 2 != 0) {
                if (arr[n - 1] < min) {
                    min = arr[n - 1]; 
                }else if (arr[n - 1] > max) {
                    max = arr[n - 1];
                }
            }
        }

        System.out.println("Min=" + min + ", Max=" + max);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", "");

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        findMinMax(arr);
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)