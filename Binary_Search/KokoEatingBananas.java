// Problem Link: https://leetcode.com/problems/koko-eating-bananas?envType=problem-list-v2&envId=wh88bf73

/*

Companies: Amazon, Microsoft, Walmart, Uber.

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

*/

/*

The main observation is:

If Koko can finish all bananas with speed k,
then she can also finish with any speed > k.

That means the answer space is monotonic, which makes it perfect for Binary Search.

You are given:

piles[i] → bananas in each pile
h → total hours available

Koko chooses a speed k bananas/hour.

In one hour:

She can eat from only ONE pile.
If pile has fewer than k, she eats all and stops for that hour.

We need to find:

Minimum k such that all bananas are eaten within h hours.

Important Formula:

To calculate hours needed for one pile:

hours = (pile+k−1)/k
	​
Approach 1 — Brute Force (Linear Search):

Try every possible eating speed from:  1 to max(piles)

For every speed:

Calculate total hours needed.
First valid speed is the answer.

Algorithm:
Find maximum pile.
For speed from 1 to maxPile
Compute total hours.
If total hours ≤ h
return speed.

Dry Run:
piles = [3,6,7,11], h = 8

Maximum pile = 11

Try k=1: hours = 3/1 + 6/1 + 7/1 + 11/1 = 27 (too slow)

Try k=2:
ceil(3/2) + ceil(6/2) + ceil(7/2) + ceil(11/2) = 2 + 3 + 4 + 6 = 15 (too slow)

Try k=3:
ceil(3/3) + ceil(6/3) + ceil(7/3) + ceil(11/3) = 1 + 2 + 3 + 4 = 10 (too slow)

Try k=4:
ceil(3/4) + ceil(6/4) + ceil(7/4) + ceil(11/4) = 1 + 2 + 2 + 3 = 8 (just right)

Return k=4.

Code:

class Solution {

    private long calculateHours(int[] piles, int speed) {
        long totalHours = 0;

        for (int pile : piles) {
            totalHours += (pile + speed - 1) / speed;
        }

        return totalHours;
    }

    public int minEatingSpeed(int[] piles, int h) {

        int maxPile = 0;

        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        -> Try every possible speed
        for (int speed = 1; speed <= maxPile; speed++) {

            long hours = calculateHours(piles, speed);

            if (hours <= h) {
                return speed;
            }
        }

        return maxPile;
    }
}

Time Complexity: O(N * M), where N is the number of piles and M is the maximum number of bananas in any pile. We check each speed from 1 to maxPile and for each speed, we calculate the hours needed by iterating through all piles.
Space Complexity: O(1), as we are using only a constant amount of extra space.


Optimal Approach: Binary Search on Answer.

We binary search on eating speed.

Search Space:
low = 1
high = max(piles)

For every mid speed:

Calculate required hours.
If hours ≤ h
mid is valid
try smaller speed
Else
speed too slow
increase speed

Why Binary Search Works

As speed increases:
Required hours decrease.
This monotonic behavior allows binary search.

Dry Run:

piles = [3,6,7,11]
h = 8

Initial:
low = 1
high = 11

Iteration 1:

mid=(1+11)/2=6

Check speed = 6

Hours:
ceil(3/6)=1
ceil(6/6)=1
ceil(7/6)=2
ceil(11/6)=2

Total hours = 1+1+2+2=6 (valid, try smaller speed)
high = 5
answer = 6

Iteration 2:
low = 1
high = 5
mid = 3

Hours:
1 + 2 + 3 + 4 = 10

Too slow.
low = 4

Iteration 3:
low = 4
high = 5
mid = 4

Hours:
1 + 2 + 2 + 3 = 8

Valid.
answer = 4
high = 3

Loop stops.

Answer = 4


*/

// Code:
class KokoEatingBananas {

    // Helper function to calculate total hours
    private long calculateHours(int[] piles, int speed) {

        long totalHours = 0;

        for (int pile : piles) {

            // Ceiling division
            totalHours += (pile + speed - 1) / speed;
        }

        return totalHours;
    }

    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Find maximum pile
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int answer = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            long requiredHours = calculateHours(piles, mid);

            // Valid speed
            if (requiredHours <= h) {

                answer = mid;

                // Try smaller speed
                high = mid - 1;
            }
            else {

                // Need faster speed
                low = mid + 1;
            }
        }

        return answer;
    }
}

// Time Complexity: O(N log M), where N is the number of piles and M is the maximum number of bananas in any pile. The binary search runs in O(log M) and we calculate the hours needed for each speed in O(N).

// Space Complexity: O(1), as we are using only a constant amount of extra space.