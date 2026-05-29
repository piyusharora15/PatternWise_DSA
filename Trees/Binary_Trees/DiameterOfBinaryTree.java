// Problem Link: https://leetcode.com/problems/diameter-of-binary-tree?envType=problem-list-v2&envId=wo7dda2m

/*

Companies: Amazon, Microsoft, Google.

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1


Approach: Using Depth First Search (DFS).

The diameter of a binary tree is defined as the length of the longest path between any two nodes in the tree. 
This path may or may not pass through the root. 
To find the diameter, we can use a depth-first search (DFS) approach to calculate the height of each subtree and update the maximum diameter found during the traversal.
We can define a helper function that calculates the height of a subtree and updates the diameter at each node.
We start with the base case where if the node is null, we return a height of 0.
For each node, we recursively calculate the height of the left and right subtrees.
The diameter at the current node can be calculated as the sum of the heights of the left and right subtrees.
We update the global diameter variable if the current diameter is greater than the previously recorded diameter.

Code:

class DiameterOfBinaryTree {
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

Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(H), where H is the height of the binary tree. This space is used by the recursion stack.


*/