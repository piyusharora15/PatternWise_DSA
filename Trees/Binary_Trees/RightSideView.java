// Problem Link: https://leetcode.com/problems/binary-tree-right-side-view?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: BFS: Level Order Traversal.
// We can use a queue to perform a level order traversal of the binary tree. At each level, we can add the last node's value to the result list, which represents the rightmost node at that level.We continue this process until we have traversed all levels of the tree.

// Code:
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                
                // If it's the last node in the current level, add its value to the result
                if (i == size - 1) {
                    result.add(currentNode.val);
                }
                
                // Add left and right children to the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        
        return result;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree, since we visit each node once.
// Space Complexity: O(w), where w is the maximum width of the binary tree, which is the maximum number of nodes at any level in the tree. In the worst case, this can be O(n) if the tree is completely balanced.

// Approach 2: DFS: Preorder Traversal (Right-First).
// We can perform a depth-first search (DFS) on the binary tree, prioritizing the right child before the left child. We can keep track of the current depth of the traversal and add the first node we encounter at each depth to the result list, which will represent the rightmost node at that depth.We continue this process until we have traversed all nodes in the tree.

// Code:
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        // If this is the first node we encounter at this depth, add it to the result
        if (depth == result.size()) {
            result.add(node.val);
        }
        
        // Traverse the right child first
        dfs(node.right, depth + 1, result);
        // Then traverse the left child
        dfs(node.left, depth + 1, result);
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree, since we visit each node once.
// Space Complexity: O(h), where h is the height of the binary tree, which is the maximum depth of the recursion stack. In the worst case, this can be O(n) if the tree is completely unbalanced (e.g., a linked list).