/*

You are given three integers: a, b and c.
In one operation, you increase one number by 1 and decrease another number by 1. Both must happen in the same operation. 
Your task: Make all three numbers equal using the minimum number of operations. If it is not possible, print -1.

Example 1: Input: "1,  2,  3"
Output: 1
Explanation: We can increase 1 and decrease 3 to make all numbers equal to 2.

Example 2: Input: "1,  1,  4"
Output: 2
Explanation: We can increase 1 and decrease 4 to make all numbers equal to 2.

*/

/*

Approach: 
Key Observation:

In one operation:
+1 to one number
-1 to another number

👉 So total sum remains constant.

Important Condition:
Let:
sum = a + b + c

If we want all numbers equal:
final value = sum / 3

👉 This must be an integer
❌ If not divisible by 3 → impossible
return -1

How to Count Operations:

Each operation moves 1 unit from one number to another.
So we just need to calculate:
👉 Total amount needed to bring numbers to target

Formula:
target = sum / 3
operations = (|a - target| + |b - target| + |c - target|) / 2

👉 Divide by 2 because each operation fixes two units at once
(one decrease + one increase)

🧪 Example 1
1, 2, 3
sum = 6 → target = 2
|1-2| + |2-2| + |3-2| = 1 + 0 + 1 = 2
operations = 2 / 2 = 1

✅ Output = 1

🧪 Example 2
1, 1, 4
sum = 6 → target = 2
|1-2| + |1-2| + |4-2| = 1 + 1 + 2 = 4
operations = 4 / 2 = 2

✅ Output = 2

*/

// Code Implementation:

import java.util.Scanner;

public class MakeThreeNumbersEqual {

    public static int minOperations(int a, int b, int c) {

        int sum = a + b + c;

        if(sum % 3 != 0)
            return -1;

        int target = sum / 3;

        return (Math.abs(a - target) 
              + Math.abs(b - target) 
              + Math.abs(c - target)) / 2;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        // Remove quotes
        input = input.replace("\"", "");

        // Split by comma (safe even with double spaces)
        String[] parts = input.split(",");

        int a = Integer.parseInt(parts[0].trim());
        int b = Integer.parseInt(parts[1].trim());
        int c = Integer.parseInt(parts[2].trim());

        System.out.println(minOperations(a, b, c));
        sc.close();
    }
}

// Time Complexity: O(1) - constant time operations
// Space Complexity: O(1) - constant space usage