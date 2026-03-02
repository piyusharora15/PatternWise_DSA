// Problem Link: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days?envType=problem-list-v2&envId=wh88bf73

// Approach: Binary Search.
// We can use binary search to find the minimum capacity of the ship that can ship all the packages within D days. The minimum capacity of the ship can be the maximum weight of the packages, and the maximum capacity can be the sum of all the weights of the packages. We can calculate the mid value of the capacity and check if it is possible to ship all the packages within D days with that capacity. If it is possible, we can try to find a smaller capacity, otherwise, we need to increase the capacity.We can repeat this process until we find the minimum capacity that can ship all the packages within D days.

// Code:

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canShip(int[] weights, int D, int capacity) {
        int days = 1;
        int currentLoad = 0;
        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                days++;
                currentLoad = 0;
            }
            currentLoad += weight;
        }
        return days <= D;
    }
}

// Time Complexity: O(n log m), where n is the number of packages and m is the sum of all weights.
// Space Complexity: O(1).