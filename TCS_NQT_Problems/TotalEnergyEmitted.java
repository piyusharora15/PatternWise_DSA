// Problem Statement: In the Kingdom of Arithmoria, a ritual staircase has n glowing steps. The first step emits energy 1, the second 2, the third 3 and so on. To activate the royal beacon, the total cumulative energy of all n steps must be calculated. However, due to time constraints, the calculation must be performed in constant time. Compute the total energy emitted by the staircase.

// Example 1: Input: n = 1
// Output: 1

// Example 2: Input: n = 5
// Output: 15

// Example 3: Input: 1000000000
// Output: 500000000500000000


// Code:

import java.math.BigInteger;
import java.util.Scanner;

public class TotalEnergyEmitted {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Calculate total energy using the formula n(n + 1) / 2
        BigInteger totalEnergy = BigInteger.valueOf(n)
                                .multiply(BigInteger.valueOf(n + 1))
                                .divide(BigInteger.valueOf(2));
        
        System.out.println(totalEnergy);
        sc.close();
    }
}

// Time Complexity: O(1) - The calculation is done in constant time using the formula for the sum of the first n natural numbers.
// Space Complexity: O(1) - The space used is constant, as we are only storing a few variables regardless of the input size.