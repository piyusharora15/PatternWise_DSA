// Problem Link: https://leetcode.com/problems/sort-array-by-increasing-frequency?envType=problem-list-v2&envId=wh88bf73

/* 

Problem Statement:
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.

Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.

Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
*/

// Approach: We can use a HashMap to count the frequency of each integer in the array. 
// Then, we can sort the array based on the frequency and value using a custom comparator.
// We will sort the integers first by their frequency in increasing order, and if two integers have the same frequency, we will sort them in decreasing order.
// We can achieve this by using the Collections.sort method with a custom comparator that compares the frequency of the integers and their values.

// Code:

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
    public int[] frequencySort(int[] nums) {
        int[] ans = new int[nums.length];
        HashMap<Integer,Integer> freq = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            freq.put(nums[i],freq.getOrDefault(nums[i],0) + 1);
        }
        List<Integer> list = new ArrayList<>(freq.keySet());
        Collections.sort(list, (a,b) -> {
            if(freq.get(a).equals(freq.get(b))) {
                return b - a;
            }
            return freq.get(a) - freq.get(b);
        });
        int k = 0;
        for(Integer num : list){
            int count = freq.get(num);
            while(count > 0){
                ans[k++] = num;
                count--;
            }
        }
        return ans;
    }
}

// Time Complexity: O(n log n) due to the sorting step, where n is the length of the input array.
// Space Complexity: O(n) for the HashMap to store the frequency of each integer.