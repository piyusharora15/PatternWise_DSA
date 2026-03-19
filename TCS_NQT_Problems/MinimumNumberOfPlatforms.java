/*

A major railway junction manages hundreds of trains daily. 
Due to infrastructure constraints, the station authority wants to determine the minimum number of platforms required so that no train has to wait. 
You are given two arrays: 
arrival[] -> arrival time of trains
departure[] -> departure times of trains.                  

Each index represents the same train(i.e. arrival[i] corresponds to departure[i]). 
A platform cannot be shared if: 
A train arrives before or at the same time another train departs. 
Your task is to compute the minimum number of platforms required so that no train waits.                                       
Constraints: 1<=N<=10^5   

Input:

6
"900 940 950 1100 1500 1800 910 1200 1120 1130 1900 2000"

Output: 3

*/


/*

Approach:
Core Idea:
We need to find maximum trains present at the station at the same time.
That maximum = minimum platforms required.

Key Trick

Instead of checking each train manually:
Sort arrival[] and departure[] separately
Then use two pointers.

Algorithm:

Sort both arrays

Use two pointers:
i → arrival
j → departure

If: arrival[i] <= departure[j]

→ new train comes → need new platform

Else:
→ train leaves → free platform

*/

// Code Implementation:
import java.util.Arrays;
import java.util.Scanner;
public class MinimumNumberOfPlatforms {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        String input = sc.nextLine();
        // remove quotes
        input = input.replace("\"", "");
        String[] parts = input.split(" ");
        int[] arrival = new int[n];
        int[] departure = new int[n];
        // first n → arrival
        for(int i = 0; i < n; i++){
            arrival[i] = Integer.parseInt(parts[i]);
        }
        // next n → departure
        for(int i = 0; i < n; i++){
            departure[i] = Integer.parseInt(parts[i + n]);
        }
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int i = 0, j = 0;
        int platforms = 0, maxPlatforms = 0;
        while(i < n && j < n){
            if(arrival[i] <= departure[j]){
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }
        System.out.println(maxPlatforms);
        sc.close();
    }
}

// Time Complexity: O(N log N) due to sorting.
// Space Complexity: O(N) for storing arrival and departure times.