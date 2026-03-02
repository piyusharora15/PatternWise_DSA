// Problem Statement: In the intergalactic archive of Planet Xyphor, two ancient data crystals a[] and b[] store encrypted energy signatures. Due to cosmic duplication anomalies, many signatures may repeat within and across the crystals. The Galactic Council must compute the total number of distinct energy signatures present when both crystals are merged into a unified repository. If a signature appears multiple times, it must be counted only once. Your task is to determine the number of unique energy signatures after combining both arrays.

// Example: 1. Input: a[] = [1,2,3,4,5], b[] = [1,2,3]
// Output: 5
// Example 2: Input: a[] = [85,25,1,32,54,6], b[] = [85,2]
// Output: 7
// Example 3: Input: a[] = [1,2,1,1,2], b[] = [2,2,1,2,1]
// Output: 2
// Code:

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class UniqueEnergySignatures {

    public int calculateUniqueSignatures(int[] a, int[] b) {
        Set<Integer> st = new HashSet<>();
        for (int i : a) {
            st.add(i);
        }
        for (int i : b) {
            st.add(i);
        }
        return st.size();
    }

    public static void main(String[] args) {
        UniqueEnergySignatures ues = new UniqueEnergySignatures();
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String[] parts1 = s1.split(",");
        int[] a = new int[parts1.length];
        for(int i=0;i<parts1.length;i++){
            a[i] = Integer.parseInt(parts1[i]);
        }
        String s2 = sc.nextLine();
        String[] parts2 = s2.split(",");
        int[] b = new int[parts2.length];
        for(int i=0;i<parts2.length;i++){
            b[i] = Integer.parseInt(parts2[i]);
        }
        System.out.println(ues.calculateUniqueSignatures(a, b));
        sc.close();
    }
}

// Time Complexity: O(n + m) where n is the length of array a and m is the length of array b, due to the need to iterate through both arrays to add elements to the HashSet.
// Space Complexity: O(n + m) in the worst case, if all elements in both arrays are unique, the HashSet will store all of them.