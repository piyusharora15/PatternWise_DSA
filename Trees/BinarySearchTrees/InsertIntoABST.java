// Problem Link: https://leetcode.com/problems/insert-into-a-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: We can use a recursive approach to insert a new value into a binary search tree (BST). The properties of a BST dictate that for any given node, all values in the left subtree are smaller, and all values in the right subtree are larger.We start at the root of the tree and compare the value to be inserted with the current node's value. If the value is smaller, we move to the left child; if it's larger, we move to the right child. We continue this process until we find an appropriate null position where we can insert the new value as a new node.We also need to handle the case where the tree is empty (i.e., the root is null), in which case we simply create a new node with the given value and return it as the new root of the tree.

// Code:
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }
}

// Time Complexity: O(h), where h is the height of the tree. In the worst case (when the tree is skewed), this can be O(n), where n is the number of nodes in the tree.
// Space Complexity: O(h) due to the recursive call stack. In the worst case, this can also be O(n) for a skewed tree.