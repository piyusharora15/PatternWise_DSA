/*

A financial AI is analyzing N days of stock prices. 
For each day i, it must calculate the "dominance span". 
The span of day i is the maximum number of consecutive days before day i (including i) such that the price on those days is less than or equal to price[i]. 
Given:
N, price[] 
Output: Span for each day. 

Constraints: 
1 <= N <= 10^5
The AI must efficiently compute spans without brute force simulation. 

Example 1: 
Input: 
[100, 80, 60, 70, 60, 75, 85] 
Output: 
1 1 1 2 1 4 6 

Example 2: 
Input: 
[10, 4, 5, 90, 120, 80] 
Output: 
1 1 2 4 5 1 

Example 3: 
Input: 
[30, 30, 30, 30] 
Output: 
1 2 3 4

*/

/*

Approach:

🧠 Core Idea:

For each day i, we want:

How many consecutive previous days (including today)
have price ≤ price[i]

🔥 Optimal Approach (Stack → O(n))

👉 Use a monotonic decreasing stack.

⚙️ Key Insight:

We store indices in stack such that:

Prices are in decreasing order in stack.

🧩 Algorithm:

For each index i:

While stack not empty AND:
price[stack.top()] ≤ price[i]

👉 Pop

If stack empty:
span = i + 1

Else:
span = i - stack.top()

Push current index

🧪 Dry Run:

Input
[100, 80, 60, 70, 60, 75, 85]

Day	Price	  Stack	     Span
0	100	      [0]	      1
1	80	      [0,1]	      1
2	60	      [0,1,2]	  1
3	70	      pop 2	     [0,1]
4	60	      [0,1,3,4]	  1
5	75	      pop 4,3,2	 [0,1]
6	85	      pop 5,1	 [0]

*/

import java.util.Scanner;
import java.util.Stack;

public class StockSpan {
    public static int[] calculateSpan(int[] price, int n) {
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++) {
            // Pop smaller or equal prices
            while(!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }
            // Calculate span
            if(stack.isEmpty()) {
                span[i] = i+1; // All previous days + today
            } else {
                span[i] = i - stack.peek(); // Distance to last higher price
            }
            stack.push(i); // Push current index
        }
        return span;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
        String[] parts = input.split("\\s+");
        int n = parts.length;
        int[] price = new int[n];
        for(int i=0;i<n;i++) {
            price[i] = Integer.parseInt(parts[i]);
        }
        int[] result = calculateSpan(price, n);
        // Print result
        for(int i=0;i<n;i++) {
            System.out.print(result[i] + " ") ;
        }
        sc.close();
    }
}

// Time Complexity: O(n) - Each element is pushed and popped at most once.
// Space Complexity: O(n) - In worst case (strictly decreasing prices), stack can hold all indices.