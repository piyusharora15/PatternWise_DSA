/*

In the lost kingdom of Zohoria, archaeologists discovered a sacred vault containing powerful relics. 
Each relic has a power value recorded in an array arr[]. 
The royal historian declares: 
There are N relics.
Each relic has a strength between 1 and 10^6. 
The relics are not arranged in any particular order.
You must determine the Kth weakest relic. 
However the High Council has forbidden the use of magical sorting spells (inbuilt sort functions). 
Your task: Identify the Kth smallest relic power without using any in-built sorting enchantments. 

Input: 
Array arr[] 
Integer K 

Output: Power value of the Kth smallest relic

Constraints: 
1 <= N <= 10^6 
1 <= arr[i] <= 10^6 
1 <= K <= N 

Expected Time: O(n + max_element) 
Expected Space: O(max_element) 

Example 1: 
Input: 
arr = [7, 10, 4, 3, 20, 15] 
k=3 
Output: 7 

Example 2: 
Input: 
arr = [2, 3, 1, 20, 15] 
k = 4 
Output: 15 

Example 3: 
Input: 
arr = [5, 5, 5, 5] 
k=2 
Output: 5

*/

/*

Approach:
🧠 Core Idea:

Since:
1 ≤ arr[i] ≤ 10^6

We can create a frequency array:

freq[x] = how many times 'x' appears

🔥 Algorithm:

1️⃣ Find max element (optional, or use 10^6 directly)
2️⃣ Build frequency array
3️⃣ Traverse from smallest → largest
4️⃣ Keep subtracting k
5️⃣ When k ≤ 0, that number is the answer

🧪 Dry Run
Example 1
arr = [7, 10, 4, 3, 20, 15]
k = 3

Step 1: Build Frequency Array
Number	Count
3    →  1
4    →  1
7    →  1
10   →  1
15   →  1
20   →  1

Step 2: Traverse
Number	Count	k
3	     1	    k=2
4	     1	    k=1
7	     1	    k=0 ✅

Answer:
7

Example 2
[2,3,1,20,15], k=4

Sorted mentally:

1,2,3,15,20

4th → 15

WHY DOES THIS WORK?
Because:
freq[i] tells how many times i appears

When we subtract:
k -= freq[i]

👉 We are skipping those many elements in sorted order.

⚠️ What About Duplicates?

Example:
arr = [5, 5, 5, 5]
k = 2

Frequency:
freq[5] = 4

Traverse:

k = 2 - 4 = -2 → ≤ 0 → answer = 5

👉 Works perfectly with duplicates.

*/

import java.util.Scanner;
public class KthSmallestElement {
    public static int kthSmallest(int[] arr, int n, int k) {
        int max = 0;
        for(int num : arr) {
            if(num > max) {
                max = num;
            }
        }
        int[] freq = new int[max+1];
        // Build frequency array
        for(int num : arr) {
            freq[num]++;
        }
        // Traverse frequency array to find kth smallest
        for(int i=1;i<=max;i++) {
            k -= freq[i];
            if(k <= 0) {
                return i;
            }
        }
        return -1; // This line should never be reached if input is valid
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input= input.replace("[", "").replace("]", "").replace(",", "").trim();
        String[] parts = input.split("\\s+");
        int n = parts.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        int k = sc.nextInt();
        System.out.println(kthSmallest(arr, n, k));
        sc.close();
    }
}

// Time Complexity: O(n + max_element) where max_element is the maximum value in arr and n is the number of elements in arr.
// Space Complexity: O(max_element) for the frequency array.