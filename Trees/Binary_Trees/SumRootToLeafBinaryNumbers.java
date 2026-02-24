// Problem Link: https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers?envType=daily-question&envId=2026-02-24

// Approach: DFS
// We can use a helper function to perform a depth-first search (DFS) on the binary tree. As we traverse the tree, we can keep track of the current path's value by shifting the previous value to the left and adding the current node's value. When we reach a leaf node, we can add the current path's value to our total sum.

// Code:
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }
        
        // Update the current sum by shifting left and adding the current node's value
        currentSum = (currentSum << 1) | node.val; // This is equivalent to currentSum * 2 + node.val
        // currentSum << 1 shifts the bits of currentSum to the left by 1 (equivalent to multiplying by 2)
        // currentSum | node.val adds the current node's value to the current sum
        
        // If it's a leaf node, return the current sum
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // Continue DFS on left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once.
// Space Complexity: O(h), where h is the height of the binary tree. In the worst case (skewed tree), the height can be equal to the number of nodes, leading to O(n) space complexity. In a balanced tree, the height would be O(log n).