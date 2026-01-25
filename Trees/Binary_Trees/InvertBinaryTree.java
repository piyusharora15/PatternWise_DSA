// Problem Link: https://leetcode.com/problems/invert-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Recursive DFS.
// We can invert a binary tree by recursively swapping the left and right children of each node.We start from the root and traverse down to the leaves, swapping the children at each node.When we reach a leaf node, we simply return back up the recursion stack, having inverted all the nodes along the way.We return the root of the inverted tree at the end.

// Code:
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(h), where h is the height of the tree. This space is used by the recursion stack. In the worst case (for a skewed tree), the height can be n, leading to O(n) space complexity. In a balanced tree, the height is log(n), leading to O(log n) space complexity.

// Approach 2: Iterative BFS.
// We can also invert a binary tree using an iterative approach with a queue for breadth-first traversal. We start by adding the root node to the queue.Then, while the queue is not empty, we dequeue a node, swap its left and right children, and enqueue its non-null children.We continue this process until all nodes have been processed, resulting in an inverted binary tree.

// Code:
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        return root;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(w), where w is the maximum width of the tree. In the worst case, the width can be n/2 for a complete binary tree, leading to O(n) space complexity.