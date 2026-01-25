// Problem Link: https://leetcode.com/problems/diameter-of-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: The diameter of a binary tree is defined as the length of the longest path between any two nodes in the tree. This path may or may not pass through the root. To find the diameter, we can use a depth-first search (DFS) approach to calculate the height of each subtree and update the maximum diameter found during the traversal.

// Code:
class Solution {
    private int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return diameter;
    }
    private int helper(TreeNode node){
        if(node == null) return 0;
        int leftHeight = helper(node.left);
        int rightHeight = helper(node.right);
        diameter = Math.max(diameter,leftHeight+rightHeight);
        return 1 + Math.max(leftHeight,rightHeight);
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(H), where H is the height of the binary tree. This space is used by the recursion stack.