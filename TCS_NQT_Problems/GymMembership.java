/*

The gym offers membership plans for specific durations. 
The cost of a one-month membership is Rs 2000, a three-month membership is Rs 5000, a six-month membership is Rs 9000, a nine-month membership is Rs 12000 and a twelve-month membership is Rs 15000. 
If the user enters a duration other than the specified membership plans, the program should display an error message.

*/

// Code:

import java.util.Scanner;
public class GymMembership {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int duration = sc.nextInt();
        switch(duration) {
            case 1:
                System.out.println(2000);
                break;
            case 3:
                System.out.println(5000);
                break;
            case 6:
                System.out.println(9000);
                break;
            case 9:
                System.out.println(12000);
                break;
            case 12:
                System.out.println(15000);
                break;
            default:
                System.out.println("Invalid duration");
        }
        sc.close();
    }
}