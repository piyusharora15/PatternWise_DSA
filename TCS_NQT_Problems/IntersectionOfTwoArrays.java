/*

Two intelligence agencies maintain distinct encrypted datasets containing unique numerical codes. 
You must determine how many distinct codes are common between both datasets. 
Return only the count of unique intersecting elements. 

Example 1: a = [89,24,75,11,23], b = [89,2,4] 
Output: 1 

Example 2: Input: a = [1,2,3,4,5,6], b = [3,4,5,6,7] 
Output: 4 

Example 3: Input: a = [10,20,30], b = [40,50,60] 
Output: 0 

Example 4: Input: a = [5,10,15,20], b = [5,10,15,20] 
Output: 4 

Example 5: a = [1,3,5,7,9], b = [2,4,6,8,10] 
Output: 0

*/

// Approach: 

// Insert all elements of array a into a HashSet.
// Traverse array b.
// If an element of b exists in the set → it is a common code.
// Increment the count.
// Because a set stores unique values, we automatically avoid duplicates.


// Code:
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public static int countIntersection(int[] a, int[] b) {
        Set<Integer> st = new HashSet<>();
        for(int num : a) {
            st.add(num);
        }
        int count = 0;
        for(int num : b) {
            if(st.contains(num)) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputA = sc.nextLine();
        inputA = inputA.replace("[", "").replace("]", "");
        String[] partsA = inputA.split(",");
        int[] a = new int[partsA.length];
        for(int i=0;i<partsA.length;i++) {
            a[i] = Integer.parseInt(partsA[i].trim());
        }
        String inputB = sc.nextLine();
        inputB = inputB.replace("[", "").replace("]", "");
        String[] partsB = inputB.split(",");
        int[] b = new int[partsB.length];
        for(int i=0;i<partsB.length;i++) {
            b[i] = Integer.parseInt(partsB[i].trim());
        }
        int result = countIntersection(a, b);
        System.out.println(result);
        sc.close();
    }
}

// Time Complexity: O(n + m) where n and m are the lengths of arrays a and b respectively.
// Space Complexity: O(n) for the HashSet storing elements of array a.