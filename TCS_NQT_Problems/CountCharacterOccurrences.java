/*

Count Character Occurrences
You are given two strings, str1 and str2. Your mission is to calculate the total number of occurrences of each unique character of str2 within the string str1. The task is to find the sum of occurrences of all unique characters from str2 in str1 and return this total count.

Input Format:

For each test case:
The first line contains the string str1.
The second line contains the string str2.
Output Format
For each test case, output the total sum of occurrences of characters in str2 found in str1 on a new line.

Constraints
1≤T≤100
1≤∣str1∣,∣str2∣≤10
The strings consists of lowercase letters only.
 
Sample 1:

helloworld do

abacabadabacaba abcd

abc abcdabcdabcdabcd

3
15
3
 

Explanation:
Test Case 1: the character 'd' appears once and 'o' appears twice in "helloworld", so the total count is 3.
Test Case 2: The characters from "abcd" appear as follows in "abacabadabacaba": 'a': 7 occurrences 'b': 4 occurrences 'c': 2 occurrences 'd': 2 occurrences Total = 7 + 4 + 2 + 2 = 15.
Test Case 3: The characters appear only once in abc as we are calculating the unique characters of abcdabcdabcdabcd.

*/

/*

Key Idea
Extract unique characters from str2
Count frequency of characters in str1
Add counts for characters present in str2

*/

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class CountCharacterOccurrences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();

        while(t-- > 0) {
            String str1 = sc.nextLine();
            String str2 = sc.nextLine();
            // Step 1: Extract unique characters from str2
            Set<Character> set = new HashSet<>();
            for(char c : str2.toCharArray()) {
                set.add(c);
            }
            // Step 2: Count frequency of characters in str1
            int[] freq = new int[26];
            for(char c : str1.toCharArray()) {
                freq[c - 'a']++;
            }
            // Step 3: Add counts for characters present in str2
            int total = 0;
            for(char c : set) {
                total += freq[c - 'a'];
            }
            System.out.println(total);
        }
        sc.close();
    }
}