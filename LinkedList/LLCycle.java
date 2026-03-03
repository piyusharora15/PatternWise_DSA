// Problem Link: https://leetcode.com/problems/linked-list-cycle

/*

Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Example:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

 */

 /*  Brute Force Approach: 

We can use a HashMap to store the visited nodes. 
If we encounter a node that is already in the HashMap, then there is a cycle in the linked list. 
The time complexity of this approach is O(n) and the space complexity is O(n).

Code:

import java.util.HashMap;
public class LLCycle {
    public boolean hasCycle(ListNode head) {
        ListNode temp = head;
        HashMap<ListNode,Integer> nodeMap = new HashMap<>();
        while(temp != null){
            if(nodeMap.containsKey(temp)){
                return true;
            }
            nodeMap.put(temp,1);
            temp = temp.next;
        }
        return false;
    }
}

 */
 /*

Optimal Approach: Fast and Slow Pointers (Floyd’s Tortoise and Hare Algorithm): 
This is a common approach to detect a cycle in a linked list. The idea is to use two pointers, one slow and one fast. 
The slow pointer moves one step at a time while the fast pointer moves two steps at a time. 
If there is a cycle in the linked list, then the fast pointer will eventually meet the slow pointer. 
The time complexity of this approach is O(n) and the space complexity is O(1).

 */
// Code:
public class LLCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)