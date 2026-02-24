// Problem Link: https://leetcode.com/problems/count-nodes-with-the-highest-score?envType=problem-list-v2&envId=wo7dda2m

// Approach: We build the tree from the given parent array. We can use a post-order traversal to calculate the score of each node. The score of a node is the product of the number of nodes in its left subtree, the number of nodes in its right subtree, and the number of nodes in the rest of the tree (which is the total number of nodes minus the number of nodes in the left and right subtrees). We can keep track of the maximum score and the count of nodes that have that score. We can use a HashMap to store the count of nodes for each score. We can return the count of nodes that have the maximum score at the end.

// Code:
class Solution {
    long maxScore = 0;
    int count = 0;
    List<Integer>[] children;
    int n;
     public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        // build tree
        children = new ArrayList[n];
        for(int i = 0; i < n; i++)
            children[i] = new ArrayList<>();

        for(int i = 1; i < n; i++)
            children[parents[i]].add(i);
        dfs(0);
        return count;
    }
     private int dfs(int node) {
        long score = 1;
        int subtreeSize = 1;
        // process children
        for(int child : children[node]) {
            int childSize = dfs(child);
            subtreeSize += childSize;
            score *= childSize;
        }
        // remaining part (parent side)
        int remaining = n - subtreeSize;
        if(remaining > 0)
            score *= remaining;
            // update answer
        if(score > maxScore) {
            maxScore = score;
            count = 1;
        } else if(score == maxScore) {
            count++;
        }
        return subtreeSize;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree.
// Space Complexity: O(n), where n is the number of nodes in the tree.