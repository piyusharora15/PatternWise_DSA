/*

Problem Link: https://leetcode.com/problems/rectangle-overlap?envType=daily-question&envId=2026-09-14

An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the coordinate of its top-right corner. 
Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.

Two rectangles overlap if the area of their intersection is positive. 
To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.

Example 1:
Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true

Example 2:
Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false

Example 3:
Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
Output: false

Approach: Using Geometry.

1. First, we need to check if the two rectangles are not overlapping. 
Two rectangles do not overlap if one is completely to the left, right, above, or below the other.

2. If the rectangles do not satisfy any of the non-overlapping conditions, then they must overlap.

3. The conditions for non-overlapping are:
   - rec1 is to the left of rec2: rec1[2] <= rec2[0]
   - rec1 is to the right of rec2: rec1[0] >= rec2[2]
   - rec1 is above rec2: rec1[1] >= rec2[3]
   - rec1 is below rec2: rec1[3] <= rec2[1]

4. If none of these conditions are true, then the rectangles overlap.

Dry Run:
Let's take an example to dry run the code.

Example: rec1 = [0,0,2,2], rec2 = [1,1,3,3]

- rec1[2] = 2, rec2[0] = 1. Since 2 > 1, the first condition is false.
- rec1[0] = 0, rec2[2] = 3. Since 0 < 3, the second condition is false.
- rec1[1] = 0, rec2[3] = 3. Since 0 < 3, the third condition is false.
- rec1[3] = 2, rec2[1] = 1. Since 2 > 1, the fourth condition is false.

Since none of the conditions are true, the rectangles overlap.


Time Complexity: O(1) - We are performing a constant number of comparisons.

Space Complexity: O(1) - We are using a constant amount of space.

*/

// Code:

class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rec1 is to the left of rec2
        if (rec1[2] <= rec2[0]) {
            return false;
        }
        // Check if rec1 is to the right of rec2
        if (rec1[0] >= rec2[2]) {
            return false;
        }
        // Check if rec1 is above rec2
        if (rec1[1] >= rec2[3]) {
            return false;
        }
        // Check if rec1 is below rec2
        if (rec1[3] <= rec2[1]) {
            return false;
        }
        return true;
    }
}