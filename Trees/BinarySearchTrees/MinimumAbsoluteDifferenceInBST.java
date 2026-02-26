// Problem Link: https://leetcode.com/problems/minimum-absolute-difference-in-bst?envType=study-plan-v2&envId=top-interview-150

// Approach 1: Inorder Traversal.
// We know that the inorder traversal of a BST gives us the nodes in sorted order. So, we can perform an inorder traversal and keep track of the minimum absolute difference between the current node and the previous node.We can use a variable to store the previous node's value and update the minimum difference whenever we encounter a new node.We can initialize the minimum difference to a large value and update it whenever we find a smaller difference. Finally, we return the minimum difference.

// Code:
class Solution {
    int minDiff = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
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

// Time Complexity: O(N), where N is the number of nodes in the BST. We visit each node once during the inorder traversal.
// Space Complexity: O(H), where H is the height of the BST. In the worst case, the height can be O(N) for a skewed tree, and in the best case, it can be O(log N) for a balanced tree.

// Approach 2: BFS Traversal.
// We can also use a breadth-first search (BFS) traversal to find the minimum absolute difference in a BST. We can use a queue to perform the level order traversal of the tree. For each node, we can compare its value with the values of its left and right children (if they exist) and update the minimum difference accordingly. We can continue this process until we have traversed all the nodes in the tree.

// Code:
class Solution {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int minDiff = Integer.MAX_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Check left child
            if (current.left != null) {
                minDiff = Math.min(minDiff, Math.abs(current.val - current.left.val));
                queue.offer(current.left);
            }

            // Check right child
            if (current.right != null) {
                minDiff = Math.min(minDiff, Math.abs(current.val - current.right.val));
                queue.offer(current.right);
            }
        }

        return minDiff;
    }
}

// Time Complexity: O(N), where N is the number of nodes in the BST. We visit each node once during the BFS traversal.
// Space Complexity: O(W), where W is the maximum width of the tree. In the worst case, the width can be O(N) for a complete binary tree, and in the best case, it can be O(1) for a skewed tree.