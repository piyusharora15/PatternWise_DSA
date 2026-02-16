// Problem Link: https://leetcode.com/problems/reverse-bits?envType=daily-question&envId=2026-02-16

// Approach: We can reverse the bits by iterating through each bit of the input number and constructing the reversed number by shifting the bits to the left and adding the current bit.We can use bitwise operations to achieve this efficiently.

// Code:
public class ReverseBits {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1; // Shift the result to the left to make room for the next bit
            result |= (n & 1); // Add the least significant bit of n to the result
            n >>= 1; // Shift n to the right to process the next bit
        }
        return result;
    }
}

// Time Complexity: O(1) - The loop runs a fixed number of times (32 iterations for a 32-bit integer).
// Space Complexity: O(1) - We are using a constant amount of space to store the result and temporary variables.