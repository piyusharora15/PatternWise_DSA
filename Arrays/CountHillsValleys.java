/*

Problem Link: https://leetcode.com/problems/count-hills-and-valleys-in-an-array?envType=problem-list-v2&envId=wh88bf73

You are given a 0-indexed integer array nums. 
An index i is part of a hill in nums if the closest non-equal neighbors of i are smaller than nums[i].
Similarly, an index i is part of a valley in nums if the closest non-equal neighbors of i are larger than nums[i]. 
Adjacent indices i and j are part of the same hill or valley if nums[i] == nums[j].

Note that for an index to be part of a hill or valley, it must have a non-equal neighbor on both the left and right of the index.

Return the number of hills and valleys in nums.

Example 1:
Input: nums = [2,4,1,1,6,5]
Output: 3
Explanation:
At index 0: There is no non-equal neighbor of 2 on the left, so index 0 is neither a hill nor a valley.
At index 1: The closest non-equal neighbors of 4 are 2 and 1. Since 4 > 2 and 4 > 1, index 1 is a hill. 
At index 2: The closest non-equal neighbors of 1 are 4 and 6. Since 1 < 4 and 1 < 6, index 2 is a valley.
At index 3: The closest non-equal neighbors of 1 are 4 and 6. Since 1 < 4 and 1 < 6, index 3 is a valley, but note that it is part of the same valley as index 2.
At index 4: The closest non-equal neighbors of 6 are 1 and 5. Since 6 > 1 and 6 > 5, index 4 is a hill.
At index 5: There is no non-equal neighbor of 5 on the right, so index 5 is neither a hill nor a valley. 
There are 3 hills and valleys so we return 3.


Example 2:
Input: nums = [6,6,5,5,4,1]
Output: 0
Explanation:
At index 0: There is no non-equal neighbor of 6 on the left, so index 0 is neither a hill nor a valley.
At index 1: There is no non-equal neighbor of 6 on the left, so index 1 is neither a hill nor a valley.
At index 2: The closest non-equal neighbors of 5 are 6 and 4. Since 5 < 6 and 5 > 4, index 2 is neither a hill nor a valley.
At index 3: The closest non-equal neighbors of 5 are 6 and 4. Since 5 < 6 and 5 > 4, index 3 is neither a hill nor a valley.
At index 4: The closest non-equal neighbors of 4 are 5 and 1. Since 4 < 5 and 4 > 1, index 4 is neither a hill nor a valley.
At index 5: There is no non-equal neighbor of 1 on the right, so index 5 is neither a hill nor a valley.
There are 0 hills and valleys so we return 0.


Approach: Using Two Pointers.

1. First, we initialize a variable count to 0, which will keep track of the number of hills and valleys found in the array. 
We also initialize two pointers, i and j, where i points to the left non-equal neighbor and j points to the current index being evaluated.

2. We iterate through the array using the pointer j, starting from index 1 and going up to n-2 (where n is the length of the array).

3. For each index j, we check if it is a hill or a valley by comparing the values of nums[i], nums[j], and nums[j+1]. 
   - If nums[i] < nums[j] and nums[j] > nums[j+1], then index j is a hill.
   - If nums[i] > nums[j] and nums[j] < nums[j+1], then index j is a valley.

4. If index j is found to be a hill or a valley, we increment the count by 1 and update the pointer i to point to the current index j.

5. Finally, we return the count of hills and valleys found in the array.

Dry Run:
Input: nums = [2,4,1,1,6,5]
1. Initialize count = 0, n = 6, i = 0, j = 1
2. Iterate through the array using pointer j:
    - For j = 1: nums[i] = 2, nums[j] = 4, nums[j+1] = 1
      - Since nums[i] < nums[j] and nums[j] > nums[j+1], index 1 is a hill. Increment count to 1 and update i to 1.
    - For j = 2: nums[i] = 4, nums[j] = 1, nums[j+1] = 1
      - Since nums[i] > nums[j] and nums[j] < nums[j+1], index 2 is a valley. Increment count to 2 and update i to 2.
    - For j = 3: nums[i] = 1, nums[j] = 1, nums[j+1] = 6
      - Since nums[i] == nums[j], we skip this index as it is part of the same valley as index 2.
    - For j = 4: nums[i] = 1, nums[j] = 6, nums[j+1] = 5
      - Since nums[i] < nums[j] and nums[j] > nums[j+1], index 4 is a hill. Increment count to 3 and update i to 4.
    - For j = 5: nums[i] = 6, nums[j] = 5, nums[j+1] does not exist
      - Since there is no non-equal neighbor on the right, we skip this index.

3. Return the final count of hills and valleys, which is 3.


Time Complexity: O(n), where n is the length of the input array nums. 
We iterate through the array once, performing constant-time operations for each element.

Space Complexity: O(1), as we are using a constant amount of extra space for variables count, i, and j, regardless of the input size.

*/

// Code:

class CountHillsValleys {
    public int countHillValley(int[] nums) {
        int count = 0;
        int n = nums.length;
        int i = 0; // points to non-equal neighbour on the left-hand side
        int j = 1; // points to non-equal neighbour on the right-hand side(j+1)
        while(j + 1 < n){
            if((nums[i] < nums[j] && nums[j] > nums[j+1]) || (nums[i] > nums[j] && nums[j] < nums[j+1])){
                count++;
                i = j;
            }
            j++;
        }
        return count;
    }
}
