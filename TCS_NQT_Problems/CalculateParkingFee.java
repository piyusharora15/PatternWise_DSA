/*

You need to calculate the parking fee based on the number of hours parked. 
Rules: 
First 2 hours -> Rs 100 per hour 
Next 3 hours (3rd to 5th hour) -> Rs 50 per hour 
Beyond 5 hours -> Rs 20 per hour.  

A single input n (number of hours). If input is not a valid integer, print "error". 
Output: Total parking charge or "error". 

Example: Input: 6 ; Output: 370

*/

import java.util.Scanner;

public class CalculateParkingFee {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            String input = sc.next();

            int n = Integer.parseInt(input.trim());

            if(n < 0) {
                System.out.println("error");
                return;
            }

            int total = 0;

            if(n == 0) {
                System.out.println(total);
                return;
            }

            if(n <= 2) {
                total = n * 100;
            }
            else if(n <= 5) {
                total = 2 * 100 + (n - 2) * 50;
            }
            else {
                total = 2 * 100 + 3 * 50 + (n - 5) * 20;
            }

            System.out.println(total);

        } catch(Exception e) {
            System.out.println("error");
        }
    }
}