// Problem Link: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: We can use the fast and slow pointer technique to find the middle element of the linked list, which will be the root of the BST. The left half of the linked list will form the left subtree, and the right half will form the right subtree. We can recursively apply this process to construct the entire BST.We will also need to disconnect the left half from the middle node to avoid cycles in the linked list.We will define a helper function that takes the head of the linked list and returns the root of the BST.We will find the middle node using the fast and slow pointer technique, create a new TreeNode with the value of the middle node, and then recursively call the helper function for the left and right halves of the linked list to construct the left and right subtrees.

// Code:
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        return convertToBST(head);
    }
    
    private TreeNode convertToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        // Find the middle node using the fast and slow pointer technique
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // The middle node is the root of the BST
        TreeNode root = new TreeNode(slow.val);
        
        // Disconnect the left half from the middle node
        if (prev != null) {
            prev.next = null;
        }
        
        // Recursively construct the left and right subtrees
        root.left = convertToBST(head);
        root.right = convertToBST(slow.next);
        
        return root;
    }
}

// Time Complexity: O(n log n) - We are finding the middle node for each recursive call, which takes O(n) time, and we are making log n recursive calls due to the division of the linked list.
// Space Complexity: O(log n) - The space complexity is O(log n) due to the recursive call stack, which will be at most log n levels deep for a balanced BST.