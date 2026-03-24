/*

At the Royal Academy of Algorithms, there are N chocolate packets. 
Each packet contains a certain number of chocolates. 
There are M elite students. The headmaster wants to: 
Give exactly one packet to each student.
Ensure the difference between the maximum chocolates and minimum chocolates distributed is as small as possible. 
The fairness index is defined as: 
Maximum packet given - Minimum packet given. 

Your objective: Minimize the fairness index.  

Input Format: 
First line: T test cases 
For each test case: 
First line: N and M
Second line: N space-separated integers. 

Output: Minimum possible fairness index. 

Constraints: 
1 <= T <= 50 
2 <= M <= N <= 10^4 
1 <= chocolates[i] <= 10^9 

Example 1: 
Input: chocolates = [12, 4, 7, 9, 2, 23, 25]
M = 3 
Output: 5 

Example 2: 
Input: chocolates = [1, 3, 4, 7, 9] 
M = 2
Output: 1

*/

/*

Approach:

🧠 Core Idea:
We need to:
Pick M packets
Minimize:
max(selected) - min(selected)

🔥 Key Insight:
👉 The best choice will always be M consecutive elements after sorting.

Because:
Sorting brings closest values together.
Any non-consecutive selection increases difference.

⚙️ Algorithm

1️⃣ Sort the array
2️⃣ Slide a window of size M
3️⃣ Compute:
diff = arr[i + M - 1] - arr[i]
4️⃣ Take minimum

🧪 Dry Run:

Example 1:

arr = [12, 4, 7, 9, 2, 23, 25]
M = 3

Step 1: Sort
[2, 4, 7, 9, 12, 23, 25]

Step 2: Check windows

Window	      Diff
[2,4,7]	      7-2 = 5 ✅
[4,7,9]	      9-4 = 5
[7,9,12]      12-7 = 5
[9,12,23]     23-9 = 14
[12,23,25]    25-12 = 13

Answer:
5

Example 2:
[1,3,4,7,9]
M=2

Sorted:
[1,3,4,7,9]

Windows:

3-1=2
4-3=1 ✅
7-4=3
9-7=2

Answer:
1

*/

import java.util.Arrays;
import java.util.Scanner;

public class ChocolateDistribution {
    public static int minFairness(int[] arr, int n, int m) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for(int i=0;i<=n-m;i++) {
            int diff = arr[i+m-1] - arr[i];
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine(); // consume newline
            String input = sc.nextLine();
            input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
            String[] parts = input.split("\\s+");
            int[] arr = new int[n];
            for(int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }
            System.out.println(minFairness(arr, n, m));
        }
        sc.close();
    }
}

// Time Complexity: O(N log N) due to sorting.
// Space Complexity: O(1) if we sort in place, otherwise O(N) for the sorted array.