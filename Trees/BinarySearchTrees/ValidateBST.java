// Problem Link: https://leetcode.com/problems/validate-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: Recursive Inorder Traversal.
// We perform an inorder traversal of the tree while keeping track of the previously visited node.In a valid BST, the value of the current node should always be greater than the value of the previously visited node. If we find any node that violates this condition, we can conclude that the tree is not a valid BST.

// Code:
class Solution {
    private TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }
    private boolean inorder(TreeNode node){
        if(node == null) return true;
        if(!inorder(node.left)) return false;
        if(prev != null && node.val <= prev.val) return false;
        prev = node;
        return inorder(node.right);
    }
}

// Time Complexity: O(N), where N is the number of nodes in the tree. In the worst case, we visit each node exactly once.
// Space Complexity: O(H), where H is the height of the tree. This space is used by the recursion stack during the inorder traversal.