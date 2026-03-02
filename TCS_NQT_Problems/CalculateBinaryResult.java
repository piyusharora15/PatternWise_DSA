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

    public static int calculateBinaryResult(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        int result = str.charAt(0) - '0'; // Initialize result with the first binary digit
        for (int i = 1; i < str.length(); i += 2) {
            char op = str.charAt(i);
            if (op == 'A') {
                result = result & (str.charAt(i + 1) - '0');
            } else if (op == 'B') {
                result = result | (str.charAt(i + 1) - '0');
            } else if (op == 'C') {
                result = result ^ (str.charAt(i + 1) - '0');
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int finalResult = calculateBinaryResult(input);
        System.out.println(finalResult);
        sc.close();
    }
}
