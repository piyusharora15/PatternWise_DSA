// Problem Link : https://leetcode.com/problems/trapping-rain-water

/*

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

*/

/* 

Brute Force Approach: 
For each index, we have to find the amount of water that can be stored and we have to sum it up. 
If we observe carefully, the amount of water stored at a particular index is the minimum of the maximum elevation to the left and right of the index minus the elevation  at that index. 
So, we can iterate through each index and for each index, we can find the maximum elevation to the left and right of the index and then find the amount of water that can be stored at that index. 
The time complexity of this approach is O(n^2) and the space complexity is O(1).

*/

/* Code :

public int trap(int[] height) {
    int n = height.length;
    int waterTrapped = 0;
    for(int i=0;i<n;i++){
        int j = i;
        int leftMax = 0, rightMax = 0;
        while(j>=0){
            leftMax = Math.max(leftMax,height[j]);
            j--;
        }
        j = i;
        while(j < n){
            rightMax = Math.max(rightMax,height[j]);
            j++;
        }
        waterTrapped += Math.min(leftMax,rightMax) - height[i];
    }
    return waterTrapped;
}

*/

/* Better Approach : 

We are taking O(n) time for computing leftMax and rightMax at each index. 
The complexity can be boiled down to O(1) if we precompute the leftMax and rightMax at each index. 
Take 2 arrays : prefix and suffix and precompute the leftMax and rightMax for each index beforehand. 
Then use the formula : min(prefix[i], suffix[i]) - height[i] to compute the water trapped at each index. 
The time complexity of this approach is O(n) and the space complexity is O(n).

Code:

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = height[0];
        for(int i=1;i<n;i++){
            prefix[i] = Math.max(prefix[i-1],height[i]);
        }
        suffix[n-1] = height[n-1];
        for(int i=n-2;i>=0;i--){
            suffix[i] = Math.max(suffix[i+1],height[i]);
        }
        int waterTrapped = 0;
        for(int i=0;i<n;i++){
            waterTrapped += Math.min(prefix[i],suffix[i]) - height[i];
        }
        return waterTrapped;
    }
}

*/

/* Optimal Aprroach : Two Pointer Approach.

// Intuition: We need a minimum of leftMax and rightMax.So if we take the case when height[l]<=height[r] we increase l++, so we can surely say that there is a block with a height more than height[l] to the right of l. And for the same reason when height[r]<=height[l] we can surely say that there is a block to the left of r which is at least of height[r]. So by traversing these cases and using two pointers approach the time complexity can be decreased without using extra space.

// Approach:
Take 2 pointers l(left pointer) and r(right pointer) pointing to 0th and (n-1)th index respectively. 
Take two variables leftMax and rightMax and initialize them to 0. 
If height[l] is less than or equal to height[r] then if leftMax is less than height[l] update leftMax to height[l] else add leftMax-height[l] to your final answer and move the l pointer to the right i.e l++.
If height[r] is less than height[l], then now we are dealing with the right block. If height[r] is greater than rightMax, then update rightMax to height[r] else add rightMax-height[r] to the final answer. Now move r to the left. 
Repeat these steps till l and r crosses each other.

*/

// Code :
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n-1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }
}

// Time Complexity: O(n) where n is the number of elements in the height array.
// Space Complexity: O(1) as we are using only constant space.