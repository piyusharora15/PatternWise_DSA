// Problem Link: https://leetcode.com/problems/count-good-nodes-in-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: Recursive DFS:
// We can use a recursive depth-first search (DFS) approach to traverse the binary tree. We will keep track of the maximum value encountered along the path from the root to the current node. If the current node's value is greater than or equal to this maximum value, it is considered a "good" node. We will then update the maximum value and continue the traversal for the left and right child nodes.

// Code:
class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root,Integer.MIN_VALUE);
    }
    private int dfs(TreeNode node,int max){
        if(node == null) return 0;
        int count = 0;
        if(node.val >= max){
            count = 1;
            max = node.val;
        }
        count += dfs(node.left,max);
        count += dfs(node.right,max);
        return count;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(h), where h is the height of the binary tree. In the worst case (skewed tree), the space complexity can be O(n). In the best case (balanced tree), it can be O(log n).