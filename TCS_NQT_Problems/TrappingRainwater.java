/*

In the Kingdom of Hydron, engineers built fortress walls of varying heights. 
The wall heights are stored in a array arr[], where: 
Each element represents the height of a vertical wall.
Each wall has width 1. 
During heavy rainfall, water gets trapped between the walls. 
Compute the total units of rainwater trapped after the storm. 

Example: Input: [3, 0, 0, 2, 0, 4] 
Output: 10 

Input Format: 
First line : N 
Second line: N space-separated integers. 

Output: Total units of trapped water. 

Constraints: 
1<= N <= 10^5 
0 <= arr[i] <= 10^9

*/

/*

Approach:

🧠 Core Idea:

Water at any index depends on:
water[i] = min(max_left, max_right) − height[i]

👉 Because water is limited by the shorter boundary.

🔥 Optimal Approach (Two Pointers → O(n), O(1))

We avoid extra arrays by using:
left pointer
right pointer
leftMax, rightMax

⚙️ Algorithm:

Initialize:
left = 0, right = n-1
leftMax = 0, rightMax = 0
water = 0
While left < right:
If arr[left] < arr[right]:
If arr[left] >= leftMax → update leftMax
Else → water += leftMax − arr[left]
Move left++
Else:
If arr[right] >= rightMax → update rightMax
Else → water += rightMax − arr[right]
Move right--

🧪 Dry Run:

Input:
[3, 0, 0, 2, 0, 4]

Water trapped:

index 1 → 3 units
index 2 → 3 units
index 3 → 1 unit
index 4 → 3 units

Total:
10

*/

import java.util.Scanner;

public class TrappingRainwater {
    public static int trapWater(int[] arr, int n) {
        int left = 0, right = n-1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        while(left < right) {
            if(arr[left] < arr[right]) {
                if(arr[left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    water += leftMax - arr[left];
                }
                left++;
            }
            else {
                if(arr[right] >= rightMax) {
                    rightMax = arr[right];
                } else {
                    water += rightMax - arr[right];
                }
                right--;
            }
        }
        return water;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline
        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
        String[] parts = input.split("\\s+");
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(trapWater(arr, n));
        sc.close();
    }
}

// Time Complexity: O(n) - We traverse the array once.
// Space Complexity: O(1) - We use only a constant amount of extra space.