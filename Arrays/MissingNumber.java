// Problem Link: https://leetcode.com/problems/missing-number?envType=problem-list-v2&envId=auswip1r

/*

Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:
Input: nums = [3,0,1]
Output: 2

Explanation:
n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:
Input: nums = [0,1]
Output: 2

Explanation:
n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8

Explanation:
n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.

 */

 /*

Approach 1: Using HashSet
1. Create a HashSet to store the numbers in the array.
2. Iterate through the array and add each number to the HashSet.
3. Iterate through the numbers from 0 to n and check if each number is present in the HashSet.
4. If a number is not present in the HashSet, return that number as the missing number.
Time Complexity: O(n) - We iterate through the array once to add elements to the HashSet and then iterate through the range [0, n] once to find the missing number.
Space Complexity: O(n) - The HashSet can contain up to n elements in the worst case.

 */

 /*

import java.util.HashSet;
public class MissingNumber {
    public int missingNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1; // This line will never be reached since there is always a missing number.
    }
}
 */

 /*

Approach 2: Using Gauss' Formula
1. Calculate the expected sum of numbers from 0 to n using the formula n*(n+1)/2.
2. Calculate the actual sum of the numbers in the array.
3. The missing number will be the difference between the expected sum and the actual sum.
Time Complexity: O(n) - We iterate through the array once to calculate the actual sum.
Space Complexity: O(1) - We only use a constant amount of extra space.

 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
}

/*

Approach 3: Using XOR.
1. Initialize a variable xor to 0.
2. Iterate through the numbers from 0 to n and XOR each number with xor.
3. Iterate through the numbers in the array and XOR each number with xor.
4. Return the value of xor as the missing number.

How it works:
- XOR of a number with itself is 0 (a ^ a = 0).
- XOR of a number with 0 is the number itself (a ^ 0 = a).
- By XORing all numbers from 0 to n and all numbers in the array, the missing number will be the only number that is not canceled out, resulting in the missing number being the final value of xor.

Time Complexity: O(n) - We iterate through the array once and through the range [0, n] once.
Space Complexity: O(1) - We only use a constant amount of extra space.

 */

 /*

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int xor = 0;
        for (int i = 0; i <= nums.length; i++) {
            xor ^= i;
        }
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}

 */
