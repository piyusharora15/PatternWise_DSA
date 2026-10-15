/*

Problem Link: https://leetcode.com/problems/find-median-from-data-stream?envType=problem-list-v2&envId=auswip1r

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

1. Problem Understanding:

We have a stream of integers. We need to support two operations:
addNum(int num): Adds a number to the data stream.
findMedian(): Returns the median of all numbers seen so far.

What is Median?
For an odd number of elements: [1, 3, 5]
Median = 3

For an even number: [1, 3, 5, 7]
Median = (3 + 5) / 2 = 4.0

So:
Odd: median = middle element
Even: median = average of two middle elements

Approach 1 — Sort Every Time.
This is the most straightforward solution.
Whenever findMedian() is called: Copy all numbers. Sort them. Find the middle element(s).

Example

Suppose: addNum(5), addNum(2), addNum(8)
Internally: [5, 2, 8]
When findMedian() is called: sort → [2, 5, 8]
Median: 5

Code:

class MedianFinder {

    private List<Integer> nums;

    public MedianFinder() {
        nums = new ArrayList<>();
    }

    public void addNum(int num) {
        nums.add(num);
    }

    public double findMedian() {
        Collections.sort(nums);

        int n = nums.size();

        if (n % 2 == 1) {
            return nums.get(n / 2);
        }

        return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;
    }
}

Dry Run:
Operations:
addNum(1), addNum(2), findMedian(), addNum(3), findMedian()

Step 1: addNum(1), nums = [1]
Step 2: addNum(2), nums = [1, 2]
Step 3: findMedian()
Sort: [1, 2]
Two middle elements: 1 and 2
Therefore: median = (1 + 2) / 2 = 1.5
Step 4: addNum(3), nums = [1, 2, 3]
Step 5: findMedian()
Sorted: [1, 2, 3]
Middle: 2
Answer: 2.0

Complexity:
If there are N elements:
addNum(): O(1)
findMedian(): Sorting: O(N log N)
Overall: addNum  → O(1), findMedian → O(N log N)
This is easy but inefficient.


Approach 2 — Maintain a Sorted List.
Instead of sorting everything every time, we can keep the numbers sorted all the time.

For example:
addNum(5): [5]
addNum(2): [2, 5]
addNum(8): [2, 5, 8]
addNum(1): [1, 2, 5, 8]

Then findMedian() becomes extremely fast because the list is already sorted.

The Problem: Java's ArrayList doesn't provide efficient insertion in the middle.

Suppose: [1, 3, 5, 7, 9]
We insert: 4
We need: [1, 3, 4, 5, 7, 9]
Elements must be shifted:
5 → right
7 → right
9 → right

So insertion costs: O(N)

We can use binary search to find the insertion position.

Code:

class MedianFinder {

    private List<Integer> nums;

    public MedianFinder() {
        nums = new ArrayList<>();
    }

    public void addNum(int num) {

        int left = 0;
        int right = nums.size();

        while (left < right) {  // Binary search for insertion position
            int mid = left + (right - left) / 2;

            if (nums.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        nums.add(left, num);
    }

    public double findMedian() {

        int n = nums.size();

        if (n % 2 == 1) {
            return nums.get(n / 2);
        }

        return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;
    }
}

Dry Run:
Input:
5
2
8
1

Add 5: []
Insert 5: [5]
Add 2: Binary search finds position 0. [2, 5]
Add 8: Position is 2. [2, 5, 8]
Add 1: Position is 0.
Existing elements shift:
[2, 5, 8]
 ↓
[1, 2, 5, 8]

Now median:
n = 4
middle elements:
2 and 5
median = (2 + 5) / 2
       = 3.5

Complexity:
Finding insertion position using binary search: O(log N)
But actually inserting into an ArrayList requires shifting elements: O(N)
Therefore:
addNum()     → O(N)
findMedian() → O(1)
This is better if findMedian() is called extremely frequently, but still not optimal because insertion can be linear.

Optimal Approach — Two Heaps.
The key observation is:
We don't need the entire array sorted. We only need access to the middle elements.

Suppose our sorted numbers are: [1, 2, 3, 4, 5, 6]
The median depends only on: 3 and 4
We don't actually care about the relative ordering of: 
1, 2
or:
5, 6

So we divide the numbers into two halves.

Left Half       Right Half

[1, 2, 3]       [4, 5, 6]

We maintain:
Left half
A Max Heap:

       3
      / \
     1   2

Why Max Heap?
Because we need the largest element of the left half.
That element is one of the possible median elements.

Right half:
A Min Heap:

       4
      / \
     5   6

Why Min Heap?
Because we need the smallest element of the right half.
That is the other possible median element.

The Two-Heap Structure

We maintain:

Max Heap                    Min Heap
LEFT                        RIGHT

largest left                smallest right
      ↓                           ↓
     max                       min

For:

[1, 2, 3, 4, 5, 6]

we get:

MaxHeap                  MinHeap

   3                        4
  / \                      / \
 1   2                    5   6

Therefore:

maxHeap.peek() = 3
minHeap.peek() = 4

Median:
(3 + 4) / 2 = 3.5

The Two Rules:
This entire algorithm revolves around two invariants.

Rule 1 — Size difference:
The heaps can differ in size by at most 1.
|maxHeap.size() - minHeap.size()| <= 1

Usually we choose:
maxHeap.size() == minHeap.size()
or:
maxHeap.size() == minHeap.size() + 1

So we keep the left heap either equal to or one larger than the right heap.

Rule 2 — Ordering:
Every number in the left half must be:
<= every number in the right half

We don't explicitly compare every number.
Instead:
maxHeap.peek() <= minHeap.peek() is sufficient.
Because:
maxHeap.peek() is the largest value in the left half.

And:
minHeap.peek() is the smallest value in the right half.

Complete Dry Run:
Let's use:

1 2 3 4 5
Add 1:
Initially:
maxHeap = []
minHeap = []

Add 1:
maxHeap = [1]
minHeap = []

Sizes: 1 vs 0, Valid.
Median:
Since: maxHeap.size() > minHeap.size()
median: maxHeap.peek() = 1

Add 2:
Initially:
maxHeap = [1]
minHeap = []
Add 2 to max heap:
maxHeap = [2, 1]

Now:
maxHeap.peek() = 2
minHeap = empty

No ordering problem because right heap is empty.

But sizes:
maxHeap = 2
minHeap = 0
Difference is 2.
We need to move one element. 
2 moves from max heap to min heap.

Result:
maxHeap = [1]
minHeap = [2]

Now:
left = [1]
right = [2]

Median:
(1 + 2) / 2
= 1.5

Add 3:
Current:
maxHeap = [1]
minHeap = [2]
Add 3 to max heap:
maxHeap = [3, 1]

Now:
maxHeap.peek() = 3
minHeap.peek() = 2

This violates our ordering: 3 > 2
We need to fix it.
Move 3 from max heap to min heap:
maxHeap = [1]
minHeap = [2, 3]
But now sizes are:
maxHeap = 1
minHeap = 2
Right is bigger.
Move smallest element of right to left: 2

So:
maxHeap = [2, 1]
minHeap = [3]

Conceptually:
LEFT          RIGHT

1, 2          3
Median: 2

Add 4:
Current:
maxHeap = [2, 1]
minHeap = [3]

Add 4 to max heap:
maxHeap = [4, 1, 2]

Ordering:
maxHeap.peek() = 4
minHeap.peek() = 3

Violation: 4 > 3
Swap boundary elements.

Move 4:
maxHeap = [2, 1]
minHeap = [3, 4]

Sizes: 2 vs 2
Perfect.

Conceptually:
LEFT          RIGHT
1, 2          3, 4

Median: (2 + 3) / 2 = 2.5

Add 5:
Current:
maxHeap = [2, 1]
minHeap = [3, 4]
Add 5 to max heap:
maxHeap = [5, 1, 2]

Ordering: 5 > 3
Fix it:
Move 5 to min heap:
maxHeap = [2, 1]
minHeap = [3, 4, 5]
Sizes: 2 vs 3
Right is too large.
Move smallest right element: 3
to max heap.

Final:
maxHeap = [3, 1, 2]
minHeap = [4, 5]

Conceptually:
LEFT              RIGHT
1, 2, 3           4, 5

Median: 3

Complexity of Two Heaps:
There are at most N elements across both heaps.
addNum()
Each insertion into a PriorityQueue costs: O(log N)
We perform a constant number of heap operations.
Therefore:
addNum() = O(log N)

findMedian()
We only call:
maxHeap.peek()
minHeap.peek()
Heap peek is: O(1)

Therefore:
findMedian() = O(1)

Space Complexity:
We store every number: O(N)

Code:

class MedianFinder {

    private PriorityQueue<Integer> maxHeap; // Left half
    private PriorityQueue<Integer> minHeap; // Right half

    public MedianFinder() {

        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        maxHeap.offer(num); // Step 1: Add to left heap

        if (!minHeap.isEmpty() &&
            maxHeap.peek() > minHeap.peek()) {  // Step 2: Make sure every element in left is <= every element in right

            minHeap.offer(maxHeap.poll());
            maxHeap.offer(minHeap.poll());
        }

        if (maxHeap.size() > minHeap.size() + 1) {  // Step 3: Balance sizes
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}

*/
