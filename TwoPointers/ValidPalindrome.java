/*

Problem Link: https://leetcode.com/problems/valid-palindrome

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. 
Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Approach: Two Pointers.

1. First, we check if the input string s is null. If it is, we return true since a null string can be considered a palindrome.

2. We initialize two pointers, i and j, to the start and end of the string, respectively.

3. We enter a while loop that continues as long as i is less than j.

4. Inside the loop, we use two nested while loops to skip over any non-alphanumeric characters. 
The first inner loop increments i until it points to an alphanumeric character or until i is no longer less than j. 
The second inner loop decrements j until it points to an alphanumeric character or until i is no longer less than j.

5. After skipping non-alphanumeric characters, we check if i is still less than j. 
If it is, we convert the characters at positions i and j to lowercase and compare them.
If they are not equal, we return false since the string cannot be a palindrome.

6. If the characters are equal, we increment i and decrement j to move towards the center of the string.

7. If we exit the while loop without finding any mismatched characters, we return true, indicating that the string is a palindrome.


Dry Run:
Input: s = "A man, a plan, a canal: Panama"
- Initialize i = 0, j = 29 (length of s - 1).
- Loop 1: 
  - i points to 'A' (alphanumeric), j points to 'a' (alphanumeric).
  - Convert both to lowercase: 'a' and 'a'. They match, so increment i to 1 and decrement j to 28.
- Loop 2:
  - i points to ' ' (non-alphanumeric), so increment i to 2.
  - j points to 'a' (alphanumeric), so decrement j to 27.
  - Convert both to lowercase: 'a' and 'a'. They match, so increment i to 3 and decrement j to 26.
- Loop 3:
  - i points to 'm' (alphanumeric), j points to 'm' (alphanumeric).
  - Convert both to lowercase: 'm' and 'm'. They match, so increment i to 4 and decrement j to 25.
- Loop 4:
  - i points to 'a' (alphanumeric), j points to 'a' (alphanumeric).
  - Convert both to lowercase: 'a' and 'a'. They match, so increment i to 5 and decrement j to 24.
- Loop 5:
  - i points to 'n' (alphanumeric), j points to 'n' (alphanumeric).
  - Convert both to lowercase: 'n' and 'n'. They match, so increment i to 6 and decrement j to 23.
- Loop 6:
  - i points to ',' (non-alphanumeric), so increment i to 7.
  - j points to 'a' (alphanumeric), so decrement j to 22.
  - Convert both to lowercase: 'a' and 'a'. They match, so increment i to 8 and decrement j to 21.
- Loop 7:
  - i points to ' ' (non-alphanumeric), so increment i to 9.
  - j points to 'P' (alphanumeric), so decrement j to 20.
  - Convert both to lowercase: 'p' and 'p'. They match, so increment i to 10 and decrement j to 19.
- Loop 8:
  - i points to 'a' (alphanumeric), j points to 'a' (alphanumeric).
  - Convert both to lowercase: 'a' and 'a'. They match, so increment i to 11 and decrement j to 18.
- Loop 9:
    - i points to ' ' (non-alphanumeric), so increment i to 12.
    - j points to 'n' (alphanumeric), so decrement j to 17.
    - Convert both to lowercase: 'n' and 'n'. They match, so increment i to 13 and decrement j to 16.
- Loop 10:
    - i points to 'p' (alphanumeric), j points to 'a' (alphanumeric).
    - Convert both to lowercase: 'p' and 'a'. They do not match, so we return false.

Time Complexity: O(n), where n is the length of the string, since we may need to traverse the entire string once.

Space Complexity: O(1), since we are using only a constant amount of extra space for the pointers and temporary variables.


*/


// Code:
class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null) return true;
        int i=0,j=s.length()-1;
        while(i < j){
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if(i < j){
                char a = Character.toLowerCase(s.charAt(i));
                char b = Character.toLowerCase(s.charAt(j));
                if(a != b) return false;
                i++;
                j--;
            }
        }
        return true;
    }
}
