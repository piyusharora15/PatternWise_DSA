// Problem Statement:
/*

A financial analytics team receives a real(floating) point number representing a transaction amount. For Audit and transformation purposes, the system performs the following operations sequentially: 
1. Reverse the integer part of the number.
2. Keep the fractional(decimal) part unchanged. 
3. Construct the new number using the reversed integer part and the original fractional part. 
4. Divide the resulting number by 2, 5. Print the final result rounded to exactly two decimal places. 
Your task is to write a program that performs the above transformation. 
Input: 123.45 
Output: 160.73

*/

// Approach: String manipulation and mathematical operations.
// We will split the number into integer and fractional parts, reverse the integer part, combine it with the fractional part, and then perform the division and rounding.

// Code:

import java.util.Scanner;

public class TransformationOnRealNumber {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double num = sc.nextDouble();

        String s = String.valueOf(num);

        String[] parts = s.split("\\.");

        String integerPart = parts[0];
        String fractionalPart = parts[1];

        // reverse integer part
        String reversed = new StringBuilder(integerPart).reverse().toString();

        // construct new number
        double newNumber = Double.parseDouble(reversed + "." + fractionalPart);

        // divide by 2
        double result = newNumber / 2;

        // print exactly 2 decimal places
        System.out.printf("%.2f", result);

        sc.close();
    }
}

// Time Complexity: O(n) where n is the number of digits in the integer part of the number (due to string reversal).
// Space Complexity: O(n) for storing the reversed integer part as a string.