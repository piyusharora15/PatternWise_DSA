/*

You are given two fractions: a/b and c/d.
Your task is to add both fractions and print the result in its simplest form.

Input: "1/2 3/4"
Output: 5/4

*/

/*

Approach:

Given:
a/b and c/d

1️⃣ Add fractions
Formula:

(a*d + b*c) / (b*d)

2️⃣ Simplify result

Divide numerator & denominator by GCD

🧪 Example

Input:

1/2 3/4

Add:
(1*4 + 2*3) / (2*4)
= (4 + 6) / 8
= 10/8

Simplify:
GCD(10,8) = 2
10/8 → 5/4

✅ Output:

5/4

*/

// Code Implementation:
import java.util.Scanner;
public class AddTwoFractions {
    public static int gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("\"", "");
        String[] parts = input.split(" ");  // Split input into two fractions
        // First fraction
        String[] f1 = parts[0].split("/");  // Split first fraction into numerator and denominator
        int a = Integer.parseInt(f1[0]);
        int b = Integer.parseInt(f1[1]);
        // Second fraction
        String[] f2 = parts[1].split("/");  // Split second fraction into numerator and denominator
        int c = Integer.parseInt(f2[0]);
        int d = Integer.parseInt(f2[1]);

        // Add fractions
        int numerator = a * d + b * c;
        int denominator = b * d;

        // Simplify result
        int g = gcd(numerator, denominator);

        numerator /= g;
        denominator /= g;

        System.out.println(numerator + "/" + denominator);
        sc.close();
    }
}

// Time Complexity: O(log(min(numerator, denominator))) due to GCD calculation.
// Space Complexity: O(1) as we are using constant extra space.