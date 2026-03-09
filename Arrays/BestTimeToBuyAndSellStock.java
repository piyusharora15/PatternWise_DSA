// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock?envType=problem-list-v2&envId=dynamic-programming

/*

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

*/


// Brute Force Approach: Checking all possible pairs of buy and sell days.
// We take each day as a buying day and check all subsequent days as selling days to find the maximum profit.

// Code:
    /*
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                int profit = prices[j] - prices[i];
                max = Math.max(max,profit);
            }
        }
        return max;
    }
    */

// Time Complexity: O(n^2)
// Space Complexity: O(1)

// Optimal Approach: Single Pass.
// We keep track of the minimum price encountered so far and calculate the potential profit at each step.
// We update the maximum profit whenever we find a higher potential profit.
// We iterate through the array once, updating the minimum price and maximum profit as we go.
// This approach ensures that we are always buying at the lowest price and selling at the highest price encountered after that.

// Code:
class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int max = 0;
        for(int price : prices){
            if(price < minPrice){
                minPrice = price;
            }
            int profit = price - minPrice;
            if(profit > max){
                max = profit;
            }
        }
        return max;
    }
}

// Time Complexity: O(n), where n is the number of days (length of the prices array).
// Space Complexity: O(1), as we are using only a constant amount of extra space to store the minimum price and maximum profit.