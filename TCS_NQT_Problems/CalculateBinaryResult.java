// Problem Statement: A digital vault in CyberRealm stores commands in an encoded binary string. The string consists of encoded binary digits separated by operation codes: A -> AND, B -> OR, C -> XOR. The vault processes the string strictly from left to right, performing one operation at a time, ignoring conventional operator precedence. If the string is NULL, return -1. Determine the final binary result.

// Example: 1. Input: 1C0C1C1A0B1
//    Output: 1

// Example: 2 Input: 0A1B1C1
//    Output: 0

// Example: 3 Input: 1
//    Output: 1

// Example: 4 Input: NULL
//    Output: -1


// Code:

import java.util.*;

public class CalculateBinaryResult {
    public static int calculateBinaryResult(String input) {
        if (input == null || input.isEmpty()) {
            return -1; // Return -1 for NULL or empty input
        }

        String[] tokens = input.split("(?=[ABC])|(?<=[ABC])"); // We split the input string into tokens based on the operation codes
        // (?=[ABC])|(?<=[ABC]) is a regex that splits the string at positions where there is an 'A', 'B', or 'C' either before or after the split point.
        int result = Integer.parseInt(tokens[0]); // Initialize result with the first binary digit

        for (int i = 1; i < tokens.length; i += 2) {
            char operation = tokens[i].charAt(0); // Get the operation code
            int nextValue = Integer.parseInt(tokens[i + 1]); // Get the next binary digit

            switch (operation) {
                case 'A': // AND
                    result = result & nextValue;
                    break;
                case 'B': // OR
                    result = result | nextValue;
                    break;
                case 'C': // XOR
                    result = result ^ nextValue;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation code: " + operation);
            }
        }

        return result; // Return the final binary result
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int finalResult = calculateBinaryResult(input);
        System.out.println(finalResult);
        sc.close();
    }
}