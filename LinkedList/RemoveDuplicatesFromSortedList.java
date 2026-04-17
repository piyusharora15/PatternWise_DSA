// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list

/*

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Input: head = [1,1,2]
Output: [1,2]

Input: head = [1,1,2,3,3]
Output: [1,2,3]

*/

// Approach:

// We take a pointer to the head of the linked list and we keep on checking if the current node's value is equal to the next node's value. 
// If it is, then we skip the next node by changing the current node's next pointer to point to the next of next node. 
// If it is not, then we move the current pointer to the next node. We repeat this process until we reach the end of the linked list.

// Code:

class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; // Skip the duplicate node
            } else {
                current = current.next; // Move to the next node
            }
        }
        return head; // Return the modified linked list
    }
}

// Time Complexity: O(n), where n is the number of nodes in the linked list.
// Space Complexity: O(1), as we are modifying the linked list in place and not using any extra space.