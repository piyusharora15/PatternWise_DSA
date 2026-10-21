/*

Problem Link: https://leetcode.com/problems/sliding-window-maximum

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]

Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:
Input: nums = [1], k = 1
Output: [1]

Why isn't a normal sliding window enough?

A straightforward solution examines all k elements in every window and finds the maximum.

For n elements, there are n - k + 1 windows. Scanning k elements per window takes O(nk) time.

The challenge is that when the window moves, its maximum might leave the window. 
We need a way to find the next maximum without scanning all the elements again.

The trick: Keep only the elements that could still become the maximum of the current or a future window.

That's where a monotonic decreasing deque comes in.

Optimal approach — Monotonic Deque.

First, what is a deque?

A deque (double-ended queue) lets us add or remove elements from both ends.

In Java, we can use:

Deque<Integer> dq = new ArrayDeque<>();

For this problem, the deque stores indices, not the actual array values.

Why indices? Because we need to know when an element has moved outside the current window.

The core rule:

Maintain indices in decreasing order of their corresponding values:

nums[dq[0]] >= nums[dq[1]] >= nums[dq[2]]

That means:

- Front: index of the maximum element in the current window.
- Back: index of the smallest remaining candidate.
- When a new element arrives, remove smaller or equal values from the back because they can no longer be useful.
- Remove expired indices from the front because they are outside the current window.

For example, suppose the deque represents:

Indices:  [1, 2, 3]
Values:   [ 5, 2, 1]

Now a new value 4 arrives.

The value 1 can never be the maximum while 4 remains in the window. The same is true for 2. So we remove both from the back and add 4:

Before: [5, 2, 1]
Add 4:  [5]
        [5, 4]

The maximum remains at the front, and we've discarded unnecessary candidates.

Dry Run:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3

Iteration 1: i = 0, value = 1
Current window is not full yet.
The deque is empty. No indices expire, and nothing needs to be removed from the back.

Add index 0.
Deque indices: [0]
Deque values:  [1]

Since i < k - 1, don't record a result yet.

Iteration 2: i = 1, value = 3
The deque contains index 0, whose value is 1.
Compare the back value with the current value:
nums[0] = 1 <= 3
Remove index 0, then add index 1.
Deque indices: [1]
Deque values:  [3]

Why remove 1? Because the newer value 3 is larger and will remain in the window at least as long as the older value 1.
No result yet: the window has only two elements.

Iteration 3: i = 2, value = -1
The current window is: [1, 3, -1]
No index has expired. Since 3 > -1, nothing is removed from the back.

Add index 2.
Deque indices: [1, 2]
Deque values:  [3, -1]

The first complete window is ready because i >= k - 1.

The front value is 3, so:
result[0] = 3

Iteration 4: i = 3, value = -3
Current window: [3, -1, -3]

The window's left boundary is:
i-k+1=3-3+1=1

Index 1 is still inside the window, so nothing expires.

Since -1 > -3, we keep index 2 and add index 3.
Deque indices: [1, 2, 3]
Deque values:  [3, -1, -3]

The maximum is at the front: 3.
result[1] = 3

Iteration 5: i = 4, value = 5
The window becomes: [-1, -3, 5]

The left boundary is: 4-3+1=2

Index 1 is now outside the window, so remove it from the front.
Deque: [2, 3]
Values: [-1, -3]

Now process the new value 5:
-3 <= 5 → remove index 3.
-1 <= 5 → remove index 2.
- Add index 4.
Deque indices: [4]
Deque values:  [5]

The maximum is 5.
result[2] = 5

Iteration 6: i = 5, value = 3
Current window: [-3, 5, 3]

The left boundary is 3. The front index 4 is still valid.
Since 5 > 3, keep index 4 and add index 5.
Deque indices: [4, 5]
Deque values:  [5, 3]

The maximum is 5.
result[3] = 5

Iteration 7: i = 6, value = 6
Current window: [5, 3, 6]

The left boundary is 4. Index 4 is still valid.

Now compare from the back:
3 <= 6 → remove index 5.
5 <= 6 → remove index 4.

Add index 6.
Deque indices: [6]
Deque values:  [6]

The maximum is 6.
result[4] = 6

Iteration 8: i = 7, value = 7
Current window: [3, 6, 7]

The left boundary is 5. Index 6 is still inside the window.

Compare the back value: 6 <= 7

Remove index 6, then add index 7.
Deque indices: [7]
Deque values:  [7]

The maximum is 7.
result[5] = 7

Output: [3, 3, 5, 5, 6, 7]

Complexity analysis:

Let n be the array length.

- Time complexity: O(n). 
Every index enters the deque exactly once and can be removed at most once. 
Although there are while loops, the total number of removals across the entire algorithm is linear.

- Auxiliary space: O(k). 
The deque stores at most k indices. The output array takes another O(n-k+1) space, but that is the required return value.

*/

// Code:

import java.util.ArrayDeque;
import java.util.Deque;

class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            // 1. Remove indices outside the current window.
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }

            // 2. Remove smaller or equal values from the back.
            while (!dq.isEmpty()
                    && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // 3. Add the current index.
            dq.offerLast(i);

            // 4. Record maximum once the first full window exists.
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
}