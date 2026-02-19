// Problem Link: https://leetcode.com/problems/binary-tree-level-order-traversal?envType=study-plan-v2&envId=top-interview-150

// Approach: BFS.
// We use a queue to perform a level order traversal of the binary tree. For each level, we process all nodes at that level and add their values to a list. We then add the list of values for that level to our result list.We continue this process until we have processed all levels of the tree.

// Code:
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(n), in the worst case, the queue can hold all nodes at the last level of the tree, which can be O(n) in a complete binary tree. Additionally, we are storing the result list which can also take O(n) space in the worst case.