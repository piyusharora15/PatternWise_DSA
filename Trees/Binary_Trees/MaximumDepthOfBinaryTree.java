// Problem Link: https://leetcode.com/problems/maximum-depth-of-binary-tree?envType=problem-list-v2&envId=tree

/*

Companies: Amazon, Microsoft, Google, Facebook, Apple

Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

*/

/*

Approach 1: Recursive Depth-First Search (DFS).

We can find the maximum depth of a binary tree using a recursive depth-first search approach. 
The idea is to compute the maximum depth of the left and right subtrees and return the maximum of the two depths plus one (to account for the current node).
We can handle the base case where the node is null by returning 0, which indicates that we have reached a leaf node's child.

Code:

class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return 1 + maxDepth(root.right);
        if (root.right == null)
            return 1 + maxDepth(root.left);
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(H), where H is the height of the tree. 
This space is used by the recursion stack. 
In the worst case (for a skewed tree), the height can be N, leading to O(N) space complexity. 
In a balanced tree, the height is log(N), leading to O(log N) space complexity.


Approach 2: Iterative Breadth-First Search (BFS).

We can also find the maximum depth of a binary tree using an iterative approach with a queue. 

We perform a level-order traversal of the tree, counting the number of levels we traverse. 
The number of levels corresponds to the maximum depth of the tree.

Code:

import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
        }

        return depth;
    }
}


Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(W), where W is the maximum width of the tree. In the worst case, the queue can hold all nodes at the deepest level, leading to O(N) space complexity in a balanced tree.

*/
