// Problem Link: https://leetcode.com/problems/search-in-a-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: We can take advantage of the properties of a binary search tree (BST) to efficiently search for a value. In a BST, for any given node, all values in the left subtree are less than the node's value, and all values in the right subtree are greater than the node's value. Therefore, we can recursively or iteratively traverse the tree, moving left or right based on comparisons with the target value.

// Code:
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val){
            return root;
        } else if(root.val > val){
            return searchBST(root.left,val);
        } else {
            return searchBST(root.right,val);
        }
    }
}

// Time Complexity: O(h), where h is the height of the tree. In the worst case, this can be O(n) for a skewed tree, but for a balanced BST, it is O(log n).
// Space Complexity: O(h) due to the recursive call stack. In the worst case, this can be O(n) for a skewed tree, but for a balanced BST, it is O(log n).