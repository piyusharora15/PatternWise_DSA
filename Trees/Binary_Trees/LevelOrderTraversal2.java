// Problem Link: https://leetcode.com/problems/binary-tree-level-order-traversal-ii?envType=problem-list-v2&envId=wo7dda2m

// Approach: We can use the same approach as level order traversal but instead of adding the nodes to the result list in the order they are visited, we can add them in reverse order. We can use a queue to keep track of the nodes at each level and a stack to reverse the order of the nodes at each level.

// Code:

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            stack.push(level);
        }
        
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
}

// Time Complexity: O(n) where n is the number of nodes in the tree.
// Space Complexity: O(n) where n is the number of nodes in the tree.