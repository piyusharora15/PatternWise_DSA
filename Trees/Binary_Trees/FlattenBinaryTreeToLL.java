// Problem Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Recursive Approach.
// We can use a recursive approach to flatten the binary tree. We will first flatten the left subtree and then the right subtree. After flattening the left subtree, we will attach it to the right of the current node and then attach the flattened right subtree to the end of the new right subtree. We will also set the left child of the current node to null.We will repeat this process for all the nodes in the tree.

// Code:
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        // Flatten the left subtree
        flatten(root.left);
        
        // Flatten the right subtree
        flatten(root.right);
        
        // Store the left and right subtrees
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        // Set the left child to null
        root.left = null;
        
        // Attach the flattened left subtree to the right of the current node
        root.right = left;
        
        // Find the end of the new right subtree and attach the flattened right subtree
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = right;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once to flatten the tree.
// Space Complexity: O(h), where h is the height of the binary tree. In the worst case, the height of the tree can be O(n) (when the tree is skewed), and in the best case, it can be O(log n) (when the tree is balanced). The space complexity is due to the recursive call stack.

// Approach 2: Iterative Approach using a Stack.
// We can also use an iterative approach to flatten the binary tree using a stack. We will start with the root node and push it onto the stack. We will then pop nodes from the stack and process them. For each popped node, we will push its right child (if it exists) onto the stack first, followed by its left child (if it exists). We will also set the left child of the current node to null and attach the next node in the stack to the right of the current node. We will repeat this process until the stack is empty.

// Code:
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            
            // Push the right child first so that it is processed after the left child
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            
            // Set the left child to null and attach the next node in the stack to the right
            current.left = null;
            if (!stack.isEmpty()) {
                current.right = stack.peek();
            }
        }
    }
}

// Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once to flatten the tree.
// Space Complexity: O(n), where n is the number of nodes in the binary tree. In the worst case, when the tree is skewed, we may have to store all the nodes in the stack. In the best case, when the tree is balanced, the space complexity can be O(log n).