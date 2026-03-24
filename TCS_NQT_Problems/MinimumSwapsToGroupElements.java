/*

After Natasha sacrificed herself, Hulk seeks help from Goku. 
To bring her back, Goku presents a challenge. 
Hulk is given: a list of  N integers representing cosmic energy values and an integer K. 
The requirement: All energy values less than or equal to K must become adjacent(grouped together).  
Rules: 
Hulk may swap any two elements.
He can perform unlimited swaps.
But Goku demands the minimum number of swaps.
If Hulk fails, Natasha remains lost forever. 

Your task: Calculate the minimum swaps required to group all elements <= K together.  

Input Format: 
First line : T test cases
For each test case: 
First line: N and K 
Second line : N space-separated integers

Output: Minimum swaps for each test case. 

Constraints:
1 <= T <= 5 
1 <= N <= 10^5 
1 <= data[i], K <= 10^9 

Example 1: 
Input: 
arr = [2, 7, 9, 5, 8, 7, 4] 
K = 5 

Output: 2 

Example 2: Input:
arr = [1, 2, 3, 4, 5] 
K = 3

Output: 0.

*/

/*

Approach:
🧠 Core Idea (Understand This Properly):
We want:

👉 All elements ≤ K to be together
👉 Using minimum swaps

🔥 Key Observation:

Instead of thinking about swaps directly:

👉 Think like this:

How many “bad elements” (> K) are inside the group?

Because:

Every bad element inside the group → needs 1 swap

⚙️ Step-by-Step Approach

Step 1: Count good elements.
good = count of elements ≤ K

👉 This defines the window size.

Step 2: Sliding Window.

Check every window of size good.

👉 Count how many elements > K inside window (bad elements).

Step 3: Answer.
Minimum bad elements in any window = minimum swaps

🧪 Dry Run:

Example 1:

arr = [2, 7, 9, 5, 8, 7, 4]
K = 5

Step 1: Count good elements
≤ 5 → [2, 5, 4] → good = 3

Step 2: Check windows of size 3
Window	Bad elements (>5)
[2,7,9]	  2
[7,9,5]	  2
[9,5,8]	  2
[5,8,7]	  2
[8,7,4]	  2

Minimum:
2

Example 2:
[1,2,3,4,5], K=3

good = 3

Window [1,2,3] → 0 bad → answer = 0

*/

import java.util.Scanner;

public class MinimumSwapsToGroupElements {
    public static int minSwaps(int[] arr, int n, int k) {
        // Step 1: Count good elements
        int good = 0;
        for(int i=0;i<n;i++) {
            if(arr[i] <= k) {
                good++;
            }
        }
        // Step 2: Count bad elements in the first window of size good
        int bad = 0;
        for(int i=0;i<good;i++) {
            if(arr[i] > k) {
                bad++;
            }
        }
        int minSwaps = bad;
        // Step 3: Slide the window and update bad count
        int i=0, j=good;
        while(j < n) {
            // remove old element
            if(arr[i] > k) {
                bad--;
            }
            // add new element
            if(arr[j] > k) {
                bad++;
            }
            minSwaps = Math.min(minSwaps, bad);
            i++;
            j++;
        }
        return minSwaps;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine(); // consume newline
        while(t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            sc.nextLine(); // consume newline
            String input = sc.nextLine();
            input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
            String[] parts = input.split("\\s+");
            int[] arr = new int[n];
            for(int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }
            System.out.println(minSwaps(arr, n, k));
        }
        sc.close();
    }
}

// Time Complexity: O(N) for counting good elements + O(N) for sliding window = O(N).
// Space Complexity: O(1) since we are using only a few extra variables.