// Problem Link: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: Postorder Traversal.
// We will calculate the sum of all the nodes in the tree and then we will do a postorder traversal and at each node we will calculate the product of the sum of the left subtree and the sum of the right subtree and update the maximum product.We will return the sum of the current node and the sum of the left and right subtree to the parent node. We will take the maximum product modulo 10^9 + 7.We will return the maximum product.

// Code:
class Solution {
    long maxProduct = 0;
    long totalSum = 0;
    public int maxProduct(TreeNode root) {
        totalSum = sum(root);
        postorder(root);
        return (int)(maxProduct % 1000000007);
    }
    
    private long sum(TreeNode node) {
        if(node == null) return 0;
        return node.val + sum(node.left) + sum(node.right);
    }
    
    private long postorder(TreeNode node) {
        if(node == null) return 0;
        long leftSum = postorder(node.left);
        long rightSum = postorder(node.right);
        maxProduct = Math.max(maxProduct, leftSum * (totalSum - leftSum));
        maxProduct = Math.max(maxProduct, rightSum * (totalSum - rightSum));
        return node.val + leftSum + rightSum;
    }
}

// Time Complexity: O(n) where n is the number of nodes in the tree.
// Space Complexity: O(h) where h is the height of the tree.