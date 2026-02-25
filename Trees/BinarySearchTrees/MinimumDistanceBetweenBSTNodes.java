// Problem Link: https://leetcode.com/problems/minimum-distance-between-bst-nodes

// Approach 1: Inorder Traversal.
// We know that the inorder traversal of a BST gives us the nodes in sorted order. So, we can perform an inorder traversal and keep track of the minimum difference between consecutive nodes.We can use a variable to store the previous node's value and update the minimum difference whenever we encounter a new node.We can initialize the minimum difference to a large value and update it whenever we find a smaller difference.We can return the minimum difference at the end of the traversal.

// Code:
class Solution {
    private int minDiff = Integer.MAX_VALUE;
    private Integer prev = null;

    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        // Traverse the left subtree
        inorder(node.left);

        // Update the minimum difference
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }
        prev = node.val;

        // Traverse the right subtree
        inorder(node.right);
    }
}

// Time Complexity: O(n), where n is the number of nodes in the BST. We visit each node once during the inorder traversal.
// Space Complexity: O(h), where h is the height of the BST. In the worst case, the height can be O(n) for a skewed tree, but in a balanced BST, it would be O(log n).

// Approach 2: BFS Traversal with a Queue.
// We can also use a breadth-first search (BFS) approach to traverse the tree level by level. We can use a queue to keep track of the nodes at each level and calculate the minimum difference between the values of the nodes at each level. We can keep track of the minimum difference found so far and update it whenever we find a smaller difference. This approach is less efficient than the inorder traversal because it does not take advantage of the sorted property of the BST, but it is still a valid solution.

// Code:
class Solution {
    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDiff = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Check the left child
            if (current.left != null) {
                minDiff = Math.min(minDiff, current.val - current.left.val);
                queue.offer(current.left);
            }

            // Check the right child
            if (current.right != null) {
                minDiff = Math.min(minDiff, current.right.val - current.val);
                queue.offer(current.right);
            }
        }

        return minDiff;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the BST. We visit each node once during the BFS traversal.
// Space Complexity: O(w), where w is the maximum width of the tree. In the worst case, the width can be O(n) for a complete binary tree, but in a balanced BST, it would be O(log n).