/*

You are given a number N, and your task is to determine whether it is a "Good Number" or not. A Good Number is defined as a number that is divisible by the sum of its own digits. If the number is divisible by the sum of its digits, it is classified as Good, otherwise, it is classified as Bad.

Input Format
Each test case contains a single integer N, the number you need to check.
Output Format
For each test case, print "Good Number" if the number is a Good, otherwise print "Bad Number".

Constraints
1≤N≤106
Sample 1:
Input

Output

3
18
19
21
Good Number
Bad Number
Good Number

*/


import java.util.Scanner;
public class GoodNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t -- > 0) {
            int n = sc.nextInt();
            int sum = 0;
            int temp = n;
            while(temp > 0) {
                sum += temp % 10;
                temp = temp / 10;
            }
            if(n % sum == 0) {
                System.out.println("Good Number");
            }
            else {
                System.out.println("Bad Number");
            }
        }
    }
}