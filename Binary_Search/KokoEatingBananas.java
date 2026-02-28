// Problem Link: https://leetcode.com/problems/koko-eating-bananas?envType=problem-list-v2&envId=wh88bf73

// Approach: Binary Search.
// We can use binary search to find the minimum eating speed K. We will start with the minimum possible speed (1) and the maximum possible speed (the maximum number of bananas in any pile). 
// We will calculate the total hours needed to eat all the bananas at each speed and adjust our search range accordingly until we find the minimum speed that allows Koko to eat all the bananas within H hours.
// We will use the formula (pile + K - 1) / K to calculate the hours needed to eat each pile, which is equivalent to Math.ceil(pile / K) but avoids floating-point division.
// We will continue to narrow down our search range until we find the minimum speed that allows Koko to eat all the bananas within H hours.

// Code:
class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int right = 0;
        
        // Find the maximum number of bananas in any pile to set the upper bound for binary search
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int hoursNeeded = 0;
            
            // Calculate the total hours needed to eat all bananas at speed mid
            for (int pile : piles) {
                hoursNeeded += (pile + mid - 1) / mid; // Equivalent to Math.ceil(pile / mid)
            }
            
            // If Koko can eat all bananas within H hours, try a smaller speed
            if (hoursNeeded <= H) {
                right = mid;
            } else { // Otherwise, try a larger speed
                left = mid + 1;
            }
        }
        
        return left; // The minimum speed that allows Koko to eat all bananas within H hours
    }
}

// Time Complexity: O(N log M), where N is the number of piles and M is the maximum number of bananas in any pile. The binary search runs in O(log M) and we calculate the hours needed for each speed in O(N).
// Space Complexity: O(1), as we are using only a constant amount of extra space.