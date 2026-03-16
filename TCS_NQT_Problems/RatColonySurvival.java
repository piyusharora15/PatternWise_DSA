/*

A colony of r rats requires unit amount of food per rat. 
An array represents food available in consecutive houses. 
Determine the minimum number of houses required to feed all rats. 
Return -1 if array is null and 0 if total food is insufficient. 
 
Example 1: Input: r=7,unit=2,arr=[2,8,3,5,7,4,1,2] 
Output: 4 

Example 2: Input: r=5,unit=3,arr=[1,2,3,4] 
Output: 0 

Example 3: Input: r=3,unit=5,arr=[10,5,5] 
Output: 2 

Example 4: Input: r=1,unit=10,arr=[10] 
Output: 1 

Example 5: Input: r=4,unit=2,arr=null 
Output: -1

*/

/*

Approach:

Each rat needs unit amount of food, and there are r rats.
So the total food required is:
foodRequired = r * unit

Then we keep adding food from houses from left to right until the requirement is met. The moment the cumulative food becomes ≥ foodRequired, we return the number of houses used.

We must also handle special conditions:
If arr == null → return -1
If total food available < required → return 0

Algorithm
If arr == null → return -1
Calculate:
requiredFood = r * unit
Traverse the array and keep adding food.
Count houses until sum >= requiredFood.
If requirement met → return house count.
If traversal ends and still insufficient → return 0.

*/

// Code Implementation:
import java.util.*;

public class RatColonySurvival {

    public static int minHouses(int r, int unit, int[] arr) {

        if (arr == null) {
            return -1;
        }

        int requiredFood = r * unit;

        int sum = 0;
        int houses = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];
            houses++;

            if (sum >= requiredFood) {
                return houses;
            }
        }

        return 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int unit = sc.nextInt();
        sc.nextLine(); // Consume the newline character after reading integers

        String input = sc.nextLine();
        if (input.equals("null")) {
            System.out.println(-1);
            return;
        }

        input = input.replace("[", "").replace("]", "");

        String[] parts = input.split(",");

        int[] arr = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }

        System.out.println(minHouses(r, unit, arr));
    }
}

// Time Complexity: O(n) where n is the number of houses in the array.
// Space Complexity: O(1) as we are using only a constant amount of extra space.