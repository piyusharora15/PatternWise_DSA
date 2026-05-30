// Problem Link : https://leetcode.com/problems/same-tree

/*

Companies: Amazon, Microsoft, Google, Facebook.

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false


Approach 1 : Recursive DFS.

First we check if both nodes are null, if they are then we return true. 

If one of them is null or their values are not equal, then we return false. 
Otherwise, we recursively check the left and right subtrees.

Once we have checked all the nodes, if we haven't returned false, then we return true.

Code:

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}

Time Complexity : O(N) where N is the number of nodes in the tree.

Space Complexity : O(H) where H is the height of the tree.


Approach 2 : Iterative BFS.

First we initialize two queues, one for each tree. We add the root nodes of both trees to their respective queues.

Then we enter a loop that continues until both queues are empty. In each iteration, we poll a node from each queue and compare them.

If both nodes are null, we continue to the next iteration. 
If one of them is null or their values are not equal, we return false.
If they are equal, we add their left and right children to their respective queues.

We continue this process until both queues are empty. If we haven't returned false by the end of the loop, then we return true.

Code:

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1 == null && node2 == null) continue;
            if(node1 == null || node2 == null || node1.val != node2.val) return false;
            queue1.add(node1.left);
            queue1.add(node1.right);
            queue2.add(node2.left);
            queue2.add(node2.right);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

Time Complexity : O(N) where N is the number of nodes in the tree.

Space Complexity : O(W) where W is the maximum width of the tree.

*/