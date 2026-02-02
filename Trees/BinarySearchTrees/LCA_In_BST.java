// Problem Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Recursive DFS.
// We can take advantage of the properties of a Binary Search Tree (BST) to find the Lowest Common Ancestor (LCA).We start from the root and compare the values of the current node with the values of p and q.If both p and q are smaller than the current node, we move to the left subtree.If both are larger, we move to the right subtree.If one is on the left and the other is on the right, or if one of them is equal to the current node, then the current node is the LCA.

// Code:
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }
}

// Time Complexity: O(h), where h is the height of the tree. In the worst case, we may have to traverse from the root to a leaf node.
// Space Complexity: O(h) for the recursion stack, where h is the height of the tree.

// Approach 2: Iterative DFS.
// Similar to the recursive approach, we can use an iterative method to find the LCA.We use a while loop to traverse the tree until we find the LCA.

// Code:
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while(curr != null){
            if(p.val < curr.val && q.val < curr.val){
                curr = curr.left;
            }
            else if(p.val > curr.val && q.val > curr.val){
                curr = curr.right;
            }
            else{
                return curr;
            }
        }
        return null;
    }
}

// Time Complexity: O(h), where h is the height of the tree. In the worst case, we may have to traverse from the root to a leaf node.
// Space Complexity: O(1) since we are using an iterative approach and not using any additional space for recursion stack.