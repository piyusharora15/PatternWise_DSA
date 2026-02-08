// Problem Link: https://leetcode.com/problems/find-largest-value-in-each-tree-row?envType=problem-list-v2&envId=wo7dda2m

// Approach: Level Order Traversal.
// We will use a queue to perform a level order traversal of the binary tree. For each level, we will keep track of the maximum value found in that level and add it to our result list.We will continue this process until we have traversed all levels of the tree.

// Code:
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int maxVal = Integer.MIN_VALUE;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                maxVal = Math.max(maxVal,node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            result.add(maxVal);
        }
        return result;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(m), where m is the maximum number of nodes at any level in the binary tree. In the worst case, this can be O(n) if the tree is completely unbalanced (like a linked list). However, in a balanced tree, this would be O(log n).