// Problem Link: https://leetcode.com/problems/count-complete-tree-nodes?envType=problem-list-v2&envId=tree

// Approach 1: Naive traversal using DFS.
// We will traverse the tree using DFS and count the number of nodes. This approach will have a time complexity of O(N), where N is the number of nodes in the tree. The space complexity will be O(H), where H is the height of the tree due to recursion stack.

// Code:
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

// Time Complexity: O(N), where N is the number of nodes in the tree.
// Space Complexity: O(H), where H is the height of the tree due to recursion stack.

// Approach 2: Optimized approach using properties of complete binary tree.
// We will calculate the height of the left and right subtrees. If they are equal, then the left subtree is a perfect binary tree and we can calculate the number of nodes in it using the formula 2^h - 1, where h is the height of the subtree. If they are not equal, then the right subtree is a perfect binary tree and we can calculate the number of nodes in it using the same formula. We will then recursively count the nodes in the other subtree.

// Code:
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if(leftHeight == rightHeight) {
            return (1 << leftHeight) + countNodes(root.right); // 1 << leftHeight is equivalent to 2^leftHeight
        } else {
            return (1 << rightHeight) + countNodes(root.left); // 1 << rightHeight is equivalent to 2^rightHeight
        }
    }

    private int getHeight(TreeNode node) {
        int height = 0;
        while(node != null) {
            height++;
            node = node.left;
        }
        return height;
    }
}

// Time Complexity: O(log^2 N), where N is the number of nodes in the tree. This is because we are calculating the height of the tree which takes O(log N) time and we are doing this for each node in the worst case.
// Space Complexity: O(1), as we are using only a constant amount of extra space.