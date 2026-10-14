/*

Problem Link: https://leetcode.com/problems/subarray-sums-divisible-by-k

Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
Input: nums = [5], k = 9
Output: 0

Approach: Using Prefix Sum and HashMap.

1. First, we will calculate the prefix sum of the array. The prefix sum at index i is the sum of all elements from index 0 to i.

2. We will then calculate the remainder of each prefix sum when divided by k. 
If two prefix sums have the same remainder, it means that the subarray between those two indices has a sum that is divisible by k.

3. We will use a HashMap to keep track of the frequency of each remainder.

4. For each prefix sum, we will check if the remainder has been seen before in the HashMap. 
If it has, we will add the frequency of that remainder to our count of valid subarrays.

5. Finally, we will return the count of valid subarrays.


Dry Run:
Input: nums = [4,5,0,-2,-3,1], k = 5

map = {0: 1} // Initialize the map with remainder 0 having frequency 1
prefixSum = 0

index 0: prefixSum = 4, remainder = 4
map = {0: 1, 4: 1} // Add remainder 4

index 1: prefixSum = 9, remainder = 4
map = {0: 1, 4: 2} // Increment frequency of remainder

index 2: prefixSum = 9, remainder = 4
map = {0: 1, 4: 3} // Increment frequency of remainder

index 3: prefixSum = 7, remainder = 2
map = {0: 1, 4: 3, 2: 1} // Add remainder 2

index 4: prefixSum = 4, remainder = 4
map = {0: 1, 4: 4, 2: 1} // Increment frequency of remainder

index 5: prefixSum = 5, remainder = 0
map = {0: 2, 4: 4, 2: 1} // Increment frequency of remainder 0

Return the count of valid subarrays.
count = 7

Time Complexity: O(n) where n is the length of the input array nums. 
We iterate through the array once to calculate the prefix sums and their remainders.

Space Complexity: O(k) where k is the number of unique remainders when dividing by k.

*/

// Code:

import java.util.HashMap;
import java.util.Map;

class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        // HashMap to store the frequency of remainders
        Map<Integer, Integer> remainderCount = new HashMap<>();
        // Initialize with remainder 0 having frequency 1
        remainderCount.put(0, 1);
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            // Calculate the remainder of the current prefix sum
            int remainder = ((prefixSum % k) + k) % k; // Handle negative remainders
            
            // If this remainder has been seen before, add its frequency to count
            if (remainderCount.containsKey(remainder)) {
                count += remainderCount.get(remainder);
            }
            
            // Update the frequency of this remainder in the map
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
}