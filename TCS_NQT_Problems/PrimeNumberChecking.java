// Problem Statement: In the Temple of Numbers, a sacred integer n is declared "Pure" only if it has exactly two divisors - 1 and itself. The priests must verify whether n is Pure. Return 1 if n is Pure, otherwise return 0. Efficiency is critical - the temple forbids checking beyond the square root of n.

// Examples: 1. Input: n = 5
//    Output: 1

// Example: 2. Input: n = 25
//    Output: 0

// Example: 3. Input: n = 1
//    Output: 0

// Example: 4. Input: n = 999983
//    Output: 1

// Code:

import java.util.*;

public class PrimeNumberChecking {
    public static int isPure(int n) {
        if (n <= 1) {
            return 0; // 1 and numbers less than 1 are not prime
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return 0; // n is divisible by a number other than 1 and itself
            }
        }
        return 1; // n is prime
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = isPure(n);
        System.out.println(result);
        sc.close();
    }
}

// Time Complexity: O(sqrt(n)) - We only check for divisibility up to the square root of n.
// Space Complexity: O(1) - We use a constant amount of space for variables.