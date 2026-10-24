/*

Problem Link: https://leetcode.com/problems/count-days-spent-together?envType=daily-question&envId=2026-10-24

Alice and Bob are traveling to Rome for separate business meetings.

You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. 
Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). 
Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.

Return the total number of days that Alice and Bob are in Rome together.

You can assume that all dates occur in the same calendar year, which is not a leap year. 
Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].

Example 1:
Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
Output: 3
Explanation: Alice will be in Rome from August 15 to August 18. 
Bob will be in Rome from August 16 to August 19. 
They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.

Example 2:
Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
Output: 0
Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.

Approach: Using Math.

1. First, we need to convert the dates from "MM-DD" format to a day of the year (1-365).
2. We can create a helper function to convert the date string to the day of the year.
3. Then, we can find the maximum of the arrival dates and the minimum of the leave dates to determine the overlapping period.
4. Finally, we can calculate the number of overlapping days and return the result.

Dry Run:
Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
1. Convert "08-15" to day of the year: 227
2. Convert "08-18" to day of the year: 230
3. Convert "08-16" to day of the year: 228
4. Convert "08-19" to day of the year: 231
5. Find the maximum of the arrival dates: max(227, 228) = 228
6. Find the minimum of the leave dates: min(230, 231) = 230
7. Calculate the number of overlapping days: 230 - 228 + 1 = 3

Time Complexity: O(1) - The conversion of dates and calculation of overlapping days are done in constant time.

Space Complexity: O(1) - We are using a fixed amount of space for the calculations and not using any additional data structures that grow with input size.

*/

// Code:

class CountDaysSpentTogether {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert dates to day of the year
        int arriveAliceDay = convertToDayOfYear(arriveAlice);
        int leaveAliceDay = convertToDayOfYear(leaveAlice);
        int arriveBobDay = convertToDayOfYear(arriveBob);
        int leaveBobDay = convertToDayOfYear(leaveBob);

        // Find the overlapping period
        int startOverlap = Math.max(arriveAliceDay, arriveBobDay);
        int endOverlap = Math.min(leaveAliceDay, leaveBobDay);

        // Calculate the number of overlapping days
        if (startOverlap > endOverlap) {
            return 0; // No overlap
        } else {
            return endOverlap - startOverlap + 1; // Inclusive count
        }
    }

    private int convertToDayOfYear(String date) {
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        // Days in each month for a non-leap year
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Calculate the day of the year
        int dayOfYear = day;
        for (int i = 1; i < month; i++) {
            dayOfYear += daysInMonth[i];
        }
        return dayOfYear;
    }
}