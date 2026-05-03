// Problem Link: https://leetcode.com/problems/minimum-depth-of-binary-tree?envType=problem-list-v2&envId=tree

/*

Companies: Amazon, Microsoft, Adobe, Oracle, Morgan Stanley, Walmart, Paytm, OYO, Zomato, Swiggy, PhonePe, Google, Facebook.

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

*/


/* 

Approach 1: Recursive DFS.

We traverse the tree recursively and keep track of the depth. 
When we reach a leaf node, we return the depth. 
If a node has only one child, we continue the search on that child. 
Finally, we return the minimum depth found.

Code:

class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return 1 + minDepth(root.right);
        if (root.right == null)
            return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}

Time Complexity: O(N), where N is the number of nodes in the tree. In the worst case, we may have to visit all nodes.

Space Complexity: O(H), where H is the height of the tree. This space is used by the recursion stack.


Approach 2: Iterative BFS.

We use a queue to perform a level-order traversal of the tree. 
We keep track of the depth as we traverse each level. 
When we encounter the first leaf node, we return the current depth.


Code:

class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i=0;i<levelSize;i++){
                TreeNode node = q.poll();
                if(node.left == null && node.right == null) return depth;
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            depth++;
        }
        return depth;
    }
}

Time Complexity: O(N), where N is the number of nodes in the tree. In the worst case, we may have to visit all nodes.

Space Complexity: O(W), where W is the maximum width of the tree. This space is used by the queue.

*/