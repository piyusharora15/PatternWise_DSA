// Problem Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: Recursive DFS.
// We traverse the tree in a depth-first manner. If we find either of the target nodes (p or q), we return that node up the call stack.If both left and right subtrees return non-null values, it means that p and q are found in different subtrees, and thus the current node is their LCA. If only one subtree returns a non-null value, we propagate that value up the call stack.

// Code:
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. In the worst case, we might have to visit all nodes.
// Space Complexity: O(H), where H is the height of the binary tree. This space is used by the recursion stack. In the worst case (for a skewed tree), the height can be N, leading to O(N) space complexity. In a balanced tree, the height would be log(N).