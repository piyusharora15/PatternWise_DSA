/*

In the futuristic city of Quantasia, traders rely on a time-recorded crystal that logs the price of a rare mineral every minute. 

The crystal provides an array prices[], where: 
Each index represents a minute. 
Each value represents the mineral's price at that minute.

A trader is allowed to: 
Buy the mineral once.
Sell it once later.
Selling before buying is strictly forbidden. 

However the city council has imposed a rule: 
If no profit can be made, the trader must report 0. 

Determine the maximum profit the trader can earn from a single buy and single sell transaction.  

Input Format: 
First line: Integer T(number of trading simulations) 
For each test case:  
First line: Integer N(number of minutes recorded) 
Second line: N space-separated integers representing prices. 

Output: For each test case, print the maximum profit. 

Constraints: 

1 <= T <= 10 
2 <= N <= 10^4 
1 <= prices[i] <= 10^9 

Example 1: 
Input: prices = [10, 2, 9, 1, 8] 
Output: 7 

Example 2: 
Input: prices = [9, 7, 5, 3, 1] 
Output: 0

*/

/*

Core Idea:
You must:

Buy first
Sell later
Maximize:
profit = sell price − buy price

🔥 Key Insight (Most Important)
👉 Instead of checking all pairs (O(n²) ❌),
we track:

Minimum price so far
Maximum profit so far

⚙️ Algorithm (O(n))

Initialize:
minPrice = prices[0]
maxProfit = 0
Traverse array:

For each price:

profit = price − minPrice
update maxProfit
update minPrice

🧪 Dry Run
Example 1:
[10, 2, 9, 1, 8]

Price	minPrice	Profit	maxProfit
10	      10	      0	       0
2	       2	      0	       0
9	       2	      7	       7 ✅
1	       1	      0	       7
8	       1	      7	       7

Output:
7

Example 2:
[9,7,5,3,1]

Always decreasing → no profit

Output = 0

*/

import java.util.Scanner;

public class MaximumProfitPossible {
    public static int maxProfit(int[] prices, int n) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i=1;i<n;i++) {
            int profit = prices[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            sc.nextLine(); // Consume newline
            String input = sc.nextLine();
            input = input.replace("[", "").replace("]", "").replace(",", " ").trim();
            String[] parts = input.split("\\s+");
            int[] prices = new int[n];
            for(int i=0;i<n;i++) {
                prices[i] = Integer.parseInt(parts[i]);
            }
            System.out.println(maxProfit(prices, n));
        }
        sc.close();
    }
}

// Time Complexity: O(n) for each test case, where n is the number of minutes recorded.
// Space Complexity: O(1) since we are using only a constant amount of extra space.