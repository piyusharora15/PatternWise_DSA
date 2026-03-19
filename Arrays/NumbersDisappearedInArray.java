// Problem Link: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array?envType=problem-list-v2&envId=wh88bf73

/*

Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

Example 2:
Input: nums = [1,1]
Output: [2]

*/

// Approach: Using HashSet to store the numbers in the array and then checking for the missing numbers in the range [1, n]. 
// We can also use the input array itself to mark the presence of numbers by negating the value at the index corresponding to the number.
// We start from the first element and for each number, we mark the index corresponding to that number as negative.
// Finally, we iterate through the array again and collect the indices which are still positive, as those indices represent the missing numbers.

// Code:
import java.util.ArrayList;
import java.util.List;
public class NumbersDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}

// Time Complexity: O(n) where n is the length of the input array.
// Space Complexity: O(1) as we are using the input array itself to mark the presence of numbers and only using a list to store the result.