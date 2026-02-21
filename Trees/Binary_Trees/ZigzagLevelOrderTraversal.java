// Problem Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal?envType=problem-list-v2&envId=wo7dda2m

// Approach: Level Order Traversal.
// We can use a boolean variable to keep track of the direction of traversal. If the variable is true, we will add the nodes to the current level list from left to right, otherwise we will add the nodes from right to left. After processing each level, we will toggle the boolean variable.We can use a queue to perform the level order traversal. We will add the root node to the queue and then process each level until the queue is empty.We will also need to keep track of the number of nodes at the current level, so we can process all nodes at that level before moving on to the next level.We will create a list of lists to store the result, where each inner list represents a level of the tree. We will add the current level list to the result list after processing each level.

// Code:
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(currentNode.val);
                } else {
                    currentLevel.add(0, currentNode.val); // Add to the front for right to left
                }
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight; // Toggle the direction
        }
        
        return result;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(n), where n is the number of nodes in the tree. In the worst case, we may have to store all nodes in the queue at once (e.g., if the tree is a complete binary tree). Additionally, we are storing the result in a list of lists, which also takes O(n) space.