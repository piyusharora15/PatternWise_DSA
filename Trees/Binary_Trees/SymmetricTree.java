// Problem Link : https://leetcode.com/problems/symmetric-tree


/*

Companies: Amazon, Microsoft, Google, Facebook, Apple.

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Approach 1 : Recursive DFS.

First check if the root is null, if it is then return true as an empty tree is symmetric. 

Then we can define a helper function isMirror that takes two nodes as input and checks if they are mirror images of each other. 

The function should check if both nodes are null, if so return true. 
If one of the nodes is null or their values are different, return false. 

Finally, we can recursively call the function for the left child of the first node and the right child of the second node, and for the right child of the first node and the left child of the second node.

We can call this helper function with the root node as both arguments to check if the tree is symmetric.

Code:

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }
    private boolean isMirror(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null || p.val != q.val) return false;
        return isMirror(p.left,q.right) && isMirror(p.right,q.left);
    }
}

Time Complexity : O(N) where N is the number of nodes in the tree.

Space Complexity : O(H) where H is the height of the tree.

Approach 2 : Iterative BFS.

First check if the root is null, if it is then return true as an empty tree is symmetric. 

Then we can use a queue to perform a level order traversal of the tree. We can enqueue the left and right children of the root node.

Then we can enter a loop that continues until the queue is empty. In each iteration, we can dequeue two nodes from the queue and check if they are mirror images of each other.

If both nodes are null, we can continue to the next iteration. If one of the nodes is null or their values are different, we can return false.

If the nodes are mirror images, we can enqueue the left child of the first node and the right child of the second node, and the right child of the first node and the left child of the second node.

We can continue this process until the queue is empty. If we exit the loop without finding any asymmetry, we can return true.

Code:

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while(!q.isEmpty()){
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if(n1 == null && n2 == null) continue;
            if(n1 == null || n2 == null || n1.val != n2.val) return false;
            q.offer(n1.left);
            q.offer(n2.right);
            q.offer(n1.right);
            q.offer(n2.left);
        }
        return true;
    }
}

Time Complexity : O(N) where N is the number of nodes in the tree.

Space Complexity : O(W) where W is the maximum width of the tree.

*/