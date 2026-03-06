// Problem Statement: 
/*

A data analytics system continuously records the number of transactions processed by a server every minute. Due to fluctuating traffic, the operations team wants to monitor the minimum load observed over every consecutive block of K minutes to detect potential performance bottlenecks. You are given an integer array A of size N , where A[i] represents the number of transactions processed in the ith minute. A fixed integer K represents the size of the observation window. For every continuous window (subarray) of size K, determine the minimum number of transactions recorded within that window. 
Test Cases: 
"8 4 2 12 3 5 1 6 7 3" 
Output: 2 2 3 1 1 1

*/

// Approach: Min Heap.
// We can use a min heap to keep track of the minimum number of transactions in the current window of size K. 
// As we slide the window across the array, we will add new elements to the heap and remove elements that are out of the current window. 
// The top of the min heap will always give us the minimum number of transactions for the current window.

// Code:

import java.util.*;

public class NumberOfTransactions
{
    static class Pair
    {
        int value;
        int index;

        Pair(int value, int index)
        {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Read entire input as string
        String input = sc.nextLine();

        String[] parts = input.split(" ");

        int n = Integer.parseInt(parts[0]);

        int[] arr = new int[n];

        for(int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(parts[i + 1]);
        }

        int k = Integer.parseInt(parts[n + 1]);

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> a.value - b.value
        );

        for(int i = 0; i < n; i++)
        {
            pq.add(new Pair(arr[i], i));

            // Remove elements outside window
            while(!pq.isEmpty() && pq.peek().index <= i - k)
            {
                pq.poll();
            }

            if(i >= k - 1)
            {
                System.out.print(pq.peek().value + " ");
            }
        }

        sc.close();
    }
}

// Time Complexity: O(N log K) due to the operations on the min heap.
// Space Complexity: O(K) for the min heap storing at most K elements.