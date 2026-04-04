// Problem Link: https://leetcode.com/problems/check-if-the-sentence-is-pangram?envType=problem-list-v2&envId=string

// Approach 1: Using Alphabet Loop.
// For each letter, 'a' to 'z', check if it is present in the string.
// If any letter is missing, return false. Otherwise, return true.

/*  Code:
class Solution {
    public boolean checkIfPangram(String sentence) {
        for(char ch='a';ch<='z';ch++){
            boolean found = false;
            for(char c : sentence.toCharArray()){
                if(c == ch){
                    found = true;
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }
}
*/


// Time Complexity: O(N*26) ~ O(N), where N is the length of the sentence.
// Space Complexity: O(1), as we are using a constant amount of extra space.

// Approach 2: Using a Boolean Array.
// Create a boolean array of size 26 to track the presence of each letter.
// Iterate through the string and mark the corresponding index in the array as true.

/*  Code:
class PangramString {
    public boolean checkIfPangram(String sentence) {
        boolean[] seen = new boolean[26];
        for(char c : sentence.toCharArray()){
            seen[c - 'a'] = true;
        }
        for(boolean flag : seen){
            if(!flag) return false;
        }
        return true;
    }
}
*/

// Time Complexity: O(N), where N is the length of the sentence.
// Space Complexity: O(1), as the size of the boolean array is constant (26).

// Approach 3: Using a HashSet.
// Use a HashSet to store unique letters from the string. Check if the size of the set is 26 at the end.

// Code:
import java.util.HashSet;
import java.util.Set;
class PangramString {
    public boolean checkIfPangram(String sentence) {
        Set<Character> st = new HashSet<>();
        for(char c : sentence.toCharArray()){
            st.add(c);
        }
        return st.size() == 26;
    }
}


// Time Complexity: O(N), where N is the length of the sentence.
// Space Complexity: O(1), as the maximum size of the HashSet is 26.